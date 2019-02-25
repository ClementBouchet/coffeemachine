package com.coffeemachine;

import POJO.OrderPOJO;
import Repository.Repository;
import Service.BeverageQuantityCheckerImpl;
import Service.EmailNotifierImpl;
import metier.OrderController;
import metier.ReportService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    OrderController orderController;
    Repository repository;
    ReportService reportService;
    BeverageQuantityCheckerImpl beverageQuantityChecker;
    EmailNotifierImpl emailNotifier;

    @Before
    public void setUp(){
        orderController = new OrderController();
        repository = new Repository();
        reportService = new ReportService();
        reportService.setRepository(repository);
        orderController.setReportService(reportService);
        beverageQuantityChecker = mock(BeverageQuantityCheckerImpl.class);
        emailNotifier = mock(EmailNotifierImpl.class);
        orderController.setBeverageQuantityChecker(beverageQuantityChecker);
        orderController.setEmailNotifier(emailNotifier);
    }

    @Test
    public void formatDrink() {
        OrderPOJO orderPOJO = new OrderPOJO("Tea",1, 0.4, false);
        doReturn(false).when(beverageQuantityChecker).isEmpty(anyString());

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
        doReturn(false).when(beverageQuantityChecker).isEmpty(anyString());

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
        doReturn(false).when(beverageQuantityChecker).isEmpty(anyString());

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
        doReturn(false).when(beverageQuantityChecker).isEmpty(anyString());

        boolean controllerResponse = orderController.handleOrder(orderPOJO);
        assertTrue(controllerResponse);
    }

    @Test
    public void notEnoughMoneyIsProvided(){
        OrderPOJO orderPOJO = new OrderPOJO("Tea",2,0.2, false);
        doReturn(false).when(beverageQuantityChecker).isEmpty(anyString());

        boolean controllerResponse = orderController.handleOrder(orderPOJO);
        assertTrue(!controllerResponse);
    }

    @Test
    public void extraHotOptionTrue(){
        OrderPOJO orderPOJO = new OrderPOJO("Chocolate",2,0.6, true);
        doReturn(false).when(beverageQuantityChecker).isEmpty(anyString());

        orderController.handleOrder(orderPOJO);
        String formattedOrder = orderController.formatOrder(orderPOJO);
        assertEquals(formattedOrder.charAt(1),'h');
    }

    @Test
    public void OrangeJuiceOrder(){
        OrderPOJO orderPOJO = new OrderPOJO("OrangeJuice",0,0.7, false);
        doReturn(false).when(beverageQuantityChecker).isEmpty(anyString());

        orderController.handleOrder(orderPOJO);
        String formattedOrder = orderController.formatOrder(orderPOJO);
        assertEquals(formattedOrder.charAt(0),'O');
    }

    @Test
    public void addDrinkCorrect(){
        OrderPOJO orderPOJO = new OrderPOJO("OrangeJuice",0,0.7, false);
        OrderPOJO orderPOJO2 = new OrderPOJO("OrangeJuice",0,0.7, false);
        OrderPOJO orderPOJO3 = new OrderPOJO("Coffee",2,0.7, false);

        reportService.updateRepository(orderPOJO);
        reportService.updateRepository(orderPOJO2);
        reportService.updateRepository(orderPOJO3);
        assertEquals(2,repository.getNumberOfOrangeJuiceSold());
    }

    @Test
    public void addAmount(){
        OrderPOJO orderPOJO = new OrderPOJO("OrangeJuice",0,0.7, false);
        OrderPOJO orderPOJO2 = new OrderPOJO("OrangeJuice",0,0.7, false);
        OrderPOJO orderPOJO3 = new OrderPOJO("Coffee",2,0.7, false);

        reportService.updateRepository(orderPOJO);
        reportService.updateRepository(orderPOJO2);
        reportService.updateRepository(orderPOJO3);
        assertEquals(1.8,repository.getTotalAmountOfMoney(),0.01);
    }

    @Test
    public void correctRepositoryUpdate(){
        OrderPOJO orderPOJO1 = new OrderPOJO("OrangeJuice",0,0.7, false);
        OrderPOJO orderPOJO2 = new OrderPOJO("Coffee",2,0.7, false);
        doReturn(false).when(beverageQuantityChecker).isEmpty(anyString());

        orderController.handleOrder(orderPOJO1);
        orderController.handleOrder(orderPOJO2);

        assertEquals(1,repository.getNumberOfOrangeJuiceSold());
        assertEquals(1,repository.getNumberOfCoffeeSold());
        assertEquals(1.2,repository.getTotalAmountOfMoney(),0.01);
    }

    @Test
    public void shortage(){
        OrderPOJO orderPOJO1 = new OrderPOJO("OrangeJuice",0,0.7, false);
        doReturn(true).when(beverageQuantityChecker).isEmpty(anyString());

        orderController.handleOrder(orderPOJO1);

        verify(emailNotifier).notifyMissingDrink(anyString());
    }

}
