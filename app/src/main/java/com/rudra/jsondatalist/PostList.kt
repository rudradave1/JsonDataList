package com.rudra.jsondatalist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PostList(navController: NavController, postViewModel: PostViewModel) {
    val posts by postViewModel.posts.collectAsState(initial = emptyList())
    val isLoading by postViewModel.isLoading.collectAsState()

    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(posts) { post ->
            PostItem(post = post) {
                navController.navigate("postDetails/${post.id}")
            }
            if (posts.last() == post) {
                LoadMoreProgressIndicator(isLoading) {
                    postViewModel.fetchNextPage()
                }
            }
        }
    }
}