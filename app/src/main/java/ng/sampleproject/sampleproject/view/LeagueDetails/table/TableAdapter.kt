package ng.sampleproject.sampleproject.view.LeagueDetails.table

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.table_item.view.*
import ng.sampleproject.sampleproject.R
import ng.sampleproject.sampleproject.data.models.StandingResponse
import ng.sampleproject.sampleproject.data.models.Table
import ng.sampleproject.sampleproject.utils.ImageUtils.loadImage
import java.util.ArrayList

/**
 * Created by USER on 10/08/2019.
 */
class TableAdapter : RecyclerView.Adapter<TableAdapter.TableViewHolder>() {
    private var resultList: List<Table> = ArrayList()


    override fun onBindViewHolder(p0: TableViewHolder, p1: Int) {
        val result = resultList[p1]

        p0.teamName.text = result.team.name
        p0.Tposition.text = result.position.toString()
        p0.MatchPlayed.text = result.playedGames.toString()
        p0.points.text = result.points.toString()
        p0.GoalDiff.text = result.goalDifference.toString()

        if (result.team.crestUrl!=null) {
            loadImage(result.team.crestUrl!!, p0.ImageCrest)
        } else {
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TableViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.table_item,
                p0, false)
        return TableViewHolder(view)

    }

    override fun getItemCount(): Int {
       return resultList.size
    }

    inner class TableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){


        val Tposition = itemView.textView12
        val ImageCrest = itemView.imageView2
        val teamName = itemView.textView13
        val MatchPlayed = itemView.textView17
        val points = itemView.textView15
        val GoalDiff = itemView.textView14


    }


    fun setItems(fixtures: StandingResponse) {
        resultList = fixtures.standings[0].table
        notifyDataSetChanged()

    }


}