public class Ingredient {
    private String name; // Nome do ingrediente

    // Construtor que normaliza o nome do ingrediente
    public Ingredient(String name) {
        this.name = name.trim().toLowerCase();
    }

    // Retorna o nome do ingrediente
    public String getName() {
        return name;
    }

    // Compara dois ingredientes com base no nome
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Ingredient) {
            return this.name.equals(((Ingredient) obj).name);
        }
        return false;
    }

    // Gera o código hash com base no nome
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    // Retorna o nome como representação textual
    @Override
    public String toString() {
        return name;
    }
}
