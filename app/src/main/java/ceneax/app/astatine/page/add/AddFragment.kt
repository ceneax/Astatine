package ceneax.app.astatine.page.add

import androidx.core.widget.addTextChangedListener
import ceneax.app.astatine.base.BaseFragment
import ceneax.app.astatine.databinding.FragmentAddBinding
import ceneax.app.lib.astatine.core.AtView
import ceneax.app.lib.astatine.core.atControl

class AddFragment : BaseFragment<FragmentAddBinding>(), AtView<AddControl> {
    override val control by atControl()
    private var mFlag = false

    override fun bindEvent() {
        binding.etWrite.addTextChangedListener {
            if (!mFlag) return@addTextChangedListener
            it?.toString()?.let { s ->
                control.editChange(s)
            }
        }
    }

    override fun invalidate() {
        mFlag = false
        binding.etWrite.setText(control.state.content)
        mFlag = true
    }
}