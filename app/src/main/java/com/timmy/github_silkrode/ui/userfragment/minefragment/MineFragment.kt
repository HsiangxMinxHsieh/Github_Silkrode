package com.timmy.github_silkrode.ui.userfragment.minefragment

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.timmy.github_silkrode.R
import com.timmy.github_silkrode.base.BaseFragment
import com.timmy.github_silkrode.databinding.FragmentMineBinding
import com.timmy.github_silkrode.databinding.FragmentUserBinding
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
