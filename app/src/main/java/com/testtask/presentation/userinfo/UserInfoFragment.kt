package com.testtask.presentation.userinfo

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.testtask.App
import com.testtask.R
import com.testtask.di.ViewModelFactory
import com.testtask.domain.model.Post
import com.testtask.library_base.presentation.extention.visible
import com.testtask.library_base.presentation.fragment.BaseContainerFragment
import com.testtask.presentation.delegates.DelegateAdapter
import kotlinx.android.synthetic.main.fragment.*
import javax.inject.Inject

class UserInfoFragment : BaseContainerFragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var viewModel: UserInfoViewModel

    override val layoutResourceId: Int = R.layout.fragment

    lateinit var adapter: DelegateAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as App).getComponent()?.inject(this)
        viewModel = ViewModelProvider(this, factory).get(UserInfoViewModel::class.java)
        arguments?.getParcelable<Post>("post")?.let {
            viewModel.setPost(it)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = DelegateAdapter(this, {})
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.stateLiveData.observe(viewLifecycleOwner,
            Observer<UserInfoViewModel.ViewState> {
                progressBar.visible(it.isLoading)
                ivError.visible(it.isError)
                adapter.items = it.displayableItems
                adapter.notifyDataSetChanged()
            })

        swiperefresh.setOnRefreshListener {
            viewModel.loadData(true)
            swiperefresh.isRefreshing = false
        }
    }

}