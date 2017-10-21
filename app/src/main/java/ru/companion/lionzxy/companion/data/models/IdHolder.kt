package ru.companion.lionzxy.companion.data.models

interface IdHolder<out IdType> {
    fun getId(): IdType
}
