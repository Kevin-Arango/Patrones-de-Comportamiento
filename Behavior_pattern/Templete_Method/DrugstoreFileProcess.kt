package Behavior_pattern.Templete_Method

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class DrugstoreFileProcess(file: File, logPath: String, movePath: String) :
    AbstractFileProcessTemplete(file, logPath, movePath) {

    private var log = ""

    override fun validateName() {
        val fileName = file.name
        if (!fileName.endsWith(".drug")) {
            throw Exception("Nombre el archivo no válido, debe terminar con .drug")
        }

        if (fileName.length != 16) {
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
                val id = line.substring(0, 3)
                val customer = line.substring(3, 5)
                val amount = line.substring(5, 8).toDouble()
                val date = line.substring(8, 16)
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
