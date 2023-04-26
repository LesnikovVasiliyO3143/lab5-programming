package application;

public class Application {

    public static void main(String[] args) {
        System.out.println("Application has been started!");
        new CommandInvoker(CollectionManager.getInstance(System.getenv("filepath"))).run();
    }
}
