package ceneax.app.astatine

import androidx.appcompat.app.AppCompatActivity
import ceneax.app.lib.astatine.core.AtView

class TestActivity : AppCompatActivity(), AtView<TestControl> {
    override val control: TestControl
        get() = TODO("Not yet implemented")

    override fun invalidate() {
    }
}