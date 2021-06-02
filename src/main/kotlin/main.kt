import java.io.File
import java.nio.file.Files

fun main() {
    val userName = System.getProperty("user.name")
    val rootDir =
        File("C:\\Users\\${userName}\\AppData\\Local\\Packages\\NAVER.LINEwin8_8ptj331gd3tyt\\AppData\\LINE\\Data\\Sticker")

    if (!rootDir.exists()) {
        println("C:\\Users\\${userName}\\AppData\\Local\\Packages\\NAVER.LINEwin8_8ptj331gd3tyt\\AppData\\LINE\\Data\\Sticker is not found!")
        return
    }

    val targetDir = File("./LINEStamps")
    targetDir.mkdirs()

    println("Running...")

    rootDir.listFiles()!!.forEach {
        if (it.isDirectory) explore(it, targetDir)
    }

    println("Successfully exported ${index + 1} stamps.")
}

var index = 0
fun explore(file: File, targetDir: File) {
    if (file.isDirectory) {
        file.listFiles()!!.forEach {
            if (it.isDirectory) {
                explore(it, targetDir)
            } else if (it.extension == "png") {
                Files.copy(it.toPath(), File("./LINEStamps/${index}.${it.extension}").toPath())
                index++
            }
        }
    }
}