package ceneax.app.astatine

import androidx.fragment.app.commit
import ceneax.app.astatine.base.BaseActivity
import ceneax.app.astatine.databinding.ActivityMainBinding
import ceneax.app.astatine.page.home.HomeFragment
import ceneax.app.lib.astatine.core.*

class MainActivity : BaseActivity<ActivityMainBinding>(), AtView<MainControl> {
    override val control by atControl()

    override fun initView() {
        setSupportActionBar(binding.toolbar)

        supportFragmentManager.commit {
            add(binding.fmContainer.id, HomeFragment())
        }
    }

    override fun At.invalidate() = build {
    }
}