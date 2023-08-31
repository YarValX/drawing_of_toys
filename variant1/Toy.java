package variant1;

class Toy {
    private int id;
    private String name;
    private double dropFrequency;

    public Toy(int id, String name, double dropFrequency) {
        this.id = id;
        this.name = name;
        this.dropFrequency = dropFrequency;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getDropFrequency() {
        return dropFrequency;
    }
}