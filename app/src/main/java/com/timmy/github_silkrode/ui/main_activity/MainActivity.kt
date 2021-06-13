package com.timmy.github_silkrode.ui.main_activity

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.TableLayout
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.timmy.github_silkrode.R
import com.timmy.github_silkrode.base.BaseActivity
import com.timmy.github_silkrode.base.ViewPagerAdapter
import com.timmy.github_silkrode.databinding.ActivityMainBinding
import com.timmy.github_silkrode.ui.userfragment.UserFragment
import com.timmy.github_silkrode.ui.userfragment.minefragment.MineFragment
import dagger.hilt.android.AndroidEntryPoint
import util.logi

@Suppress("PLUGIN_WARNING")
@SuppressLint("CheckResult")
@AndroidEntryPoint
class MainActivity : BaseActivity() {
    val TAG = javaClass.simpleName
    private val activity = this
    private val context: Context = this

    lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //隱藏標題列
        supportActionBar?.hide()

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initView()

        initEvent()

    }

    private fun initView() {
        mBinding.viewPager.apply {
            adapter = ViewPagerAdapter(
                supportFragmentManager,
                listOf(UserFragment(), MineFragment())
            )
            offscreenPageLimit = 1
        }
    }

    private fun initEvent() {
        //下方tab按鈕點擊事件
        mBinding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                mBinding.viewPager.currentItem = tab?.position ?: 0
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
//        mBinding.tabs.setupWithViewPager(mBinding.viewPager)
        //頁面滑動後應觸動下方按鈕
        mBinding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                mBinding.tabs.setTabSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })
    }

    fun TabLayout.setTabSelected(position: Int) {
        for (i in 0..this.childCount) {
            if (i == position)
                this.getTabAt(i)?.select()
        }
    }
}