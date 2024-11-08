package isel.leic.tds.checkers.helpers

import isel.leic.tds.checkers.classes.Game
import isel.leic.tds.checkers.classes.Square
import isel.leic.tds.checkers.classes.Square.Companion.toSquare


fun handleInactiveGameCommand(split: List<String>, game: Game): Game {
    return when {
        split.isEmpty() -> game
        split[0].equals("start", ignoreCase = true) -> handleStartCommand(split, game)
        else -> game
    }
}

private fun handleStartCommand(split: List<String>, game: Game): Game {
    return when (split.size) {
        1 -> game // Missing game name
        2 -> {
            val gameName = split[1]
            //comeca smp o branco
            val fp = checkAndCreateFile('W',gameName)
            val board = game.board.retrieveFromFile(fp)
            val currToPlay = getCurrPlay(fp)
            board.printBoard()
            println("NOW PLAYS: " + game.who)
            game.copy(who = currToPlay , name = gameName, fp = fp, board = board, active = true)
        }
        else -> game // Invalid command for inactive game
    }
}

fun handleActiveGameCommand(split: List<String>, game: Game): Game {
    return when {
        split.isEmpty() -> game
        split[0].equals("move", ignoreCase = true) -> handleMoveCommand(split, game)
        split[0].equals("refresh", ignoreCase = true) -> handleRefreshCommand(game)
        split[0].equals("grid", ignoreCase = true) -> handleGridCommand(game)
        split[0].equals("help", ignoreCase = true) -> handleHelpCommand(game)
        else -> game // Invalid command for active game
    }
}

private fun handleMoveCommand(split: List<String>, game: Game): Game {
    if (split.size < 3) return game // Invalid move command

    val srcMove: Square = split[1].toSquare()
    val destMove: Square = split[2].toSquare()
    val nextToPlay = when(game.who){
        'B' -> 'W'
        'W' -> 'B'
        else-> 'W'
    }
    val updatedBoard = game.board.movePiece(
        srcMove.row.displayidx,
        srcMove.column.displayChar,
        destMove.row.displayidx,
        destMove.column.displayChar,
        nextToPlay
    )


    if(updatedBoard.rows != game.board.rows){
        updatedBoard.printBoard()
        println("NOW PLAYS: $nextToPlay")
        return game.copy(who = nextToPlay ,board = updatedBoard)
    }else{
        updatedBoard.printBoard()
        println("NOW PLAYS: " +game.who)
        return game.copy(who = game.who ,board = updatedBoard)
    }

    return game.copy(who = nextToPlay ,board = updatedBoard)
}

private fun handleRefreshCommand(game: Game): Game {
    val updatedBoard = game.board.retrieveFromFile(game.fp)
    updatedBoard.printBoard()
    return game.copy(board = updatedBoard)
}

private fun handleGridCommand(game: Game): Game {
    game.board.printBoard()
    return game
}

private fun handleHelpCommand(game: Game): Game {
    println("possible commands:")
    println("start <gameId>")
    println("move <from> <to>")
    println("grid")
    println("refresh")
    return game
}
