package com.szakes1.makdolannative.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.szakes1.makdolannative.R
import com.szakes1.makdolannative.helpers.Makdolan
import kotlinx.android.synthetic.main.activity_generated_coupon_email.*
import net.danlew.android.joda.JodaTimeAndroid

class GeneratedCouponEmailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generated_coupon_email)
        JodaTimeAndroid.init(this)

        val mk = Makdolan()

        release_date_generated_TV.text = mk.calculateDate()
        unique_code_generated_TV.text = mk.calculateUniqueCode()

        val couponImage = intent.getIntExtra("coupon_image", 0)
        generated_coupon_IMGV.setImageDrawable(ContextCompat.getDrawable(applicationContext, couponImage))
    }
}
