package commands;

import application.CollectionManager;
import model.Movie;
import utils.IDGenerator;

public class InsertCommand {

    private final CollectionManager collectionManager;

    public InsertCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute(Long key, Movie movie) {
        this.collectionManager.getCollection().put(key, movie);
        IDGenerator.saveId(movie.getId());
        return "Element has been added!\n";
    }

}