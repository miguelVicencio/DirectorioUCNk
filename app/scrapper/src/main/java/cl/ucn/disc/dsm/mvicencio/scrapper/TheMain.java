package cl.ucn.disc.dsm.mvicencio.scrapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;


import lombok.extern.slf4j.Slf4j;


/**
 * Main class to scrappe the Directorio telefonico of UCN.
 *
 * @author Miguel Vicencio
 **/

@Slf4j
public final class TheMain {

  /**
   * the gson parser
   */
  private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

  /**
   * the starting point.
   *
   * @param args to use.
   */
  public static void main(String[] args) throws IOException, InterruptedException {
    //load the file
    String data = FileUtils.readFileToString(new File("funcionarios.json"),
        StandardCharsets.UTF_8);

    //define the type
    Type type = new TypeToken<List<Funcionario>>() {

    }.getType();

    // the list of funcionario (string -> list of funcionarios)
    List<Funcionario> funcionarios = GSON.fromJson(data, type);

    //the last funcionario loaded
    int start = funcionarios.get(funcionarios.size() - 1).getId();
    int stop = 30000;
    log.debug("starting the screapping");

    for (int id = start; id <= stop; id++) {

      //wait
      Thread.sleep(150);

      // connect and get the Document
      Document doc = Jsoup
          .connect("https://admision01.ucn.cl/directoriotelefonicoemail/fichaGenerica/?cod=" + id)
          .get();

      String nombre = doc.getElementById("lblNombre").text();
      String cargo = doc.getElementById("lblCargo").text();
      String unidad = doc.getElementById("lblUnidad").text();
      String email = doc.getElementById("lblEmail").text();
      String telefono = doc.getElementById("lblTelefono").text();
      String oficina = doc.getElementById("lblOficina").text();
      String direccion = doc.getElementById("lblDireccion").text();

      if (nombre.length() <= 1) {
        log.debug("no hay datos para la id{}.", id);
        continue;
      }
      log.debug("hay datos para la id{}.", id);
      //call constructor
      Funcionario f = Funcionario.builder()
          .id(id)
          .nombre(nombre)
          .cargo(cargo)
          .unidad(unidad)
          .email(email)
          .telefono(telefono)
          .oficina(oficina)
          .direccion(direccion)
          .build();

      //insert in the array list of funcionarios
      funcionarios.add(f);
      // save by add multiply of 5
      if (funcionarios.size() % 5 == 0) {
        FileUtils.writeStringToFile(
            new File("funcionarios.json"),
            GSON.toJson(funcionarios),
            StandardCharsets.UTF_8);
      }
    }
    //write the list of funcionario in json format

    log.debug("Done.");

  }

}
