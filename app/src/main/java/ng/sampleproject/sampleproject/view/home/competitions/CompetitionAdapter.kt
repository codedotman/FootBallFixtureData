package ng.sampleproject.sampleproject.view.home.competitions

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.competition_item.view.*

import java.util.ArrayList

import ng.sampleproject.sampleproject.R
import ng.sampleproject.sampleproject.data.models.Competition
import ng.sampleproject.sampleproject.data.models.CompetitionResponse


class CompetitionAdapter(private val listItemClickListener: ListItemClickListener) : RecyclerView.Adapter<CompetitionAdapter.CompetitionViewHolder>() {

    private var resultList: List<Competition> = ArrayList()

    interface ListItemClickListener {
        fun onListItemClick(leagueId: Int, leagueName: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetitionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.competition_item,
                parent, false)
        return CompetitionViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompetitionViewHolder, position: Int) {
        val result = resultList[position]
        holder.textView.text = result.name
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    fun setItems(results: CompetitionResponse) {
        resultList = results.competitions
        notifyDataSetChanged()
    }

    inner class CompetitionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val textView = itemView.competition_name

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val user = resultList!![adapterPosition]
            val LeagueId = user.id!!
            val LeagueName = user.name
            listItemClickListener.onListItemClick(LeagueId, LeagueName!!)
        }

    }

    }



