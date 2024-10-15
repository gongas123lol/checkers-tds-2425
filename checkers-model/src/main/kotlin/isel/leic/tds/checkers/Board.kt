package isel.leic.tds.checkers

class Board(size: Int) {

    val rows: List<List<Square>> = List(size) {
        List(size) { Square(Row(0), Column(0)) }  // Create a row with `size` number of Square objects
    }

}