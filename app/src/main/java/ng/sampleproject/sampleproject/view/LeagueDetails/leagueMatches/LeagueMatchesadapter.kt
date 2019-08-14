package ng.sampleproject.sampleproject.view.LeagueDetails.leagueMatches

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.matches_item.view.*
import ng.sampleproject.sampleproject.R
import ng.sampleproject.sampleproject.data.models.Match
import ng.sampleproject.sampleproject.data.models.MatchesResponse
import ng.sampleproject.sampleproject.utils.DateUtils
import java.util.ArrayList

/**
 * Created by USER on 11/08/2019.
 */
class LeagueMatchesadapter : RecyclerView.Adapter<LeagueMatchesadapter.LeagueMatchesViewHolder>() {

    override fun getItemCount(): Int {
        return resultList.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): LeagueMatchesViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.matches_item,
                p0, false)
        return LeagueMatchesViewHolder(view)    }

    override fun onBindViewHolder(p0: LeagueMatchesViewHolder, p1: Int) {
        val result = resultList[p1]
        p0.mStatus.text = result.status
        p0.mMatchday.text = "MD: "+result.matchday.toString()
        p0.HomeTeam.text = result.homeTeam?.name
        p0.AwayTeam.text = result.awayTeam?.name
        if (result.score?.fullTime?.homeTeam == null) {
            p0.HomeScore.text = "-"

        } else {
            p0.HomeScore.text = result.score?.fullTime?.homeTeam.toString() + ""
        }
        if (result.score?.fullTime?.awayTeam == null) {
            p0.AwayScore.text = "-"
        } else {
            p0.AwayScore.text = result.score?.fullTime?.awayTeam.toString() + ""
        }

        p0.mTime.text = DateUtils.convertDate(result.utcDate.toString())
        if(result.score?.fullTime?.homeTeam == null){
            p0.PlayTime.text = "-"

        }else{
            p0.PlayTime.text = DateUtils.getMatchTime(result.utcDate.toString(), result.status.toString())
        }
    }

    private var resultList: List<Match> = ArrayList()


    inner class LeagueMatchesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val mStatus = itemView.textView4
        val mTime = itemView.textView5
        val mMatchday = itemView.textView6
        val HomeTeam = itemView.textView7
        val AwayTeam = itemView.textView8
        val HomeScore = itemView.textView9
        val AwayScore = itemView.textView10
        val PlayTime = itemView.textView11

    }

    fun setItems(results: MatchesResponse) {
        resultList = results.matches
        notifyDataSetChanged()
    }
}