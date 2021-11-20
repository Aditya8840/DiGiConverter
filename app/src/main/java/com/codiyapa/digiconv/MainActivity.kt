package com.codiyapa.digiconv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codiyapa.digiconv.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.container, HomeFragment()).commit()
    }
}