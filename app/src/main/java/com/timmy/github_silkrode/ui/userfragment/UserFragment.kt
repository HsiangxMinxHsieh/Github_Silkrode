package com.timmy.github_silkrode.ui.userfragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.timmy.github_silkrode.R
import com.timmy.github_silkrode.api.UserManager
import com.timmy.github_silkrode.base.BaseFragment
import com.timmy.github_silkrode.databinding.FragmentUserBinding
import com.timmy.github_silkrode.databinding.ItemUsersBinding
import com.timmy.github_silkrode.db.ReceivedEvent
import com.timmy.github_silkrode.ext.observe
import com.timmy.github_silkrode.ui.use_rinfo_activity.UserInfoActivity
import dagger.hilt.android.AndroidEntryPoint
import util.options

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
        mBinding.mRecyclerView.apply {
            adapter = mAdapter
        }
        if (UserManager.isThingInitialized)
            observe(mViewModel.eventListLiveData) {
                mAdapter.submitData(lifecycle, it)
            }
    }

    private fun initEvent() {
        observe(mAdapter.observeItemEvent(),this::toUserInfoActivity)
    }

    private fun toUserInfoActivity(data:ReceivedEvent){
        val intent = Intent(activity,UserInfoActivity::class.java)
        intent.putExtra(UserInfoActivity.DATA_LIST_CLICK,data)
        startActivity(intent)
    }

}


class UserPagedAdapter : PagingDataAdapter<ReceivedEvent, UserPagedViewHolder>(diffCallback) {

    private val itemEventObservable: MutableLiveData<ReceivedEvent> = MutableLiveData()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserPagedViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), R.layout.item_users, parent, false) as ItemUsersBinding
        return UserPagedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserPagedViewHolder, position: Int) {
        holder.binds(getItem(position)!!, position, itemEventObservable)
    }

    fun observeItemEvent(): LiveData<ReceivedEvent> {
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


    fun binds(data: ReceivedEvent, position: Int, liveData: MutableLiveData<ReceivedEvent>) {
        Glide.with(binding.root.context)
            .load(data.avatarUrl)
            .apply(options)
            .into(binding.ivAvatar)
        binding.clContent.setOnClickListener {
            liveData.postValue(data)
        }
        binding.tvName.text = data.login


    }

}