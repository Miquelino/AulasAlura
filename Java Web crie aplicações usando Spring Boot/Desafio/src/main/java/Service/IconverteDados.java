package Service;

import java.util.List;

public interface IconverteDados {
    <T> T obterDados(String json, Class<T> classe); // Para converter um único objeto JSON

    <T> List<T> obterListDados(String json, Class<T> classe); // Para converter uma lista de objetos JSON

}
