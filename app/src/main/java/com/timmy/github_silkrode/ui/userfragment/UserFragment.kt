package com.timmy.github_silkrode.ui.userfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.timmy.github_silkrode.R
import com.timmy.github_silkrode.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : BaseFragment() {

    private val mViewModel: UserViewModel by viewModels()

    override val layoutId: Int = R.layout.fragment_user

//    private val mAdapter: HomePagedAdapter = HomePagedAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        toolbar.inflateMenu(R.menu.menu_home_search)
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
