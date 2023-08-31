package variant1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Random;

public class ToyCollection {
    public static void main(String[] args) {
        String input = "1|Lego|0.2\n2|Transformers Set|0.2\n3|Doll|0.6";
        String[] lines = input.split("\n");

        PriorityQueue<Toy> toyQueue = new PriorityQueue<>((t1, t2) -> Double.compare(t2.getDropFrequency(), t1.getDropFrequency()));

        for (String line : lines) {
            String[] parts = line.split("\\|");
            if (parts.length >= 3) {
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double dropFrequency = Double.parseDouble(parts[2]);
                toyQueue.add(new Toy(id, name, dropFrequency));
            }
        }

        Random random = new Random();
        try (FileWriter fileWriter = new FileWriter("variant1/results.txt")) {
            for (int i = 0; i < 10; i++) {
                double randomValue = random.nextDouble();
                Toy selectedToy = null;

                double cumulativeFrequency = 0.0;
                for (Toy toy : toyQueue) {
                    cumulativeFrequency += toy.getDropFrequency();
                    if (randomValue <= cumulativeFrequency) {
                        selectedToy = toy;
                        break;
                    }
                }

                if (selectedToy != null) {
                    fileWriter.write(selectedToy.getId() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
