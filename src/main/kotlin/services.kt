
fun search(
    dataList: List<String>,
    indexedData: MutableMap<String, MutableList<Int>>,
    matchStrategy: String
) {
    println("Enter names or emails to search (separated by spaces):")
    val queries = readln().lowercase().split(" ")


    val resultIndexes = when (matchStrategy.uppercase()) {
        "ALL" -> {
            // Intersect indexes for all queries
            queries.mapNotNull { indexedData[it] }
                .reduceOrNull { acc, set -> acc.intersect(set.toSet()).toMutableList() }
        }

        "ANY" -> {
            // Union of all indexes for the queries
            queries.mapNotNull { indexedData[it] }
                .flatten()
                .toSet()
        }

        "NONE" -> {
            // Find indexes of all data entries, subtract those matching the queries
            val allIndexes = dataList.indices.toSet()
            val matchingIndexes = queries.mapNotNull { indexedData[it] }
                .flatten()
                .toSet()
            allIndexes - matchingIndexes
        }

        else -> {
            println("Invalid matching strategy: $matchStrategy")
            return
        }
    }

    if (!resultIndexes.isNullOrEmpty()) {
        if (matchStrategy == "ALL") {
            println("${resultIndexes.size} person${if (resultIndexes.size > 1) "s" else ""} found:")
        }
        resultIndexes.forEach { index ->
            println(dataList[index])
        }
    } else {
        println("No matching people found.")
    }
}

fun printAll(dataList: List<String>) {
    dataList.forEach {
        println(it)
    }
}

fun indexData(dataList: List<String>): MutableMap<String, MutableList<Int>> {
    val invertedIndex = mutableMapOf<String, MutableList<Int>>()

    dataList.forEachIndexed { index, line ->
        val words = line.split(" ", ":").filter { it.isNotEmpty() }
        words.forEach { word ->
            val lowerCaseWord = word.lowercase()
            if (lowerCaseWord !in invertedIndex) {
                invertedIndex[lowerCaseWord] = mutableListOf()
            }
            invertedIndex[lowerCaseWord]?.add(index)
        }
    }

    return invertedIndex
}
