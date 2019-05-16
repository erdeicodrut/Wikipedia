package com.example.wikipedia.base

import android.os.Bundle
import androidx.lifecycle.*
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


abstract class BaseActivity<viewModel : ViewModel, viewModelFactory : ViewModelProvider.Factory>(private val viewModelClass: Class<viewModel>) :
    DaggerAppCompatActivity(),LifecycleOwner
    {

    @Inject
    lateinit var viewModelFactory: viewModelFactory

    private lateinit var viewModel: viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
    }
}