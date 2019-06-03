package com.fms.fmslider

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.viewpager.widget.ViewPager
import com.fms.fmslider.adapter.SliderAdapter
import kotlinx.android.synthetic.main.slider_layout.view.*


class FMSliderLayout : RelativeLayout, View.OnClickListener {

    private var list = mutableListOf<String>()
//    var listener : FMClickListener? = null

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
     * @param height: altura da tela do dispositivo
     * @param width: largura da tela do dispositivo
     * @param factor: fator de redimensionamento da altura (porcentagem da altura da tela). Default = 1
     */
    fun initImages(listImagePath: List<String>, height: Int, width: Int, factor: Float = 1F) {

        arrowLeft.visibility = View.GONE
        arrowRight.visibility = View.VISIBLE

        arrowLeft.setOnClickListener(this)
        arrowRight.setOnClickListener(this)

        list = listImagePath as MutableList<String>

        val adapter = SliderAdapter(list, height, width, factor)
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

    fun clearMemory() {
        viewPager.removeAllViews()
        viewPager.adapter = null
    }
}