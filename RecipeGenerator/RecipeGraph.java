import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.ArrayList;

public class RecipeGraph {
    // Tabela de adjacência que guarda os vizinhos (ingredientes relacionados) de cada ingrediente
    private HashTable<Ingredient, Ingredient> adjacencyList = new HashTable<>();

    public void buildGraph(List<Recipe> recipes) {
        for (Recipe recipe : recipes) {
            // Converte o conjunto de ingredientes da receita numa lista para indexação
            List<Ingredient> ingList = new ArrayList<>(recipe.getIngredients());
            for (int i = 0; i < ingList.size(); i++) {
                for (int j = i + 1; j < ingList.size(); j++) {
                    adjacencyList.put(ingList.get(i), ingList.get(j));
                    adjacencyList.put(ingList.get(j), ingList.get(i));
                }
            }
        }
    }

    public Set<Ingredient> getNeighbors(Ingredient ingredient) {
        return adjacencyList.get(ingredient);
    }

    public boolean pathExists(Ingredient start, Ingredient target, Set<Ingredient> available) {
        Queue<Ingredient> queue = new LinkedList<>();
        Set<Ingredient> visited = new HashSet<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Ingredient current = queue.poll();
            // Caminho encontrado
            if (current.equals(target)) return true;
            // Visita os vizinhos do ingrediente atual
            for (Ingredient neighbor : getNeighbors(current)) {
                // Adiciona à fila apenas vizinhos ainda não visitados e que estejam disponíveis
                if (!visited.contains(neighbor) && (available.contains(neighbor) || neighbor.equals(target))) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return false;
    }
}
