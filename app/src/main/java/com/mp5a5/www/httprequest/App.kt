package com.mp5a5.www.httprequest

import android.app.Application
import android.util.ArrayMap
import com.mp5a5.www.library.utils.ApiConfig

/**
 * @author ：mp5a5 on 2018/12/28 15：12
 * @describe
 * @email：wwb199055@126.com
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        val baseUrl = "http://op.juhe.cn/"
        val headMap = ArrayMap<String, String>()
        headMap["key1"] = "value1"
        headMap["key2"] = "value2"
        headMap["key3"] = "value3"

        val build = ApiConfig.Builder()
            .setBaseUrl(baseUrl)//BaseUrl，这个地方加入后项目中默认使用该url
            .setInvalidateToken(200)//Token失效码
            .setSucceedCode(0)//成功返回码
            //Token失效后发动态广播的Filter，配合BaseObserver中的标识进行接收使用
            // public static final String TOKEN_INVALID_TAG = "token_invalid";
            // public static final String QUIT_APP = "quit_app";

            //注册Token失效，退出登录的动态广播
            /**
             * private inner class QuitAppReceiver : BroadcastReceiver() {

            override fun onReceive(context: Context?, intent: Intent?) {

            if (ApiConfig.getQuitBroadcastReceiverFilter() == intent?.action) {

            val msg = intent?.getStringExtra(BaseObserver.TOKEN_INVALID_TAG)

            if (!TextUtils.isEmpty(msg)) {
            toast("$msg")
            //Toast.makeText(this@MainActivity,msg,Toast.LENGTH_SHORT).show()
            }
            }
            }
            }
             */
            //onCreate中
            /**
             *   private fun initReceiver() {
            val filter = IntentFilter()
            filter.addAction(ApiConfig.getQuitBroadcastReceiverFilter())
            registerReceiver(quitAppReceiver, filter)
            }
             */
            .setFilter("com.mp5a5.quit.broadcastFilter")
            //.setDefaultTimeout(2000)//响应时间，可以不设置，默认为2000毫秒
            //.setHeads(headMap)//动态添加的header，也可以在其他地方通过ApiConfig.setHeads()设置
            .build()
        build.init(this)

    }
}
