package isel.leic.tds.checkers


class Board(size: Int?, rows: List<List<Square>>?) {
    val rows: List<List<Square>> = if (rows != null) rows else List(size ?: defSize) { rowIndex ->
        List(size ?: defSize) { colIndex ->
            val square = Square(Row(rowIndex), Column(colIndex))
            when {
                rowIndex < 3 && square.black -> square.piece = Piece.BLACK
                rowIndex >= (size ?: defSize) - 3 && square.black -> square.piece = Piece.WHITE
            }
            square
        }
    }
    val size = size ?: defSize

    fun withPiece(row: Int, col: Int, piece: Piece?): Board {
        val rrow = (size - 1) -row
        val newRows = rows.mapIndexed { rowIndex, rowList ->
            if (rowIndex == rrow) {
                rowList.mapIndexed { colIndex, square ->
                    if (colIndex == col) square.copy(piece = piece) else square
                }
            } else {
                rowList
            }
        }
        return Board(size = null, rows = newRows)
    }

    private fun intToChar(i : Int): Char =  ('a' + i).toChar()
    private  fun CharToInt(c :Char) : Int = (c - 'a').toInt()

    fun movePiece(row: Int, col: Char, destRow: Int, destCol: Char): Board {

        val colInt = CharToInt(col)
        val destColInt =CharToInt(destCol)
        val rrow = row //(size - 1) -row
        val rDrow = destRow//(size - 1) -destRow
        if (destRow == row && destCol == col) return this

        val curr = getSquare(rrow, colInt)
        val dest = getSquare(rDrow, destColInt)

        return withPiece(destRow, destColInt, curr.piece)
            .withPiece(row, colInt, null)
    }

    fun printBoard() {
        val n = "    a  b  c  d  e  f  g  h"
        var cnt = this.size

        println(n)
        val line = "  +" + "-".repeat(this.size * 3) + "+"
        println(line)
        for (row in rows) {
            print(cnt.toString())
            print(" |")
            for (square in row) {
                val pieceChar = when (square.piece) {
                    Piece.BLACK -> " b "
                    Piece.WHITE -> " w "
                    else -> "   "
                }
                print(pieceChar)
            }
            print("| ")
            print(cnt--.toString())
            println()
        }
        println(line)
        println(n)

       // println("1a" + (getSquare(0,0).piece))
       // println("2b" + (getSquare(1,1).piece))
       // println("8h" + (getSquare(7,7).piece))
    }

    fun getSquare(row: Int, col: Int): Square = rows[(size - 1) -row][ col]
}
