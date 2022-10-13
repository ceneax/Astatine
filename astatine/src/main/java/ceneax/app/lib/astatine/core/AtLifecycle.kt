package ceneax.app.lib.astatine.core

/**
 * [Activity, Fragment]
 *
 * 初始化 => [android.app.Activity.onCreate] -> [onInit]
 *
 * 销毁 => [onDispose] -> [android.app.Activity.onDestroy]
 */
interface AtLifecycle {
    fun onInit() {}

    fun onDispose() {}
}