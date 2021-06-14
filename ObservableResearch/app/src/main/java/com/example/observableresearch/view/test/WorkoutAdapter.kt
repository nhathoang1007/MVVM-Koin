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
class WorkoutAdapter(private val onMarkChanged: (Data) -> Unit) : BaseBindingAdapter<ViewDailyWorkoutBinding, Data>() {
    override fun getLayoutId(viewType: Int): Int = R.layout.view_daily_workout

    override fun bindViewHolder(dataBinding: ViewDailyWorkoutBinding, position: Int) {
        val item = list[position]
        dataBinding.apply {
            data = item
            viewGroup.removeAllViews()
            item.assignments.forEach { assignment ->
                viewGroup.addView(
                    CustomWorkoutViewBinding.inflate(LayoutInflater.from(root.context)).apply {
                        this.root.layoutParams = ConstraintLayout.LayoutParams(
                            ConstraintLayout.LayoutParams.MATCH_PARENT,
                            ConstraintLayout.LayoutParams.WRAP_CONTENT,
                        ).apply {
                            setMargins(0.dp, 8.dp, 0.dp, 0.dp)
                        }
                        this.data = assignment
                        this.timestamp = item.timestamp
                        setOnItemClicked {
                            assignment.isMarked = !assignment.isMarked
                            notifyItemChanged(position)
                            onMarkChanged.invoke(item)
                        }
                    }.root
                )
            }
            executePendingBindings()
        }
    }
}