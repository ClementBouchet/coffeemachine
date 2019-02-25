package POJO;

public class OrderPOJO {

    private String drinkType;
    private int numberOfSugars;
    private double money;

    public OrderPOJO(){}

    public OrderPOJO(String drinkType, int numberOfSugars, double money) {
        this.drinkType = drinkType;
        this.numberOfSugars = numberOfSugars;
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getNumberOfSugars() {
        return numberOfSugars;
    }

    public void setNumberOfSugars(int numberOfSugars) {
        this.numberOfSugars = numberOfSugars;
    }

    public String getDrinkType() {
        return drinkType;
    }

    public void setDrinkType(String drinkType) {
        this.drinkType = drinkType;
    }
}
