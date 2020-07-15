package com.example.ui.home

import androidx.lifecycle.viewModelScope
import com.example.api.Status
import com.example.repository.GithubRepository
import com.example.ui.base.BaseViewModel
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel(
    private val githubRepository: GithubRepository
) : BaseViewModel() {

    enum class ContextEvent {
        GET_REPOS_BUTTON_CLICKED,
    }

    val contextEventBus: PublishSubject<ContextEvent> = PublishSubject.create()

    fun onGetReposBtnClick() {
        contextEventBus.onNext(ContextEvent.GET_REPOS_BUTTON_CLICKED)
        getGitHubReposRequest()
    }

    // Sample HTTP request
    private fun getGitHubReposRequest() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = githubRepository.getRepos()
            viewModelScope.launch(Dispatchers.Main) {
                when (response.status) {
                    Status.SUCCESS -> {
                        if (response.data != null) {
                            Timber.d("getGitHubReposRequest response: ${response.data}")
                        } else {
                            Timber.e("getGitHubReposRequest ERROR")
                        }
                    }
                    Status.ERROR -> {
                        Timber.e("getGitHubReposRequest ERROR")
                    }
                }
            }
        }
    }
}
