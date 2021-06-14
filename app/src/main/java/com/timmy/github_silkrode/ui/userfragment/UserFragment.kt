package com.timmy.github_silkrode.ui.userfragment

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.timmy.github_silkrode.R
import com.timmy.github_silkrode.base.BaseFragment
import com.timmy.github_silkrode.databinding.FragmentUserBinding
import com.timmy.github_silkrode.databinding.ItemUsersBinding
import com.timmy.github_silkrode.db.ReceivedEvent
import com.timmy.github_silkrode.db.Type
import com.timmy.github_silkrode.ext.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : BaseFragment<FragmentUserBinding>(FragmentUserBinding::inflate) {

    private val mViewModel: UserViewModel by viewModels()

    private val mAdapter: UserPagedAdapter = UserPagedAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        initEvent()

    }

    private fun initView() {
        mBinding.mRecyclerView.apply{
            adapter = mAdapter
        }
        observe(mViewModel.eventListLiveData) {
            mAdapter.submitData(lifecycle, it)
            mBinding.mRecyclerView.scrollToPosition(0)
        }
    }

    private fun initEvent() {
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


class UserPagedAdapter : PagingDataAdapter<ReceivedEvent, UserPagedViewHolder>(diffCallback) {

    private val itemEventObservable: MutableLiveData<String> = MutableLiveData()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserPagedViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), R.layout.item_users, parent, false) as ItemUsersBinding
        val viewHolder = UserPagedViewHolder(binding)
        if (viewType == 0) {
            //根據不同的 viewType 改變 view 風格
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: UserPagedViewHolder, position: Int) {
        holder.binds(getItem(position)!!, position, itemEventObservable)
    }

    fun observeItemEvent(): LiveData<String> {
        return itemEventObservable
    }

    companion object {

        private val diffCallback: DiffUtil.ItemCallback<ReceivedEvent> =
            object : DiffUtil.ItemCallback<ReceivedEvent>() {

                override fun areItemsTheSame(oldItem: ReceivedEvent, newItem: ReceivedEvent): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: ReceivedEvent, newItem: ReceivedEvent): Boolean {
                    return oldItem == newItem
                }
            }
    }
}
 class UserPagedViewHolder(var binding: ItemUsersBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {


    fun binds(data: ReceivedEvent, position: Int, liveData: MutableLiveData<String>) {
        Glide.with(binding.root.context)
            .load(data.avatarUrl)
            .apply(RequestOptions().circleCrop())
            .into(binding.ivAvatar)

        binding.tvName.text = data.name


//        ivEventType.setImageDrawable(
//            when (data.type) {
//                Type.WatchEvent ->
//                    ContextCompat.getDrawable(ivEventType.context, R.mipmap.ic_star_yellow_light)
//                Type.CreateEvent, Type.ForkEvent, Type.PushEvent ->
//                    ContextCompat.getDrawable(ivEventType.context, R.mipmap.ic_fork_green_light)
//                else -> null
//            }
//        )
    }

//    private fun getTitle(data: ReceivedEvent, liveData: MutableLiveData<String>): CharSequence {
//        val actor = data.actor.displayLogin
//        val action = when (data.type) {
//            Type.WatchEvent -> "starred"
//            Type.CreateEvent -> "created"
//            Type.ForkEvent -> "forked"
//            Type.PushEvent -> "pushed"
//            else -> data.type.name
//        }
//        val repo = data.repo.name
//
//        val actorSpan = object : ClickableSpan() {
//            override fun onClick(widget: View) {
//                liveData.postValue(data.actor.url)
//            }
//        }
//        val repoSpan = object : ClickableSpan() {
//            override fun onClick(widget: View) {
//                liveData.postValue(data.repo.url)
//            }
//        }
//        val styleSpan = StyleSpan(Typeface.BOLD)
//        val styleSpan2 = StyleSpan(Typeface.BOLD)
//
//        tvEventContent.movementMethod = LinkMovementMethod.getInstance()
//
//        return SpannableStringBuilder().apply {
//            append("$actor $action $repo")
//            setSpan(actorSpan, 0, actor.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//            setSpan(styleSpan, 0, actor.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//            setSpan(repoSpan,
//                actor.length + action.length + 2,
//                actor.length + action.length + repo.length + 2,
//                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//            setSpan(styleSpan2,
//                actor.length + action.length + 2,
//                actor.length + action.length + repo.length + 2,
//                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//        }
//    }
}