package serializers;

import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import model.Location;

public class LocationSerializer {

    public static String serialize(Location location) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(location);
        } catch (JsonProcessingException jsonProcessingException) {
            return null;
        }
    }

    public static Location deserialize(String json) {
        try {
            return new ObjectMapper().readValue(json, Location.class);
        } catch (JsonProcessingException jsonProcessingException) {
            return null;
        }
    }

}
