package com.coffeemachine;

import POJO.OrderPOJO;
import metier.OrderController;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        OrderController orderController = new OrderController();
        OrderPOJO orderPOJO = new OrderPOJO("Coffee",2);
        orderController.sendMessage(orderPOJO);

        orderPOJO.setNumberOfSugars(0);
        orderController.sendMessage(orderPOJO);
    }
}
