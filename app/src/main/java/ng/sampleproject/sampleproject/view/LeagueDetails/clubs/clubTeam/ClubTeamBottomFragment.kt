package ng.sampleproject.sampleproject.view.LeagueDetails.clubs.clubTeam


import android.app.Dialog
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_club.*
import kotlinx.android.synthetic.main.fragment_club_team_bottom.*

import ng.sampleproject.sampleproject.R
import ng.sampleproject.sampleproject.data.models.Player
import ng.sampleproject.sampleproject.data.models.TeamPlayerResponse
import ng.sampleproject.sampleproject.utils.ImageUtils
import ng.sampleproject.sampleproject.view.LeagueDetails.clubs.ClubAdapter
import ng.sampleproject.sampleproject.view.LeagueDetails.clubs.ClubFragment
import ng.sampleproject.sampleproject.view.LeagueDetails.clubs.ClubViewModel


/**
 * A simple [Fragment] subclass.
 */
class ClubTeamBottomFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(teamPlayerResponse: TeamPlayerResponse) =
                ClubTeamBottomFragment().apply {
                    arguments = Bundle().apply {
                        putString("name", teamPlayerResponse.name)
                        putString("url", teamPlayerResponse.crestUrl)
                        putParcelableArrayList("players", ArrayList(teamPlayerResponse.squad))
                    }
                }
    }


    private lateinit var dialog: BottomSheetDialog
    private lateinit var behavior: BottomSheetBehavior<View>
    private lateinit var mAdapter: ClubTeamAdapter


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener {
            val d = it as BottomSheetDialog
            val sheet = d.findViewById<View>(android.support.design.R.id.design_bottom_sheet)
            behavior = BottomSheetBehavior.from(sheet)
            behavior.isHideable = false
            behavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        return dialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_club_team_bottom, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments!=null){
            team_name.text = arguments?.getString("name")
            ImageUtils.loadImage(arguments?.getString("url").toString(), team_logo_)
            initViews(arguments?.getParcelableArrayList("players") ?: ArrayList())
        }
        close_diologue.setOnClickListener{
            behavior.state = BottomSheetBehavior.STATE_COLLAPSED
            dialog.hide()

        }

    }

    private fun initViews(players: List<Player>) {
        mAdapter = ClubTeamAdapter()
        player_recyclerview.setHasFixedSize(true)
        player_recyclerview.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        player_recyclerview.layoutManager = LinearLayoutManager(activity)
        player_recyclerview.adapter = mAdapter
        mAdapter.setItems(players)

    }


    fun refreshTeam(teamPlayerResponse: TeamPlayerResponse) {
        if (context!=null){
            team_name.text = teamPlayerResponse.name
            ImageUtils.loadImage(teamPlayerResponse.crestUrl, team_logo_)
            initViews(teamPlayerResponse.squad)
        }
    }

}// Required empty public constructor
