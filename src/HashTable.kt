class HashTable(vararg numbers: Int) {

    private var size = 32

    private val table = arrayOfNulls<MutableList<Int>>(size)

    private fun hash(value: Int): Int = value % size

    //Тоже самое в виде свойства почему то не работает
    fun loadFactor(): Double = this.values().size.toDouble() / (size * 5.0)

    init {
        while (numbers.size / (size * 5) >= 0.7) {
            size *= 2
        }
        val values = numbers.toSet().toIntArray()
        for (i in 0 until values.size) {
            val index = hash(values[i])
            if (table[index].isNullOrEmpty()) {
                table[index] = mutableListOf(values[i])
            } else table[index]!!.add(values[i])
        }
    }

    fun values(): List<Int> {
        val values = mutableListOf<Int>()
        for (i in 0 until table.size) {
            if (!table[i].isNullOrEmpty()) {
                for (j in 0 until table[i]!!.size) {
                    values.add(table[i]!![j])
                }
            }
        }
        return values.sorted()
    }

    operator fun contains(value: Int): Boolean = this.values().contains(value)

    fun add(vararg numbers: Int) {
        // не знаю как это нормально сделать
        /*
        while (this.loadFactor() + (numbers.size.toDouble() / (size * 5)) >= 0.7) {
            size *= 2
        }
        */
        for (i in 0 until numbers.size) {
            if (!this.contains(numbers[i])) {
                val index = hash(numbers[i])
                if (table[index].isNullOrEmpty()) {
                    table[index] = mutableListOf(numbers[i])
                } else table[index]!!.add(numbers[i])
            }
        }
    }

    fun remove(vararg numbers: Int) {
        for (i in 0 until numbers.size) {
            if (this.contains(numbers[i])) {
                table[hash(numbers[i])]!!.remove(numbers[i])
            }
        }
    }

    override fun equals(other: Any?): Boolean = other is HashTable && this.size == other.size && this.values() == other.values()

    // Не знаю как лучше
    override fun toString(): String {
        var str = ""
        for (i in 0 until this.size) {
            if (!table[i].isNullOrEmpty()) {
                str += "[$i -> " + table[i]!!.joinToString(separator = ",") + "] "
            }
        }
        return str.trim()
    }
        /*
        val sb = StringBuilder()
        for (i in 0 until this.size) {
            if (!table[i].isNullOrEmpty()) {
                sb.append("[").append(i).append(" -> ")
                sb.append(table[i]!!.joinToString(separator = ","))
                sb.append("] ")

            }
        }
        return sb.toString().trim()
    }
*/

    override fun hashCode(): Int {
        var result = 0
        for (i in 0 until table.size) {
            if (!table[i].isNullOrEmpty()) {
                result += i * table[i]!!.sum()
            }
        }
        return result
    }

}