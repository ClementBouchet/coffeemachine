package com.coffeemachine;

import POJO.OrderPOJO;
import metier.OrderController;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        OrderPOJO orderPOJO = new OrderPOJO("Tea",1, 0.4, false);
        orderController.handleOrder(orderPOJO);
        String formattedOrder = orderController.formatOrder(orderPOJO);
        assertEquals(formattedOrder.charAt(0),'T');

        orderPOJO.setDrinkType("Chocolate");
        orderController.handleOrder(orderPOJO);
        formattedOrder = orderController.formatOrder(orderPOJO);
        assertEquals(formattedOrder.charAt(0),'H');
    }

    @Test
    public void formatSugar(){
        OrderPOJO orderPOJO = new OrderPOJO("Tea",1,0.4, false);
        orderController.handleOrder(orderPOJO);
        String formattedOrder = orderController.formatOrder(orderPOJO);
        assertEquals(formattedOrder.charAt(2),'1');

        orderPOJO.setNumberOfSugars(0);
        orderController.handleOrder(orderPOJO);
        formattedOrder = orderController.formatOrder(orderPOJO);
        assertEquals(formattedOrder.charAt(2),':');
    }

    @Test
    public void formatStick(){
        OrderPOJO orderPOJO = new OrderPOJO("Tea",2,0.4, false);
        orderController.handleOrder(orderPOJO);
        String formattedOrder = orderController.formatOrder(orderPOJO);
        assertEquals(formattedOrder.charAt(formattedOrder.length()-1),'0');

        orderPOJO.setNumberOfSugars(0);
        orderController.handleOrder(orderPOJO);
        formattedOrder = orderController.formatOrder(orderPOJO);
        assertEquals(formattedOrder.charAt(formattedOrder.length()-1),':');
    }

    @Test
    public void enoughMoneyIsProvided(){
        OrderPOJO orderPOJO = new OrderPOJO("Tea",2,0.4, true);
        boolean controllerResponse = orderController.handleOrder(orderPOJO);
        assertTrue(controllerResponse);
    }

    @Test
    public void notEnoughMoneyIsProvided(){
        OrderPOJO orderPOJO = new OrderPOJO("Tea",2,0.2, false);
        boolean controllerResponse = orderController.handleOrder(orderPOJO);
        assertTrue(!controllerResponse);
    }

    @Test
    public void extraHotOptionTrue(){
        OrderPOJO orderPOJO = new OrderPOJO("Chocolate",2,0.6, true);
        orderController.handleOrder(orderPOJO);
        String formattedOrder = orderController.formatOrder(orderPOJO);
        assertEquals(formattedOrder.charAt(1),'h');
    }

    @Test
    public void OrangeJuiceOrder(){
        OrderPOJO orderPOJO = new OrderPOJO("OrangeJuice",2,0.7, false);
        orderController.handleOrder(orderPOJO);
        String formattedOrder = orderController.formatOrder(orderPOJO);
        assertEquals(formattedOrder.charAt(0),'O');
    }
}
