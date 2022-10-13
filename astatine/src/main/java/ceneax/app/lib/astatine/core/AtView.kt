package ceneax.app.lib.astatine.core

import androidx.lifecycle.*
import ceneax.app.lib.astatine.AtLog

interface AtView<C : AtControl<out AtState>> : LifecycleOwner, ViewModelStoreOwner {
    val control: C

    fun invalidate()
}

@PublishedApi
internal fun <C : AtControl<out AtState>, V : AtView<C>> V.withAtInit(control: Lazy<C>): Lazy<C> {
    lifecycleScope.launchWhenStarted {
        listOf(
            ParamAtInitSlots(),
            LaunchAtInitSlots()
        ).forEach {
            it.execute(this@withAtInit, control.value)
        }
    }.invokeOnCompletion {
        AtLog.d("${control.value::class.java.simpleName} -> onDispose()")
        control.value.onDispose()
    }

    return control
}