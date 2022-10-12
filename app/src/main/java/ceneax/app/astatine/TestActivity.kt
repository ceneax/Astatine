package ceneax.app.astatine

import androidx.appcompat.app.AppCompatActivity
import ceneax.app.lib.astatine.core.AtView
import ceneax.app.lib.astatine.core.atControl

class TestActivity : AppCompatActivity(), AtView<TestControl> {
    override val control by atControl()

    override fun invalidate() {
    }
}