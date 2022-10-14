package ceneax.app.lib.astatine.core

import android.util.ArrayMap
import ceneax.app.lib.astatine.AtLog
import kotlin.reflect.KProperty0

interface At {
    operator fun KProperty0<*>.invoke(block: () -> Unit)
}

internal class AtInvalidator : At {
    private val mRebuildHolder = ArrayMap<String, Pair<KProperty0<*>, () -> Unit>>()

    override fun KProperty0<*>.invoke(block: () -> Unit) {
        AtLog.i("${this.name} ---- value: ${this.get()}")
        mRebuildHolder[name] = Pair(this, block)
    }

    fun rebuild(state: AtState) = state::class.java.declaredFields.forEach { field ->
        val holder = mRebuildHolder[field.name]?.also {
            field.isAccessible = true
        }
        if (holder == null || field.get(state) == holder.first.get()) {
            return@forEach
        }

//        AtLog.i("rebuild ${field.name} ---- value: ${holder.first.get()}")
//        AtLog.i("rebuild ${field.name} ---- value: ${field.get(state)}")

        holder.second()
    }
}

inline fun <V : AtView<out AtControl<S>>, S : AtState> V.build(builder: S.() -> Unit) {
    control.state.builder()
}