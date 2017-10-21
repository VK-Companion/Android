package ru.companion.lionzxy.companion.data.models;

interface PaginatorItem<out IdType, out DistinctType> : IdHolder<IdType> {
    fun distinctBy(): DistinctType
}