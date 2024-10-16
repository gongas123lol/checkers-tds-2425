package isel.leic.tds.checkers

class Board(size: Int? , rows: List<List<Square>>) {

    val rows: List<List<Square>> = if(rows != null) rows else List(size?: defSize) { rowIndex ->
        List(size?: defSize) { colIndex ->
            val square = Square(Row(rowIndex), Column(colIndex))
            // Place pieces on the first three and last three rows (standard checkers setup)
            if (rowIndex < 3 && square.black) {
                square.piece = Piece.BLACK
            } else if (rowIndex >= (size ?: (defSize - 3)) && square.black) {
                square.piece = Piece.WHITE
            }
            square
        }
    }

    fun withPiece(row: Int, col: Int, piece: Piece?): Board {
        // Create a copy of the row with the updated square
        val newRows = rows.mapIndexed { rowIndex, rowList ->
            if (rowIndex == row) {
                // Replace the specific square with a new one with the piece updated
                rowList.mapIndexed { colIndex, square ->
                    if (colIndex == col) square.copy(piece = piece) else square
                }
            } else {
                rowList // Keep rows unchanged
            }
        }
        return Board(null, rows=newRows) // Return a new Board with the updated rows
    }

    fun printBoard() {
        for (row in rows) {
            for (square in row) {
                print(square)
            }
            println()
        }
    }

    fun getSquare(row: Int, col: Int): Square {
        return rows[row][col]
    }

    fun movePiece(row: Int, col: Int, destRow: Int, destCol: Int): Board {
        if(destRow == row && destCol == row) return this

        val curr = getSquare(row, col)
        val dest = getSquare(destRow, destCol)

        val a = withPiece(destRow,destCol,curr.piece)
        val b = a.withPiece(row,col, null)

        return b

    }
}
