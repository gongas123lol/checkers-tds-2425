package isel.leic.tds.checkers

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Welcome to the Checkers App")
    } else {
        println("Command-line arguments: ${args.joinToString(", ")}")
    }

    while(true){
        val command = readln()
        commandHandler(command)
    }

}

