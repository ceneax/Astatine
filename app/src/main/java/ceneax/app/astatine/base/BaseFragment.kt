package ceneax.app.astatine.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

@Suppress("UNCHECKED_CAST")
open class BaseFragment<VB : ViewBinding> : Fragment() {
    private var _viewBinding: VB? = null
    protected val binding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 使用反射得到ViewBinding的class
        val aClass = (this::class.java.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<*>
        val method = aClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
        _viewBinding = method.invoke(null, layoutInflater) as VB
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initVariable()
        initView()
        bindEvent()
        initObserver()
        initData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    open fun initVariable() {}
    open fun initView() {}
    open fun bindEvent() {}
    open fun initObserver() {}
    open fun initData() {}
}