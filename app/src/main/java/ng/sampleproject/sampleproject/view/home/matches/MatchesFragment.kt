package ng.sampleproject.sampleproject.view.home.matches


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_matches.*
import kotlinx.android.synthetic.main.fragment_table.*

import ng.sampleproject.sampleproject.R
import ng.sampleproject.sampleproject.utils.NetworkUtils
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class MatchesFragment : DaggerFragment() {

    @Inject
    internal lateinit var factory: ViewModelProvider.Factory
    private lateinit var mAdapter: MatchesAdapter
    private lateinit var mMatchViewModel: MatchesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_matches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        mMatchViewModel = ViewModelProviders.of(this, factory).get(MatchesViewModel::class.java)
        getMatches()
        retry_button.setOnClickListener{
            error_crest.visibility = View.INVISIBLE
            error_text.visibility = View.INVISIBLE
            retry_button.visibility = View.INVISIBLE
            getMatches()
        }
    }

    private fun initViews() {
        mAdapter = MatchesAdapter()
        matchesRecyclerview.setHasFixedSize(true)
        matchesRecyclerview.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        matchesRecyclerview.layoutManager = LinearLayoutManager(context)
        matchesRecyclerview.adapter = mAdapter

    }


    private fun getMatches() {

        mMatchViewModel.getMatches().observe(this, Observer{ listResource ->
            if (listResource != null && listResource.errorMessage.isEmpty()) {
                mAdapter.setItems(listResource)
            } else{
                CheckInternet()

            }
        })
    }

    private fun CheckInternet() {
        if (NetworkUtils.checkInternetConnection(context = context!!)){
            error_crest.visibility = View.VISIBLE
            error_text.text = "No table"
            error_text.visibility = View.VISIBLE

        }else{
            error_crest.visibility = View.VISIBLE
            error_text.visibility = View.VISIBLE
            error_text.text = "No Internet"
            retry_button.visibility = View.VISIBLE
        }
    }



}// Required empty public constructor
