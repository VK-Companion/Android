package com.togezzer.android.business.common

import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Completable

interface Paginator<Entity> {
    fun loadNextPage()
    fun loadPrevPage()
    fun finish()
    fun reload(): Completable
    fun observable(): BehaviorRelay<PaginationState<Entity>>
}