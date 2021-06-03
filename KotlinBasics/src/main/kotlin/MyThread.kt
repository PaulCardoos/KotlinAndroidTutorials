import java.lang.Thread.sleep

class MyThread(var threadName : String) : Thread(){

    override fun run(){
        var count = 0
        while(count < 100){
            println("Count in $threadName : $count")
            count ++
            Thread.sleep(1000)
        }
    }
}


fun main(args: Array<String>){
    var thr1 = MyThread("Thread1")
    thr1.start()
    var thr2 = MyThread("Thread2")
    thr2.start()
    var thr3 = MyThread("Thread3")
    thr3.start()
}