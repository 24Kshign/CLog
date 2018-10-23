package cn.jack.lib.log

import android.util.Log
import cn.jack.lib.BuildConfig
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


/**
 * Created by manji
 * Date：2018/10/18 下午3:52
 * Desc：
 */
object CLog {

    ////////////////////////////////////////////有tag///////////////////////////////////////////////

    fun v(tag: String, msg: String?) {
        logImpl(Log.VERBOSE, tag, msg, null)
    }

    fun d(tag: String, msg: String?) {
        logImpl(Log.DEBUG, tag, msg, null)
    }

    fun i(tag: String, msg: String?) {
        logImpl(Log.INFO, tag, msg, null)
    }

    fun w(tag: String, msg: String?) {
        logImpl(Log.WARN, tag, msg, null)
    }

    fun e(tag: String, msg: String?) {
        logImpl(Log.ERROR, tag, msg, null)
    }

    fun a(tag: String, msg: String?) {
        logImpl(Log.ASSERT, tag, msg, null)
    }

    /****************************************tag+throwable*****************************************/

    fun v(tag: String, tr: Throwable?) {
        logImpl(Log.VERBOSE, tag, "", tr)
    }


    fun d(tag: String, tr: Throwable?) {
        logImpl(Log.DEBUG, tag, "", tr)
    }

    fun i(tag: String, tr: Throwable?) {
        logImpl(Log.INFO, tag, "", tr)
    }

    fun w(tag: String, tr: Throwable?) {
        logImpl(Log.WARN, tag, "", tr)
    }

    fun e(tag: String, tr: Throwable?) {
        logImpl(Log.ERROR, tag, "", tr)
    }

    fun a(tag: String, tr: Throwable?) {
        logImpl(Log.ASSERT, tag, "", tr)
    }


    ////////////////////////////////////////////无tag///////////////////////////////////////////////

    fun verbose(msg: String?) {
        logImpl(Log.VERBOSE, "", msg, null)
    }

    fun debug(msg: String?) {
        logImpl(Log.DEBUG, "", msg, null)
    }

    fun info(msg: String?) {
        logImpl(Log.INFO, "", msg, null)
    }

    fun warn(msg: String?) {
        logImpl(Log.WARN, "", msg, null)
    }

    fun error(msg: String?) {
        logImpl(Log.ERROR, "", msg, null)
    }

    fun assert(msg: String?) {
        logImpl(Log.ASSERT, "", msg, null)
    }

    /******************************************throwable*******************************************/

    fun verbose(tr: Throwable?) {
        logImpl(Log.VERBOSE, "", "", tr)
    }

    fun debug(tr: Throwable?) {
        logImpl(Log.DEBUG, "", "", tr)
    }

    fun info(tr: Throwable?) {
        logImpl(Log.INFO, "", "", tr)
    }

    fun warn(tr: Throwable?) {
        logImpl(Log.WARN, "", "", tr)
    }

    fun error(tr: Throwable?) {
        logImpl(Log.ERROR, "", "", tr)
    }

    fun assert(tr: Throwable?) {
        logImpl(Log.ASSERT, "", "", tr)
    }


    //////////////////////////////////////////////json//////////////////////////////////////////////

    fun json(json: String?) {
        json("", json)
    }

    fun json(tag: String, json: String?) {
        logJson(Log.ERROR, tag, json)
    }


    ////////////////////////////////////////////最终实现/////////////////////////////////////////////

    private fun logJson(logLever: Int, tag: String, json: String?) {
        if (json.isEmpty()) {
            return
        }
        val logTag = getFormatTag(tag)
        val logMsg = getPrettyJson(json)

        logImpl(logLever, logTag, logMsg, null)
    }

    private const val JSON_INDENT: Int = 2

    private fun getPrettyJson(json: String?): String {
        var jsonStr = json
        jsonStr?.let { it ->
            try {
                jsonStr = it.trim { it <= ' ' }
                if (it.startsWith("{")) {
                    val jsonObject = JSONObject(it)
                    return jsonObject.toString(JSON_INDENT)
                }
                if (it.startsWith("[")) {
                    val jsonArray = JSONArray(it)
                    return jsonArray.toString(JSON_INDENT)
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        return "Invalid Json, Please Check: $jsonStr"
    }

    private fun logImpl(logLever: Int, tag: String?, msg: String?, th: Throwable?) {
        if (!isLogLevelEnable(logLever)) {
            return
        }

        val logTag = getFormatTag(tag)
        var logMsg = ""

        //配置log的样式
        val stackTraceElement = getTargetStackTraceElement()
        logMsg = th?.message?.string() ?: msg.string()
        logMsg = if (!logMsg.startsWith("[") && !logMsg.startsWith("{")) {
            "(" + stackTraceElement?.fileName + ":" + stackTraceElement?.lineNumber + ") -> " + logMsg
        } else {
            "(" + stackTraceElement?.fileName + ":" + stackTraceElement?.lineNumber + ") ->\n" + logMsg
        }
        Log.println(logLever, logTag, logMsg)
    }

    private fun getFormatTag(tag: String?): String {
        val stackTraceElement = getTargetStackTraceElement()
        return if (null == stackTraceElement) {
            if (tag.isEmpty()) {
                "TAG"
            } else {
                tag.string()
            }
        } else {
            if (tag.isEmpty()) {
                stackTraceElement.className
            } else {
                stackTraceElement.className + "——" + tag
            }
        }
    }

    private fun isLogLevelEnable(logLevel: Int): Boolean {
        //这里可以根据日志等级做一些限定
        return BuildConfig.DEBUG
    }

    private fun getTargetStackTraceElement(): StackTraceElement? {
        var targetStackTrace: StackTraceElement? = null
        var shouldTrace = false
        val stackTrace = Thread.currentThread().stackTrace

        for (stackTraceElement: StackTraceElement in stackTrace) {
            val isLogMethod = stackTraceElement.className == CLog::class.java.name
            //只有非本类名下的类才有效（非本库下的文件）
            if (shouldTrace && !isLogMethod) {
                targetStackTrace = stackTraceElement
                break
            }
            shouldTrace = isLogMethod
        }

        return targetStackTrace
    }

    private fun String?.isEmpty() = this == null || length == 0

    private fun Any?.string(): String = toString()
}