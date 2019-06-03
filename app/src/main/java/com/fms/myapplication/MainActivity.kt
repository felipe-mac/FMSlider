package com.fms.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.content_main.*
import android.util.DisplayMetrics



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
/*        val list = mutableListOf<String>()
        list.add("https://static3.tcdn.com.br/img/img_prod/311840/camisa_nike_psg_home_2017_58219_2_20190311155923.jpg")
        list.add("https://static3.tcdn.com.br/img/img_prod/311840/camisa_nike_psg_home_2017_58219_1_20190311155923.jpg")
        list.add("https://static3.tcdn.com.br/img/img_prod/311840/camisa_nike_psg_home_2017_58219_2_20190311155923.jpg")

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels

        fmSlider.initImages(list, height, width, 0.70F)*/

    }
}
