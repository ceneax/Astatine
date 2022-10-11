package ceneax.app.astatine

import ceneax.app.lib.astatine.core.AtControl
import ceneax.app.lib.astatine.core.AtState

data class TestState(
    val title: String = ""
) : AtState()

class TestControl : AtControl<TestState>() {
}