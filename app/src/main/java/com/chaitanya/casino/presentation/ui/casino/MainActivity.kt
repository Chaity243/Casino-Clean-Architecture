package com.chaitanya.casino.presentation.ui.casino

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.chaitanya.casino.BR
import com.chaitanya.casino.R
import com.chaitanya.casino.databinding.ActivityMainBinding
import com.chaitanya.casino.presentation.ui.base.BaseActivity
import kotlinx.android.synthetic.main.layout_search.view.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: CasinoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[CasinoViewModel::class.java]
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        binding.root.rv_suggested_address.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

        initObserver()

     /*   viewModel.findAddress()*/
    }

    private fun initObserver() {
        viewModel.userEntry.observe(this, Observer {
            viewModel.publish(it)
        })
    }

    override fun onDestroy() {
        viewModel.clearDisposables()
        super.onDestroy()
    }


}
