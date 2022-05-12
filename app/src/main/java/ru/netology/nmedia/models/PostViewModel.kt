package ru.netology.nmedia.models

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemory

class PostViewModel : ViewModel() {
    // упрощённый вариант
    private val repository: PostRepository = PostRepositoryInMemory()
    val data = repository.get()
    fun like() = repository.like()
    fun share() = repository.share()
}