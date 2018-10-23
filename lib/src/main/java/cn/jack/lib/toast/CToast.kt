package cn.jack.lib.toast

import android.app.Application
import android.widget.Toast

/**
 * Created by jack on 2018/5/23
 */

class CToast {

    companion object {

        private var oldMsg: String? = null
        private var mToast: Toast? = null
        private var lastTime: Long = 0
        private var nextTime: Long = 0

        private var mApplication: Application? = null

        @JvmStatic
        fun init(application: Application) {
            mApplication = application
        }

        @JvmStatic
        fun showToastShort(s: String?) {
            if (null == mApplication) {
                return
            }
            if (mToast == null) {
                mToast = Toast.makeText(mApplication, s, Toast.LENGTH_SHORT)
                mToast?.show()
                lastTime = System.currentTimeMillis()
            } else {
                nextTime = System.currentTimeMillis()
                if (s == oldMsg) {
                    if (nextTime - lastTime > Toast.LENGTH_SHORT) {
                        mToast!!.show()
                    }
                } else {
                    oldMsg = s
                    mToast?.setText(s)
                    mToast?.show()
                }
            }
            lastTime = nextTime
        }

        @JvmStatic
        fun showToastShort(textRes: Int) {
            showToastShort(mApplication?.resources?.getString(textRes))
        }

        @JvmStatic
        fun showToastLong(s: String?) {
            if (null == mApplication) {
                return
            }
            if (mToast == null) {
                mToast = Toast.makeText(mApplication, s, Toast.LENGTH_LONG)
                mToast?.show()
                lastTime = System.currentTimeMillis()
            } else {
                nextTime = System.currentTimeMillis()
                if (s == oldMsg) {
                    if (nextTime - lastTime > Toast.LENGTH_LONG) {
                        mToast!!.show()
                    }
                } else {
                    oldMsg = s
                    mToast?.setText(s)
                    mToast?.show()
                }
            }
            lastTime = nextTime
        }

        @JvmStatic
        fun showToastLong(textRes: Int) {
            showToastShort(mApplication?.resources?.getString(textRes))
        }
    }
}