package com.example.team

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.myreportaftertravel.SubNameAdapter
import com.koushikdutta.ion.Ion
import kotlinx.android.synthetic.main.search_sub.*
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStream

class SubNameSearch:AppCompatActivity() {
    var keySet = mutableListOf<String>()
    var place_info: HashMap<String, SubwayName> = HashMap()
    lateinit var textIntent:List<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_sub)
        textIntent = intent.getStringExtra("name").split("+")
        readFile()
    }
    fun readFile() {
        var strIon = "http://openAPI.seoul.go.kr:8088/764f706b57636f6d313232684d41795a/xml/SearchInfoBySubwayNameService/1/1000"
        Ion.with( this ).load( strIon.trim())
            .asInputStream()
            .setCallback { e, result ->
                if(result!=null) {
                    parsingXML(result)
                }
                else
                    Log.e("result", "result")
                //Toast.makeText(applicationContext, "뉴스를 불러올 수 없습니다.", Toast.LENGTH_SHORT).show()
            }
    }

    fun parsingXML( result: InputStream) {
        var factory = XmlPullParserFactory.newInstance()
        factory.isNamespaceAware=true
        val xpp = factory.newPullParser()
        xpp.setInput(result, "UTF-8")
        var eventType = xpp.eventType

        var isItem = false
        var dataSet = false
        var status = 0
        var i=0
        while (eventType != XmlPullParser.END_DOCUMENT) {
            var station_cd = ""
            var station_nm = ""
            var line_num = ""
            var fr_code = ""
            when(eventType){
                XmlPullParser.START_DOCUMENT->{}

                XmlPullParser.START_TAG -> {
                    val tagname = xpp.name
                    if(isItem){
                        if(tagname.equals("tool"))
                            status = 7
                        if(tagname.equals("STATION_CD")||tagname.equals("STATION_NM")
                            ||tagname.equals("LINE_NUM")||tagname.equals("FR_CODE")) {
                            dataSet = true
                            when (tagname) {
                                "STATION_CD" -> {
                                    status = 1
                                }
                                "STATION_NM" -> {
                                    status = 2
                                }
                                "LINE_NUM" -> {
                                    status = 3
                                }
                                "FR_CODE"->{
                                    status = 4
                                }
                            }
                        }
                    }
                    if (tagname.equals("row")) {
                        isItem = true
                    }
                }

                XmlPullParser.TEXT->{
                    if ( dataSet ) {
                        if ( status == 1 ) {
                            station_cd = xpp.text
                            keySet.add(station_cd)
                            place_info.put( station_cd , SubwayName( station_cd , "" , "" , ""))
                        } else if ( status == 2 ) {
                            station_nm = xpp.text
                            place_info.get( keySet.get(i))!!.station_nm=station_nm
                        } else if ( status == 3 ) {
                            line_num = xpp.text
                            place_info.get( keySet.get(i))!!.line_num=line_num
                        } else if(status == 4) {
                            fr_code = xpp.text
                            place_info.get( keySet.get(i))!!.fr_code=fr_code
                            status = 0
                            i ++
                            isItem = false
                        }else if(status == 7){
                            status = 0
                            isItem = false
                        }
                        dataSet = false
                    }
                }
                XmlPullParser.END_TAG->{
                }
            }
            eventType = xpp.next()
        }
        initLayout()
    }
    fun initLayout(){
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        list_sub.layoutManager = layoutManager
        var infoArr = mutableListOf<SubwayName>()
        var k = 0
        for(i in 0..place_info.size-1){
            val sublist = place_info.get(keySet.get(i))!!
            if(sublist.station_nm.trim().equals(textIntent[1])){
                Log.e("text", textIntent[1])
                infoArr.add(k++, sublist)
            }
        }

        //adapter = MyMarketAdapter(infoArr, this)
        list_sub.adapter = SubNameAdapter(infoArr, { partItem : SubwayName -> partItemClicked(partItem) })
    }
    fun partItemClicked(partItem : SubwayName) {
        val searchIntent = Intent(this, MainActivity::class.java)
        searchIntent.putExtra("subname", textIntent[0]+":"+partItem.line_num+"-"+partItem.station_nm)
        startActivity(searchIntent)
    }
}