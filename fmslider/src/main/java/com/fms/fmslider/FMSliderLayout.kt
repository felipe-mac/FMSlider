package com.fms.fmslider

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.viewpager.widget.ViewPager
import com.fms.fmslider.adapter.SliderAdapter
import com.fms.fmslider.interfaces.FMClickListener
import com.squareup.picasso.RequestCreator
import kotlinx.android.synthetic.main.slider_layout.view.*
import java.util.*


class FMSliderLayout : RelativeLayout, View.OnClickListener {

    data class Config(
        var placeholder: Boolean = false,
        var cache: Boolean = false,
        var centerCrop: Boolean = false,
        var fit: Boolean = true,
        var resize: Map<Int, Int>? = null
    )

    data class SliderData(
        var url: String,
        var action: String
    )

    private var list = mutableListOf<SliderData>()

    var placeholder: Boolean = false
    var cache: Boolean = false
    var centerCrop: Boolean = false
    var fit: Boolean = false
    var resize: Map<Int, Int>? = null

    private lateinit var mHandler: Handler
    private lateinit var runnable: Runnable

    var timeCycle: Long = 4000

    var factor = 1
    private var hasCycleStarted = false


    constructor(context: Context?) : super(context) {
        LayoutInflater.from(context).inflate(R.layout.slider_layout, this, true)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        LayoutInflater.from(context).inflate(R.layout.slider_layout, this, true)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

        LayoutInflater.from(context).inflate(R.layout.slider_layout, this, true)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.arrowLeft -> {
                    try {
                        if ((viewPager.currentItem - 1) >= 0) viewPager.setCurrentItem(
                            viewPager.currentItem - 1, true
                        )
                        else {
                            viewPager.setCurrentItem(viewPager.adapter!!.count - 1, false)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                }

                R.id.arrowRight -> {
                    try {
                        if ((viewPager.currentItem + 1) <= viewPager.adapter!!.count - 1)
                            viewPager.setCurrentItem(viewPager.currentItem + 1, true)
                        else {
                            viewPager.setCurrentItem(0, true)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    /**
     * Esse método inicializa o carregamento das imagens a partir de uma lista de URLs.
     *
     * @param listImagePath: lista de URLS a serem carregadas.
     */
    fun initImages(listImagePath: List<String>) {

        list.clear()

        listImagePath.forEach {
            list.add(SliderData(it, ""))
        }

        sliderConfigs(null)
    }

    fun initBanners(sliderList: List<SliderData>, listener: FMClickListener) {

        list.clear()
        list = sliderList as MutableList<SliderData>

        sliderConfigs(listener)
    }

    private fun sliderConfigs(listener: FMClickListener?) {

        arrowLeft.visibility = View.VISIBLE
        arrowRight.visibility = View.VISIBLE

        arrowLeft.setOnClickListener(this)
        arrowRight.setOnClickListener(this)

        val configs = Config()
        configs.cache = cache
        configs.centerCrop = centerCrop
        configs.fit = fit
        configs.placeholder = placeholder
        configs.resize = resize

        val adapter = SliderAdapter(list, configs, listener)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 4
        /*viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                println("==> position: $position")
                if (position == 1) {
                    adapter.playVideo()
                }
                else{
                    adapter.stopVideo()
                }
            }
        })*/

    }

    fun startCycle() {
        hasCycleStarted = true
        mHandler = Handler()
        runnable = Runnable {
            arrowRight.performClick()
            mHandler.postDelayed(runnable, timeCycle)
        }
        mHandler.postDelayed(runnable, timeCycle)
    }

    fun stopCycle() {
        try {
            if (hasCycleStarted)
                mHandler.removeCallbacks(runnable)
        } catch (e: UninitializedPropertyAccessException) {
            // e.printStackTrace()
        }
    }


    fun getCurrentPage(): Int {
        return viewPager.currentItem
    }

    fun getUrlFromCurrentImage(): String {
        try {
            return list[viewPager.currentItem].url
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }
    }

    fun clearMemory() {
        list.clear()
        viewPager.removeAllViews()
        stopCycle()
        viewPager.adapter = null
    }

}