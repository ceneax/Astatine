package ceneax.app.lib.astatine.core

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelStoreOwner
import kotlinx.coroutines.CoroutineScope

sealed interface AtContext {
    val activity: FragmentActivity
    val fragmentManager: FragmentManager
    val coroutineScope: CoroutineScope
    val viewModelStoreOwner: ViewModelStoreOwner
}

data class AtActivityContext(
    override val activity: FragmentActivity,
    override val fragmentManager: FragmentManager,
    override val coroutineScope: CoroutineScope,
    override val viewModelStoreOwner: ViewModelStoreOwner
) : AtContext

data class AtFragmentContext(
    override val activity: FragmentActivity,
    override val fragmentManager: FragmentManager,
    override val coroutineScope: CoroutineScope,
    override val viewModelStoreOwner: ViewModelStoreOwner,
    val fragment: Fragment
) : AtContext