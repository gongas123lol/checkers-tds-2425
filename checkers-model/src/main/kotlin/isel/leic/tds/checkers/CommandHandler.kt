package isel.leic.tds.checkers

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
    } else {  // Game is active
        if (split[0].contains("move", true)) {
            println("Handling move command")
            val tr = game.copy(board = game.board.movePiece(3,'a', 5,'c') )
            tr.board.printBoard()
            return tr
        }

        game.board.printBoard()
    }

    return game
// Return the game in its current state
}
