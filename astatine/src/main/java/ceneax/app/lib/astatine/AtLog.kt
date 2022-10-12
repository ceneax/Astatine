package ceneax.app.lib.astatine

import android.util.Log

@PublishedApi
internal object AtLog {
    private const val TAG = "Log_Astatine"
    private const val PREFIX = "[ Astatine ]"
    private const val ARROW = " => "

    fun d(msg: String) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, format("Debug", msg))
        }
    }

    fun i(msg: String) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, format("Info", msg))
        }
    }

    fun w(msg: String) {
        if (BuildConfig.DEBUG) {
            Log.w(TAG, format("Warn", msg))
        }
    }

    fun e(msg: String) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, format("Error", msg))
        }
    }

    private fun format(type: String, msg: String) = "${PREFIX}[ $type ]$ARROW$msg"
}