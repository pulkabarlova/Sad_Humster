package com.example.sadhumster

object JokesData {
    fun getJokesList(): List<Joke> {
        val jokesList = listOf(
            Joke("Joke #1", "What has a face and two hands but no arms or legs?", "a clock"),
            Joke("Joke #2", "What does a house wear?", "ad-dress."),
            Joke("Joke #3", "What kind of murderer is full of fiber?", "a cereal killer"),
            Joke("Joke #4", "How does a bee get to school?", "on a buzz!"),
            Joke("Joke #5", "What kind of running leads to walking?", "running out of gas!"),
            Joke(
                "Joke #6",
                "I Start with M, end with X, and have a never-ending amount of letters. What am I?",
                "a mailbox"
            ),
            Joke("Joke #7", "What is orange and sounds like a parrot?", "a carrot.")
        )
        return jokesList
    }
}