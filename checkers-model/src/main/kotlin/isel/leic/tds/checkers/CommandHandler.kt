package isel.leic.tds.checkers

import isel.leic.tds.checkers.Square.Companion.toSquareOrNull

fun commandHandler(command: String, game: Game): Game {
    println("$command $game")
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
            println("Handling move command")
            if(split.size == 1) {
                println("Invalid command for an active game")
                return game
            }
            val srcMove = split[1].toSquareOrNull()
            val destMove = split[2].toSquareOrNull()
            if (srcMove != null) {
                println("${srcMove.row},${srcMove.index},${srcMove.piece}")
            }
            if (destMove != null) {
                println("${destMove.row},${destMove.index},${destMove.piece}")
            }
            println(destMove)
            val tr = game.copy(board = game.board.movePiece(3,'a', 4,'b') )
            println(tr.board.getSquare(5,'c').toString())
            tr.board.printBoard()
            return tr
        }

        game.board.printBoard()
    }

    return game
// Return the game in its current state
}
