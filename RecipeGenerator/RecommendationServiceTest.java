import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class RecommendationServiceTest {

    private Ingredient chicken, onion, garlic, pepper, tomato, pasta, salt;
    private Recipe r1, r2, r3;
    private List<Recipe> recipeList;

    @BeforeEach
    public void setUp() {
        chicken = new Ingredient("chicken");
        onion = new Ingredient("onion");
        garlic = new Ingredient("garlic");
        pepper = new Ingredient("pepper");
        tomato = new Ingredient("tomato");
        pasta = new Ingredient("pasta");
        salt = new Ingredient("salt");

        r1 = new Recipe("r1", Set.of(chicken, onion, garlic));
        r2 = new Recipe("r2", Set.of(tomato, pasta, salt));
        r3 = new Recipe("r3", Set.of(onion, pepper));

        recipeList = List.of(r1, r2, r3);
    }


    private String captureOutput(Runnable block) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));
        try {
            block.run();
        } finally {
            System.setOut(originalOut);
        }
        return out.toString();
    }

    // ========== UNIT TESTS ==========

    @Test
    public void testNoCompleteRecipes() {
        Set<Ingredient> available = Set.of(pepper);

        RecommendationService service = new RecommendationService(recipeList);
        String output = captureOutput(() -> service.recommend(available));

        assertTrue(output.contains("Receitas completas:"));
        assertFalse(output.contains("r1"));
        assertFalse(output.contains("r2"));
        assertFalse(output.contains("r3"));
    }

    @Test
    public void testOneCompleteRecipePrinted() {
        Set<Ingredient> available = Set.of(onion, pepper);

        RecommendationService service = new RecommendationService(recipeList);
        String output = captureOutput(() -> service.recommend(available));

        assertTrue(output.contains("Receitas completas:"));
        assertTrue(output.contains("r3"));
        assertFalse(output.contains("r1"));
        assertFalse(output.contains("r2"));
    }

    @Test
    public void testIncompleteRecipeSuggestedWhenMissingAtMostTwo() {
        Set<Ingredient> available = Set.of(onion);

        RecommendationService service = new RecommendationService(recipeList);
        String output = captureOutput(() -> service.recommend(available));

        assertTrue(output.contains("Receitas incompletas"));
        assertTrue(output.contains("Se comprar [garlic, chicken] pode cozinhar r1"));
    }

    @Test
    public void testIncompleteRecipeNotSuggestedIfNoOverlap() {
        Set<Ingredient> available = Set.of(salt); // Not part of r1 or r3

        RecommendationService service = new RecommendationService(recipeList);
        String output = captureOutput(() -> service.recommend(available));

        assertFalse(output.contains("pode cozinhar r1"));
        assertFalse(output.contains("pode cozinhar r3"));
    }

    // ========== SCENARIO TEST ==========

    @Test
    public void testScenario_CompleteAndIncompleteRecommendations() {
        Set<Ingredient> available = Set.of(chicken, onion, garlic, tomato);

        RecommendationService service = new RecommendationService(recipeList);
        String output = captureOutput(() -> service.recommend(available));

        assertTrue(output.contains("Receitas completas:"));
        assertTrue(output.contains("r1")); 
        assertFalse(output.contains("r2")); 

        assertTrue(output.contains("pode cozinhar r2"));
        assertFalse(output.contains("r3")); }
}