package com.timmy.github_silkrode.ui.userfragment

import com.timmy.github_silkrode.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
@SuppressWarnings("checkResult")
class UserViewModel @Inject constructor(
        repository: UserRepository
) : BaseViewModel() {

//    val eventListLiveData: LiveData<PagingData<ReceivedEvent>> =
//            repository.fetchPager().flow.cachedIn(viewModelScope).asLiveData()

}
