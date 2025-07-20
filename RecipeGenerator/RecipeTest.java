import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeTest {

    // ===== UNIT TESTS =====

    @Test
    public void testConstructorAndGetters() {
        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(new Ingredient("onion"));
        ingredients.add(new Ingredient("chicken"));

        Recipe recipe = new Recipe("r001", ingredients);

        assertEquals("r001", recipe.getName());
        assertEquals(ingredients, recipe.getIngredients());
    }

    @Test
    public void testToString() {
        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(new Ingredient("garlic"));
        ingredients.add(new Ingredient("butter"));

        Recipe recipe = new Recipe("r002", ingredients);
        String output = recipe.toString();

        assertTrue(output.contains("r002"));
        assertTrue(output.contains("garlic"));
        assertTrue(output.contains("butter"));
    }

    // ===== SCENARIO TEST =====

    @Test
    public void testScenario_MultipleRecipesWithSharedIngredients() {
        Set<Ingredient> ingredients1 = Set.of(
                new Ingredient("chicken"),
                new Ingredient("onion"),
                new Ingredient("ginger")
        );

        Set<Ingredient> ingredients2 = Set.of(
                new Ingredient("butter"),
                new Ingredient("onion"),
                new Ingredient("garlic")
        );

        Recipe r1 = new Recipe("Chicken Curry", ingredients1);
        Recipe r2 = new Recipe("Butter Garlic", ingredients2);

        assertTrue(r1.getIngredients().contains(new Ingredient("ginger")));
        assertTrue(r2.getIngredients().contains(new Ingredient("garlic")));
        assertFalse(r1.getIngredients().contains(new Ingredient("garlic")));

        assertEquals("Chicken Curry", r1.getName());
        assertEquals("Butter Garlic", r2.getName());
    }
}
