package com.sejun2.trippath

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree
import timber.log.Timber.Forest.plant


@HiltAndroidApp
class TripPathApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            plant(DebugTree())
        }

        KakaoSdk.init(this, "111966c37ff062241e35d34b3a673095")
    }
}