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
        // при использовании infix и выражения table contains 80 на что-то ругается
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


    // ничего не работает ;_;
    /*
    @Test
    fun loadfactor() {
        val table = HashTable(23,32,43,65,6567,7655,7876754,6565,5367,2456,6773,667,43652,5636,36635,5356)
        assertEquals(0.64, table.loadFactor())
        table.add(1,2,3,4,5,6,7,8,9,10,11)
        assertEquals(0.5, table.loadFactor())
    }
    */

}