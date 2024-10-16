package isel.leic.tds.checkers

import java.io.File
const val defPath : String = "checkers-model/src/main/games/"



/*
This function checks if a game already exists, if not, it creates it and returns the file path.
 */

fun checkAndCreateFile(filePath: String): String {
    val fp = "$defPath$filePath.che"
    val file = File(fp)

    if (!file.exists()) {
        file.writeText("hello")
        println("'$filePath' game created")
        println("starting game...")
        return fp
    } else {
        println("starting game...")
        return fp
    }
}

