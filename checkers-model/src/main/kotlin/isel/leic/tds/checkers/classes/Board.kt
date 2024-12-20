package isel.leic.tds.checkers.classes

import isel.leic.tds.checkers.helpers.parseBoardString
import java.io.File

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

     //esta funcao poderia receber um square....
    fun movePiece(row: Int, col: Char, destRow: Int, destCol: Char, who: Char): Board {
        if (row == destRow && col == destCol){
            println("invalid play.")
            return this
        }

        val curr = getSquare(row, col)
        val dest = getSquare(destRow, destCol)
        val now = if(who == 'B') Piece.BLACK else Piece.WHITE

        val dist = distanceBetween(row,col,destRow,destCol)
        if(dist != sqrt2 && dist != sqrt8 ||
            (curr.piece == Piece.BLACK && destCol > col) ||
            (curr.piece == Piece.WHITE && destCol < col) ||
            (curr.piece != now) ||
            (dest.piece != null)
            ){
            println("---invalid play---")
            return this
        }
         val boardAfterMove = this.withPiece(destRow, destCol, curr.piece)

         val middleRow = (row + destRow) / 2
         val middleCol = intToChar((CharToInt(col) + CharToInt(destCol)) / 2)
         val pieceInTheMiddle = getMiddle(row,col,destRow, destCol)

         val boardmid: Board = if(dist == sqrt8 && (pieceInTheMiddle.piece != null && pieceInTheMiddle.piece != now)){
             println("eaten!")
              boardAfterMove.withPiece(middleRow,middleCol, null)
         }else{
              boardAfterMove
         }


        println(boardAfterMove)
        return boardmid.withPiece(row, col, null)

    }

    private fun getMiddle(row: Int, col: Char, destRow: Int, destCol: Char): Square {
        val middleRow = (row + destRow) / 2
        val middleCol = intToChar((CharToInt(col) + CharToInt(destCol)) / 2)

        return getSquare(middleRow, middleCol)
    }



    fun printBoard() {
        val columnHeaders = "    a  b  c  d  e  f  g  h"
        println(columnHeaders)

        val line = "  +" + "-".repeat(size * 3) + "+"
        println(line)

        var rowNumber = size
        for (row in rows) {
            print("$rowNumber |")
            for (square in row) {
                //println("${square.row},${square.index},${square.piece}")
                val pieceChar = when (square.piece) {
                    Piece.BLACK -> " b "
                    Piece.WHITE -> " w "
                    Piece.BLACK_KING -> " B "
                    Piece.WHITE_KING -> " W "
                    else -> if (square.black) " · " else "   "
                }
                print(pieceChar)
            }
            println("| $rowNumber")
            rowNumber--
        }
        println(line)
        println(columnHeaders)
    }
    private fun distanceBetween(row1: Int, col1: Char, row2: Int, col2: Char): Double {

        val internalRow1 = (size - row1) - 1
        val internalRow2 = (size - row2) - 1

        val rowDifference = (internalRow1 - internalRow2).toDouble()
        val colDifference = (col1 - col2).toDouble()
        return kotlin.math.sqrt(rowDifference * rowDifference + colDifference * colDifference)
    }
     fun getSquare(row: Int, col: Char): Square = rows[(size) -row][ col - 'a']



    fun saveToFile(fp : String, w: Char) {
            val file = File(fp)
            file.writeText("$w|")
            file.appendText(rows.toString())
        return
    }

    fun retrieveFromFile(fp: String): Board{
        val file = File(fp)
        val text = file.readText()
        val rows = parseBoardString(text.split("|")[1])
        return Board(BOARD_DIM,rows)
    }
}
