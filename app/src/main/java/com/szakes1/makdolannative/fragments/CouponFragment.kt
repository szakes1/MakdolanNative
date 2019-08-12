package com.szakes1.makdolannative.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat

import com.szakes1.makdolannative.R
import com.szakes1.makdolannative.activities.GeneratedCouponActivity
import com.szakes1.makdolannative.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_coupon.view.*


class CouponFragment(private val coupon_image: Int) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewOfLayout = inflater.inflate(R.layout.fragment_coupon, container, false)
        val couponImage = viewOfLayout.coupon_IMGV

        couponImage.setImageDrawable(ContextCompat.getDrawable(activity!!.applicationContext, coupon_image))

        couponImage.setOnClickListener {
            val intent = Intent(activity!!.applicationContext, GeneratedCouponActivity::class.java)
            intent.putExtra("coupon_image", coupon_image)

            startActivity(intent)
        }
        return viewOfLayout
    }
}
