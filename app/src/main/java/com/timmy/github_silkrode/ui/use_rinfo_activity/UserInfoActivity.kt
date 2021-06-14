package com.timmy.github_silkrode.ui.use_rinfo_activity

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.timmy.github_silkrode.base.BaseActivity
import com.timmy.github_silkrode.databinding.ActivityUserInfoBinding
import com.timmy.github_silkrode.db.ReceivedEvent
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