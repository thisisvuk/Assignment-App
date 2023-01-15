package com.app.assignmentapp.feedscreen.pagination

interface Paginator<Key, Post> {
    suspend fun loadNextItems()
    fun reset()
}