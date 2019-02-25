package com.coffeemachine;

import POJO.OrderPOJO;
import metier.OrderController;


public class App 
{
    public static void main( String[] args )
    {
        OrderController orderController = new OrderController();
        OrderPOJO orderPOJO = new OrderPOJO("Coffee",2,0.4, false);
        orderController.handleOrder(orderPOJO);

        orderPOJO.setMoney(0.7);
        orderController.handleOrder(orderPOJO);
    }
}
