package ceneax.app.astatine

import ceneax.app.lib.astatine.core.AtContext
import ceneax.app.lib.astatine.core.AtControl
import ceneax.app.lib.astatine.core.AtState

data class TestState(
    val title: Int = 0
) : AtState

class TestControl(context: AtContext.Activity) : AtControl<TestState>(context) {
    fun test() {
        setState { copy(title = title + 1) }
    }
}