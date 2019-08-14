package ng.sampleproject.sampleproject.view.home.matches

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.matches_item.view.*
import ng.sampleproject.sampleproject.R
import ng.sampleproject.sampleproject.data.models.Match
import ng.sampleproject.sampleproject.data.models.MatchesResponse
import ng.sampleproject.sampleproject.utils.DateUtils
import java.util.*

/**
 * Created by USER on 10/08/2019.
 */
class MatchesAdapter : RecyclerView.Adapter<MatchesAdapter.MatchesViewHolder>() {

    private var resultList: List<Match> = ArrayList()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MatchesViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.matches_item,
                p0, false)
    return MatchesViewHolder(view)
    }

    override fun onBindViewHolder(p0: MatchesViewHolder, p1: Int) {
        val result = resultList[p1]
        p0.mStatus.text = result.status
        if (result.matchday==null){
            p0.mMatchday.text = "MD: -"
        }else{
            p0.mMatchday.text = "MD: "+result.matchday.toString()
        }

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

    override fun getItemCount(): Int {
        return resultList.size
    }

    inner class MatchesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
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