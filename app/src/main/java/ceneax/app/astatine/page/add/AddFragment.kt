package ceneax.app.astatine.page.add

import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import ceneax.app.astatine.base.BaseFragment
import ceneax.app.astatine.databinding.FragmentAddBinding
import ceneax.app.lib.astatine.core.*
import ceneax.app.lib.astatine.setTextIfDifferent

class AddFragment : BaseFragment<FragmentAddBinding>(), AtView<AddControl> {
    override val control by atControl()

    override fun bindEvent() {
        binding.etWrite.addTextChangedListener {
            it?.toString()?.let { s ->
                control.editChange(s)
            }
        }

        binding.btOk.setOnClickListener {
            parentFragmentManager.popBackStack()
            parentFragmentManager.setFragmentResult("123", bundleOf(
                "data" to control.state.content
            ))
        }

        binding.btTest.setOnClickListener {
            control.setTitle()
        }
    }

    override fun At.build() = builder {
        ::content { binding.etWrite.setTextIfDifferent(content) }
        ::title { }
    }
}