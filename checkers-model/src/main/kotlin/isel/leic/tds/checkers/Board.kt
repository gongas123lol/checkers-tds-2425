package isel.leic.tds.checkers


class Board(size: Int?, rows: List<List<Square>>?) {
    private val sqrt2: Double = 1.4142135623730951
    private val sqrt8 : Double = 2.8284271247461903

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

    fun withPiece(row: Int, col: Char, piece: Piece?): Board {
        val rcol : Int = col - 'a'
        val rrow = size -row
        val newRows = rows.mapIndexed { rowIndex, rowList ->
            if (rowIndex == rrow) {
                rowList.mapIndexed { colIndex, square ->
                    if (colIndex == rcol) square.copy(piece = piece) else square
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
        if (row == destRow && col == destCol){
            println("invalid play.")
            return this
        }

        val curr = getSquare(row, col)
        val dest = getSquare(destRow, destCol)

        val dist = distanceBetween(row,col,destRow,destCol)
        if(dist != sqrt2 && dist != sqrt8){
            println("invalid play.")
            return this
        }

        if(dist == sqrt8){
            val pieceInTheMiddle = getMiddle(row,col,destRow, destCol)
            println(pieceInTheMiddle)
        }else if(dist == sqrt2){
            return this //...
        }




        val boardAfterMove = this.withPiece(destRow, destCol, curr.piece)
        return boardAfterMove.withPiece(row, col, null)
    }

    private fun getMiddle(row: Int, col: Char, destRow: Int, destCol: Char): Square {
        val middleRow = (row + destRow) / 2
        val middleCol = intToChar((CharToInt(col) + CharToInt(destCol)) / 2)

        return getSquare(middleRow, middleCol)
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
                    Piece.BLACK_KING -> " B "
                    Piece.WHITE_KING -> " W "
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
    fun distanceBetween(row1: Int, col1: Char, row2: Int, col2: Char): Double {

        val internalRow1 = (size - row1) - 1
        val internalRow2 = (size - row2) - 1

        val rowDifference = (internalRow1 - internalRow2).toDouble()
        val colDifference = (col1 - col2).toDouble()
        return kotlin.math.sqrt(rowDifference * rowDifference + colDifference * colDifference)
    }
    fun getSquare(row: Int, col: Char): Square = rows[(size - 2) -row][ col - 'a']
}
