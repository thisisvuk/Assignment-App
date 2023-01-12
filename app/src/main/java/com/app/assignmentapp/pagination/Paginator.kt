package com.app.assignmentapp.pagination

interface Paginator<Key, Post> {
    suspend fun loadNextItems()
    fun reset()
}