package ceneax.app.lib.astatine

import android.app.Application
import android.content.Context
import androidx.startup.Initializer

class AtInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        Astatine.init(context as Application)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}