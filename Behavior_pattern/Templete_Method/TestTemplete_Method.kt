package Behavior_pattern.Templete_Method

import java.io.File
import java.util.*

class TestTemplete_Method : TimerTask() {
    private val paths = arrayOf("C:\\Users\\US_SONER\\Downloads\\Templete_Method\\drugstore",
        "C:\\Users\\US_SONER\\Downloads\\Templete_Method\\grocery")
    private val logDir = "C:\\Users\\US_SONER\\Downloads\\Templete_Method\\logs"
    private val processDir = "C:\\Users\\US_SONER\\Downloads\\Templete_Method\\process"

    fun start() {
        try {
            val timer = Timer()
            timer.schedule(this, Date(), 10000)
            readInput()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun readInput() {
        try {
            System.`in`.read()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun run() {
        println("> Monitoring start")
        processFilesInDirectory(paths[0], DrugstoreFileProcess::class.java)
        processFilesInDirectory(paths[1], GroceryFileProcess::class.java)
    }

    private fun processFilesInDirectory(path: String, processingClass: Class<out AbstractFileProcessTemplete>) {
        val directory = File(path)
        if (!directory.exists()) {
            throw RuntimeException("El path '$path' no existe")
        }

        val files = directory.listFiles()
        files?.forEach { file ->
            try {
                println("> File found > " + file.name)
                val constructor = processingClass.getConstructor(File::class.java, String::class.java, String::class.java)
                val processingInstance = constructor.newInstance(file, logDir, processDir) as AbstractFileProcessTemplete
                processingInstance.execute()
                println("Archivo procesado > " + file.name)
            } catch (e: Exception) {
                System.err.println(e.message)
            }
        }
    }
}

fun main() {
    TestTemplete_Method().start()
}