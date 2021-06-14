package com.timmy.github_silkrode.ui.main_activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.timmy.github_silkrode.base.BaseViewModel
import com.timmy.github_silkrode.base.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val repo: MainRepository) : BaseViewModel() {
    val getDataOK by lazy { MutableLiveData<Boolean>() }
    fun login() {
        viewModelScope.launch {
            val result = repo.login()

            getDataOK.postValue(true)
        }
    }
}
