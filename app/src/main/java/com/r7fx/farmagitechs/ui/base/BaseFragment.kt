package com.r7fx.farmagitechs.ui.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData

open class BaseFragment : Fragment {
    constructor() : super()
    constructor(resId: Int) : super(resId)

    protected fun <T> LiveData<T>.onResult(action: (T) -> Unit) {
        observe(this@BaseFragment) { data -> data?.let(action) }
    }
}