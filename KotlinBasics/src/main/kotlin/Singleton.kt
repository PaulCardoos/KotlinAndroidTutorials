class Singleton {
    //instance variable initialized at null
    var name:String?= null
    private constructor(){
        println("instance is created")
    }

    //define our singleton class here
    companion object{
        val instance: Singleton by lazy { Singleton() }
    }
}