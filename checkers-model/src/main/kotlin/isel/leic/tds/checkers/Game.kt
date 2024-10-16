package isel.leic.tds.checkers

const val defSize = 8

data class Game(val name: String, val active : Boolean, val board: Board, val fp: String)
