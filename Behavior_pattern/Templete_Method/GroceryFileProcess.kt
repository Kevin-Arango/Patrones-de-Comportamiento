package Behavior_pattern.Templete_Method

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
class GroceryFileProcess(file: File, logPath: String, movePath: String) :
    AbstractFileProcessTemplete(file, logPath, movePath) {

    private var log = ""

    override fun validateName() {
        val fileName = file.name
        if (!fileName.endsWith(".gry")) {
            throw Exception("Nombre del archivo no válido, debe terminar con .gry")
        }

        if (fileName.length != 7) {
            throw Exception("El documento no tiene el formato esperado")
        }
    }

    @Throws(Exception::class)
    override fun processFile() {
        val input = FileInputStream(file)
        try {
            val fileContent = ByteArray(input.available())
            input.read(fileContent)
            val message = String(fileContent)
            val lines = message.split("\n")
            for (line in lines) {
                val parts = line.split(",")
                val id = parts[0]
                val customer = parts[1]
                val amount = parts[2].toDouble()
                val date = parts[3]
                val exist = OnMemoryDataBase.customerExist(customer.toInt())

                log += when {
                    !exist -> "$id E$customer\t\t$date El cliente no existe\n"
                    amount > 200 -> "$id E$customer\t\t$date El monto excede el máximo\n"
                    else -> "$id E$customer\t\t$date Aplicado exitosamente\n"
                }
            }
        } finally {
            input.close()
        }
    }

    @Throws(Exception::class)
    override fun createLog() {
        var out: FileOutputStream? = null
        try {
            val outFile = File("$logPath/${file.name}")
            if (!outFile.exists()) {
                outFile.createNewFile()
            }
            out = FileOutputStream(outFile, false)
            out.write(log.toByteArray())
            out.flush()
        } finally {
            out?.close()
        }
    }
}
