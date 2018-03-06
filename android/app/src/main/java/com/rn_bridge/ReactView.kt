package com.rn_bridge

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import cn.qiuxiang.react.amap3d.maps.AMapView

/**
 * Created by kangning on 2018/3/5.
 */
class ReactView : FrameLayout {

    private val inflater: LayoutInflater by lazy {
        LayoutInflater.from(context)
    }

    constructor(context: Context?, attributeSet: AttributeSet, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr) {
        init()
    }

    constructor(context: Context?, attributeSet: AttributeSet) : super(context, attributeSet) {
        init()
    }

    constructor(context: Context?) : super(context) {
        init()
    }


    fun init(): Unit {
        Log.d(ReactView::javaClass.name, "ReactView init")
        val rootView = inflater.inflate(R.layout.module_view, null, false) as LinearLayout
        // this.addView(rootView)
        val aMapView = AMapView(context)
        this.addView(aMapView)
    }

}