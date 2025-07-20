import java.util.Set;

public class Recipe {
    private String name; // Nome da receita
    private Set<Ingredient> ingredients; // Conjunto de ingredientes da receita

     // Construtor que inicializa o nome e os ingredientes da receita
    public Recipe(String name, Set<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    // Retorna o nome da receita
    public String getName() {
        return name;
    }

    // Retorna o conjunto de ingredientes da receita
    public Set<Ingredient> getIngredients() {
        return ingredients;
    }
    
    // Representação textual da receita (nome + ingredientes)
    @Override
    public String toString() {
        return name + " " + ingredients;
    }
}
