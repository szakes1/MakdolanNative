package com.szakes1.makdolannative.activities

import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.szakes1.makdolannative.R
import com.szakes1.makdolannative.adapters.ViewPagerAdapter
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: PagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Creates dynamic shortcuts
        if (Build.VERSION.SDK_INT >= 25) {
            val shortcutManager = getSystemService<ShortcutManager>(ShortcutManager::class.java)
            val hamburgerShortcut = ShortcutInfo.Builder(applicationContext, "hamburger")
                .setShortLabel("Hamburger")
                .setLongLabel("Wygeneruj kod dla hamburgera")
                .setIcon(Icon.createWithResource(applicationContext, R.drawable.ic_hamburger))
                .setIntent(Intent(applicationContext, GeneratedCouponActivity::class.java).setAction(Intent.ACTION_VIEW).putExtra("coupon_image", R.drawable.coupon_hamburger))
                .build()

            val icecreamShortcut = ShortcutInfo.Builder(applicationContext, "icecream")
                .setShortLabel("Lody")
                .setLongLabel("Wygeneruj kod dla lodów")
                .setIcon(Icon.createWithResource(applicationContext, R.drawable.ic_ice_cream))
                .setIntent(Intent(applicationContext, GeneratedCouponActivity::class.java).setAction(Intent.ACTION_VIEW).putExtra("coupon_image", R.drawable.coupon_icecream))
                .build()

            val cheeseburgerShortcut = ShortcutInfo.Builder(applicationContext, "cheeseburger")
                .setShortLabel("Cheeseburger")
                .setLongLabel("Wygeneruj kod dla lodów")
                .setIcon(Icon.createWithResource(applicationContext, R.drawable.ic_cheeseburger))
                .setIntent(Intent(applicationContext, GeneratedCouponActivity::class.java).setAction(Intent.ACTION_VIEW).putExtra("coupon_image", R.drawable.coupon_cheeseburger))
                .build()

            val friesShortcut = ShortcutInfo.Builder(applicationContext, "fries")
                .setShortLabel("Frytki")
                .setLongLabel("Wygeneruj kod dla lodów")
                .setIcon(Icon.createWithResource(applicationContext, R.drawable.ic_french_fries))
                .setIntent(Intent(applicationContext, GeneratedCouponActivity::class.java).setAction(Intent.ACTION_VIEW).putExtra("coupon_image", R.drawable.coupon_fries))
                .build()

            shortcutManager!!.dynamicShortcuts = Arrays.asList(hamburgerShortcut, icecreamShortcut, cheeseburgerShortcut, friesShortcut)
        }



        // Sets color for Navigation and Status Bar
        if (Build.VERSION.SDK_INT >= 21) {
            val color = ContextCompat.getColor(applicationContext, R.color.colorPrimaryDark)

            window.statusBarColor = color
            window.navigationBarColor = color
        }

        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        pagerAdapter = ViewPagerAdapter(supportFragmentManager, 5)

        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager, true)
    }
}
