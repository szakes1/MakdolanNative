package com.szakes1.makdolannative.activities

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.szakes1.makdolannative.R
import com.szakes1.makdolannative.adapters.ViewPagerAdapter
import com.szakes1.makdolannative.helpers.AutoUpdater

class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: PagerAdapter
    private lateinit var autoUpdater: AutoUpdater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        if(Build.VERSION.SDK_INT >= 21) {
            autoUpdater = AutoUpdater()
            autoUpdater.act = this
            autoUpdater.checkForPermission()
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode==1) if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) autoUpdater.showDialog()
    }
}
