package com.coffeemachine;

import POJO.OrderPOJO;
import metier.OrderController;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */

    OrderController orderController;

    @Before
    public void setUp(){
        orderController = new OrderController();
    }

    @Test
    public void formatDrink() {
        OrderPOJO orderPOJO = new OrderPOJO("Tea",1);
        String formattedOrder = orderController.formatOrder(orderPOJO);
        assertEquals(formattedOrder.charAt(0),'T');

        orderPOJO.setDrinkType("Chocolate");
        formattedOrder = orderController.formatOrder(orderPOJO);
        assertEquals(formattedOrder.charAt(0),'H');
    }

    @Test
    public void formatSugar(){
        OrderPOJO orderPOJO = new OrderPOJO("Tea",1);
        String formattedOrder = orderController.formatOrder(orderPOJO);
        assertEquals(formattedOrder.charAt(2),'1');

        orderPOJO.setNumberOfSugars(0);
        formattedOrder = orderController.formatOrder(orderPOJO);
        assertEquals(formattedOrder.charAt(2),':');
    }

    @Test
    public void formatStick(){
        OrderPOJO orderPOJO = new OrderPOJO("Tea",2);
        String formattedOrder = orderController.formatOrder(orderPOJO);
        assertEquals(formattedOrder.charAt(formattedOrder.length()-1),'0');

        orderPOJO.setNumberOfSugars(0);
        formattedOrder = orderController.formatOrder(orderPOJO);
        assertEquals(formattedOrder.charAt(formattedOrder.length()-1),':');
    }
}
