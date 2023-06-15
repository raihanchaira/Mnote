package com.example.mnotes.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/** Raihan Chaira on 5/27/2023
 * raihanchaira21@gmail.com
 */
@Parcelize
data class Faq(
    val tittle : String,
    val field : String
) : Parcelable