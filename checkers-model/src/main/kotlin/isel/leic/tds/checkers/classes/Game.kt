package isel.leic.tds.checkers.classes


const val defSize = 8

data class Game(
    val who: Char,
    val playerW: Player,
    val playerB: Player,
    val name: String,
    val active : Boolean,
    val board: Board,
    val fp: String
)
