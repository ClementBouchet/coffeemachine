package metier;

import POJO.OrderPOJO;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class OrderController {

    public OrderController() {
    }

    private final static String TEA = "Tea";
    private final static String COFFEE = "Coffee";
    private final static String CHOCOLATE = "Chocolate";
    private final static String ORANGE_JUICE = "OrangeJuice";

    private final static double TEA_PRICE = 0.4;
    private final static double COFFEE_PRICE = 0.6;
    private final static double CHOCOLATE_PRICE = 0.5;
    private final static double ORANGE_JUICE_PRICE = 0.6;

    private String drink;
    private double drinkPrice;
    private String extraHotOption;

    //This is the method that will be called when the customer makes an order
    public boolean handleOrder(OrderPOJO orderPOJO){
        checkDrinkCase(orderPOJO);
        if(checkIfEnoughMoneyProvided(orderPOJO)){
            formatOrder(orderPOJO);
            sendOKMessage(orderPOJO);
            return true;
        }else {
            sendErrorMessage(calculateMissingMoney(orderPOJO.getMoney(), drinkPrice));
            return false;
        }
    }

    private boolean checkIfEnoughMoneyProvided(OrderPOJO orderPOJO){
        return orderPOJO.getMoney() >= drinkPrice;
    }

    public String formatOrder(OrderPOJO orderPOJO){
        int numberOfSugars = orderPOJO.getNumberOfSugars();
        System.out.println(drink+extraHotOption+":"+checkNumberOfSugars(numberOfSugars)+":"+ checkIfStick(numberOfSugars));
        return drink+extraHotOption+":"+checkNumberOfSugars(numberOfSugars)+":"+ checkIfStick(numberOfSugars);
    }

    private void checkDrinkCase(OrderPOJO orderPOJO){
        switch (orderPOJO.getDrinkType()) {
            case TEA:
                setParameters("T",TEA_PRICE, checkIfExtraHot(orderPOJO.isExtraHotOption()));
                break;
            case COFFEE:
                setParameters("C",COFFEE_PRICE, checkIfExtraHot(orderPOJO.isExtraHotOption()));
                break;
            case CHOCOLATE:
                setParameters("H",CHOCOLATE_PRICE,checkIfExtraHot(orderPOJO.isExtraHotOption()));
                break;
            case ORANGE_JUICE:
                setParameters("O",ORANGE_JUICE_PRICE, "");
                break;
        }
    }

    private void setParameters(String drink, double price, String extraHotOption){
        this.drink = drink;
        this.drinkPrice = price;
        this.extraHotOption = extraHotOption;
    }

    private String checkNumberOfSugars(int numberOfSugars){
        if(numberOfSugars >0){
            return Integer.toString(numberOfSugars);
        }else{
            return "";
        }
    }


    private String checkIfStick(int numberOfSugars){
        if(numberOfSugars > 0){
            return "0";
        }else{
            return "";
        }
    }

    private String checkIfExtraHot(boolean extraHotOption){
        if(extraHotOption){
            return "h";
        }else{
            return "";
        }
    }

    private void sendOKMessage(OrderPOJO orderPOJO){
        String stick;
        String extraHotOption;
        if(checkIfStick(orderPOJO.getNumberOfSugars()).equals("0")){
            stick = "YES";
        }else {
            stick = "NO";
        }
        if(orderPOJO.isExtraHotOption()){
            extraHotOption = "YES";
        }else {
            extraHotOption = "NO";
        }
        System.out.println("Drink : "+orderPOJO.getDrinkType()+" -- Sugars : "+orderPOJO.getNumberOfSugars()+" -- Stick : "+stick
        +" -- Extra Hot : "+extraHotOption);
    }

    private String calculateMissingMoney(double moneyProvided, double price){
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(price - moneyProvided);
    }

    private void sendErrorMessage(String moneyMissing){
        System.out.println("Sorry, you need to add "+moneyMissing+" more euro");
    }

}
