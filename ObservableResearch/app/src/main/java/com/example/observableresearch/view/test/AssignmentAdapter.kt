package com.example.observableresearch.view.test

import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.observableresearch.R
import com.example.observableresearch.base.adapter.BaseBindingAdapter
import com.example.observableresearch.databinding.CustomWorkoutViewBinding
import com.example.observableresearch.databinding.ViewDailyWorkoutBinding
import com.example.observableresearch.extensions.dp
import com.example.observableresearch.model.Assignment
import com.example.observableresearch.model.Data

/**
 * Created by Nhat Vo on 14/06/2021.
 */
class AssignmentAdapter(private val item: Data, private val onMarkChanged: (Data) -> Unit) :
    BaseBindingAdapter<CustomWorkoutViewBinding, Assignment>() {
    override fun getLayoutId(viewType: Int): Int = R.layout.custom_workout_view

    override fun bindViewHolder(dataBinding: CustomWorkoutViewBinding, position: Int) {
        val assignment = list[position]
        dataBinding.apply {
            this.root.layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ).apply {
                setMargins(0.dp, if (position != 0) 8.dp else 0.dp, 0.dp, 0.dp)
            }
            this.data = assignment
            this.status = assignment.getStatus(item.timestamp)
            setOnItemClicked {
                assignment.isCompletedMarked = !assignment.isCompletedMarked
                notifyItemChanged(position)
                onMarkChanged.invoke(item)
            }
            executePendingBindings()
        }
    }
}