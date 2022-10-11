package ceneax.app.lib.astatine.core

import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelStoreOwner

interface AtView<C : AtControl<out AtState>> : LifecycleOwner, ViewModelStoreOwner,
    HasDefaultViewModelProviderFactory {
    val control: C

    fun invalidate()
}