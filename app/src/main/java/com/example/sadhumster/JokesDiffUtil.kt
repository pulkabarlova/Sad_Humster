package com.example.sadhumster

import androidx.recyclerview.widget.DiffUtil

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
                oldList[oldItemPosition].question == newList[newItemPosition].question
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}