package cn.qiuxiang.react.amap3d.location.service

import android.database.Cursor
import android.util.Log
import cn.qiuxiang.react.amap3d.location.CommonLocation
import cn.qiuxiang.react.amap3d.location.db.DBConfig
import cn.qiuxiang.react.amap3d.location.db.DataBaseOpenHelper
import cn.qiuxiang.react.amap3d.location.db.DataBaseOperateToken
import cn.qiuxiang.react.amap3d.location.db.IQueryCallback
import cn.qiuxiang.react.amap3d.location.utils.DateUtil
import cn.qiuxiang.react.amap3d.location.websocket.WsManager
import com.orhanobut.logger.Logger
import java.util.*

class UploadThread : Thread {

    companion object {
        var thread = UploadThread()
        var lock = java.lang.Object()
            private set
        var status = 0
    }

    private val cache: LocCache = LocCache.cache

    private constructor() {
        //todo query数据库
    }

    var time = System.currentTimeMillis()
    private val WAIT_TIMEOUT: Long = 2000

    override fun run() {
        while (true) {
            //Logger.t("轨迹上传").d("${cache.length()}+cache长度")
            while (!cache.isEmpty()) {
                var time = System.currentTimeMillis()
                val upload = cache.pollElement()
                if (upload != null) {
                    synchronized(UploadThread.lock) {
                        sendData(upload)
                        UploadThread.lock.wait(WAIT_TIMEOUT)
                        if (UploadThread.status == 1) {
                            //Logger.t("轨迹上传2").d("${upload.id}上传完毕\n+timestamp:${System.currentTimeMillis() - time}\n")
                            Log.d("UploadThread", "${cache.removeElement()?.id}的点已被移除")
                        } else {
                            Log.d("UploadThread", "${upload.id}上传失败\n+timestamp:${System.currentTimeMillis() - time}\n")
                            WsManager.getInstance().reconnect()
                        }
                    }
                }
            }
            Thread.sleep(100)
            // print("我在等待~\n")
        }
    }


    private fun sendData(loc: CommonLocation) {
        UploadThread.status = 0
        val dict = HashMap<String, Any>()
        dict["id"] = loc.id
        //aLogger.t("轨迹上传").d("发送Id${loc.id}")
        dict["platform"] = "android"
        dict["lat"] = loc.latitude
        dict["lng"] = loc.longitude
        dict["speed"] = loc.speed
        dict["accuracy"] = loc.accuracy
        dict["timestamp"] = loc.time
        WsManager.getInstance().send(dict)
    }


}