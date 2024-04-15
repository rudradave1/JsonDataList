package com.rudra.jsondatalist

class PostRepository(private val apiService: ApiService) {
    suspend fun getPosts(page: Int, limit: Int): List<Post> {
        return apiService.getPosts(page, limit)
    }

    suspend fun getPostById(postId: Int): Post {
        return apiService.getPostById(postId)
    }
}