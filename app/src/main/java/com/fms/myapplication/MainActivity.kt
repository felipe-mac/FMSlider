package com.fms.myapplication

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
/*
        val list = mutableListOf<String>()
        list.add("https://static3.tcdn.com.br/img/img_prod/311840/camisa_nike_psg_home_2017_58219_2_20190311155923.jpg")
        list.add("https://static3.tcdn.com.br/img/img_prod/311840/camisa_nike_psg_home_2017_58219_1_20190311155923.jpg")
        list.add("https://static3.tcdn.com.br/img/img_prod/311840/camisa_nike_psg_home_2017_58219_2_20190311155923.jpg")

        fmSlider.initImages(list)*/
    }
}
