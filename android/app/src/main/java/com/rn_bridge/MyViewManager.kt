package com.rn_bridge

import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewGroupManager

/**
 * Created by kangning on 2018/3/5.
 */
class MyViewManager : ViewGroupManager<ReactView>() {

    override fun createViewInstance(reactContext: ThemedReactContext?): ReactView {
        return ReactView(reactContext)
    }

    override fun getName(): String = "MyView"

}