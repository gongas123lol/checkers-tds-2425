package isel.leic.tds.checkers.helpers

import isel.leic.tds.checkers.classes.*
import java.io.File
const val defPath : String = "checkers-model/src/main/games/"



/*
This function checks if a game already exists, if not, it creates it and returns the file path.
 */

fun checkAndCreateFile(w: Char, filePath: String): String {
    val fp = "$defPath$filePath.che"
    val file = File(fp)

    if (!file.exists()) {
        val bd = Board(BOARD_DIM, null)
        bd.saveToFile(fp, w)
        println("'$filePath' game created")
        println("starting game...")
        return fp
    } else {
        println("starting game...")
        return fp
    }
}

fun parseBoardString(boardString: String): List<List<Square>> {
    val rows = boardString.trim('[', ']').split("], [").map { rowString ->
        // Split each row into individual elements
        rowString.split(", ").map { cellString ->
            val piece: Piece? = when (cellString) {
                "b" -> Piece.BLACK
                "w" -> Piece.WHITE
                "B" -> Piece.BLACK_KING
                "W" -> Piece.WHITE_KING
                "·" -> null
                else -> null
            }
            Square(Row(0), Column(0), piece)
        }
    }

    return rows.mapIndexed { rowIndex, rowList ->
        rowList.mapIndexed { colIndex, square ->

            Square(Row(rowIndex), Column(colIndex), square.piece)
        }
    }
}

fun getCurrPlay(filePath: String): Char{
    val file = File(filePath)
    val text = file.readText()
    val currToPlay = text.split("|")[0]
    return currToPlay.toChar()
}

fun String.toChar(): Char {
    return this[0]
}