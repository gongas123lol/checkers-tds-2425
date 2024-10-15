package isel.leic.tds.checkers

import isel.leic.tds.checkers.Column.Companion.toColumnOrNull
import isel.leic.tds.checkers.Row.Companion.toRowOrNull


class Square(val row: Row, val column: Column) {

    val index: Int get() = row.index * BOARD_DIM + column.index

    val black: Boolean get() = (row.index + column.index) % 2 == 1
    val white: Boolean get() = (row.index + column.index) % 2 == 0

    override fun toString(): String {
        return "${row.digit}${column.symbol}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Square) return false
        return row == other.row && column == other.column
    }

    override fun hashCode(): Int = 31 * row.hashCode() + column.hashCode()

    companion object {

        val values: List<Square> = getFulllist()

        private fun getFulllist(): List<Square> {
            var list = mutableListOf<Square>()
            for(i in 0 until BOARD_DIM) {
                for(j in 0 until BOARD_DIM) {
                    list+= Square(Row(i), Column(i))
                }
            }
            return list
        }

        fun String.toSquareOrNull(): Square? {
            if (length != 2) return null
            val row = this[0].toRowOrNull() ?: return null
            val column = this[1].toColumnOrNull() ?: return null
            return Square(row, column)
        }

        fun String.toSquare(): Square {
            return toSquareOrNull() ?: throw IllegalArgumentException("Invalid square string: $this")
        }
    }
}
