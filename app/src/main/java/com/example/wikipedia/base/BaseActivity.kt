package com.example.wikipedia.base

import android.os.Bundle
import androidx.lifecycle.*
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


abstract class BaseActivity<VM : ViewModel, VMF : ViewModelProvider.Factory>(private val vmClass: Class<VM>) :
    DaggerAppCompatActivity(),LifecycleOwner
    {

    @Inject
    lateinit var viewModelFactory: VMF

    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(vmClass)
    }
}