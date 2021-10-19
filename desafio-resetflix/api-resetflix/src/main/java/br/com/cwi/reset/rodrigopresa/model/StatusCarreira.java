package br.com.cwi.reset.rodrigopresa.model;

public enum StatusCarreira {
    EM_ATIVIDADE("Em Atividade"),
    APOSENTADO("Aposentado");

    private String status;

    StatusCarreira(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
