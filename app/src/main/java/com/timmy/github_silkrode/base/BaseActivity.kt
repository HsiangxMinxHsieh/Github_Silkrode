package com.timmy.github_silkrode.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding


abstract class BaseActivity<B : ViewBinding>(val bindingFactory: (LayoutInflater) -> B) : AppCompatActivity() {
     lateinit var mBinding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //隱藏標題列
        supportActionBar?.hide()
        mBinding = bindingFactory(layoutInflater)
        setContentView(mBinding.root)
    }

}