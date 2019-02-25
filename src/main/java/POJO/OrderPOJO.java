package POJO;

public class OrderPOJO {

    private String drinkType;
    private int numberOfSugars;

    public OrderPOJO(){}

    public OrderPOJO(String drinkType, int numberOfSugars) {
        this.drinkType = drinkType;
        this.numberOfSugars = numberOfSugars;

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
