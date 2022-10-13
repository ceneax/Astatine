package ceneax.app.lib.astatine.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope

val RecyclerView.atAdapter get() = adapter as AtAdapter

inline fun <reified I : Any, reified VB : ViewBinding> RecyclerView.AtAdapter(
    nestedScrollingEnabled: Boolean = true,
    layoutManager: RecyclerView.LayoutManager? = null,
    builder: ItemProviderBuilder<I, VB>.() -> Unit
) = AtAdapter(nestedScrollingEnabled, layoutManager) {
    add(builder)
}

@JvmName("AtAdapterRecyclerView")
inline fun RecyclerView.AtAdapter(
    nestedScrollingEnabled: Boolean = true,
    layoutManager: RecyclerView.LayoutManager? = null,
    builder: AtAdapterBuilder.() -> Unit
): AtAdapter {
    isNestedScrollingEnabled = nestedScrollingEnabled
    if (this.layoutManager == null && layoutManager == null) {
        this.layoutManager = LinearLayoutManager(context)
    } else if (this.layoutManager == null) {
        this.layoutManager = layoutManager
    }
    adapter = (this.context as LifecycleOwner).AtAdapter(builder)
    return atAdapter
}

@JvmName("AtAdapterSingle")
inline fun <reified I : Any, reified VB : ViewBinding> LifecycleOwner.AtAdapter(
    builder: ItemProviderBuilder<I, VB>.() -> Unit
) = AtAdapter(lifecycleScope, builder)

@JvmName("AtAdapterSingle")
inline fun <reified I : Any, reified VB : ViewBinding> AtAdapter(
    scope: CoroutineScope,
    builder: ItemProviderBuilder<I, VB>.() -> Unit
) = AtAdapter(scope) {
    add(builder)
}

inline fun LifecycleOwner.AtAdapter(
    builder: AtAdapterBuilder.() -> Unit
) = AtAdapter(lifecycleScope, builder)

inline fun AtAdapter(
    scope: CoroutineScope,
    builder: AtAdapterBuilder.() -> Unit
) = AtAdapterBuilder(scope).apply(builder).build()

class AtAdapterBuilder(val scope: CoroutineScope) {
    val adapter = AtAdapter()

    fun build() = adapter

    inline fun <reified I : Any, reified VB : ViewBinding> add(
        block: ItemProviderBuilder<I, VB>.() -> Unit
    ) {
        val inflateMethod = VB::class.java.getDeclaredMethod(
            "inflate",
            LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java
        )
        return adapter.addItemConfig(
            I::class,
            ItemProviderBuilder<I, VB>(scope, inflateMethod).apply(block).build()
        )
    }
}