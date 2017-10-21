package com.togezzer.android.business.common

import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import ru.companion.lionzxy.companion.data.models.PaginatorItem
import timber.log.Timber

abstract class AbstractPaginator<Entity : PaginatorItem<Int, *>> : Paginator<Entity> {

    companion object {
        const val LIMIT = 50

        // сравниваем 2 списка, чтобы понять какое действие произошло: полное обновление списка или добавление элементов
        fun <Entity> checkPaginatorAction(list: List<Entity>, newList: List<Entity>): PaginatorAction {

            //новый список меньше - полное обновление
            if (newList.size <= list.size) {
                return PaginatorAction.RELOAD
            }

            //грани вхождения старого списка в новый
            var startEdge = -1
            var endEdge = -1
            var currentIndex = -1

            for (index in 0..list.size - 1) {
                val indexInNewList = newList.indexOfFirst { it == list[index] }

                //не нашли элемент из старого списка в новом - не является подсписком
                if (indexInNewList == -1) {
                    return PaginatorAction.RELOAD
                }

                if (startEdge == -1) {
                    startEdge = indexInNewList
                }
                endEdge = indexInNewList

                //разрыв в последовательности
                if (currentIndex != -1 && indexInNewList - currentIndex != 1) {
                    return PaginatorAction.RELOAD
                }

                currentIndex = indexInNewList
            }

            val addedToStart = startEdge > 0
            val addedToEnd = endEdge < newList.size - 1

            if (addedToStart && addedToEnd) {
                return PaginatorAction.RELOAD
            } else if (addedToStart) {
                return PaginatorAction.GET_PREV
            } else if (addedToEnd) {
                return PaginatorAction.GET_NEXT
            }

            return PaginatorAction.RELOAD
        }

    }

    private val relay: BehaviorRelay<PaginationState<Entity>>
    protected var list: List<Entity> = listOf()

    private var startError = false // ошибка при загрузке сверху
    protected var startAll = false // сверху все загружено
    private var endError = false // ошибка при загрузке снизу
    private var endAll = false // снизу все загружено
    protected var action = PaginatorAction.RELOAD

    protected val lock = Unit
    protected val subscriptions = CompositeDisposable()

    init {
        relay = BehaviorRelay.createDefault<PaginationState<Entity>>(getState())
    }

    override fun loadNextPage() {
        if (endAll) {
            return
        }
        endError = false
        loadNextObserve(getLastId())
                .subscribe(
                        { newItems ->
                            if (!endAll) {
                                endAll = newItems.isEmpty()
                            }

                            addItemsToEnd(newItems)
                            Timber.d("loadNextPage endAll=$endAll")
                            Timber.d("last id after next=${list.lastOrNull()?.getId()}")
                            emit()
                        },
                        {
                            Timber.e(it)
                            endError = true
                            emit()
                        })
    }

    override fun loadPrevPage() {
        if (startAll) {
            return
        }
        startError = false
        Timber.d("loadPrevPage")
        Timber.d("loadPrevPage. startAllBefore=$startAll")
        loadPrevObserve(getFirstId())
                .subscribe(
                        { newItems ->
                            startAll = newItems.isEmpty()
                            Timber.d("loadPrevPage. startAllAfter=$startAll")
                            addItemsToStart(newItems)
                            emit()
                        },
                        {
                            Timber.e(it)
                            startError = true
                            emit()
                        })
    }


    open protected fun getFirstId() = list.firstOrNull()?.getId() ?: 0
    open protected fun getLastId() = list.lastOrNull()?.getId() ?: 0

    abstract protected fun loadPrevObserve(from: Int, limit: Int = LIMIT): Single<List<Entity>>
    abstract protected fun loadNextObserve(from: Int, limit: Int = LIMIT): Single<List<Entity>>
    abstract protected fun observeDb(from: Int?, to: Int?): Flowable<List<Entity>>?
    abstract protected fun onRefresh(limit: Int = LIMIT): Single<List<Entity>>

    fun subscribeDb() {
        finish()

        val lastId = if (endAll) null else getLastId()
        val firstId = if (startAll) null else getFirstId()

        observeDb(firstId, lastId)?.subscribe({ items ->
            Timber.d("observe chat size=${items.size} from subscribe")
            if (list != items) {
                addObservingItems(items)
                emit()
            }
        })?.let {
            subscriptions.add(it)
        }
    }

    fun resubscribeDb() {
        finish()

        val lastId = if (endAll) null else getLastId()
        val firstId = if (startAll) null else getFirstId()

        observeDb(firstId, lastId)
                ?.skip(1)
                ?.subscribe({ items ->
                    Timber.d("observe chat size=${items.size} from resubscribe")
                    if (list != items) {
                        addObservingItems(items)
                        emit()
                    }
                })?.let {
            subscriptions.add(it)
        }
    }

    override fun finish() {
        subscriptions.clear()
    }

    private fun emit() {
        relay.accept(getState())
    }

    private fun getState(): PaginationState<Entity> {
        Timber.d("paginator emit size=${list.size} action=$action")
        return PaginationState<Entity>(list, startAll, endAll, startError, endError, action)
    }

    override fun reload(): Completable {
        return Completable.fromSingle(onRefresh()
                .doOnSuccess {
                    addItemsReload(it)
                    Timber.d("last id after refresh=${list.lastOrNull()?.getId()}")
                    emit()
                }
        )
    }

    open protected fun updateItems(newItems: List<Entity>) {
        list = listSorting(newItems.distinctBy { it.distinctBy() })
    }

    open protected fun listSorting(list: List<Entity>): List<Entity> = list

    private fun addItemsToStart(newItems: List<Entity>) {
        synchronized(lock) {
            action = PaginatorAction.GET_PREV
            updateItems(list + newItems)
            resubscribeDb()
        }
    }

    private fun addItemsToEnd(newItems: List<Entity>) {
        synchronized(lock) {
            action = PaginatorAction.GET_NEXT
            updateItems(list + newItems)
            resubscribeDb()
        }
    }

    private fun addObservingItems(newItems: List<Entity>) {
        synchronized(lock) {
            action = checkPaginatorAction(list, newItems)
            updateItems(newItems)
        }
    }

    private fun addItemsReload(newItems: List<Entity>) {
        synchronized(lock) {
            action = PaginatorAction.RELOAD
            updateItems(list + newItems)
            subscribeDb()
        }
    }

    override fun observable(): BehaviorRelay<PaginationState<Entity>> {
        return relay
    }

}