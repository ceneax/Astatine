package ceneax.app.lib.astatine.core

abstract class AtControl<S : AtState> {

}

inline fun <reified C : AtControl<out AtState>> AtView<C>.atControl(): Lazy<C> {

}