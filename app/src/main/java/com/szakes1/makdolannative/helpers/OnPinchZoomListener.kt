package com.szakes1.makdolannative.helpers

import android.view.ScaleGestureDetector
import android.view.View

class OnPinchZoomListener(private var mScaleFactor: Float, private var zoomableView: View) : ScaleGestureDetector.SimpleOnScaleGestureListener() {
    override fun onScale(detector: ScaleGestureDetector?): Boolean {
        mScaleFactor *= detector!!.scaleFactor
        mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f))
        zoomableView.scaleX = mScaleFactor
        zoomableView.scaleY = mScaleFactor

        return true
    }
}