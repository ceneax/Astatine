package ceneax.app.lib.astatine.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.reflect.KClass

private const val ViewModelProviderDefaultKey = "ceneax.app.lib.astatine.ViewModelProvider.DefaultKey"

internal interface IAtStateStoreFactory : ViewModelProvider.Factory {
    val stateFlow: MutableStateFlow<out AtState>
}

internal class DefaultAtStateStoreFactory(
    override val stateFlow: MutableStateFlow<out AtState>
) : IAtStateStoreFactory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MutableStateFlow::class.java).newInstance(stateFlow)
    }
}

private fun <S : AtState> getViewModelKey(stateClass: KClass<S>): String =
    "$ViewModelProviderDefaultKey:${stateClass.java.canonicalName}:${AtStateStore::class.java.canonicalName}"

internal fun <S : AtState> createStateStore(
    context: AtContext,
    initialState: S,
    stateScope: AtStateScope
): AtStateStore<out AtState> = ViewModelProvider(
    when (context) {
        is AtContext.Activity -> context.activity.viewModelStore
        is AtContext.Fragment -> if (stateScope == AtStateScope.EXCLUSIVE) {
            context.fragment.viewModelStore
        } else {
            context.activity.viewModelStore
        }
    },
    DefaultAtStateStoreFactory(MutableStateFlow(initialState))
)[getViewModelKey(initialState::class), AtStateStore::class.java]