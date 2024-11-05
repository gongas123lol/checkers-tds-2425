package isel.leic.tds.checkers.cli

import isel.leic.tds.checkers.classes.Board
import isel.leic.tds.checkers.classes.Game
import isel.leic.tds.checkers.classes.Player
import isel.leic.tds.checkers.helpers.commandHandler


//sou da turma 33d REMEMBER

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Welcome to the Checkers App")
    } else {
        println("Command-line arguments: ${args.joinToString(", ")}")
    }

    var game = Game('W',Player('W',0),Player('B', 0),"n", false, Board(8, null),"")

    while(true){
        val command = readln()
        //println("hello")
        game = commandHandler(command, game)
        if(game.active) game.board.saveToFile(game.fp,game.who)
    }

}

