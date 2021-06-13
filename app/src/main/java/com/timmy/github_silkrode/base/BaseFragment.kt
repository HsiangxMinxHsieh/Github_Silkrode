package com.timmy.github_silkrode.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.timmy.github_silkrode.view.IView

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB: ViewBinding>( private val inflate: Inflate<VB>) : Fragment(), IView {

    private var _binding: VB? = null
    val mBinding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = inflate.invoke(inflater, container, false)
        return mBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//
//    lateinit var mBinding: ViewDataBinding
//    abstract val layoutId: Int
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        logi("onCreateView","layoutId 是===>$layoutId")
//        mBinding = DataBindingUtil.setContentView(activity as Activity, layoutId) as ViewDataBinding
//        logi("onCreateView","mBinding 是===>$mBinding")
//        return mBinding.root
//    }
//
////    override fun onDestroyView() {
////        super.onDestroyView()
////        mRootView = null
////    }
}