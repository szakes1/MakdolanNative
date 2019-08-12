package com.szakes1.makdolannative.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.ablanco.zoomy.Zoomy
import com.szakes1.makdolannative.R
import com.szakes1.makdolannative.helpers.Makdolan
import kotlinx.android.synthetic.main.activity_generated_coupon.*
import net.danlew.android.joda.JodaTimeAndroid
import android.view.View

class GeneratedCouponActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generated_coupon)
        JodaTimeAndroid.init(this)

        val mk = Makdolan()

        release_date_TV.text = mk.calculateDate()
        unique_code_TV.text = mk.calculateUniqueCode()

        val couponImage = intent.getIntExtra("coupon_image", 0)
        generated_coupon_IMGV.setImageDrawable(ContextCompat.getDrawable(applicationContext, couponImage))

        //Enables immersive mode
        layout.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                )
    }

    //Enables immersive mode if activity has focus
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) layout.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                )
    }
}
