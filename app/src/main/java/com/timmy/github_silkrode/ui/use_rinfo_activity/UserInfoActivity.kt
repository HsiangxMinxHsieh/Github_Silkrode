package com.timmy.github_silkrode.ui.use_rinfo_activity

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayout
import com.timmy.github_silkrode.R
import com.timmy.github_silkrode.base.BaseActivity
import com.timmy.github_silkrode.base.ViewPagerAdapter
import com.timmy.github_silkrode.databinding.ActivityMainBinding
import com.timmy.github_silkrode.databinding.ActivityUserInfoBinding
import com.timmy.github_silkrode.db.ReceivedEvent
import com.timmy.github_silkrode.ui.userfragment.UserFragment
import com.timmy.github_silkrode.ui.userfragment.minefragment.MineFragment
import dagger.hilt.android.AndroidEntryPoint
import util.options
import java.lang.Exception

@Suppress("PLUGIN_WARNING")
@SuppressLint("CheckResult")
@AndroidEntryPoint
class UserInfoActivity : BaseActivity<ActivityUserInfoBinding>({ ActivityUserInfoBinding.inflate(it) }) {
    companion object {
        const val DATA_LIST_CLICK = "DATA_LIST_CLICK"
    }
    private val context: Context = this
    private val mViewModel: UserInfoViewModel by viewModels()

    private lateinit var userData: ReceivedEvent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        initData()

        initView()

        initEvent()
    }

    private fun initData() {
        val bundle = intent.extras
        var s = ""
        if (bundle != null) {
            userData = bundle.get(DATA_LIST_CLICK) as ReceivedEvent
        }
    }

    private fun initView() {
        try {
            Glide.with(context)
                .load(userData.avatarUrl)
                .apply(options)
                .into(mBinding.ivUserAvatar)

            mBinding.tvFullName.text = userData.login
            mBinding.tvLocate.text = userData.location
            mBinding.tvUrl.text = userData.url

        }catch (e:Exception){
        }


    }

    private fun initEvent() {
        //按鈕點擊事件
        mBinding.ivClose.setOnClickListener {
            this.finish()
        }
    }

}