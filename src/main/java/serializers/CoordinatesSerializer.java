package serializers;

import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import model.Coordinates;

public class CoordinatesSerializer {

    public static String serialize(Coordinates coordinates) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(coordinates);
        } catch (JsonProcessingException jsonProcessingException) {
            return null;
        }
    }

    public static Coordinates deserialize(String json) {
        try {
            return new ObjectMapper().readValue(json, Coordinates.class);
        } catch (JsonProcessingException jsonProcessingException) {
            return null;
        }
    }



}
