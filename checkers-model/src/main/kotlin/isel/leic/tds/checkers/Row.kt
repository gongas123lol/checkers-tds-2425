package isel.leic.tds.checkers

const val BOARD_DIM = 8

@JvmInline
value class Row(val index: Int) {
    val ch : Char get()= ('a' + index)
        //useless
    val digit: Char
        get() = ('1' + (BOARD_DIM - 1) - index)

    init {
        require(index in 0 until BOARD_DIM) {
            "Invalid row index: $index"
        }
    }

    companion object {

        fun Char.toRowOrNull(): Row? {
            val idx = (BOARD_DIM - 1) - (this - '1')
            return if (idx in 0 until BOARD_DIM) Row(idx) else null
        }

        val values: List<Row> = (0 until BOARD_DIM).map { Row(it) }
    }
}
