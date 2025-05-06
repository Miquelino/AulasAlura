package Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import java.io.IOException;
import java.util.List;

public class ConverterDados implements IconverteDados {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (IOException e) {
            System.out.println("Erro ao converter JSON para objeto: " + e.getMessage());
            return null;
        }
    }

    @Override
    public <T> List<T> obterListDados(String json, Class<T> classe) {
        CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, classe);
        try {
            return mapper.readValue(json, listType);
        } catch (IOException e) {
            System.out.println("Erro ao converter JSON para List: " + e.getMessage());
            return null;
        }
    }
}