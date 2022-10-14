package ceneax.app.lib.astatine

import android.os.Bundle
import android.text.Spanned
import android.widget.EditText
import androidx.core.os.bundleOf
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1

// -------------------------- ClassExt --------------------------

@Deprecated("", ReplaceWith(""))
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

// -------------------------- AppExt --------------------------

fun atBundleOf(vararg pairs: Pair<KProperty1<*, *>, Any?>): Bundle = bundleOf(
    *Array(pairs.size) {
        Pair(pairs[it].first.name, pairs[it].second)
    }
)

// -------------------------- ViewExt --------------------------

/**
 * Set the given text on the textview.
 *
 * @return True if the given text is different from the previous text on the textview.
 */
fun EditText.setTextIfDifferent(newText: CharSequence?): Boolean {
    if (!isTextDifferent(newText, text)) {
        // Previous text is the same. No op
        return false
    }

    setText(newText)
    // Since the text changed we move the cursor to the end of the new text.
    // This allows us to fill in text programmatically with a different value,
    // but if the user is typing and the view is rebound we won't lose their cursor position.
    setSelection(newText?.length ?: 0)
    return true
}

/**
 * @return True if str1 is different from str2.
 *
 *
 * This is adapted from how the Android DataBinding library binds its text views.
 */
internal fun isTextDifferent(str1: CharSequence?, str2: CharSequence?): Boolean {
    if (str1 === str2) {
        return false
    }
    if (str1 == null || str2 == null) {
        return true
    }
    val length = str1.length
    if (length != str2.length) {
        return true
    }

    if (str1 is Spanned) {
        return str1 != str2
    }

    for (i in 0 until length) {
        if (str1[i] != str2[i]) {
            return true
        }
    }
    return false
}