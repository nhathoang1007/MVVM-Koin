package com.example.observableresearch.model

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import com.example.observableresearch.R
import com.example.observableresearch.extensions.*
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Nhat Vo on 14/06/2021.
 */

open class Assignment(
    var _id: String = "",
    var title: String = "",
    @SerializedName("total_exercise")
    var exerciseCount: Int = 0,
    var status: Int = 0,
    var isMarked: Boolean = false
) : RealmObject() {

    fun getStatus(timestamp: String?): SpannableStringBuilder {
        val temps = R.string.exercises_size.getString().formatString(exerciseCount)
        val mStatus = Status.getByValue(status)
        return when {
            mStatus == Status.COMPLETED -> {
                SpannableStringBuilder(R.string.completed.getString())
            }
            timestamp.isPast() || mStatus == Status.MISSED -> {
                val missed = R.string.missed.getString()
                val task = " • $temps"
                val span = SpannableStringBuilder(missed.plus(task))
                span.setSpan(
                    ForegroundColorSpan(R.color.color_red.getColor()),
                    0,
                    missed.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                span
            }
            else -> {
                SpannableStringBuilder(temps)
            }
        }
    }
}

enum class Status(val status: Int) {
    TODO(0),
    ASSIGNED(1),
    COMPLETED(2),
    MISSED(3);

    companion object {
        private val values = values()
        fun getByValue(status: Int?) = values.firstOrNull { status == it.status } ?: TODO
    }
}