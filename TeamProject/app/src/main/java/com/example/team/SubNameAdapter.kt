package com.example.myreportaftertravel

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.team.R
import com.example.team.SubwayName

class SubNameAdapter(var items:List<SubwayName>, val clickListener: (SubwayName) -> Unit) : RecyclerView.Adapter<SubNameAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var name : TextView
        var icon : ImageView
        init{
            name = itemView.findViewById(R.id.r_name)
            icon = itemView.findViewById(R.id.listicon)
        }
        fun bind(part: SubwayName, clickListener: (SubwayName) -> Unit) {
            itemView.setOnClickListener { clickListener(part)}
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.route_result_card, p0, false)
        return ViewHolder(v)
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        return items.size
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {    //data, View connection

        p0.name.text = items.get(p1).station_nm
        when(items.get(p1).line_num){
            "01호선"->p0.icon.setImageResource(R.drawable.circle1)
            "02호선"->p0.icon.setImageResource(R.drawable.circle2)
            "03호선"->p0.icon.setImageResource(R.drawable.circle3)
            "04호선"->p0.icon.setImageResource(R.drawable.circle4)
            "05호선"->p0.icon.setImageResource(R.drawable.circle5)
            "06호선"->p0.icon.setImageResource(R.drawable.circle6)
            "07호선"->p0.icon.setImageResource(R.drawable.circle7)
            "08호선"->p0.icon.setImageResource(R.drawable.circle8)
            "09호선"->p0.icon.setImageResource(R.drawable.circle9)
            "분당선"->p0.icon.setImageResource(R.drawable.circlebundang)
            "중앙선"->p0.icon.setImageResource(R.drawable.circlecenter)
            "경춘선"->p0.icon.setImageResource(R.drawable.circlegyungchun)
            "수인선"->p0.icon.setImageResource(R.drawable.circlesuin)
            "인천선"->p0.icon.setImageResource(R.drawable.circleincheon1)
            "인천2호선"->p0.icon.setImageResource(R.drawable.circleincheon2)
            "공항철도"->p0.icon.setImageResource(R.drawable.circleairport)
            "신분당선"->p0.icon.setImageResource(R.drawable.circlesinbundang)
            "용인경전철"->p0.icon.setImageResource(R.drawable.circleyongin)
            "의정부경전철"->p0.icon.setImageResource(R.drawable.circleuijungbu)
            "우이신설경전철"->p0.icon.setImageResource(R.drawable.circlewooi)
            "서해선"->p0.icon.setImageResource(R.drawable.circlewesternsea)
        }
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}