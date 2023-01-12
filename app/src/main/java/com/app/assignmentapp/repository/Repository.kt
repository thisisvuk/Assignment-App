package com.app.assignmentapp.repository

import com.app.assignmentapp.R
import com.app.assignmentapp.dataclass.Post

class Repository {
    private val dataSource = (1..100).map {
        Post(
            33,
            "Ayush Agarwal",
            R.drawable.profile,
            "SOIL AWARENESS",
            1,
            "Do organic farming and save soil from harmful chemicals.",
            arrayListOf(R.drawable.image1),
            10,
            12
        )
    }

    fun getItems(page: Int, pageSize: Int): Result<List<Post>> {
        val startingIndex = page + pageSize
        return if (startingIndex + pageSize <= dataSource.size) {
            Result.success(
                dataSource.slice(startingIndex until startingIndex + pageSize)
            )
        } else Result.success(emptyList())
    }
}
