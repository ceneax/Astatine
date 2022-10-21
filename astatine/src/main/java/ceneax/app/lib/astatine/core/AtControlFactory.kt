package ceneax.app.lib.astatine.core

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope

@PublishedApi
internal interface IAtControlFactory {
    fun <C : AtControl<out AtState>> create(cls: Class<C>, context: AtContext): C
}

@PublishedApi
internal class DefaultAtControlFactory : IAtControlFactory {
    override fun <C : AtControl<out AtState>> create(
        cls: Class<C>,
        context: AtContext
    ): C = cls.getConstructor(cls.constructors[0].parameterTypes[0]).newInstance(context)
}

@PublishedApi
internal inline fun <reified C : AtControl<out AtState>> createAtControl(
    context: AtContext,
    factory: IAtControlFactory = DefaultAtControlFactory()
): C = factory.create(C::class.java, context)

inline fun <V, reified C : AtControl<out AtState>> V.atControl(): Lazy<C>
where V : FragmentActivity, V : AtView<C> = withAtInit(lazy(LazyThreadSafetyMode.NONE) {
    createAtControl(AtContext.Activity(
        activity = this,
        fragmentManager = supportFragmentManager
    ))
})

inline fun <V, reified C : AtControl<out AtState>> V.atControl(): Lazy<C>
where V : Fragment, V : AtView<C> = withAtInit(lazy(LazyThreadSafetyMode.NONE) {
    createAtControl(AtContext.Fragment(
        activity = requireActivity(),
        fragmentManager = parentFragmentManager,
        fragment = this
    ))
})