package Primeiro_Projeto_Banco.First.Entidades.Conta;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

// Se estiver usando Spring Boot mais antigo, use javax.persistence em vez de jakarta.persistence

@Converter(autoApply = true) // Aplica este conversor automaticamente a todos os campos TipoConta
public class TipoContaConverter implements AttributeConverter<TipoConta, Integer> {

    // Método para traduzir de Java (Enum) para Banco de Dados (Integer)
    @Override
    public Integer convertToDatabaseColumn(TipoConta tipoConta) {
        if (tipoConta == null) {
            return null;
        }
        // Usa o método getCodigo() que definimos!
        return tipoConta.getCodigo();
    }

    // Método para traduzir de Banco de Dados (Integer) para Java (Enum)
    @Override
    public TipoConta convertToEntityAttribute(Integer codigo) {
        if (codigo == null) {
            return null;
        }
        // Usa o método fromCodigo() que definimos!
        return TipoConta.fromCodigo(codigo);
    }
}
