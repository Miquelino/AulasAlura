package AulaSpringWeb.screenmatch.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MyMemoryTranslate {

    public static String obterTraducao(String textoOriginal) throws Exception {
        String textoCodificado = URLEncoder.encode(textoOriginal, StandardCharsets.UTF_8.toString());
        String urlStr = "https://api.mymemory.translated.net/get?q=" + textoCodificado + "&langpair=en|pt";

        URL url = new URL(urlStr);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestMethod("GET");
        conexao.setConnectTimeout(5000);
        conexao.setReadTimeout(5000);

        int status = conexao.getResponseCode();

        if (status != 200) {
            throw new RuntimeException("Erro na requisição: HTTP status " + status);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
        String inputLine;
        StringBuilder conteudo = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            conteudo.append(inputLine);
        }
        in.close();
        conexao.disconnect();

        // Parse JSON com Gson
        JsonObject jsonObject = JsonParser.parseString(conteudo.toString()).getAsJsonObject();
        JsonObject responseData = jsonObject.getAsJsonObject("responseData");
        String traducao = responseData.get("translatedText").getAsString();

        return traducao;
    }
}
