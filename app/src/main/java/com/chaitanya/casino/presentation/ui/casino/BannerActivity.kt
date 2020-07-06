package com.chaitanya.casino.presentation.ui.casino

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chaitanya.casino.R
import com.facebook.ads.AdSize
import com.facebook.ads.AdView
import kotlinx.android.synthetic.main.activity_banner.*


class BannerActivity : AppCompatActivity() {

    private lateinit var adView :AdView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner)

        initBanner()
    }

    private fun initBanner() {
        // Instantiate an AdView object.
        // NOTE: The placement ID from the Facebook Monetization Manager identifies your App.
        // To get test ads, add IMG_16_9_APP_INSTALL# to your placement id. Remove this when your app is ready to serve real ads.
        adView = AdView(this, "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID", AdSize.BANNER_HEIGHT_50)

        // Add the ad view to your activity layout
        banner_container.addView(adView)
        // Request an ad
        adView.loadAd()
    }


    override fun onDestroy() {
        adView.destroy()
        super.onDestroy()
    }
}