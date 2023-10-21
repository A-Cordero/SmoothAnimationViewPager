package com.aridev.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import com.aridev.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val layouts = listOf(
        R.layout.landed_content,
        R.layout.signup_content)

    private val viewPagerAdapter = ViewPagerAdapter(layouts)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setViewPager()
    }

    private fun setViewPager() {
        binding.vpContent.adapter = viewPagerAdapter
    }


    /**
     * Converts a pixel value to a density-independent pixel (dp) value.
     * @return The value in dp.
     */
    private fun Float.getDpValue() : Float {
        val resources = resources
        val metrics = resources.displayMetrics
        return this*((metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT))
    }
}

