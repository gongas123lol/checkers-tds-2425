package isel.leic.tds.checkers.helpers

import isel.leic.tds.checkers.classes.Square.Companion.toSquare
import isel.leic.tds.checkers.classes.Game
import isel.leic.tds.checkers.classes.Square

fun commandHandler(command: String, gamei: Game): Game {
    val split = command.split(" ")
    val game = if (gamei.active) gamei.copy(board = gamei.board.retrieveFromFile(gamei.fp)) else gamei

    return if (!game.active) {
        handleInactiveGameCommand(split, game)
    } else {
        handleActiveGameCommand(split, game)
    }
}
