import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class HashTableTest {
    @Test
    fun hashTable() {
        val table = HashTable(5,3,4,2)
        assertEquals(table, HashTable(5,3,4,2))
        assertEquals(table, HashTable(4,3,5,2))
    }

    @Test
    fun add() {
        val table = HashTable()
        table.add(8,6,7,5)
        assertEquals(table, HashTable(5,7,6,8))
        table.add(7,8,9)
        assertEquals(table, HashTable(5,7,6,8,9))
    }

    @Test
    fun contains() {
        val table = HashTable(40,80,50,22,3)
        assertTrue (table.contains(40))
        assertTrue (80 in table)
        assertTrue(90 in HashTable(90,290,2938))
    }

    @Test
    fun remove() {
        val table = HashTable(33,24,50,22,3)
        table.remove(33,24)
        assertEquals(table, HashTable(50,22,3))
    }

    @Test
    fun equals() {
        val table = HashTable(12,4,6,16)
        assertFalse(table == HashTable(4,12,6,15))
        assertTrue(table == HashTable(4,12,6,16))
        table.add(20,24)
        assertTrue(table == HashTable(20,4,12,6,16,24))
    }

}