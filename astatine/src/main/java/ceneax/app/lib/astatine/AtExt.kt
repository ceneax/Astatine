package ceneax.app.lib.astatine

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import kotlin.reflect.KClass

internal fun <R> Class<R>.newInstance(vararg args: Any): R {
    return getDeclaredConstructor(*args.map { it::class.java }.toTypedArray()).newInstance(*args)
}

@Suppress("UNCHECKED_CAST")
internal fun <R : Any, T> KClass<R>.getGenericsClass(index: Int): Class<T> {
    val type = java.genericSuperclass as ParameterizedType
    return type.actualTypeArguments[index] as Class<T>
}

internal fun <R : Any, T> KClass<R>.newGenericsInstance(index: Int, vararg args: Any): T {
    return getGenericsClass<R, T>(index).newInstance(*args)
}

internal fun <R : Any> KClass<R>.newInstance(vararg args: Any): R {
    return java.newInstance(*args)
}

internal fun Type.getActualTypeClass(index: Int): Class<*> {
    return (this as ParameterizedType).actualTypeArguments[index] as Class<*>
}

internal fun Class<*>.setDeclaredField(instance: Any, name: String, value: Any) {
    getDeclaredField(name).let {
        it.isAccessible = true
        it.set(instance, value)
    }
}

internal fun <R : Any> R.setDeclaredField(name: String, value: Any): R {
    this::class.java.setDeclaredField(this, name, value)
    return this
}