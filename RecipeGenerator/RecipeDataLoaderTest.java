import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeDataLoaderTest {

    // ===== UNIT TEST =====

    @Test
    public void testLoadRecipesFromValidFile(@TempDir Path tempDir) throws Exception {
        File tempFile = tempDir.resolve("receitas.txt").toFile();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("r001,chicken,onion,ginger\n");
            writer.write("r002,butter,garlic\n");
        }

        RecipeDataLoader loader = new RecipeDataLoader(tempFile.getAbsolutePath());
        List<Recipe> recipes = loader.loadRecipes();

        assertEquals(2, recipes.size());

        Recipe r1 = recipes.get(0);
        assertEquals("r001", r1.getName());
        assertEquals(Set.of(new Ingredient("chicken"), new Ingredient("onion"), new Ingredient("ginger")), r1.getIngredients());

        Recipe r2 = recipes.get(1);
        assertEquals("r002", r2.getName());
        assertTrue(r2.getIngredients().contains(new Ingredient("butter")));
        assertTrue(r2.getIngredients().contains(new Ingredient("garlic")));
    }

    // ===== SCENARIO TEST =====

    @Test
    public void testScenario_LoadMultipleRecipesAndVerifyContents(@TempDir Path tempDir) throws Exception {
        File file = tempDir.resolve("receitas_scenario.txt").toFile();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("r010,apple,banana\n");
            writer.write("r011,banana,orange\n");
            writer.write("r012,apple,orange,grape\n");
        }

        RecipeDataLoader loader = new RecipeDataLoader(file.getAbsolutePath());
        List<Recipe> recipes = loader.loadRecipes();

        assertEquals(3, recipes.size());

        assertEquals("r010", recipes.get(0).getName());
        assertTrue(recipes.get(0).getIngredients().contains(new Ingredient("banana")));

        assertEquals("r011", recipes.get(1).getName());
        assertTrue(recipes.get(1).getIngredients().contains(new Ingredient("orange")));

        assertEquals("r012", recipes.get(2).getName());
        assertEquals(3, recipes.get(2).getIngredients().size());
    }

    @Test
    public void testLoadFromNonExistentFile() {
        RecipeDataLoader loader = new RecipeDataLoader("nonexistent.txt");

       
        List<Recipe> recipes = loader.loadRecipes();
        assertNotNull(recipes);
        assertTrue(recipes.isEmpty());
    }
}
