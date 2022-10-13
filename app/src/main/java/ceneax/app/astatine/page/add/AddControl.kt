package ceneax.app.astatine.page.add

import ceneax.app.lib.astatine.core.AtContext
import ceneax.app.lib.astatine.core.AtControl
import ceneax.app.lib.astatine.core.AtState

data class AddState(
    val content: String = ""
) : AtState

class AddControl(context: AtContext) : AtControl<AddState>(context) {
    fun editChange(s: String) {
        setState { copy(content = s) }
    }
}