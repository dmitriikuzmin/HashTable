class HashTable(vararg numbers: Int) {

    private var size = 32

    private var table = arrayOfNulls<MutableList<Int>>(size)

    private fun hash(value: Int): Int = value % size

    fun loadFactor(): Double = this.values().size.toDouble() / (size * 3.0)

    init {
        while (numbers.size / (size * 2) >= 0.7) {
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
        return values
    }

    operator fun contains(value: Int): Boolean {
        val hash = hash(value)
        return if (!table[hash].isNullOrEmpty()) {
            table[hash]!!.contains(value)
        } else false
    }

    fun add(vararg numbers: Int) {
        while (this.loadFactor() + (numbers.size.toDouble() / (size * 2)) >= 0.7) {
            size *= 2
        }
        val allValues = this.values().toIntArray() + numbers.toSet().toIntArray()
        //HashTable(*smth)
        for (i in 0 until allValues.size) {
            if (!this.contains(allValues[i])) {
                val index = hash(allValues[i])
                if (table[index].isNullOrEmpty()) {
                    table[index] = mutableListOf(allValues[i])
                } else table[index]!!.add(allValues[i])
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

    override fun equals(other: Any?): Boolean {
        if (other is HashTable && this.size == other.size) {
            for ( i in 0 until this.size) {
                if (this.table[i].isNullOrEmpty() != other.table[i].isNullOrEmpty()) return false
                else {
                    if (!table[i].isNullOrEmpty()) {
                        if (this.table[i]!!.sorted() != other.table[i]!!.sorted()) return false
                    }
                }
            }
            return true
        } else return false
    }

    override fun toString(): String {
        val sb = StringBuilder()
        for (i in 0 until this.size) {
            if (!table[i].isNullOrEmpty()) {
                sb.append("[").append(i).append(" -> ").append(table[i]!!.joinToString(separator = ",")).append("] ")
            }
        }
        return sb.toString().trim()
    }

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