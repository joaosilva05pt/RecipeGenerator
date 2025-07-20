import java.util.Scanner;
import java.util.Set;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RecipeDataLoader loader = new RecipeDataLoader("receitas.txt"); // Carregador de receitas a partir do ficheiro
        List<Recipe> recipes = loader.loadRecipes(); // Lista de receitas carregadas
        RecommendationService service = new RecommendationService(recipes); // Serviço de recomendação criado com as receitas

        System.out.println("Digite seus ingredientes disponíveis (separados por espaço):"); // Solicita ingredientes ao utilizador
        String input = scanner.nextLine();

        // Divide a string por espaços e transforma numa lista de objetos Ingredient
        String[] inputArray = input.trim().split("\\s+");

        Set<Ingredient> availableIngredients = Arrays.stream(inputArray)
                .map(String::trim) // Remove espaços extra
                .map(String::toLowerCase) // Converte para minúsculas
                .map(Ingredient::new) // Cria objeto Ingredient
                .collect(Collectors.toSet()); // Converte para conjunto

        service.recommend(availableIngredients); // Chama o sistema de recomendação com os ingredientes fornecidos
        scanner.close();
    }
}
