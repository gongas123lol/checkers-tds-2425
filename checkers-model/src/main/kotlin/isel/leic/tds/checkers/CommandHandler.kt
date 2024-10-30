package isel.leic.tds.checkers

import isel.leic.tds.checkers.Square.Companion.toSquare
import isel.leic.tds.checkers.Square.Companion.toSquareOrNull
import kotlin.jvm.Throws

fun commandHandler(command: String, gamei: Game): Game {
    val split = command.split(" ")
    val game = if(gamei.active) gamei.copy(board = gamei.board.retrieveFromFile(gamei.fp)) else gamei


    if (!game.active) {  // Game not active
        if (split[0].contains("start", true)) {
            if (split.size == 1) {  // Missing game name
                println("Missing game name")
            } else if (split.size == 2) {
                val gameName = split[1]
                val fp = checkAndCreateFile(gameName)
                // Activate the game by setting active = true
                val bd = game.board.retrieveFromFile(fp)
                bd.printBoard()
                return game.copy(name = gameName, fp = fp, board = bd, active = true)
            } else {
                println("Invalid command, write 'help' to see possible commands")
            }
        } else {
            println("Invalid command for an inactive game")
        }
    } else {
        if(split.isEmpty()){
            println("Invalid command for an active game")
            return game
        }
        if (split[0].contains("move", true)) {

            if(split.size == 1) {
                println("Invalid command for an active game")
                return game
            }
            val srcMove: Square = split[1].toSquare()
            val destMove : Square = split[2].toSquare()
            //println("${srcMove.row.displayidx},${srcMove.column.displayChar}")
            //println("${destMove.row.displayidx},${destMove.column.displayChar}")

            val tr = game.copy(
                board = game.board.movePiece( srcMove.row.displayidx,srcMove.column.displayChar, destMove.row.displayidx,destMove.column.displayChar),

            )
            tr.board.printBoard()
            return tr
        }else if(split[0].contains("refresh", true)){
            game.board.retrieveFromFile(game.fp)
            game.board.printBoard()
            return game
        }else if(split[0].contains("grid", true)){
            game.board.printBoard()
            return game
        }else if(split[0].contains("help", true)){
            println("possible commands:")
            println("start <gameId>")
            println("play <from> <to>")
            println("grid")
            println("refresh")

            return game
        }else{
            println("Invalid command, write 'help' to see possible commands")
            return game
        }

        game.board.printBoard()
    }

    return game
// Return the game in its current state
}

