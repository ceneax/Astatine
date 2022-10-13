package ceneax.app.lib.astatine.core

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import ceneax.app.lib.astatine.AtLog
import ceneax.app.lib.astatine.Param
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

internal interface AtInitSlots {
    fun <V : AtView<C>, C : AtControl<out AtState>> execute(view: V, control: C)
}

internal class LaunchAtInitSlots : AtInitSlots {
    override fun <V : AtView<C>, C : AtControl<out AtState>> execute(view: V, control: C) {
        AtLog.d("${control::class.java.simpleName} -> onInit()")
        control.onInit()

        control.stateStore.stateFlow.onEach {
            view.invalidate()
        }.launchIn(view.lifecycleScope)
    }
}

internal class ParamAtInitSlots : AtInitSlots {
    override fun <V : AtView<C>, C : AtControl<out AtState>> execute(view: V, control: C) {
        val bundle = when (view) {
            is FragmentActivity -> view.intent?.extras
            is Fragment -> view.arguments
            else -> null
        }

        bundle?.keySet()?.forEach {
            val filed = control.state::class.java.getDeclaredField(it)
            if (!filed.isAnnotationPresent(Param::class.java)) {
                return@forEach
            }

            filed.isAccessible = true
            filed.set(control.state, bundle[it])
        }
    }
}