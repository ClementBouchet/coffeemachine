package metier;

import POJO.OrderPOJO;

public class OrderController {

    public OrderController() {
    }

    private final static String TEA = "Tea";
    private final static String COFFEE = "Coffee";
    private final static String CHOCOLATE = "Chocolate";


    public void handleOrder(OrderPOJO orderPOJO){
        formatOrder(orderPOJO);
        sendMessage(orderPOJO);
    }

    public String formatOrder(OrderPOJO orderPOJO){
        String drinkType = orderPOJO.getDrinkType();
        int numberOfSugars = orderPOJO.getNumberOfSugars();
        return checkDrink(drinkType)+":"+checkNumberOfSugars(numberOfSugars)+":"+ checkIfStick(numberOfSugars);
    }

    private String checkDrink(String drinkType){
        switch (drinkType) {
            case TEA:
                return "T";
            case COFFEE:
                return "C";
            case CHOCOLATE:
                return "H";
            default:
                return "";
        }
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

    public void sendMessage(OrderPOJO orderPOJO){
        String stick;
        if(checkIfStick(orderPOJO.getNumberOfSugars()) == "0"){
            stick = "YES";
        }else {
            stick = "NO";
        }
        System.out.println("Drink : "+orderPOJO.getDrinkType()+" -- Sugars : "+orderPOJO.getNumberOfSugars()+" -- Stick : "+stick);
    }

}
