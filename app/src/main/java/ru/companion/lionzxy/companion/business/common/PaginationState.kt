package com.togezzer.android.business.common

data class PaginationState<Entity>(
        var dataList: List<Entity>,
        val allLoadedStart: Boolean,
        val allLoadedEnd: Boolean,
        val loadingErrorStart: Boolean,
        val loadingErrorEnd: Boolean,
        val action: PaginatorAction
)