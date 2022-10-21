package ceneax.app.lib.astatine.core

import android.util.ArrayMap
import kotlin.reflect.KProperty0

class At internal constructor() {
    @PublishedApi
    internal val mRebuildHolder = ArrayMap<String, KProperty0<*>>()

    inline operator fun <T> KProperty0<T>.invoke(block: (T) -> Unit) {
        val holder = mRebuildHolder[name]
        if (holder == null) {
            mRebuildHolder[name] = this.also { block(get()) }
            return
        }

        val newValue = get()
        if (newValue == holder.get()) {
            return
        }

        mRebuildHolder[name] = this.also { block(newValue) }
    }
}

//class At internal constructor() {
//    internal data class Pair<K, V>(var first: K, val second: V)
//
//    @PublishedApi
//    internal val mRebuildHolder = ArrayMap<String, Pair<KProperty.Getter<Any>, (Any) -> Unit>>()
//
//    operator fun <T : Any> KProperty0<T>.invoke(block: (T) -> Unit) {
//        mRebuildHolder[name] = Pair(this.getter as KProperty.Getter<Any>, block as (Any) -> Unit).also { block(get()) } // this.also { block(get()) }
//    }
//
//    fun rebuild(state: AtState) = state::class.java.declaredFields.forEach { field ->
//        val holder = mRebuildHolder[field.name]?.also {
//            field.isAccessible = true
//        }
//        AtLog.i("================ rebuild1")
//
//        if (holder == null || field.get(state) == if (holder.first.parameters.isEmpty()) holder.first.call() else holder.first.call(state)) {
//            return@forEach
//        }
//
//        AtLog.i("================ rebuild2 ${field.kotlinProperty!!.getter.parameters.size}")
//        holder.first = field.kotlinProperty!!.getter as KProperty.Getter<Any>
//
//        holder.second(field.get(state)!!)
//    }
//}

inline fun <V : AtView<out AtControl<S>>, S : AtState> V.builder(builder: S.() -> Unit) {
    control.state.builder()
}