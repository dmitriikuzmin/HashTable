import java.lang.IllegalArgumentException

class HashTable(vararg numbers: Int) {

    private val size = 32

    private val table = arrayOfNulls<MutableList<Int>>(size)

    private fun hash(value: Int): Int = (value*57 + value) % size

    init {
        if (numbers.size > numbers.toSet().size) throw IllegalArgumentException("Повторяющиеся значения")
        for (i in 0 until numbers.size) {
            val index = hash(numbers[i])
            if ( table[index].isNullOrEmpty() ) {
                table[index] = mutableListOf(numbers[i])
            }
            else table[index]!!.add(numbers[i])
        }
    }

    operator fun contains(value: Int): Boolean {
        val index = hash(value)
        return if (table[index].isNullOrEmpty()) false
        else {
            for (i in 0 until table[index]!!.size) {
                if (table[index]!![i] == value) return true
            }
            false
        }
    }

    fun add(vararg numbers: Int) {
        for (i in 0 until numbers.size) {
            if (!this.contains(numbers[i])) {
            val index = hash(numbers[i])
                if ( table[index].isNullOrEmpty() ) {
                    table[index] = mutableListOf(numbers[i])
                }
                else table[index]!!.add(numbers[i])
            }
        }
    }

    fun remove(vararg numbers: Int) {
        for (i in 0 until numbers.size) {
            if (this.contains(numbers[i])) {
                table[hash(numbers[i])] = null
            }
        }
    }


    override fun equals(other: Any?): Boolean {
        if (other is HashTable && this.size == other.size) {
            for (i in 0 until this.size) {
                if (!table[i].isNullOrEmpty()) {
                    if (this.table[i] == other.table[i]) continue
                    else return false
                }
            }
            return true
        } else return false
    }

    override fun toString(): String {
        var s = ""
        for (i in 0 until table.size)  {
            if (!table[i].isNullOrEmpty())
            s += "[$i -> " + table[i].toString().replace(Regex("\\D"), "") + "], "
            s.trim()
        }
        return s.trim()
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