package com.jack.clog

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import cn.jack.lib.log.CLog
import cn.jack.lib.toast.CToast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mList: ArrayList<TestBean> = ArrayList()

        for (i in 0 until 10) {
            val date = TestBean(i, i + 20, "张三${i + 1}")
            mList.add(date)
        }

        CLog.debug("i am a test")
        CLog.error("i am a test")
        CLog.info("i am a test")
        CLog.error(Throwable("i am a error test"))
        CLog.assert("i am a test")
        CLog.verbose("i am a test")
        CLog.json("TAG", Gson().toJson(mList))

        amTvShort.setOnClickListener {
            CToast.showToastShort("时间短")
        }

        amTvLong.setOnClickListener {
            CToast.showToastLong("时间长")
        }

    }
}
