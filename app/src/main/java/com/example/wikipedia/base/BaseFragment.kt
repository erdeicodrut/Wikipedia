package com.example.wikipedia.base

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.wikipedia.Managers.WIkiManager
import dagger.android.support.DaggerFragment
import javax.inject.Inject


abstract class BaseFragment<VM : ViewModel, VMF : ViewModelProvider.Factory>(private val vmClass: Class<VM>) : DaggerFragment(), LifecycleOwner {

    @Inject
    lateinit var wikiManager: WIkiManager

    @Inject
    lateinit var viewModelFactory: VMF

    protected lateinit var viewModel: VM

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(vmClass)
    }
}