package ceneax.app.astatine.page.home

import ceneax.app.lib.astatine.core.AtContext
import ceneax.app.lib.astatine.core.AtControl
import ceneax.app.lib.astatine.core.AtState

data class HomeState(
    val list: List<String> = emptyList()
) : AtState

class HomeControl(context: AtContext) : AtControl<HomeState>(context) {
}