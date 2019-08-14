package ng.sampleproject.sampleproject.view.LeagueDetails

import android.os.Bundle
import android.support.v4.view.ViewPager
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_league_details.*
import ng.sampleproject.sampleproject.R
import ng.sampleproject.sampleproject.data.models.TeamPlayerResponse
import ng.sampleproject.sampleproject.utils.TabAdapter
import ng.sampleproject.sampleproject.view.LeagueDetails.clubs.ClubFragment
import ng.sampleproject.sampleproject.view.LeagueDetails.clubs.clubTeam.ClubTeamBottomFragment
import ng.sampleproject.sampleproject.view.LeagueDetails.leagueMatches.LeagueMatchesFragment
import ng.sampleproject.sampleproject.view.LeagueDetails.table.TableFragment

class LeagueDetailsActivity : DaggerAppCompatActivity(), ClubFragment.OnFragmentInteractionListener {


    companion object {
        var LEAGUE_ID_EXTRA = "LEAGUE_ID_EXTRA"
        var LEAGUE_NAME_EXTRA = "LEAGUE_NAME_EXTRA"
    }

    private lateinit var mAdapter: TabAdapter
    private lateinit var mViewPager: ViewPager
    private var clubTeamBottomFragment: ClubTeamBottomFragment? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league_details)


        val leagueId = intent.getIntExtra(LEAGUE_ID_EXTRA, -1)
        val teamName = intent.getStringExtra(LEAGUE_NAME_EXTRA)

        toolbar.title = teamName
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        mAdapter = TabAdapter(supportFragmentManager)
        mAdapter.addFragment(TableFragment.newInstance(leagueId), "Table")
        mAdapter.addFragment(LeagueMatchesFragment.newInstance(leagueId), "Fixtures")
        mAdapter.addFragment(ClubFragment.newInstance(leagueId), "Teams")

        mViewPager = viewPager
        mViewPager.adapter = mAdapter
        tabLayout.setupWithViewPager(mViewPager)
        mViewPager.offscreenPageLimit = 2


    }

    override fun onTeamClicked(teamPlayerResponse: TeamPlayerResponse) {
        if (clubTeamBottomFragment == null) {
            clubTeamBottomFragment = ClubTeamBottomFragment.newInstance(teamPlayerResponse)
            clubTeamBottomFragment?.show(supportFragmentManager, clubTeamBottomFragment?.tag)
        } else {
            clubTeamBottomFragment?.refreshTeam(teamPlayerResponse)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
