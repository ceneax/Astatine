package ceneax.app.astatine

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ceneax.app.astatine.databinding.ActivityTestBinding
import ceneax.app.lib.astatine.core.AtView
import ceneax.app.lib.astatine.core.atControl

class TestActivity : AppCompatActivity(), AtView<TestControl> {
    override val control by atControl()

    private val binding by lazy { ActivityTestBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btTest.setOnClickListener {
            control.test()
        }
    }

    override fun invalidate() {
        Log.i("Log_Astatine", control.state.toString())
    }
}