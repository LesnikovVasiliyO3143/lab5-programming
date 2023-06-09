package commands;

import application.CollectionManager;
import model.Movie;
import utils.IDGenerator;

import java.util.HashMap;
import java.util.Map;

public class RemoveGreaterCommand {

    private final CollectionManager collectionManager;

    public RemoveGreaterCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute(Movie movie) {
        HashMap<Long, Movie> collection = this.collectionManager.getCollection();
        HashMap<Long, Movie> newCollection = new HashMap<>();
        for (Map.Entry<Long, Movie> current : collection.entrySet()) {
            if (current.getValue().compareTo(movie) < 0) {
                IDGenerator.removeId(current.getValue().getId());
            } else {
                newCollection.put(current.getKey(), current.getValue());
            }
        }
        this.collectionManager.setCollection(newCollection);
        return "All elements which are greater then given were removed\n";
    }

}