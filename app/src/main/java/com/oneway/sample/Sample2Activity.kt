package com.oneway.sample

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.oneway.loaisir.LoadSir
import com.oneway.loaisir.callback.Callback
import com.oneway.loaisir.callback.DefEmptyCallBack
import com.oneway.loaisir.core.LoadService
import com.oneway.loaisir.core.Transport
import com.oneway.sample.callback.CustomCallBack
import com.oneway.sample.callback.EmptyCallBack
import kotlinx.android.synthetic.main.activity_main.btn1
import kotlinx.android.synthetic.main.activity_main.btn2
import kotlinx.android.synthetic.main.activity_sample1.*


class Sample2Activity : AppCompatActivity(), View.OnClickListener {
    lateinit var mLoadService: LoadService<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample1)
        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
        btn4.setOnClickListener(this)

        //普通使用方式,复用全局添加的CallBack
        val defLoadSir = LoadSir.getDefault()
        //保留全局设定,并添加一个自定义的loadSir,包含全局初始化中commit后的CallBack 和新添加的CallBack
        val cloneloadSir = LoadSir.getDefault()
            .cloneBuilder()
            .addCallback(EmptyCallBack(R.string.fine_no_data, R.mipmap.status_empty, true))
            .addCallback(CustomCallBack())
            .build()
        //创建一个全新的CallBack,只包含新的CallBack
        val newLoadSir = LoadSir.Builder()
            .addCallback(CustomCallBack())
            .build()
        mLoadService = cloneloadSir.register(content, object : Callback.OnReloadListener {
            override fun onReload(v: View?) {
                // 重新加载逻辑
                Toast.makeText(this@Sample2Activity, "重新加载逻辑...", Toast.LENGTH_SHORT).show()
            }
        })
            .setCallBack(CustomCallBack::class.java, object : Transport {
                override fun order(context: Context, view: View) {
                    val tv = view.findViewById<TextView>(R.id.tv)
                    tv.setText("动态修改Callback的内容")
                }
            })
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn1 -> {//
                mLoadService.showCallback(CustomCallBack::class.java)
            }
            R.id.btn2 -> {//
                mLoadService.showCallback(EmptyCallBack::class.java)
            }
            R.id.btn3 -> {//
                mLoadService.showCallback(DefEmptyCallBack::class.java)
            }
            R.id.btn4 -> {//
                mLoadService.showSuccess()
            }
        }
    }
}