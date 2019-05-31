package com.fms.fmslider.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide



public class SliderAdapter(val list: MutableList<String>) : androidx.viewpager.widget.PagerAdapter() {


    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(container.context)
        imageView.scaleType = ImageView.ScaleType.FIT_CENTER
        val requestBuilder = Glide.with(container.context).`as`(Drawable::class.java)
/*        requests.with(container.context.applicationContext)
            .load(list[position])
            .into(imageView)*/
        requestBuilder.load(list[position])
            .into(imageView)
        container.addView(imageView)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ImageView)
    }
}