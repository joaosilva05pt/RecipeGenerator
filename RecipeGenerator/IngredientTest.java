import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientTest {

    // ===== UNIT TESTS =====

    @Test
    public void testConstructorAndGetName() {
        Ingredient ing = new Ingredient("  Onion ");
        assertEquals("onion", ing.getName());
    }

    @Test
    public void testEqualsWithSameName() {
        Ingredient ing1 = new Ingredient("Garlic");
        Ingredient ing2 = new Ingredient("garlic");
        assertEquals(ing1, ing2);
    }

    @Test
    public void testEqualsWithDifferentName() {
        Ingredient ing1 = new Ingredient("ginger");
        Ingredient ing2 = new Ingredient("turmeric");
        assertNotEquals(ing1, ing2);
    }

    @Test
    public void testEqualsWithNullAndOtherObject() {
        Ingredient ing = new Ingredient("lemon");
        assertNotEquals(ing, null);
        assertNotEquals(ing, "lemon");
    }

    @Test
    public void testHashCodeConsistentWithEquals() {
        Ingredient ing1 = new Ingredient("Pepper");
        Ingredient ing2 = new Ingredient("pepper");
        assertEquals(ing1.hashCode(), ing2.hashCode());
    }

    @Test
    public void testToString() {
        Ingredient ing = new Ingredient("Tomato");
        assertEquals("tomato", ing.toString());
    }

    // ===== SCENARIO TEST =====

    @Test
    public void testScenario_AddIngredientsToSet_AvoidDuplicates() {
        Set<Ingredient> ingredients = new HashSet<>();

        ingredients.add(new Ingredient("chicken"));
        ingredients.add(new Ingredient("Chicken"));
        ingredients.add(new Ingredient("  CHICKEN "));
        ingredients.add(new Ingredient("onion"));
        ingredients.add(new Ingredient("garlic"));

        // Deve eliminar duplicados ("chicken")
        assertEquals(3, ingredients.size());

        assertTrue(ingredients.contains(new Ingredient("chicken")));
        assertTrue(ingredients.contains(new Ingredient("onion")));
        assertTrue(ingredients.contains(new Ingredient("garlic")));
    }
}
