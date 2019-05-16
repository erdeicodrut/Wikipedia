package com.example.wikipedia.base

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.wikipedia.Managers.WIkiManager
import dagger.android.support.DaggerFragment
import javax.inject.Inject


abstract class BaseFragment<viewModel : ViewModel, viewModelFactory : ViewModelProvider.Factory>(private val viewModelClass: Class<viewModel>) : DaggerFragment(), LifecycleOwner {

    @Inject
    protected lateinit var wikiManager: WIkiManager

    @Inject
    private lateinit var viewModelFactory: viewModelFactory

    private lateinit var viewModel: viewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
    }
}