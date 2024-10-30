package isel.leic.tds.checkers

import isel.leic.tds.checkers.Square.Companion.toSquare
import isel.leic.tds.checkers.Square.Companion.toSquareOrNull
import kotlin.jvm.Throws

fun commandHandler(command: String, game: Game): Game {
    val split = command.split(" ")

    if (!game.active) {  // Game not active
        if (split[0].contains("start", true)) {
            if (split.size == 1) {  // Missing game name
                println("Missing game name")
            } else if (split.size == 2) {
                val gameName = split[1]
                val fp = checkAndCreateFile(gameName)
                // Activate the game by setting active = true
                return game.copy(name = gameName, fp = fp, active = true)
            } else {
                println("Invalid command")
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
                board = game.board.movePiece( srcMove.row.displayidx,srcMove.column.displayChar, destMove.row.displayidx,destMove.column.displayChar)
               // board = game.board.movePiece(3,'g', 4,'f')
            )
            tr.board.printBoard()
            return tr
        }

        game.board.printBoard()
    }

    return game
// Return the game in its current state
}
