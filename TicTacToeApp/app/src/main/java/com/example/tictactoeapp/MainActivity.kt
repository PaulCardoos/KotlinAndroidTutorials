package com.example.tictactoeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun selectSquare(view : View){
        var cellId = 0
        val select = view as Button
        when(select.id){
            R.id.bu1 -> cellId = 1
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
    var player2 = ArrayList<Int>()

    private fun playGame(cellId:Int, select: Button){
        if(activePlayer == 1){
            select.text = "X"
            select.setTextColor(resources.getColor(R.color.red))
            player1.add(cellId)
            activePlayer = 2
            soloPlay()
        }else{
            select.text = "O"
            select.setTextColor(resources.getColor(R.color.green))
            player2.add(cellId)
            activePlayer = 1
        }

        select.isEnabled = false

        //after each move check if there is a winner
        checkWinner()

    }

    private fun soloPlay() {


    }


    private fun checkWinner() {

        var winner = -1

        //check for horizontal winner
        if(player1.contains(1) && player1.contains(2) && player1.contains(3)){
            winner = 1
        }
        if(player2.contains(1) && player2.contains(2) && player2.contains(3)){
            winner = 2
        }
        if(player1.contains(4) && player1.contains(5) && player1.contains(6)){
            winner = 1
        }
        if(player2.contains(4) && player2.contains(5) && player2.contains(6)){
            winner = 2
        }

        if(player1.contains(7) && player1.contains(8) && player1.contains(9)){
            winner = 1
        }
        if(player2.contains(7) && player2.contains(8) && player2.contains(9)){
            winner = 2
        }

        //check for vertical winner
        if(player1.contains(1) && player1.contains(4) && player1.contains(7)){
            winner = 1
        }
        if(player2.contains(1) && player2.contains(4) && player2.contains(7)){
            winner = 2
        }

        if(player1.contains(2) && player1.contains(5) && player1.contains(8)){
            winner = 1
        }
        if(player2.contains(2) && player2.contains(5) && player2.contains(8)){
            winner = 2
        }

        if(player1.contains(3) && player1.contains(6) && player1.contains(9)){
            winner = 1
        }
        if(player2.contains(3) && player2.contains(6) && player2.contains(9)){
            winner = 2
        }

        //check diagonal winner
        if(player1.contains(1) && player1.contains(5) && player1.contains(9)){
            winner = 1
        }
        if(player2.contains(1) && player2.contains(5) && player2.contains(9)){
            winner = 2
        }

        if(player1.contains(3) && player1.contains(5) && player1.contains(6)){
            winner = 1
        }
        if(player2.contains(3) && player2.contains(6) && player2.contains(5)){
            winner = 2
        }

        if(winner == 1){
            Toast.makeText(applicationContext, "Player 1 wins!", Toast.LENGTH_SHORT).show()
        }else if(winner == 2){
            Toast.makeText(applicationContext, "Player 2 wins!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(applicationContext, "Draw", Toast.LENGTH_SHORT).show()
        }

    }
}
