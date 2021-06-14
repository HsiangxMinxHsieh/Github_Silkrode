package com.timmy.github_silkrode.ui.minefragment

import com.timmy.github_silkrode.api.UserInfo
import com.timmy.github_silkrode.api.UserManager

data class MineViewState(
        val isLoading: Boolean,
        val throwable: Throwable?,
        val userInfo: UserInfo?
) {

    companion object {

        fun initial(): MineViewState {
            return MineViewState(
                    isLoading = false,
                    throwable = null,
                    userInfo = UserManager.INSTANCE
            )
        }
    }


}