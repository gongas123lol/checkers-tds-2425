package isel.leic.tds.checkers
const val defPath : String = "checkers-model/src/main/games"
const val defSize = 8

class Game(name: String){
    val name = name
    init{
        val curr = newBoard(defSize)
    }

    private fun newBoard(defSize: Int): Board {

    }

}