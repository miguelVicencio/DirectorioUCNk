package cl.ucn.disc.dsm.mvicencio.thedirectorio;
import android.app.Application;
import android.content.Context;

import org.acra.ACRA;
import org.acra.annotation.AcraCore;

/**
 * main App.
 * @author Miguel Vicencio-Gomez
 */
@AcraCore(buildConfigClass = BuildConfig.class)
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

    ACRA.init(this);
  }
}
