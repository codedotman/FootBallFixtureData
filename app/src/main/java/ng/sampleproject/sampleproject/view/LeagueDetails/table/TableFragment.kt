package ng.sampleproject.sampleproject.view.LeagueDetails.table


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_club_team_bottom.*
import kotlinx.android.synthetic.main.fragment_matches.*
import kotlinx.android.synthetic.main.fragment_table.*

import ng.sampleproject.sampleproject.R
import ng.sampleproject.sampleproject.utils.NetworkUtils
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class TableFragment : DaggerFragment() {

    companion object {
        private val ARG_LEAGUE_ID = "LEAGUE_ID"
        fun newInstance(teamId: Int): TableFragment {
            val fragment = TableFragment()
            val args = Bundle()
            args.putInt(ARG_LEAGUE_ID, teamId)
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    internal lateinit var factory: ViewModelProvider.Factory
    private lateinit var mAdapter: TableAdapter
    private lateinit var mTableViewModel: TableViewModel
    private lateinit var recyclerView: RecyclerView

    private var teamId: Int = 0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        teamId = arguments?.getInt(ARG_LEAGUE_ID, 0)!!
        val view = inflater.inflate(R.layout.fragment_table, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        mTableViewModel = ViewModelProviders.of(this, factory).get(TableViewModel::class.java)
        populateUsers()
        button.setOnClickListener{
            imageView6.visibility = View.INVISIBLE
            textView3.visibility = View.INVISIBLE
            button.visibility = View.INVISIBLE
            populateUsers()
        }
    }
    private fun initViews() {
        mAdapter = TableAdapter()
        recyclerView = tableRecyclerview
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = mAdapter

    }

    private fun populateUsers() {

        mTableViewModel.getTable(teamId).observe(this, Observer{ listResource ->
            if (listResource != null && listResource.errorMessage.isEmpty()) {
                mAdapter.setItems(listResource)
            } else{
                CheckInternet()

            }
        })
    }

    private fun CheckInternet() {
        if (NetworkUtils.checkInternetConnection(context = context!!)){
            imageView6.visibility = View.VISIBLE
            textView3.text = "No table"
            textView3.visibility = View.VISIBLE
            button.visibility = View.VISIBLE


        }else{
            imageView6.visibility = View.VISIBLE
            textView3.visibility = View.VISIBLE
            button.visibility = View.VISIBLE
        }
    }


}// Required empty public constructor
