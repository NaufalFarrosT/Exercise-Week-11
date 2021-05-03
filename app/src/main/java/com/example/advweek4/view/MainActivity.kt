package com.example.advweek4.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.advweek4.R
import com.example.advweek4.util.createNotificationChannel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    init{
        instance = this
    }

    companion object{
        private var instance:MainActivity?=null

        fun showNotification(title:String, content:String, icon:Int){
            val channelId = "${instance?.packageName}-${instance?.getString(R.string.app_name)}"

            val notificationBuilder= NotificationCompat.Builder(instance!!.applicationContext, channelId).apply {
                setSmallIcon(icon)
                setContentTitle(title)
                setContentText(content)
                setStyle(NotificationCompat.BigTextStyle())
                priority=NotificationCompat.PRIORITY_DEFAULT
                setAutoCancel(true)
            }

            val notificationManager = NotificationManagerCompat.from(instance!!.applicationContext.applicationContext!!)

            notificationManager.notify(1001, notificationBuilder.build())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel(this,NotificationManagerCompat.IMPORTANCE_DEFAULT, false, getString(R.string.app_name),
            "App Notification Channel.")

        /*
        val observable = Observable.just("a stream of data", "hellow", "world")
        val observer = object : Observer<String>{
            override fun onComplete() {
                Log.d("Messages", "complete")
            }

            override fun onSubscribe(d: Disposable?) {
                Log.d("Messages", "Subscribe")
            }

            override fun onNext(t: String?) {
                Log.d("Messages","data: $t")
            }

            override fun onError(e: Throwable?) {
                Log.d("Messages", "error: ${e!!.message.toString()}")
            }

        }

        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)

         */

        /*
        Observable.just("1st emit", "2nd emit", "3rd emit")
                .subscribe(
                        {Log.d("Messages", "data: $it")},
                        {Log.e("Messages","error: ${it.message.toString()}")},
                        {Log.d("Messages","complete")}
                )
         */
    }
}