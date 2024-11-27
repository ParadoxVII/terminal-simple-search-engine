import java.io.File

fun main(args: Array<String>) {
    if (args.isEmpty() || args[0] != "--data") {
        println("Usage: --data <filename>")
        return
    }

    val dataFile = args.getOrNull(1)
    if (dataFile == null) {
        println("File name not provided.")
        return
    }

    val dataText = File(dataFile)
    if (!dataText.exists()) {
        println("File not found: $dataFile")
        return
    }

    val dataList = dataText.readLines()
    val indexedData = indexData(dataList)
    mainMenu(indexedData, dataList)
}
