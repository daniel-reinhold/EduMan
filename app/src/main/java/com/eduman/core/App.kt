package com.eduman.core

import android.os.Build
import com.eduman.BuildConfig


class App {

    companion object {

        const val IS_DEBUG = BuildConfig.BUILD_TYPE == "debug"
        const val IS_RELEASE = BuildConfig.BUILD_TYPE == "release"

    }

}