package ceneax.app.lib.astatine.core

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope

sealed interface AtContext {
    val activity: FragmentActivity
    val fragmentManager: FragmentManager
    val coroutineScope: CoroutineScope

    data class Activity(
        override val activity: FragmentActivity,
        override val fragmentManager: FragmentManager
    ) : AtContext {
        override val coroutineScope get() = activity.lifecycleScope
    }

    data class Fragment(
        override val activity: FragmentActivity,
        override val fragmentManager: FragmentManager,
        val fragment: androidx.fragment.app.Fragment
    ) : AtContext {
        override val coroutineScope get() = fragment.lifecycleScope
    }
}