package ceneax.app.lib.astatine.core

import androidx.lifecycle.ViewModel

abstract class AtState : ViewModel()

enum class AtStateScope {
    OWNER,
    HOST
}