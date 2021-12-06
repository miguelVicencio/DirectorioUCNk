package cl.ucn.disc.dsm.mvicencio.thedirectorio;

import android.app.Application;
import android.content.Context;
import org.acra.ACRA;
import org.acra.annotation.AcraCore;


/**
 * main App.
 * @author Miguel Vicencio-Gomez
 */
//@AcraCore(buildConfigClass = BuildConfig.class)
public class app extends Application {

  /**
   * Set the base context for this ContextWrapper.  All calls will then be delegated to the base
   * context.  Throws IllegalStateException if a base context has already been set.
   *
   * @param base The new base context for this wrapper.
   */
  @Override
  protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);

    CoreConfigurationBuilder builder = new CoreConfigurationBuilder(this);
    //core configuration:
    builder
        .withBuildConfigClass(BuildConfig.class)
        .withReportFormat(StringFormat.JSON)
        .withEnabled(true);
    //each plugin you chose above can be configured with its builder like this:
    builder.getPluginConfigurationBuilder(DialogConfigurationBuilder.class)
        .withResText(R.string.acra_dialog_title)
        .withResCommentPrompt(R.string.acra_dialog_comment)
        .withEnables(true);
    ACRA.init(this, builder);

  }
}
