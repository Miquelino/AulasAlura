package Model;

import java.time.YearMonth;

public enum Mes {
    JANEIRO(31), FEVEREIRO(28), MARCO(31), ABRIL(30),
    MAIO(31), JUNHO(30), JULHO(31), AGOSTO(31),
    SETEMBRO(30), OUTUBRO(31), NOVEMBRO(30), DEZEMBRO(31);

    private final int numeroDeDiasPadrao;

    Mes(int numeroDeDias) {
        this.numeroDeDiasPadrao = numeroDeDias;
    }

    public int getNumeroDeDias(int ano) {
        if (this == FEVEREIRO && isAnoBissexto(ano)) {
            return 29;
        }
        return numeroDeDiasPadrao;
    }

    private boolean isAnoBissexto(int ano) {
        return (ano % 4 == 0 && (ano % 100 != 0 || ano % 400 == 0));
    }

}
