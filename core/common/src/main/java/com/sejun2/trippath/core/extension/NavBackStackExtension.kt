package com.sejun2.trippath.core.extension

import androidx.compose.runtime.snapshots.SnapshotStateList

fun SnapshotStateList<*>.canGoBack(): Boolean {
    return size > 1
}

fun SnapshotStateList<*>.back() {
    if (!canGoBack()) return
    removeLastOrNull()
}

fun SnapshotStateList<Any>.replace(key: Any) {
    removeLastOrNull()
    add(key)
}