public class Flower {

    //Instance Variables
    private String name;
    private int numPetals;
    private float price;

    Flower(String name, int numPetals, float price){
        this.name = name;
        this.numPetals= numPetals;
        this.price=price;

    }
    public String getName() {
        return name;
    }

    public int getNumPetals() {
        return numPetals;
    }

    public float getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumPetals(int numPetals) {
        this.numPetals = numPetals;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
