import kotlin.random.Random

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    // 1-ая задача
    println("Сумма ряда = ${sumOfRow()}")

    // 2-ая задача
    println("Максимальная высота, которую может иметь грузовик = ${maximumTruckHeight()}")

    // 3-я задача
    twiceEven()

    // 4-ая задача
    println("Наибольшая подстрока, в которой все символы уникальны: ${largestSubstring()}")

    // 5-ая задача
    println("Введите количество строк матрицы: ")
    val rows: Int = readln().toInt()
    println("Введите количество столбцов матрицы: ")
    val columns: Int = readln().toInt()
    val randomMatrix = Array<Array<Int>>(rows){Array<Int>(columns){ Random.nextInt(1, 100)} }

    val resultArray = findMaxInEachRow(randomMatrix)
    println(resultArray.joinToString(", "))
}

// 1-ая задача
fun sumOfRow(): Int {
    println("Введите количество чисел: ")
    val n: Int = readln().toInt()
    if (n <= 0) {
        return 0
    }

    val numbers: Array<Int> = Array(n, {0})
    var sum: Int = 0

    for (i in 0..n-1) {
        println("Введите число ${i+1}")
        numbers[i] = readln().toInt()
    }

    for (i in 0..numbers.size - 1) {
        if ((i+1) % 2 == 0) {
            sum -= numbers[i]
        }
        else sum += numbers[i]
    }

    return sum
}


// 2-ая задача
fun maximumTruckHeight():Double {

    println("Введите количество дорог:")
    val roadCount:Int = readln().toInt()
    val minHeightOfEachRoad: Array<Double> = Array(roadCount, {0.0})
    var maxHeightOfTrack: Double = 0.0

    for (roadIndex in 0..roadCount - 1) {
        var minHeight: Double = Double.POSITIVE_INFINITY
        println("Дорога ${roadIndex + 1}:")
        println("Введите количество туннелей:")
        val tunnelCount: Int = readln().toInt()

        val tunnelHeights = mutableListOf<Double>()

        for (tunnelIndex in 0..tunnelCount - 1) {
            println("Введите высоту туннеля ${tunnelIndex + 1} в сантиметрах:")
            val height: Double = readln().toDouble()
            tunnelHeights.add(height)
            if (tunnelHeights.get(tunnelIndex) < minHeight) minHeight = tunnelHeights.get(tunnelIndex)
        }

        minHeightOfEachRoad[roadIndex] = minHeight

        println("Высоты туннелей для дороги ${roadIndex + 1}: ${tunnelHeights.joinToString(", ")}\n")
    }

    for (h in 0..minHeightOfEachRoad.size - 1) {
        if (minHeightOfEachRoad[h] >= maxHeightOfTrack) maxHeightOfTrack = minHeightOfEachRoad[h]
    }

    return maxHeightOfTrack
}


// 3-я задача
fun twiceEven() {
    println("Введите трехзначное число: ")
    var number: Int = readln().toInt()
    if (number > 999 || number < 100) {
        println("Incorrect expression")
    }

    var sumOfNumbers: Int = 0
    var multiplyOfNumbers: Int = 1

    while (number > 0) {
        sumOfNumbers += number % 10
        multiplyOfNumbers *= number % 10
        number = number / 10
    }

    if (sumOfNumbers % 2 == 0 && multiplyOfNumbers % 2 == 0) {
        println("twice even number")
    }
    else println("not twice even number")
}


// 4-ая задача
fun largestSubstring():String {
    println("Введите строку: ")
    val str: String = readln()
    val subStr = mutableListOf<Char>()
    var maxLength: Int = 0
    var result: String = ""

    for (index in str.indices) {
        if (subStr.contains(str.get(index))) {
            if (subStr.size >= maxLength) {
                maxLength = subStr.size
                result = subStr.toString()
                subStr.clear()
            }
        }
        subStr.add(str.get(index))
        result = subStr.toString()
    }

    return result
}


// 5-ая задача
fun findMaxInEachRow(matrix: Array<Array<Int>>): IntArray {
    val result = IntArray(matrix.size)

    for (row in matrix.indices) {
        for (column in matrix[row].indices) {
            print(" " + matrix[row][column])
        }
        println()
    }

    for (i in matrix.indices) {
        var maxElement: Int = 0
        for (j in matrix[i].indices) {
            if (matrix[i][j] > maxElement) maxElement = matrix[i][j]
        }
        result[i] = maxElement
    }

    return result
}