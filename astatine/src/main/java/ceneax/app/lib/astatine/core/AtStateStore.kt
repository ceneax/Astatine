package ceneax.app.lib.astatine.core

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal interface IAtStateStore<S : AtState> {
    val stateFlow: StateFlow<S>
    val state: S get() = stateFlow.value

    fun setState(newState: S.() -> S)
}

internal class AtStateStore<S : AtState>(
    override val stateFlow: MutableStateFlow<S>
) : IAtStateStore<S> {
    override fun setState(newState: S.() -> S) {
        stateFlow.tryEmit(newState(state))
    }
}