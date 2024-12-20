package isel.leic.tds.checkers.classes

import isel.leic.tds.checkers.classes.Column.Companion.toColumnOrNull
import isel.leic.tds.checkers.classes.Row.Companion.toRowOrNull

class Square(val row: Row, val column: Column, var piece: Piece? = null) {

    val index: Int get() = row.index * BOARD_DIM + column.index

    val black: Boolean get() = (row.index + column.index) % 2 == 1
    val white: Boolean get() = (row.index + column.index) % 2 == 0

    override fun toString(): String {
        return piece?.toString() ?: if (black) "·" else " "
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Square) return false
        return row == other.row && column == other.column
    }

    override fun hashCode(): Int = 31 * row.hashCode() + column.hashCode()


    fun copy(piece: Piece?): Square {
        return Square(row, column, piece)
    }

    companion object {
        fun String.toSquareOrNull(): Square? {
            if (length != 2) return null
            val rowChar = this[0]
            val colChar = this[1]
            val row = rowChar.toRowOrNull()
            val col = colChar.toColumnOrNull()
            return if (row != null && col != null) Square(row, col) else null
        }

        fun String.toSquare(): Square {
            return toSquareOrNull() ?: throw IllegalArgumentException("Invalid square string: $this")
        }
    }

}
