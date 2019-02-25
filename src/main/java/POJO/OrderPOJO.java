package POJO;

public class OrderPOJO {

    private String drinkType;
    private int numberOfSugars;
    private double money;
    private boolean extraHotOption;

    public OrderPOJO(){}

    public OrderPOJO(String drinkType, int numberOfSugars, double money, boolean extraHotOption) {
        this.drinkType = drinkType;
        this.numberOfSugars = numberOfSugars;
        this.money = money;
        this.extraHotOption=extraHotOption;
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

    public void setNumberOfSugars(int numberOfSugars) { this.numberOfSugars = numberOfSugars; }

    public String getDrinkType() {
        return drinkType;
    }

    public void setDrinkType(String drinkType) {
        this.drinkType = drinkType;
    }

    public boolean isExtraHotOption() {
        return extraHotOption;
    }

    public void setExtraHotOption(boolean extraHotOption) {
        this.extraHotOption = extraHotOption;
    }
}
