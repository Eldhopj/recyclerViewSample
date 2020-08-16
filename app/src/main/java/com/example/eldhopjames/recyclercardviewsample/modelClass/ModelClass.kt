package com.example.eldhopjames.recyclercardviewsample.modelClass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**NOTE: always assign values when using Parcelable*/
@Parcelize
data class ModelClass(
    val head: String? = null,
    val desc: String? = null,
    val type: Int = 0,
    val primaryKey: Int = 0
) : Parcelable