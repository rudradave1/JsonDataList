package com.rudra.jsondatalist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostViewModel(private val repository: PostRepository) : ViewModel() {
    private val pageSize = 10
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> get() = _posts

    private var currentPage = 1

    init {
        fetchNextPage()
    }

    fun fetchNextPage() {
        if (_isLoading.value) return

        viewModelScope.launch {
            _isLoading.value = true
            try {
                val fetchedPosts = repository.getPosts(currentPage, pageSize)
                _posts.value += fetchedPosts
                currentPage++
            } catch (e: Exception) {
                // Handle error, e.g., show error message to user
            } finally {
                _isLoading.value = false
            }
        }
    }

    suspend fun getPostById(postId: Int): Post {
        return repository.getPostById(postId)
    }
}