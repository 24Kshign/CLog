package com.jack.clog

import android.app.Application

import cn.jack.lib.toast.CToast

/**
 * Created by manji
 * Date：2018/10/23 上午9:51
 * Desc：
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        CToast.init(this)
    }

    companion object {

        private var mInstance: App? = null

        val instance: Application?
            get() = mInstance
    }
}
