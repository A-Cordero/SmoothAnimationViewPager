package com.aridev.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.aridev.myapplication.databinding.ActivityMainBinding

// Hello, to start building this amazing animation, we will download the base project,
// which i will leave the link in the video description ^^
class MainActivity : AppCompatActivity() {

    // If you liked the video, it would greatly help me if you subscribed and left a like.
    // Thank you ^^
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    // We will have the following views that will be displayed initially.
    // landed_content and signup_content
    private val layouts = listOf(
        R.layout.landed_content,
        R.layout.signup_content)

    private val viewPagerAdapter = ViewPagerAdapter(layouts)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setViewPager()

        // No, with the project already open, we will begin
        // by listening to the changes that occur when moving from on view to another
        setViewPagerCallback()

        // now we take click action
        setActionListener()
    }

    private fun setActionListener() {
        binding.btnAction.setOnClickListener {
            when(binding.vpContent.currentItem) {
                0 -> binding.vpContent.currentItem = 1
            }
        }
    }

    private fun setViewPagerCallback() {
        binding.vpContent.offscreenPageLimit = 2
        binding.vpContent.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                val progress = position + positionOffset
                // The value of progress will tell us how much movement has been mada
                // when switching from on view to another
                Log.e("pageProgress", progress.toString())

                // Now, as we want to increase the height of our view when accessing the signup page,
                //we will use the progress value for this.
                binding.containerContent.apply {
                    val layoutParams = this.layoutParams
                    layoutParams.height = (350f.getDpValue() + progress * 140f.getDpValue()).toInt()
                    this.layoutParams = layoutParams
                }

                //Now, we also wat our button to move upward in relation to the viewpager scrolling
                binding.btnAction.apply {
                    var layoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
                    layoutParams.bottomMargin =
                        (24f.getDpValue() + progress * 140f.getDpValue()).toInt()
                    this.layoutParams = layoutParams
                }

                // Now, to achieve the text change effect, we will use the alpha property
                // of the text and increase its width using the same technique as before

                binding.textSignup.alpha = progress
                binding.textStarted.alpha = 1 - progress

                binding.btnAction.apply {
                    val layoutParams = this.layoutParams
                    layoutParams.width = (160f.getDpValue() + progress * 32f.getDpValue()).toInt()
                    this.layoutParams = layoutParams
                    this.requestLayout()
                }
            }
        })
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

