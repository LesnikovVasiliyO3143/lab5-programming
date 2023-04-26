package serializers;

import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import model.Movie;
import wrappers.MovieWrapper;

public class MovieSerializer {

    public static String serialize(Movie movie) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            MovieWrapper movieWrapper = new MovieWrapper().wrap(movie);
            return objectMapper.writeValueAsString(movieWrapper);
        } catch (JsonProcessingException jsonProcessingException) {
            System.err.println(jsonProcessingException.getMessage());
            jsonProcessingException.printStackTrace();
            return null;
        }
    }

    public static Movie deserialize(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            MovieWrapper movieWrapper = objectMapper.readValue(json, MovieWrapper.class);
            return movieWrapper.unwrap();
        } catch (JsonProcessingException jsonProcessingException) {
            return null;
        }
    }

}
