package com.fms.fmslider.adapter

import android.content.Context
import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.VideoView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.fms.fmslider.FMSliderLayout
import com.fms.fmslider.R
import com.fms.fmslider.interfaces.FMClickListener
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import kotlinx.android.synthetic.main.item_video.view.*

public class SliderAdapter(
    val list: MutableList<FMSliderLayout.SliderData>,
    val configs: FMSliderLayout.Config,
    val listener: FMClickListener?
) : androidx.viewpager.widget.PagerAdapter() {

//    private var vid : VideoView? = null

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int {
        return list.size
    }

    @Suppress("UNREACHABLE_CODE")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val link = list[position].url
        val q = link.split(".")
        if (q.count() > 0) {
            if (q.last().contentEquals("mp4")) {

                println("==> video")

                val inflater = container.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val v = inflater.inflate(R.layout.item_video, container, false)
                v.progress_circular.show()
                val videoView = v.videoView

//                vid = videoView

                container.addView(v)

                val uri = Uri.parse(link)
                videoView.setVideoURI(uri)
                videoView.start()
                videoView.setOnPreparedListener { mp ->
                    mp.isLooping = true
                    v.progress_circular.hide()
                }

                return v
            }
        }

        val imageView = ImageView(container.context)
        imageView.tag = "img"
        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        if (listener != null) imageView.setOnClickListener { listener.viewClickListener(list[position].action) }

//        println("config: $configs")

        val picasso = Picasso.get().load(list[position].url)

        if (configs.fit) {
            picasso.fit()
        }

        if (configs.cache) {
            picasso.memoryPolicy(MemoryPolicy.NO_CACHE).networkPolicy(NetworkPolicy.NO_CACHE)
        }

        if (configs.placeholder) {
            val circularProgressDrawable = CircularProgressDrawable(container.context)
            circularProgressDrawable.strokeWidth = 10f
            circularProgressDrawable.centerRadius = 50f
            circularProgressDrawable.start()
            picasso.placeholder(circularProgressDrawable)
        }



        if (configs.resize != null) {
            val entry = configs.resize!!.entries.firstOrNull()
            if (entry != null) {
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
        val view = `object` as View
        if (view.tag == "img") {
            val imgv = `object` as ImageView?
            imgv?.setImageBitmap(null)
        }
        container.removeView(view)
    }

//    fun playVideo(){
//        vid?.requestFocus()
//        vid?.start()
//    }
//
//    fun stopVideo() {
//        vid?.stopPlayback()
//    }


}