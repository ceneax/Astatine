package ceneax.app.astatine.page.home

import androidx.fragment.app.commit
import ceneax.app.astatine.R
import ceneax.app.astatine.base.BaseFragment
import ceneax.app.astatine.databinding.FragmentHomeBinding
import ceneax.app.astatine.databinding.ItemHomeBinding
import ceneax.app.astatine.page.add.AddFragment
import ceneax.app.lib.astatine.adapter.AtAdapter
import ceneax.app.lib.astatine.adapter.atAdapter
import ceneax.app.lib.astatine.adapter.bind
import ceneax.app.lib.astatine.core.*

class HomeFragment : BaseFragment<FragmentHomeBinding>(), AtView<HomeControl> {
    override val control by atControl()

    override fun initView() {
        binding.recyclerView.AtAdapter<String, ItemHomeBinding> {
            bind { item ->
                tvTitle.text = item
            }
        }
    }

    override fun bindEvent() {
        binding.fabAdd.setOnClickListener {
            parentFragmentManager.setFragmentResultListener("123", this) { s, data ->
                parentFragmentManager.clearFragmentResult(s)
                parentFragmentManager.clearFragmentResultListener(s)
                control.addTodoItem(data.getString("data", ""))
            }
            parentFragmentManager.commit {
                add(R.id.fmContainer, AddFragment())
                addToBackStack(null)
            }
        }
    }

    override fun At.build() = builder {
        ::list { binding.recyclerView.atAdapter.updateList(list) }
    }
}