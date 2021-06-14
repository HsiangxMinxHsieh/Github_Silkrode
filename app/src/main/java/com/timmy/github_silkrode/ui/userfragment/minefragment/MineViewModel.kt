package com.timmy.github_silkrode.ui.userfragment.minefragment

import androidx.lifecycle.MutableLiveData
import com.timmy.github_silkrode.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
@SuppressWarnings("checkResult")
class MineViewModel @Inject constructor(
        repository: MineRepository
) : BaseViewModel() {
        val viewStateLiveData: MutableLiveData<MineViewState> by lazy { MutableLiveData(MineViewState.initial()) }
}
