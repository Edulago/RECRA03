import java.util.Random;
import java.util.HashMap;

public class HashApp {

    public static void main(String[] args) {
        long seed = (new Random()).nextLong();
        int[] testSizes = {40000, 200000, 1000000, 2000000, 10000000};
        int[] testTimes = {20000, 100000, 500000, 1000000, 5000000};

        for (int i = 0; i < 5; i++) {
            Funcao funcao = new Funcao(testSizes[i]);
            test(funcao, "Multiplicacao", seed, testTimes[i]);
        }
        System.out.println();

        for (int i = 0; i < 5; i++) {
            Funcao funcao = new Funcao(testSizes[i]);
            test(funcao, "Hash XOR", seed, testTimes[i]);
        }
        System.out.println();

        for (int i = 0; i < 5; i++) {
            Funcao funcao = new Funcao(testSizes[i]);
            test(funcao, "MurmurHash3Difusao", seed, testTimes[i]);
        }
        System.out.println();

        for (int i = 0; i < 5; i++) {
            Funcao funcao = new Funcao(testSizes[i]);
            test(funcao, "Hash Divisão", seed, testTimes[i]);
        }
        System.out.println();

        System.out.println("Encerrado.");
    }

    public static void test(Funcao funcao, String name, long seed, int times) {
        long startTime, endTime;
        Registro[] keys = new Registro[times];
        Registro[] values = new Registro[times];
        Random rand = new Random(seed);

        int size = 9;
        int[] digits = new int[size];

        for (int i = 0; i < times; i++) {
            for (int j = 0; j < size; j++) {
                digits[j] = (int) Math.floor(rand.nextDouble() * 9);
            }
            keys[i] = new Registro(size);
            keys[i].setDigits(digits);

            for (int j = 0; j < size; j++) {
                digits[j] = (int) Math.floor(rand.nextDouble() * 9);
            }
            values[i] = new Registro(size);
            values[i].setDigits(digits);
        }

        startTime = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            int key = keys[i].getValue();
            int value = values[i].getValue();

            int hashResult = funcao.hashMultiplicao(key);
            funcao.put(hashResult, value);
        }
        endTime = System.currentTimeMillis();

        System.out.println("###################################");
        System.out.println("Inserção hash " + name);
        System.out.println("Tempo: " + (endTime - startTime) + "ms");
        System.out.println("Operações: " + times);
        System.out.println("Colisões: " + funcao.getCollisions());
        System.out.println("Tamanho: " + funcao.size());
        System.out.println("##################################");
    }
}