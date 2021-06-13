package com.timmy.github_silkrode.ui.userfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.timmy.github_silkrode.base.BaseViewModel
import com.timmy.github_silkrode.db.ReceivedEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
@SuppressWarnings("checkResult")
class UserViewModel @Inject constructor(
        repository: UserRepository
) : BaseViewModel() {

    @OptIn(androidx.paging.ExperimentalPagingApi::class)
    val eventListLiveData: LiveData<PagingData<ReceivedEvent>> =
            repository.fetchPager().flow.cachedIn(viewModelScope).asLiveData()

}
