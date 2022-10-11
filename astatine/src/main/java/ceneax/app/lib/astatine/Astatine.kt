package ceneax.app.lib.astatine

import android.app.Application

object Astatine {
    private lateinit var _application: Application
    internal val application get() = _application

    internal fun init(app: Application) {
        AtLog.d("Astatine init")
        _application = app
    }
}