package Service;

public class EmailNotifierImpl implements EmailNotifier {

    @Override
    public void notifyMissingDrink(String drink) {
        System.out.println("Sorry, there is a shortage in ingredient, an email has been send");
    }
}
