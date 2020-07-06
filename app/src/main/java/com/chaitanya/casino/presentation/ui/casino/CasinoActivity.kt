package com.chaitanya.casino.presentation.ui.casino

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chaitanya.casino.R
import com.chaitanya.casino.spinningwheel.RouletteView
import com.facebook.ads.*
import kotlinx.android.synthetic.main.activity_casino.*
import timber.log.Timber
import java.util.*

@Suppress("DEPRECATION")
class CasinoActivity : AppCompatActivity(), RouletteView.OnRotationListener<String> {

    var rand = Random()
    private lateinit var adView: AdView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_casino)

        initWheelView()
        initListeners()
        initBanner()

    }

    private fun initBanner() {
        // Instantiate an AdView object.
        // NOTE: The placement ID from the Facebook Monetization Manager identifies your App.
        // To get test ads, add IMG_16_9_APP_INSTALL# to your placement id. Remove this when your app is ready to serve real ads.
        adView = AdView(this, "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID", AdSize.BANNER_HEIGHT_50)
        adView.setAdListener(adlistener)

        // Add the ad view to your activity layout
        ll_top_container.addView(adView)
        // Request an ad
        adView.loadAd()
    }


    private fun initWheelView() {
        wheel.setItems(R.array.wheelItems)

    }

    private fun initListeners() {

        wheel.onRotationListener = this

        rotate.setOnClickListener {
            // max angle 50
            // duration 10 second
            // every 50 ms rander rotation
            val maxAngle: Float = rand.nextInt(180).toFloat()
            val duration: Long = rand.nextInt(3000).toLong()
            val interval: Long = rand.nextInt(50).toLong()

            /*     wheelView.rotate(90, 3000, 50);*/
            /*     wheelView.rotate(90, 3000, 50);*/

            wheel.rotate(maxAngle, duration, interval)
        }

    }

    override fun onRotation() {
        Timber.tag("CasinoActivity").d("On Rotation")
    }

    override fun onStopRotation(item: String?) {
        Toast.makeText(this, "You win $item", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        /*Releasing adview resources*/
        adView.destroy()
        super.onDestroy()
    }

    private var adlistener = object : AdListener {
        override fun onAdClicked(ad: Ad?) {
            Toast.makeText(
                this@CasinoActivity, "onAdClicked: " + ad.toString(),
                Toast.LENGTH_LONG
            ).show()
        }

        override fun onError(ad: Ad?, adError: AdError?) {
            // Ad error callback
            Toast.makeText(
                this@CasinoActivity, "Error: " + adError?.errorMessage,
                Toast.LENGTH_LONG
            ).show()
        }

        override fun onAdLoaded(ad: Ad?) {
            Toast.makeText(
                this@CasinoActivity, "onAdLoaded: " + ad.toString(),
                Toast.LENGTH_LONG
            ).show()
        }

        override fun onLoggingImpression(ad: Ad?) {
            Toast.makeText(
                this@CasinoActivity, "onLoggingImpression: " + ad.toString(),
                Toast.LENGTH_LONG
            ).show()
        }

    }
}

