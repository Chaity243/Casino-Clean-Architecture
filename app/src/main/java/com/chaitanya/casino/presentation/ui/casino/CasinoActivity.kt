package com.chaitanya.casino.presentation.ui.casino

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chaitanya.casino.R
import com.chaitanya.casino.spinningwheel.RouletteView
import kotlinx.android.synthetic.main.activity_casino.*
import java.util.*

class CasinoActivity : AppCompatActivity(), RouletteView.OnRotationListener<String> {

    var rand = Random()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_casino)

        initWheelView()
        initListeners()

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
        Log.d("CasinoActivity", "On Rotation")
    }

    override fun onStopRotation(item: String?) {
        Toast.makeText(this, "You win $item", Toast.LENGTH_LONG).show()
    }
}

