package Behavior_pattern.Templete_Method
object OnMemoryDataBase {
    private val processDocuments = hashMapOf<String, String>()
    private val customers = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100)

    fun getFileStatus(fileName: String): String? {
        return processDocuments[fileName]
    }

    fun setProcessFile(fileName: String) {
        processDocuments[fileName] = "Procesado"
    }

    fun customerExist(id: Int): Boolean {
        return customers.contains(id)
    }
}
