package isel.leic.tds.checkers
const val BOARD_DIM = 8
@JvmInline
value class Row(val index: Int) {
    val displayChar: Char get() {

        return ('1' + (BOARD_DIM - 1) - index)  // Corrects display for 1-based bottom row
    }

    init {
        require(index in 0 until BOARD_DIM) {
            "Invalid row index: $index"
        }
    }

    companion object {
        fun Char.toRowOrNull(): Row? {
            val idx = (BOARD_DIM - 1) - (this - '1')  // Adjust for board starting from 1
            return if (idx in 0 until BOARD_DIM) Row(idx) else null
        }
    }
}
