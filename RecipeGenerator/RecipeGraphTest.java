import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeGraphTest {

    private RecipeGraph graph;

    @BeforeEach
    public void setUp() {
        graph = new RecipeGraph();
    }

    // ========== UNIT TESTS ==========

    @Test
    public void testBuildGraphCreatesBidirectionalLinks() {
        Ingredient i1 = new Ingredient("chicken");
        Ingredient i2 = new Ingredient("onion");
        Ingredient i3 = new Ingredient("ginger");

        Set<Ingredient> ingredients = new HashSet<>(Arrays.asList(i1, i2, i3));
        Recipe recipe = new Recipe("r001", ingredients);

        graph.buildGraph(List.of(recipe));

        Set<Ingredient> neighborsOfChicken = graph.getNeighbors(i1);
        assertTrue(neighborsOfChicken.contains(i2));
        assertTrue(neighborsOfChicken.contains(i3));

        Set<Ingredient> neighborsOfOnion = graph.getNeighbors(i2);
        assertTrue(neighborsOfOnion.contains(i1));
        assertTrue(neighborsOfOnion.contains(i3));
    }

    @Test
    public void testGetNeighborsReturnsEmptyIfNoNeighbors() {
        Ingredient i1 = new Ingredient("salt");
        assertTrue(graph.getNeighbors(i1).isEmpty());
    }

    @Test
    public void testPathExistsDirectConnection() {
        Ingredient i1 = new Ingredient("a");
        Ingredient i2 = new Ingredient("b");

        Recipe recipe = new Recipe("r001", Set.of(i1, i2));
        graph.buildGraph(List.of(recipe));

        Set<Ingredient> available = new HashSet<>(Set.of(i1));
        assertTrue(graph.pathExists(i1, i2, available));
    }

    @Test
    public void testPathExistsIndirectConnection() {
        Ingredient a = new Ingredient("a");
        Ingredient b = new Ingredient("b");
        Ingredient c = new Ingredient("c");

        Recipe r1 = new Recipe("r1", Set.of(a, b));
        Recipe r2 = new Recipe("r2", Set.of(b, c));
        graph.buildGraph(List.of(r1, r2));

        Set<Ingredient> available = new HashSet<>(Set.of(a, b));
        assertTrue(graph.pathExists(a, c, available));
    }

    @Test
    public void testPathDoesNotExist() {
        Ingredient a = new Ingredient("a");
        Ingredient b = new Ingredient("b");

        Set<Ingredient> available = new HashSet<>(Set.of(a));
        assertFalse(graph.pathExists(a, b, available));
    }

    // ========== SCENARIO TEST ==========

    @Test
    public void testScenario_ComplexGraphAndPathFinding() {
        // Recipe 1: A, B, C
        // Recipe 2: C, D
        // Recipe 3: D, E
        // Recipe 4: F (isolated)
        Ingredient a = new Ingredient("a");
        Ingredient b = new Ingredient("b");
        Ingredient c = new Ingredient("c");
        Ingredient d = new Ingredient("d");
        Ingredient e = new Ingredient("e");
        Ingredient f = new Ingredient("f");

        List<Recipe> recipes = List.of(
                new Recipe("r1", Set.of(a, b, c)),
                new Recipe("r2", Set.of(c, d)),
                new Recipe("r3", Set.of(d, e)),
                new Recipe("r4", Set.of(f))
        );

        graph.buildGraph(recipes);

        Set<Ingredient> available = new HashSet<>(Set.of(a, b, c, d));

        assertTrue(graph.pathExists(a, e, available)); // a -> b -> c -> d -> e
        assertFalse(graph.pathExists(a, f, available)); // no path to f
    }
}
