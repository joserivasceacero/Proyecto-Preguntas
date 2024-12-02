package com.example.myapplication;
import java.util.HashMap;

public class Pregunto {
    private String enunciado;
    private HashMap<String, Boolean> opciones;

    public Pregunto(String enunciado) {
        this.enunciado = enunciado;
        this.opciones = new HashMap<>();
    }

    public void agregarOpcion(String respuesta, boolean esCorrecta) {
            opciones.put(respuesta, esCorrecta);
    }

    public String getEnunciado() {
        return enunciado;
    }

    public HashMap<String, Boolean> getOpciones() {
        return opciones;
    }
}