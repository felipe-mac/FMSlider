package com.fms.fmslider.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso

public class SliderAdapter(
    val list: MutableList<String>,
    val height: Int,
    val width: Int,
    val factor: Float
) : androidx.viewpager.widget.PagerAdapter() {


    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(container.context)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP

        val params = RelativeLayout.LayoutParams(width, (height*factor).toInt())
        imageView.setPadding(2,6,2,6)
        imageView.layoutParams = params

        imageView.cropToPadding = true

        Picasso.get()
            .load(list[position])
            .resize(width, (height*factor).toInt())
//            .networkPolicy(NetworkPolicy.NO_CACHE)
//            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .into(imageView)
        container.addView(imageView)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ImageView)
    }
}