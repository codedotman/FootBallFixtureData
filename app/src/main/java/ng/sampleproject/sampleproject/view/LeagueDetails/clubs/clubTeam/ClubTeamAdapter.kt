package ng.sampleproject.sampleproject.view.LeagueDetails.clubs.clubTeam

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.player_name_item.view.*
import ng.sampleproject.sampleproject.R
import ng.sampleproject.sampleproject.data.models.Player
import java.util.ArrayList

/**
 * Created by USER on 11/08/2019.
 */
class ClubTeamAdapter : RecyclerView.Adapter<ClubTeamAdapter.ClubTeamViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ClubTeamViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.player_name_item,
                p0, false)
        return ClubTeamViewHolder(view)    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    override fun onBindViewHolder(p0: ClubTeamViewHolder, p1: Int) {
        val result = resultList[p1]
        p0.playerName.text = result.name
        p0.playerPosition.text = result.position
        p0.numbering.text = (p1 + 1).toString()


    }

    private var resultList: List<Player> = ArrayList()


    inner class ClubTeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val playerName = itemView.textView19
        val playerPosition = itemView.textView20
        val numbering = itemView.textView18

    }

    fun setItems(results: List<Player>) {
        resultList = results
        notifyDataSetChanged()
    }
}