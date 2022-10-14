package ceneax.app.astatine

import ceneax.app.lib.astatine.core.AtContext
import ceneax.app.lib.astatine.core.AtControl
import ceneax.app.lib.astatine.core.AtState

data class MainState(
    val title: String = "test main title"
) : AtState

class MainControl(context: AtContext) : AtControl<MainState>(context) {
}