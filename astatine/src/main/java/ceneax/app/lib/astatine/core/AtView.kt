package ceneax.app.lib.astatine.core

import androidx.lifecycle.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

interface AtView<C : AtControl<out AtState>> : LifecycleOwner, ViewModelStoreOwner,
    HasDefaultViewModelProviderFactory {
    val control: C

    fun invalidate()
}

@PublishedApi
internal fun <C : AtControl<out AtState>, V : AtView<C>> V.withAtInit(control: C): C {
    lifecycleScope.launchWhenCreated {
        control.stateStore.stateFlow.onEach {
            invalidate()
        }.collect()
    }
    return control
}