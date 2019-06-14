package com.fms.fmslider.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.fms.fmslider.FMSliderLayout
import com.fms.fmslider.interfaces.FMClickListener
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator

public class SliderAdapter(
    val list: MutableList<FMSliderLayout.SliderData>,
    val configs: FMSliderLayout.Config,
    val listener: FMClickListener?
) : androidx.viewpager.widget.PagerAdapter() {


    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(container.context)
        imageView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        if(listener != null) imageView.setOnClickListener { listener.viewClickListener(list[position].action) }

//        println("config: $configs")

        val picasso = Picasso.get().load(list[position].url)

        if(configs.fit) {
            picasso.fit()
        }

        if(configs.cache) {
            picasso.memoryPolicy(MemoryPolicy.NO_CACHE).networkPolicy(NetworkPolicy.NO_CACHE)
        }

        if(configs.placeholder) {
            val circularProgressDrawable = CircularProgressDrawable(container.context)
            circularProgressDrawable.strokeWidth = 10f
            circularProgressDrawable.centerRadius = 50f
            circularProgressDrawable.start()
            picasso.placeholder(circularProgressDrawable)
        }



        if(configs.resize != null) {
            val entry = configs.resize!!.entries.firstOrNull()
            if(entry != null) {
                picasso.centerCrop()
                picasso.resize(entry.key, entry.value)
                imageView.layoutParams = ViewGroup.LayoutParams(entry.key, entry.value)
            }
        }

        picasso.into(imageView)

        /*Picasso.get()
            .load(list[position])
            .placeholder(circularProgressDrawable)
            .networkPolicy(NetworkPolicy.NO_CACHE)
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .into(imageView)*/

        container.addView(imageView)

        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        println("==> destroyItem: $position")
        val imgv = `object` as ImageView?
        imgv?.setImageBitmap(null)
        container.removeView(imgv)
    }
}