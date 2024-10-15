package isel.leic.tds.checkers

fun commandHandler(command: String) {
    val split = command.split(" ")
    if(split[0].contains("start",true)){
        if(split.size == 1){ // meaning no other stuff
            println("Missing game name")
        }
    }
}
