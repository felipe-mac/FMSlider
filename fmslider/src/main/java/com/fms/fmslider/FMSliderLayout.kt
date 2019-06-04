package com.fms.fmslider

import android.content.Context
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
        var fit: Boolean = false,
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


    constructor(context: Context?) : super(context) {
        LayoutInflater.from(context).inflate(R.layout.slider_layout, this, true)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        LayoutInflater.from(context).inflate(R.layout.slider_layout, this, true)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        LayoutInflater.from(context).inflate(R.layout.slider_layout, this, true)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.arrowLeft -> {
                    if ((viewPager.currentItem - 1) >= 0) viewPager.setCurrentItem(
                        viewPager.currentItem - 1, true
                    )
                    if (viewPager.currentItem == 0) arrowLeft.visibility = GONE
                    arrowRight.visibility = View.VISIBLE

                }

                R.id.arrowRight -> {
                    if ((viewPager.currentItem + 1) <= viewPager.adapter!!.count - 1)
                        viewPager.setCurrentItem(viewPager.currentItem + 1, true)
                    if (viewPager.currentItem >= viewPager.adapter!!.count - 1) arrowRight.visibility = GONE
                    arrowLeft.visibility = View.VISIBLE
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

    fun initBanners(sliderList: List<SliderData>, listener:FMClickListener) {

        list.clear()
        list = sliderList as MutableList<SliderData>

        sliderConfigs(listener)
    }

    private fun sliderConfigs(listener : FMClickListener?) {

        arrowLeft.visibility = View.GONE
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

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                arrowLeft.visibility = VISIBLE
                arrowRight.visibility = VISIBLE

                if (position == 0) arrowLeft.visibility = GONE
                if (position >= viewPager.adapter!!.count - 1) arrowRight.visibility = GONE
            }

        })
    }

/*    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if(listener != null && ev?.action == MotionEvent.ACTION_DOWN) {
            println("==> onIntercept: ${viewPager.currentItem}")
            listener!!.viewClickListener(viewPager.currentItem)
        }
        return super.onInterceptTouchEvent(ev)
    }*/

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
        viewPager.removeAllViews()
        viewPager.adapter = null
    }

}