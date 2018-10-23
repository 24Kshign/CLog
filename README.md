# Log和Toast封装

该库为日志打印和吐司功能封装，用法如下：

### CLog

```
//正常String，有TAG和无TAG
CLog.debug("i am a test")
CLog.d("TAG","i am a test")

//Throwable类
CLog.error(Throwable("i am a error test"))

//json形式
val mList: ArrayList<TestBean> = ArrayList()
for (i in 0 until 10) {
    val date = TestBean(i, i + 20, "张三${i + 1}")
    mList.add(date)
}
CLog.json(Gson().toJson(mList))
```

![](http://ooaap25kv.bkt.clouddn.com/18-10-23/44499708.jpg)


### CToast

```
//先在application中初始化
class App : Application() {
    override fun onCreate() {
        CToast.init(this)
    }
}

//Toast.LENGTH_SHORT
CToast.showToastShort("时间短")

//Toast.LENGTH_LONG
CToast.showToastLong("时间长")
```
