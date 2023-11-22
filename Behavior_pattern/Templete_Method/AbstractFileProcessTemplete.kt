package Behavior_pattern.Templete_Method

import java.io.File
abstract class AbstractFileProcessTemplete(
    protected val file: File,
    protected val logPath: String,
    protected val movePath: String
) {
    @Throws(Exception::class)
    fun execute() {
        validateName()
        validateProcess()
        processFile()
        createLog()
        moveDocument()
        markAsProcessFile()
    }

    @Throws(Exception::class)
    protected abstract fun validateName()

    @Throws(Exception::class)
    protected open fun validateProcess() {
        val fileStatus = OnMemoryDataBase.getFileStatus(file.name)
        if (fileStatus != null && fileStatus == "Procesado") {
            throw Exception("El archivo '${file.name}' ya fue procesado")
        }
    }

    @Throws(Exception::class)
    protected abstract fun processFile()

    @Throws(Exception::class)
    protected abstract fun createLog()

    private fun moveDocument() {
        val newPath = "$movePath/${file.name}"
        val change = file.renameTo(File(newPath))
    }

    @Throws(Exception::class)
    protected open fun markAsProcessFile() {
        OnMemoryDataBase.setProcessFile(file.name)
    }
}
