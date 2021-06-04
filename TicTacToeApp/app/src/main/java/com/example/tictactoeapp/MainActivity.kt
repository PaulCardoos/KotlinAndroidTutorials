package com.example.tictactoeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun selectSquare(view : View){
        var cellId = 0
        val select = view as Button
        when(select.id){
            R.id.button1 -> cellId = 1
            R.id.button2 -> cellId = 2
            R.id.button3 -> cellId = 3
            R.id.button4 -> cellId = 4
            R.id.button5 -> cellId = 5
            R.id.button6 -> cellId = 6
            R.id.button7 -> cellId = 7
            R.id.button8 -> cellId = 8
            R.id.button9 -> cellId = 9
        }
        //Toast.makeText(applicationContext, cellId.toString(), Toast.LENGTH_SHORT).show()
        playGame(cellId, select)
    }
    var activePlayer = 1
    var player1 = ArrayList<Int>()
    fun playGame(cellId:Int, select:Button){
        if(activePlayer == 1){
            select.text = "X"
            select.setBackgroundResource(R.color.green)
        }else{
            select.text = "O"
            select.setBackgroundResource(R.color.pink)
        }

    }
}