package com.cerp.Modelo;

/**
 * @file Pregunta.java
 * @brief Clase para representar una pregunta en el juego de preguntas y respuestas.
 * @date 20/03/2023
 * @version 1.0
 */

 import java.io.Serializable;

 /**
  * @class Pregunta
  * @brief Clase para representar una pregunta en el juego de preguntas y respuestas.
  * Implementa Serializable para poder ser guardada en un archivo.
  */
 public class Pregunta implements Serializable{
    private int idPregunta; // índice de la pregunta
    private String pregunta; // pregunta
    private String correcta; // respuesta correcta
    private String[] opciones; // opciones posibles

    public void setOpciones(String[] opciones) {
        this.opciones = opciones;
    }

    /**
     * Constructor para la clase pregunta.
     * @param idPregunta int que representa el numero de pregunta
     * @param pregunta String que representa la pregunta.
     * @param opciones String[] que contiene las opciones posibles.
     * @param answerIndex int que representa el índice de la respuesta correcta en el arreglo opciones.
     */
    public Pregunta(int idPregunta, String pregunta, String correcta, String[] opciones) {
        this.idPregunta = idPregunta;
        this.pregunta = pregunta;
        this.correcta = correcta;
        this.opciones = opciones;
    }

    /**
     * Getter para obtener el id de la pregunta.
     * @return int que representa el id de la pregunta.
     */
    public int getIdPregunta() {
        return idPregunta;
    }

    /**
     * Setter para establecer el id de la pregunta.
     * @param idPregunta int que representa el id de la pregunta.
     */
    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    /**
     * Getter para obtener la pregunta.
     * @return String que representa la pregunta.
     */
    public String getPregunta() {
        return pregunta;
    }

    /**
     * Setter para establecer la pregunta.
     * @param pregunta String que representa la pregunta.
     */
    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    /**
     * Getter para obtener la respuesta correcta.
     * @return String que representa la respuesta correcta.
     */
    public String getCorrecta() {
        return correcta;
    }

    /**
     * Setter para establecer la respuesta correcta.
     * @param correcta String que representa la respuesta correcta.
     */
    public void setCorrecta(String correcta) {
        this.correcta = correcta;
    }

    /**
     * Getter para obtener las opciones posibles.
     * @return String[] que contiene las opciones posibles.
     */
    public String[] getOpciones() {
        return opciones;
    }


    public static Pregunta fromString(String PreguntaString) {
        // Separar la cadena en los diferentes campos
        String[] fields = PreguntaString.split("\\|");

        System.out.println("ID de pregunta: " + fields[0]);
        System.out.println("Pregunta: " + fields[1]);
        System.out.println("Correcta: " + fields[2]);
        System.out.println("Opciones: " + fields[3]);
    
        // Crear la pregunta a partir de los campos
        int idPregunta = Integer.parseInt(fields[0]);
        String pregunta = fields[1];
        String correcta = fields[2];
        String[] opciones = fields[3].split(",");
    
        return new Pregunta(idPregunta, pregunta, correcta, opciones);
    }
}
