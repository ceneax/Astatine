package ceneax.app.astatine.page.add

import android.util.Log
import androidx.core.widget.addTextChangedListener
import ceneax.app.astatine.base.BaseFragment
import ceneax.app.astatine.databinding.FragmentAddBinding
import ceneax.app.lib.astatine.core.At
import ceneax.app.lib.astatine.core.AtView
import ceneax.app.lib.astatine.core.atControl
import ceneax.app.lib.astatine.core.build

class AddFragment : BaseFragment<FragmentAddBinding>(), AtView<AddControl> {
    override val control by atControl()
    private var mFlag = false

    override fun bindEvent() {
        binding.etWrite.addTextChangedListener {
//            if (!mFlag) return@addTextChangedListener
            it?.toString()?.let { s ->
                control.editChange(s)
            }
        }
    }

    override fun At.invalidate() = build {
        ::content { Log.e("Log_", "AddFragment invalidate content value: $content") }
    }
}