package ceneax.app.lib.astatine.core

import ceneax.app.lib.astatine.newGenericsInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class AtControl<S : AtState> @JvmOverloads constructor(
    private val context: AtContext,
    private val stateScope: AtStateScope = AtStateScope.EXCLUSIVE
) : AtLifecycle {
    internal val internalContext get() = context

    @PublishedApi
    @Suppress("UNCHECKED_CAST")
    internal val stateStore = createStateStore(
        context,
        this::class.newGenericsInstance(0),
        stateScope
    ) as AtStateStore<S>

    val state: S get() = stateStore.state

    protected inline fun setState(crossinline newState: S.() -> S) = stateStore.setState {
        newState()
    }
}

fun AtControl<out AtState>.launch(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job = internalContext.coroutineScope.launch(context, start, block)