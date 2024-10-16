package isel.leic.tds.checkers

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Welcome to the Checkers App")
    } else {
        println("Command-line arguments: ${args.joinToString(", ")}")
    }

    var game = Game("n", false,Board(8),"")

    while(true){
        val command = readln()
        println("hello")
        game = commandHandler(command, game)
    }

}

