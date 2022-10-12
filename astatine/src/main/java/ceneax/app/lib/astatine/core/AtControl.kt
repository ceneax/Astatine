package ceneax.app.lib.astatine.core

import ceneax.app.lib.astatine.newGenericsInstance
import kotlinx.coroutines.flow.MutableStateFlow

abstract class AtControl<S : AtState>(private val stateScope: AtStateScope = AtStateScope.OWNER) {
    private val stateStore = AtStateStore(MutableStateFlow(this::class.newGenericsInstance(0)))
}