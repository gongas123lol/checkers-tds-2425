package isel.leic.tds.checkers

enum class Piece(val symbol: Char) {
    BLACK('b'),
    WHITE('w'),
    BLACK_KING('B'),
    WHITE_KING('W');

    override fun toString(): String = symbol.toString()
}
