package cl.ucn.disc.dsm.mvicencio.scrapper;

import lombok.Builder;
import lombok.Getter;

/**
 * The UCN Funcionario.
 *
 * @author Miguel Vicencio
 */
@Builder
public final class Funcionario {

  /**
   * the id
   */
  @Getter
  private final Integer id;
  /**
   * the nombre
   */
  @Getter
  private final String nombre;
  /**
   * the cargo
   */
  @Getter
  private final String cargo;
  /**
   * the unidad
   */
  @Getter
  private final String unidad;
  /**
   * the email
   */
  @Getter
  private final String email;
  /**
   * the telefono
   */
  @Getter
  private final String telefono;
  /**
   * the oficina
   */
  @Getter
  private final String oficina;
  /**
   * the Direccion
   */
  @Getter
  private final String direccion;

}
