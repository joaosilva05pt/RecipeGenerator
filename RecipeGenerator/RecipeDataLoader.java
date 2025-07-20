import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

public class RecipeDataLoader {
    private String filePath; // Caminho para o ficheiro de receitas

    public RecipeDataLoader(String filePath) {
        this.filePath = filePath; // Inicializa o caminho do ficheiro
    }

    public List<Recipe> loadRecipes() {
        List<Recipe> recipes = new ArrayList<>(); // Lista onde as receitas serão armazenadas

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Separa o nome da receita dos ingredientes
                String recipeName = parts[0]; // Primeiro elemento é o nome da receita
                Set<Ingredient> ingredients = new HashSet<>(); // Conjunto de ingredientes
                // Adiciona os ingredientes ao conjunto
                for (int i = 1; i < parts.length; i++) {
                    ingredients.add(new Ingredient(parts[i]));
                }
                // Cria uma nova receita e adiciona à lista
                recipes.add(new Recipe(recipeName, ingredients));
            }
        } catch (IOException e) {
             // Trata erros de leitura de ficheiro
            System.out.println("Erro ao carregar receitas: " + e.getMessage());
        }

        return recipes; // Retorna a lista de receitas carregadas
    }
}
