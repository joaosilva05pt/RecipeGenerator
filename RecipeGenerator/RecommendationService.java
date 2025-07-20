import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class RecommendationService {
    private List<Recipe> recipes;
    private RecipeGraph graph;

    public RecommendationService(List<Recipe> recipes) {
        this.recipes = recipes;
        this.graph = new RecipeGraph();
        graph.buildGraph(recipes);
    }

    public void recommend(Set<Ingredient> available) {
    System.out.println("Receitas completas:");
    for (Recipe recipe : recipes) {
        if (available.containsAll(recipe.getIngredients())) {
            System.out.println(recipe);
        }
    }

    System.out.println("\nReceitas incompletas (faltam no máximo 2 ingredientes):");
    for (Recipe recipe : recipes) {
        Set<Ingredient> missing = new HashSet<>(recipe.getIngredients());
        missing.removeAll(available);

        // Só mostra se faltar no máximo 2 ingredientes
        if (!missing.isEmpty() && missing.size() <= 2) {
            // Só mostra se o utilizador tem pelo menos 1 ingrediente da receita
            boolean contemAlgum = false;
            for (Ingredient ing : available) {
                if (recipe.getIngredients().contains(ing)) {
                    contemAlgum = true;
                    break;
                }
            }
            if (contemAlgum) {
                System.out.print("Se comprar " + missing + " pode cozinhar " + recipe.getName() + ": ");
                System.out.println(recipe.getIngredients());
            }
        }
    }
}
}
