package isel.leic.tds.checkers

import isel.leic.tds.checkers.Column.Companion.toColumnOrNull
import isel.leic.tds.checkers.Row.Companion.toRowOrNull

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
            val row : Int = this[0].digitToIntOrNull()?:return null
            val column: Int = (this[1] - 'a') + 1
            return Square(Row(row - 1), Column(column))
        }

        fun String.toSquare(): Square {
            return toSquareOrNull() ?: throw IllegalArgumentException("Invalid square string: $this")
        }
    }
}
