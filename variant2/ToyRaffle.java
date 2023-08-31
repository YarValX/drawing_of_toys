package variant2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Random;

public class ToyRaffle {
    public static void main(String[] args) {
        // Создаем массив игрушек
        Toys[] toys = {
                new Toys(1, "робот", 2),
                new Toys(2, "конструктор", 2),
                new Toys(3, "кукла", 6)
        };

        // Создаем приоритетную очередь на основе весов игрушек
        PriorityQueue<Toys> toyQueue = new PriorityQueue<>((t1, t2) -> t2.weight - t1.weight);

        // Заполняем приоритетную очередь игрушками
        for (Toys toy : toys) {
            toyQueue.offer(toy);
        }

        // Вызываем Get 10 раз и записываем результат в файл
        try {
            FileWriter fileWriter = new FileWriter("variant2/results.txt");

            for (int i = 0; i < 10; i++) {
                String result = getRandomToy(toyQueue);
                fileWriter.write(result + "\n");
            }

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getRandomToy(PriorityQueue<Toys> toyQueue) {
        Random random = new Random();
        int totalWeight = toyQueue.stream().mapToInt(t -> t.weight).sum();
        int randomWeight = random.nextInt(totalWeight);
        int currentWeight = 0;

        for (Toys toy : toyQueue) {
            currentWeight += toy.weight;
            if (randomWeight < currentWeight) {
                return Integer.toString(toy.id);
            }
        }

        // If there's an issue, return default id
        return Integer.toString(toyQueue.peek().id);
    }
}