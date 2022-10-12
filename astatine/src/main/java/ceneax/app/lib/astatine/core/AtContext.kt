package ceneax.app.lib.astatine.core

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelStoreOwner
import kotlinx.coroutines.CoroutineScope

sealed interface AtContext {
    val activity: FragmentActivity
    val fragmentManager: FragmentManager
    val coroutineScope: CoroutineScope

    data class Activity(
        override val activity: FragmentActivity,
        override val fragmentManager: FragmentManager,
        override val coroutineScope: CoroutineScope,
    ) : AtContext

    data class Fragment(
        override val activity: FragmentActivity,
        override val fragmentManager: FragmentManager,
        override val coroutineScope: CoroutineScope,
        val fragment: androidx.fragment.app.Fragment
    ) : AtContext
}