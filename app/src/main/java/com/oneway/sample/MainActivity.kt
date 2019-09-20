package com.oneway.sample

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn1 -> {//复用
                startActivity(Intent(this, Sample1Activity::class.java))
            }
            R.id.btn2 -> {//新建或者创建副本
                startActivity(Intent(this, Sample2Activity::class.java))
            }
        }
    }
}
