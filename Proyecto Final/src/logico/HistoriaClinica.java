package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class HistoriaClinica implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<Consulta> misConsultas;

    // <-- CAMBIO: Se añade un constructor vacío
    public HistoriaClinica() {
        this.misConsultas = new ArrayList<>();
    }

    public ArrayList<Consulta> getMisConsultas() {
        return misConsultas;
    }

    public void setMisConsultas(ArrayList<Consulta> misConsultas) {
        this.misConsultas = misConsultas;
    }
}