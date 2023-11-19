import java.util.HashMap;

class Funcao {

    private int size, collisions;
    private HashMap<Integer, Integer> table;

    public Funcao(int size) {
        this.size = size;
        this.collisions = 0;
        this.table = new HashMap<>(size);
    }

    public int hashXOR(int value) {
        int hash = 0;

        while (value != 0) {
            hash ^= (value & 0xFF);
            value >>>= 8;
        }

        return hash;
    }

    public int HashDivisao(int value) {
        return value % size;
    }

    public int hashMultiplicao(int value) {
        final long A = 2654435761L; // Constante mágica para multiplicação
        return (int) ((value * A) & 0x7FFFFFFF);
    }

    public void put(int key, int value) {
        if (!isFull()) {
            int pos = Index(key);
            if (table.containsKey(pos)) {
                do {
                    pos = rehash(pos);
                    collisions++;
                } while (table.containsKey(pos));
            }
            table.put(pos, value);
        }
    }

    public int size() {
        return table.size();
    }

    public int getCollisions() {
        return collisions;
    }

    private boolean isFull() {
        return size() >= size;
    }

    private int rehash(int value) {
        return (value + 1) % size;
    }

    private int Index(int value) {
        return Math.abs(value) % size;
    }
}