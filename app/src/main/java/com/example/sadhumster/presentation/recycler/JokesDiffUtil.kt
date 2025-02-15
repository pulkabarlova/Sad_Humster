package com.example.sadhumster.presentation.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.sadhumster.domain.model.Joke

class JokesDiffUtil(

    private val oldList: List<Joke>,
    private val newList: List<Joke>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].category == newList[newItemPosition].category &&
                oldList[oldItemPosition].setup == newList[newItemPosition].setup
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}