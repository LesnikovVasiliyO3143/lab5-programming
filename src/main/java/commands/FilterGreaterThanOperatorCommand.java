package commands;

import application.CollectionManager;
import model.Movie;
import model.Person;

import java.util.Map;

public class FilterGreaterThanOperatorCommand {

    private final CollectionManager collectionManager;

    public FilterGreaterThanOperatorCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute(Person operator) {
        StringBuilder stringBuilder = new StringBuilder();
        Map<Long, Movie> collection = this.collectionManager.getCollection();
        for (Map.Entry<Long, Movie> entry : collection.entrySet()) {
            if (entry.getValue().getOperator().compareTo(operator) > 0) {
                stringBuilder.append(entry.getValue()).append("\n");
            }
        }
        return stringBuilder.toString();
    }

}
