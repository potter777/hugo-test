package cadenas.rene.hugotest

import android.util.Log

/**
 * @author René Cadenas
 */

fun debug(message: String) {
    if (BuildConfig.DEBUG) {
        Log.d(BuildConfig.APPLICATION_ID, message)
    }
}