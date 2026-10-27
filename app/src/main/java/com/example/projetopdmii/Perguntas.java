package com.example.projetopdmii;

public class Perguntas {
    private String pergunta;
    private String alt1;
    private String alt2;
    private String alt3;
    private String alt4;
    private String gabarito;

    public Perguntas(String pergunta, String alt1, String alt2, String alt3, String alt4, String gabarito) {
        this.pergunta = pergunta;
        this.alt1 = alt1;
        this.alt2 = alt2;
        this.alt3 = alt3;
        this.alt4 = alt4;
        this.gabarito = gabarito;
    }


    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        // Altera o texto da pergunta se necessário
        this.pergunta = pergunta;
    }

    public String getAlt1() {
        return alt1;
    }

    public void setAlt1(String alt1) {
        this.alt1 = alt1;
    }

    public String getAlt2() {
        return alt2;
    }

    public void setAlt2(String alt2) {
        this.alt2 = alt2;
    }

    public String getAlt3() {
        return alt3;
    }

    public void setAlt3(String alt3) {
        this.alt3 = alt3;
    }

    public String getAlt4() {
        return alt4;
    }

    public void setAlt4(String alt4) {
        this.alt4 = alt4;
    }

    public String getGabarito() {
        return gabarito;
    }

    public void setGabarito(String gabarito) {
        // Define qual alternativa (ex: "A", "B" ou o texto completo) é a correta
        this.gabarito = gabarito;
    }
}

