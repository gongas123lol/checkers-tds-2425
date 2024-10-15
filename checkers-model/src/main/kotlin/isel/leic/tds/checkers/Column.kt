package isel.leic.tds.checkers


//similar to the row value class
@JvmInline
value class Column(val index: Int) {

    val symbol: Char
        get() = ('a' + index)

    init {
        require(index in 0 until BOARD_DIM) {
            "Invalid column index: $index"
        }
    }

    companion object {

        fun Char.toColumnOrNull(): Column? {
            val idx = this - 'a'
            return if (idx in 0 until BOARD_DIM) Column(idx) else null
        }

        val values: List<Column> = (0 until BOARD_DIM).map { Column(it) }
    }

}
