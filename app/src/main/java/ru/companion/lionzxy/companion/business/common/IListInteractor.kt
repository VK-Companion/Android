package com.togezzer.android.business.common

import io.reactivex.Completable
import io.reactivex.Observable

interface IListInteractor<Entity> {
    fun reload(): Completable?
    fun loadNextPage()
    fun loadPrevPage()
    fun observable(): Observable<PaginationState<Entity>>?
    fun finish()
    fun toggleObservingNewItems(enabled: Boolean)
}