package Repository;

public class Repository {

    public Repository() {
    }

    private double totalAmountOfMoney;
    private int numberOfCoffeeSold;
    private int numberOfTeaSold;
    private int numberOfChocolateSold;
    private int numberOfOrangeJuiceSold;

    public double getTotalAmountOfMoney() {
        return totalAmountOfMoney;
    }

    public void setTotalAmountOfMoney(double totalAmountOfMoney) {
        this.totalAmountOfMoney = totalAmountOfMoney;
    }

    public int getNumberOfCoffeeSold() {
        return numberOfCoffeeSold;
    }

    public void setNumberOfCoffeeSold(int numberOfCoffeeSold) {
        this.numberOfCoffeeSold = numberOfCoffeeSold;
    }

    public int getNumberOfTeaSold() {
        return numberOfTeaSold;
    }

    public void setNumberOfTeaSold(int numberOfTeaSold) {
        this.numberOfTeaSold = numberOfTeaSold;
    }

    public int getNumberOfChocolateSold() {
        return numberOfChocolateSold;
    }

    public void setNumberOfChocolateSold(int numberOfChocolateSold) {
        this.numberOfChocolateSold = numberOfChocolateSold;
    }

    public int getNumberOfOrangeJuiceSold() {
        return numberOfOrangeJuiceSold;
    }

    public void setNumberOfOrangeJuiceSold(int numberOfOrangeJuiceSold) {
        this.numberOfOrangeJuiceSold = numberOfOrangeJuiceSold;
    }
}
