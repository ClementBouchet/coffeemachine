package com.coffeemachine;

import POJO.OrderPOJO;
import Repository.Repository;
import metier.OrderController;
import metier.ReportService;
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
    Repository repository;
    ReportService reportService;

    @Before
    public void setUp(){
        orderController = new OrderController();
        repository = new Repository();
        reportService = new ReportService();
        reportService.setRepository(repository);
        orderController.setReportService(reportService);
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
        OrderPOJO orderPOJO = new OrderPOJO("OrangeJuice",0,0.7, false);
        orderController.handleOrder(orderPOJO);
        String formattedOrder = orderController.formatOrder(orderPOJO);
        assertEquals(formattedOrder.charAt(0),'O');
    }

    @Test
    public void addDrinkCorrect(){
        OrderPOJO orderPOJO = new OrderPOJO("OrangeJuice",0,0.7, false);
        OrderPOJO orderPOJO2 = new OrderPOJO("OrangeJuice",0,0.7, false);
        OrderPOJO orderPOJO3 = new OrderPOJO("Coffee",2,0.7, false);

        reportService.addDrink(orderPOJO);
        reportService.addDrink(orderPOJO2);
        reportService.addDrink(orderPOJO3);
        assertEquals(2,repository.getNumberOfOrangeJuiceSold());
    }

    @Test
    public void addAmount(){
        OrderPOJO orderPOJO = new OrderPOJO("OrangeJuice",0,0.7, false);
        OrderPOJO orderPOJO2 = new OrderPOJO("OrangeJuice",0,0.7, false);
        OrderPOJO orderPOJO3 = new OrderPOJO("Coffee",2,0.7, false);

        reportService.addAmount(orderPOJO);
        reportService.addAmount(orderPOJO2);
        reportService.addAmount(orderPOJO3);
        assertEquals(1.8,repository.getTotalAmountOfMoney(),0.01);
    }

    @Test
    public void correctRepositoryUpdate(){
        OrderPOJO orderPOJO1 = new OrderPOJO("OrangeJuice",0,0.7, false);
        OrderPOJO orderPOJO2 = new OrderPOJO("Coffee",2,0.7, false);
        orderController.handleOrder(orderPOJO1);
        orderController.handleOrder(orderPOJO2);

        assertEquals(1,repository.getNumberOfOrangeJuiceSold());
        assertEquals(1,repository.getNumberOfCoffeeSold());
        assertEquals(1.2,repository.getTotalAmountOfMoney(),0.01);
    }
}
