package ceneax.app.lib.astatine.core

import ceneax.app.lib.astatine.newGenericsInstance

abstract class AtControl<S : AtState> @JvmOverloads constructor(
    private val context: AtContext,
    private val stateScope: AtStateScope = AtStateScope.EXCLUSIVE
) {
    @PublishedApi
    @Suppress("UNCHECKED_CAST")
    internal val stateStore by lazy(LazyThreadSafetyMode.NONE) { createStateStore(
        context,
        this::class.newGenericsInstance(0),
        stateScope
    ) as AtStateStore<S> }

    val state: S get() = stateStore.state

    protected inline fun setState(crossinline newState: S.() -> S) = stateStore.setState {
        newState()
    }
}