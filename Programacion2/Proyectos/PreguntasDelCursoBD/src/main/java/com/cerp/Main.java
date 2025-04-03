package com.cerp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.cerp.Controlador.InicioControlador;

import com.cerp.Modelo.Pregunta;
import com.cerp.Vista.InicioVista;


/**
 * @file com.cerp.Main.java
 * @brief Clase principal del programa que permite crear, modificar y eliminar preguntas.
 * @date 20/03/2023
 * @version 1.0
 * @author Domingo E. Pérez
 */

/**
 * @class com.cerp.Main
 * @brief Clase principal que permite interactuar con la aplicación de preguntas.
 * @Author Domingo Perez
 */
public class Main {
    
    /**
     * @brief Método principal que inicia la ejecución del programa.
     * @param args Parámetros de línea de comandos (no se utilizan).
     * @throws Exception
     */
    public static void main(String[] args) {
        
        // Crear instancia de FileHandler para cargar las preguntas desde preguntas.data
        //FileHandler<Pregunta> fileHandler = new FileHandler<Pregunta>("D:\\_VSCode\\_Programacion3-JAVA\\Curso-JAVA\\Proyectos\\PreguntasDelCursoMVC\\preguntas.data");
        //FileHandler<Pregunta> fileHandler = new FileHandler<Pregunta>("D:\\_VSCode\\_Programacion2-JAVA\\Proyectos\\PreguntasDelCursoBD\\src\\main\\resources\\preguntas.data");
        DataBaseHandler dataBaseHandler = new DataBaseHandler();

        List<Pregunta> modelo = new ArrayList<>();        


        //modelo = fileHandler.fileToList();
        modelo = dataBaseHandler.obtenerPreguntas();


        System.out.println("El tamanio es " + modelo.size());

        /*InicioVista vistaInicio = new InicioVista();
        vistaInicio.setVisible(true);*/

         
        //InicioVista vistaInicio = new InicioVista(fileHandler);
        InicioVista vistaInicio = new InicioVista(dataBaseHandler);
        InicioControlador controlador = new InicioControlador(modelo, vistaInicio);
        vistaInicio.setControlador(controlador);
        
    }
}
