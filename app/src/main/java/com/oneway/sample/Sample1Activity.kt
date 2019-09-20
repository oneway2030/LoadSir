package com.oneway.sample

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.oneway.loaisir.LoadSir
import com.oneway.loaisir.callback.DefEmptyCallBack
import com.oneway.loaisir.callback.DefHintCallback
import com.oneway.loaisir.callback.DefLoadingCallback
import com.oneway.loaisir.core.LoadService
import kotlinx.android.synthetic.main.activity_main.btn1
import kotlinx.android.synthetic.main.activity_main.btn2
import kotlinx.android.synthetic.main.activity_sample1.*


class Sample1Activity : AppCompatActivity(), View.OnClickListener {
    lateinit var mLoadService: LoadService<Any>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample1)
        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
        btn4.setOnClickListener(this)

        mLoadService = LoadSir.getDefault().register(content) {
            // 重新加载逻辑
            Toast.makeText(this@Sample1Activity, "重新加载逻辑...", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn1 -> {//
                mLoadService.showCallback(DefLoadingCallback::class.java)
            }
            R.id.btn2 -> {//
                mLoadService.showCallback(DefHintCallback::class.java)
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