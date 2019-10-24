package com.fms.myapplication

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.content_main.*
import android.util.DisplayMetrics
import com.fms.fmslider.FMSlider
import com.fms.fmslider.FMSliderLayout
import com.fms.fmslider.interfaces.FMClickListener
import kotlin.collections.Map.Entry


class MainActivity : AppCompatActivity(), FMClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
/*        val link = "https://cdn.futfanatics.com.br/futfanatics-nacional/videos-produtos/70184.mp4"
        val uri = Uri.parse(link)
        videoView.setVideoURI(uri)
        videoView.start()
        videoView.setOnCompletionListener {
            println("==> complete")
        }

        videoView.setOnPreparedListener {
            println("==> prepared")
        }*/

        val list = mutableListOf<String>()
        val url5 = "https://cdn.futfanatics.com.br/futfanatics-nacional/videos-produtos/70184.mp4"

        fmSlider.initImages(list)

//        val list = mutableListOf<FMSliderLayout.SliderData>()
        val url1 = "https://static3.tcdn.com.br/img/img_prod/311840/camisa_nike_psg_home_2017_58219_2_20190311155923.jpg"
        val url2 = "https://static3.tcdn.com.br/img/img_prod/311840/camisa_nike_psg_home_2017_58219_1_20190311155923.jpg"
        val url3 = "https://static3.tcdn.com.br/img/img_prod/311840/camisa_nike_psg_home_2017_58219_2_20190311155923.jpg"
        val url4 = "https://static3.tcdn.com.br/img/img_prod/311840/camisa_nike_psg_home_2017_58219_1_20190311155923.jpg"

        list.add(url1)
        list.add(url5)
        list.add(url2)
        list.add(url3)
        list.add(url4)
        fmSlider.initImages(list)
//        list.add(url1)
//        list.add(url2)
//        list.add(url3)
/*
        val sliderData1 = FMSliderLayout.SliderData(url1, "act1")
        val sliderData2 = FMSliderLayout.SliderData(url2, "act2")
        val sliderData3 = FMSliderLayout.SliderData(url3, "act3")
        val sliderData4 = FMSliderLayout.SliderData(url4, "act4")

        list.add(sliderData4)
        list.add(sliderData1)
        list.add(sliderData2)
        list.add(sliderData3)

//        val displayMetrics = DisplayMetrics()
//        windowManager.defaultDisplay.getMetrics(displayMetrics)
//        val height = displayMetrics.heightPixels
//        val width = displayMetrics.widthPixels

        fmSlider.cache = true
        fmSlider.placeholder = false
//        fmSlider.centerCrop = true
//        fmSlider.resize = mapOf(Pair(150,250))

//        fmSlider.initImages(list)
        fmSlider.initBanners(list, this)
        fmSlider.startCycle()

        Handler().postDelayed({ fmSlider.stopCycle()
        fmSlider.clearMemory()}, 40000)*/

    }

    override fun viewClickListener(data: String) {
       // println("==> MA $data")
    }
}
