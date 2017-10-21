package com.togezzer.android.business.common

class Range(val from: Int, val to: Int, val type: RangeType) {
    enum class RangeType {
        INSERTED, DELETED, CHANGED
    }
}