package ceneax.app.astatine.page.add

import android.util.Log
import androidx.core.widget.addTextChangedListener
import ceneax.app.astatine.base.BaseFragment
import ceneax.app.astatine.databinding.FragmentAddBinding
import ceneax.app.lib.astatine.core.At
import ceneax.app.lib.astatine.core.AtView
import ceneax.app.lib.astatine.core.atControl
import ceneax.app.lib.astatine.core.build
import ceneax.app.lib.astatine.setTextIfDifferent

class AddFragment : BaseFragment<FragmentAddBinding>(), AtView<AddControl> {
    override val control by atControl()

    override fun bindEvent() {
        binding.etWrite.addTextChangedListener {
            it?.toString()?.let { s ->
                control.editChange(s)
                Log.e("Log_", "TextChangedListener: $s")
            }
        }

        binding.btOk.setOnClickListener {
            control.editChange(System.currentTimeMillis().toString())
        }
    }

    override fun At.invalidate() = build {
        ::content { binding.etWrite.setTextIfDifferent(content) }
        ::title {  }
    }
}