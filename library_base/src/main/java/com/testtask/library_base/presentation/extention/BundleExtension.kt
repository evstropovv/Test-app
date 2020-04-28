package com.testtask.library_base.presentation.extention

import android.os.Bundle
import androidx.core.os.bundleOf

fun Bundle.putAny(key: String, value: Any?) {
    putAll(bundleOf(key to value))
}
