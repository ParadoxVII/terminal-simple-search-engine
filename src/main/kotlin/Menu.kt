


fun mainMenu(indexedData: MutableMap<String, MutableList<Int>>, datalist: List<String>) {
    menuLoop@ while (true) {
        println(
            """=== Menu ===
        |1. Find a person
        |2. Print all people
        |0. Exit
        """.trimMargin()
        )
        try {
            when (readln().toInt()) {
                1 -> {
                    val matchStrategy = searchMenu()
                    search(datalist, indexedData, matchStrategy)
                }

                2 -> printAll(datalist)
                0 -> break@menuLoop
                else -> println("Incorrect option! Try again.")
            }
        } catch (e: NumberFormatException) {
            println("Invalid input! Please enter a number.")
        }
    }
    println("Bye")
}

fun searchMenu(): String {
    println("Select a matching strategy: ALL, ANY, NONE")
    while (true) {
        when (val input = readln().trim().uppercase()) { // Trim whitespace and normalize input
            "ALL", "ANY", "NONE" -> return input // Valid input; return the strategy
            else -> println("Invalid option! Please enter one of: ALL, ANY, NONE")
        }
    }
}
