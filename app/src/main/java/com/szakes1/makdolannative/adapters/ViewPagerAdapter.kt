package com.szakes1.makdolannative.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.szakes1.makdolannative.R
import com.szakes1.makdolannative.fragments.CouponFragment
import com.szakes1.makdolannative.fragments.HomeFragment

class ViewPagerAdapter(fm: FragmentManager, private val numberOfFrags: Int) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return HomeFragment()
            1 -> return CouponFragment(R.drawable.coupon_hamburger)
            2 -> return CouponFragment(R.drawable.coupon_icecream)
            3 -> return CouponFragment(R.drawable.coupon_cheeseburger)
            4 -> return CouponFragment(R.drawable.coupon_fries)
        }
        return null
    }

    override fun getCount(): Int {
        return numberOfFrags
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Jak używać?"
            1 -> return "Hamburger"
            2 -> return "Lody"
            3 -> return "Cheeseburger"
            4 -> return "Małe frytki"
        }
        return super.getPageTitle(position)
    }
}