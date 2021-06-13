package com.timmy.github_silkrode.ui.userfragment.minefragment

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.timmy.github_silkrode.R
import com.timmy.github_silkrode.base.BaseFragment
import com.timmy.github_silkrode.databinding.FragmentMineBinding
import com.timmy.github_silkrode.databinding.FragmentUserBinding
import dagger.hilt.android.AndroidEntryPoint

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
        // when button was clicked, scrolling list to top.
//        fabTop.setOnClickListener {
//            mRecyclerView.scrollToPosition(0)
//        }
//
//        // swipe refresh event.
//        mSwipeRefreshLayout.setOnRefreshListener(mAdapter::refresh)
//
//        // search menu item clicked event.
//        toolbar.setOnMenuItemClickListener {
//            SearchActivity.launch(requireActivity())
//            true
//        }
//
//        // list item clicked event.
//        observe(mAdapter.observeItemEvent(), requireActivity()::jumpBrowser)
//
//        observe(mAdapter.loadStateFlow.asLiveData()) { loadStates ->
//            mSwipeRefreshLayout.isRefreshing = loadStates.refresh is LoadState.Loading
//        }
//
//        observe(mViewModel.eventListLiveData) {
//            mAdapter.submitData(lifecycle, it)
//            mRecyclerView.scrollToPosition(0)
//        }
    }
}
