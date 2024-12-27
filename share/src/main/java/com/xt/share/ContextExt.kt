package com.xt.share

import android.content.Context

fun Context.dpToPx(dp: Int, ): Int {
    return (dp * this.resources.displayMetrics.density).toInt()
}
