package ng.sampleproject.sampleproject.view.LeagueDetails.clubs

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.teams_item.view.*
import ng.sampleproject.sampleproject.R
import ng.sampleproject.sampleproject.data.models.Teams
import ng.sampleproject.sampleproject.data.models.TeamsResponse
import ng.sampleproject.sampleproject.utils.ImageUtils
import java.util.ArrayList

/**
 * Created by USER on 10/08/2019.
 */
class ClubAdapter(private val listItemClickListener: ListItemClickListener) : RecyclerView.Adapter<ClubAdapter.ClubViewHolder>() {

    private var resultList: List<Teams> = ArrayList()


    interface ListItemClickListener {
        fun onListItemClick(leagueId: Int, leagueName: String)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ClubViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.teams_item,
                p0, false)
        return ClubViewHolder(view)
    }

    override fun onBindViewHolder(p0: ClubViewHolder, p1: Int) {
        val result = resultList[p1]
        p0.teamName.text = result.name
        if (result.crestUrl!=null) {
            ImageUtils.loadImage(result.crestUrl!!, p0.ImageCrest)
        } else {

        }

    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    inner class ClubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        val teamName = itemView.club_name
        val ImageCrest = itemView.club_logo

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val user = resultList!![adapterPosition]
            val TeamId = user.id
            val TeamName = user.name
            listItemClickListener.onListItemClick(TeamId, TeamName)
        }


    }

    fun setItems(results: TeamsResponse) {
        resultList = results.teams
        notifyDataSetChanged()
    }
}