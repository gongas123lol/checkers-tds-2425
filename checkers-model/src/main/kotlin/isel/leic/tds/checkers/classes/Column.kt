package isel.leic.tds.checkers.classes

@JvmInline
value class Column(val index: Int) {
    val displayChar: Char get() = ('a' + index)  // Maps index to 'a'-based character

    init {
        require(index in 0 until BOARD_DIM) {
            "Invalid column index: $index"
        }
    }

    companion object {
        fun Char.toColumnOrNull(): Column? {
            val idx = this - 'a'  // Direct mapping from 'a' to index
            return if (idx in 0 until BOARD_DIM) Column(idx) else null
        }
    }
}
