package ng.sampleproject.sampleproject.view.home.competitions


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_competition.*


import ng.sampleproject.sampleproject.R
import ng.sampleproject.sampleproject.utils.NetworkUtils
import ng.sampleproject.sampleproject.view.LeagueDetails.LeagueDetailsActivity
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class CompetitionFragment : DaggerFragment(), CompetitionAdapter.ListItemClickListener {
    override fun onListItemClick(leagueId: Int, leagueName: String) {
        val tabbedDetailActivity = Intent(activity, LeagueDetailsActivity::class.java)
        tabbedDetailActivity.putExtra(LeagueDetailsActivity.LEAGUE_ID_EXTRA, leagueId)
        tabbedDetailActivity.putExtra(LeagueDetailsActivity.LEAGUE_NAME_EXTRA, leagueName)
        startActivity(tabbedDetailActivity)
    }

    @Inject
    internal lateinit var factory: ViewModelProvider.Factory
    private lateinit var mAdapter: CompetitionAdapter
    private lateinit var mCompetitionViewModel: CompetitionViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_competition, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        mCompetitionViewModel = ViewModelProviders.of(this, factory).get(CompetitionViewModel::class.java)
        populateUsers()

        button.setOnClickListener{
            textView3.visibility = View.INVISIBLE
            imageView6.visibility = View.INVISIBLE
            button.visibility = View.INVISIBLE
            populateUsers()
        }
    }

    private fun initViews() {
        mAdapter = CompetitionAdapter(this)
        competitionsRecyclerview.setHasFixedSize(true)
        competitionsRecyclerview.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        competitionsRecyclerview.layoutManager = LinearLayoutManager(activity)
        competitionsRecyclerview.adapter = mAdapter

    }

    private fun populateUsers() {

        mCompetitionViewModel.getCompetitions().observe(this, Observer{ listResource ->
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
            textView3.text = "No Competition"
            textView3.visibility = View.VISIBLE

        }else{
            imageView6.visibility = View.VISIBLE
            textView3.visibility = View.VISIBLE
            textView3.text = "No Internet"
            button.visibility = View.VISIBLE

        }
    }


}// Required empty public constructor
