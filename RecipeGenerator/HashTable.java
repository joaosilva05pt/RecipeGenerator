import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HashTable<K, V> {
    private Map<K, Set<V>> table; // Mapa que associa cada chave a um conjunto de valores

    // Construtor que inicializa o mapa
    public HashTable() {
        table = new HashMap<>();
    }

    // Adiciona um valor ao conjunto associado a uma chave
    public void put(K key, V value) {
        table.computeIfAbsent(key, k -> new HashSet<>()).add(value);
    }

    // Retorna o conjunto de valores associado à chave, ou um conjunto vazio se não existir
    public Set<V> get(K key) {
        return table.getOrDefault(key, new HashSet<>());
    }

    // Retorna o conjunto de todas as chaves do mapa
    public Set<K> keySet() {
        return table.keySet();
    }

    // Verifica se uma chave existe no mapa
    public boolean containsKey(K key) {
        return table.containsKey(key);
    }

    // Retorna o mapa completo
    public Map<K, Set<V>> getTable() {
        return table;
    }
}
