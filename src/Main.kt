interface Animal {
    val weight: Int
    val age: Int
}

interface Dog : Animal {
    val bite: String
}

interface Cat : Animal {
    val activity: String
}

class Husky(override val weight: Int, override val bite: String, override val age: Int) : Dog {}
class Corgi(override val weight: Int, override val bite: String, override val age: Int) : Dog {}
class Scottish(override val weight: Int, override val activity: String, override val age: Int) : Cat {}
class Siamese(override val weight: Int, override val activity: String, override val age: Int) : Cat {}

class Zoo {
    fun catOrDog(breed: Any) {
        when (breed) {
            is Dog -> println("It is a dog")
            is Cat -> println("It is a cat")
        }
    }
}

fun main() {
    val husky = Husky(weight = 10, bite = "Прямой", age = 6)
    val zoo = Zoo()
    zoo.catOrDog(husky)
}