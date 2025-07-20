import org.junit.jupiter.api.Test;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class HashTableTest {

    // ===== UNIT TESTS =====

    @Test
    public void testPutAndGet() {
        HashTable<String, String> hashTable = new HashTable<>();
        hashTable.put("fruit", "banana");
        hashTable.put("fruit", "apple");

        Set<String> values = hashTable.get("fruit");
        assertEquals(2, values.size());
        assertTrue(values.contains("banana"));
        assertTrue(values.contains("apple"));
    }

    @Test
    public void testGetReturnsEmptySetIfKeyNotPresent() {
        HashTable<String, String> hashTable = new HashTable<>();
        Set<String> values = hashTable.get("nonexistent");
        assertNotNull(values);
        assertTrue(values.isEmpty());
    }

    @Test
    public void testContainsKey() {
        HashTable<String, String> hashTable = new HashTable<>();
        hashTable.put("animal", "cat");

        assertTrue(hashTable.containsKey("animal"));
        assertFalse(hashTable.containsKey("vehicle"));
    }

    @Test
    public void testKeySet() {
        HashTable<String, String> hashTable = new HashTable<>();
        hashTable.put("a", "1");
        hashTable.put("b", "2");
        hashTable.put("c", "3");

        Set<String> keys = hashTable.keySet();
        assertEquals(3, keys.size());
        assertTrue(keys.contains("a"));
        assertTrue(keys.contains("b"));
        assertTrue(keys.contains("c"));
    }

    @Test
    public void testGetTableReturnsCorrectMap() {
        HashTable<String, String> hashTable = new HashTable<>();
        hashTable.put("key", "value");

        assertEquals(1, hashTable.getTable().size());
        assertTrue(hashTable.getTable().get("key").contains("value"));
    }

    // ===== SCENARIO TEST =====

    @Test
    public void testScenario_AddIngredientsToRecipes() {
        HashTable<String, String> recipeIngredients = new HashTable<>();

        // Adiciona ingredientes a receitas
        recipeIngredients.put("r001", "chicken");
        recipeIngredients.put("r001", "onion");
        recipeIngredients.put("r001", "ginger");

        recipeIngredients.put("r002", "butter");
        recipeIngredients.put("r002", "garlic");

        // Verifica r001
        Set<String> r001 = recipeIngredients.get("r001");
        assertEquals(3, r001.size());
        assertTrue(r001.contains("chicken"));
        assertTrue(r001.contains("onion"));
        assertTrue(r001.contains("ginger"));

        // Verifica r002
        Set<String> r002 = recipeIngredients.get("r002");
        assertEquals(2, r002.size());
        assertTrue(r002.contains("butter"));
        assertTrue(r002.contains("garlic"));

        // Verifica chave inexistente
        assertTrue(recipeIngredients.get("r999").isEmpty());

        // Verifica chaves existentes
        Set<String> keys = recipeIngredients.keySet();
        assertEquals(2, keys.size());
        assertTrue(keys.contains("r001"));
        assertTrue(keys.contains("r002"));
    }
}
