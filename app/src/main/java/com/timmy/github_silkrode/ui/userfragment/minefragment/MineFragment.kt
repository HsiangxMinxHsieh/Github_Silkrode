package com.timmy.github_silkrode.ui.userfragment.minefragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.timmy.github_silkrode.api.UserManager
import com.timmy.github_silkrode.base.BaseFragment
import com.timmy.github_silkrode.databinding.FragmentMineBinding
import com.timmy.github_silkrode.ext.observe
import dagger.hilt.android.AndroidEntryPoint
import util.options

@AndroidEntryPoint
class MineFragment : BaseFragment<FragmentMineBinding>(FragmentMineBinding::inflate) {

    private val mViewModel: MineViewModel by viewModels()

//    private val mAdapter: UserPagedAdapter = UserPagedAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binds()
//
//        mRecyclerView.adapter = mAdapter
//        mRecyclerView.removeAllAnimation()
    }

    private fun binds() {
        if (UserManager.isThingInitialized)
            observe(mViewModel.viewStateLiveData, this::onNewState)
    }

    private fun onNewState(state: MineViewState) {
        if (state.throwable != null) {
            // handle throwable.
        }
        if (state.userInfo != null) {
            Glide.with(requireContext())
                .load(state.userInfo.avatarUrl)
                .apply(options)
                .into(mBinding.ivMineAvatar)
            mBinding.tvYourName.text = state.userInfo.name
            mBinding.tvYourLogin.text = state.userInfo.login
            mBinding.tvFollowersCount.text = state.userInfo.followers.toString()
            mBinding.tvFollowingCount.text = state.userInfo.following.toString()
            mBinding.tvYourEmail.text = state.userInfo.email
        }
    }
}
