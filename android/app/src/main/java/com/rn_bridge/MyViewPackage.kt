package com.rn_bridge

import android.view.View
import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ReactShadowNode
import com.facebook.react.uimanager.ViewManager

/**
 * Created by kangning on 2018/3/5.
 */
class MyViewPackage : ReactPackage {

    override fun createNativeModules(reactContext: ReactApplicationContext?): List<NativeModule> {
        return emptyList()
    }

    override fun createViewManagers(reactContext: ReactApplicationContext?): List<ViewManager<*, *>> {
        return listOf(
                MyViewManager()
        )
    }
}