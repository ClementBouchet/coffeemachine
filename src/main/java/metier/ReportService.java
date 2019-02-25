package metier;

import POJO.OrderPOJO;
import Repository.Repository;

public class ReportService {

    private Repository repository;
    private OrderPOJO orderPOJO;

    public ReportService() {
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public void updateRepository(OrderPOJO orderPOJO){
        switch (orderPOJO.getDrinkType()){
            case OrderController.ORANGE_JUICE:
                repository.setNumberOfOrangeJuiceSold(repository.getNumberOfOrangeJuiceSold()+1);
                repository.setTotalAmountOfMoney(repository.getTotalAmountOfMoney()+OrderController.ORANGE_JUICE_PRICE);
                break;
            case OrderController.COFFEE:
                repository.setNumberOfCoffeeSold(repository.getNumberOfCoffeeSold()+1);
                repository.setTotalAmountOfMoney(repository.getTotalAmountOfMoney()+OrderController.COFFEE_PRICE);
                break;
            case OrderController.CHOCOLATE:
                repository.setNumberOfChocolateSold(repository.getNumberOfChocolateSold()+1);
                repository.setTotalAmountOfMoney(repository.getTotalAmountOfMoney()+OrderController.CHOCOLATE_PRICE);
                break;
            case OrderController.TEA:
                repository.setNumberOfTeaSold(repository.getNumberOfTeaSold()+1);
                repository.setTotalAmountOfMoney(repository.getTotalAmountOfMoney()+OrderController.TEA_PRICE);
                break;
        }
    }

    public void printReport(){
        System.out.println("Coffee sold : "+repository.getNumberOfCoffeeSold()+" -- Chocolate sold : "+repository.getNumberOfChocolateSold()
            +" -- Tea sold : "+repository.getNumberOfTeaSold()+" -- Orange Juice sole : " + repository.getNumberOfOrangeJuiceSold()
            +" -- Total of money earned : "+repository.getTotalAmountOfMoney());
    }
}
