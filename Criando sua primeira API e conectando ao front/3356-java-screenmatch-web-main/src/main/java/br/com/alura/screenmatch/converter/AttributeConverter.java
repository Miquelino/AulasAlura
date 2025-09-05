//package br.com.alura.screenmatch.converter;
//
//import br.com.alura.screenmatch.model.Categoria;
//import jakarta.persistence.AttributeConverter;
//import jakarta.persistence.Converter;
//
//@Converter(autoApply = true)
//public class CategoriaConverter implements AttributeConverter<Categoria, String> {
//
//    // Converte do enum para o banco (string em português)
//    @Override
//    public String convertToDatabaseColumn(Categoria categoria) {
//        if (categoria == null) return null;
//        return categoria.getCategoriaPortugues(); // ex: "Ficção Científica"
//    }
//
//    // Converte do banco (string) para o enum
//    @Override
//    public Categoria convertToEntityAttribute(String dbData) {
//        if (dbData == null) return null;
//        return Categoria.fromPortugues(dbData); // usa seu método do enum
//    }
//}
