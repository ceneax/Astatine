package ceneax.app.lib.astatine.core

/**
 * 初始化 [Activity] => [android.app.Activity.onCreate] -> [onInit]
 *
 * [Fragment] => [androidx.fragment.app.Fragment.onViewCreated] -> [onInit]
 *
 * 销毁 => [android.app.Activity.onDestroy] -> [onDispose]
 */
interface AtLifecycle {
    fun onInit() {}

    fun onDispose() {}
}