package ng.sampleproject.sampleproject.view.LeagueDetails.clubs


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_club.*

import ng.sampleproject.sampleproject.R
import ng.sampleproject.sampleproject.data.models.TeamPlayerResponse
import ng.sampleproject.sampleproject.utils.NetworkUtils
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class ClubFragment : DaggerFragment(), ClubAdapter.ListItemClickListener {
    override fun onListItemClick(leagueId: Int, leagueName: String) {
        getPlayers(leagueId)
    }


    companion object {
        private val ARG_LEAGUE_ID = "LEAGUE_ID"
        fun newInstance(teamId: Int): ClubFragment {
            val fragment = ClubFragment()
            val args = Bundle()
            args.putInt(ARG_LEAGUE_ID, teamId)
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    internal lateinit var factory: ViewModelProvider.Factory
    private lateinit var mAdapter: ClubAdapter
    private lateinit var mTableViewModel: ClubViewModel
    private var listener: OnFragmentInteractionListener? = null
    var teamId: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        teamId = arguments?.getInt(ARG_LEAGUE_ID, 0)!!
        return inflater.inflate(R.layout.fragment_club, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        mTableViewModel = ViewModelProviders.of(this, factory).get(ClubViewModel::class.java)
        getTeams()
        retry_button.setOnClickListener{
            error_crest.visibility = View.INVISIBLE
            error_text.visibility = View.INVISIBLE
            retry_button.visibility = View.INVISIBLE
            getTeams()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun initViews() {
        mAdapter = ClubAdapter(this)
        clubRecyclerview.setHasFixedSize(true)
        clubRecyclerview.layoutManager = GridLayoutManager(activity, 3)
        clubRecyclerview.adapter = mAdapter
    }

    private fun getTeams() {
        mTableViewModel.getClub(teamId).observe(this, Observer{ listResource ->
            if (listResource != null && listResource.errorMessage.isEmpty()) {
                mAdapter.setItems(listResource)
            } else{
                CheckInternet()

            }
        })
    }

    private fun getPlayers(teamID: Int){
        mTableViewModel.getClubTeam(teamID).observe(this, Observer{ listResource ->
           if(listResource!=null){
               listener?.onTeamClicked(listResource)
           }
        }
            )
    }

    private fun CheckInternet() {
        if (NetworkUtils.checkInternetConnection(context = context!!)){
            error_crest.visibility = View.VISIBLE
            error_text.text = "No team"
            error_text.visibility = View.VISIBLE

        }else{
            error_crest.visibility = View.VISIBLE
            error_text.visibility = View.VISIBLE
            retry_button.visibility = View.VISIBLE
        }
    }


    interface OnFragmentInteractionListener {
        fun onTeamClicked(teamPlayerResponse: TeamPlayerResponse)
    }

}// Required empty public constructor
