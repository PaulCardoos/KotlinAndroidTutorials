import kotlin.Comparable

class Person(var name : String, var age : Int): Comparable<Person> {

    override fun compareTo(other: Person): Int {
        if (this.name == other.name) {
            return 0
        } else if (this.name < other.name) {
            return 1
        } else {
            return -1
        }
    }
}


fun main(args: Array<String>) {

    var listOfNames = ArrayList<Person>()
    listOfNames.add(Person("Jenna", 30))
    listOfNames.add(Person("Paul", 27))
    listOfNames.add(Person("Mirna", 25))
    listOfNames.add(Person("Diana", 24))
    listOfNames.sort()
    for(person in listOfNames){
        println("Name : " + person.name )
        println("Age : " + person.age )
    }
    println("Sorted")

    for(name in listOfNames){
        println(name)
    }
}