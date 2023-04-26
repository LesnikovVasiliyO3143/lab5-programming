package serializers;

import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import model.Person;

public class PersonSerializer {

    public static String serialize(Person person) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(person);
        } catch (JsonProcessingException jsonProcessingException) {
            return null;
        }
    }

    public static Person deserialize(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, Person.class);
        } catch (JsonProcessingException jsonProcessingException) {
            return null;
        }
    }

}
