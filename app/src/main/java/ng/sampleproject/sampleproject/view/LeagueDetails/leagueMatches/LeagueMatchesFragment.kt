package ng.sampleproject.sampleproject.view.LeagueDetails.leagueMatches


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
import kotlinx.android.synthetic.main.fragment_league_matches.*

import ng.sampleproject.sampleproject.R
import ng.sampleproject.sampleproject.utils.NetworkUtils
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class LeagueMatchesFragment : DaggerFragment() {

    companion object {
        private val ARG_LEAGUE_ID = "LEAGUE_ID"
        fun newInstance(teamId: Int): LeagueMatchesFragment {
            val fragment = LeagueMatchesFragment()
            val args = Bundle()
            args.putInt(ARG_LEAGUE_ID, teamId)
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    internal lateinit var factory: ViewModelProvider.Factory
    private lateinit var mAdapter: LeagueMatchesadapter
    private lateinit var mLeagueMatchesViewModel: LeagueMatchesViewModel
    var teamId: Int = 0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        teamId = arguments?.getInt(ARG_LEAGUE_ID, 0)!!
        return inflater.inflate(R.layout.fragment_league_matches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        mLeagueMatchesViewModel = ViewModelProviders.of(this, factory).get(LeagueMatchesViewModel::class.java)
        populateUsers()
        retry_button.setOnClickListener{
            error_crest.visibility = View.INVISIBLE
            error_text.visibility = View.INVISIBLE
            retry_button.visibility = View.INVISIBLE
            populateUsers()
        }
    }

    private fun initViews() {
        mAdapter = LeagueMatchesadapter()
        leagueMatchesRecyclerview.setHasFixedSize(true)
        leagueMatchesRecyclerview.layoutManager = LinearLayoutManager(context)
        leagueMatchesRecyclerview.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        leagueMatchesRecyclerview.adapter = mAdapter

    }


    private fun populateUsers() {
        mLeagueMatchesViewModel.getLeagueMatches(teamId).observe(this, Observer{ listResource ->
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
            error_text.text = "No Matches"
            error_text.visibility = View.VISIBLE

        }else{
            error_crest.visibility = View.VISIBLE
            error_text.visibility = View.VISIBLE
            retry_button.visibility = View.VISIBLE
        }
    }

}// Required empty public constructor
