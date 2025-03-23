package com.cerp.Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import com.cerp.Modelo.Pregunta;
import com.cerp.Vista.AdminVista;
import com.cerp.Vista.InicioVista;
import com.cerp.Vista.PreguntaVista;


public class InicioControlador implements ActionListener {
    private List<Pregunta> modelo;
    private InicioVista vista;

    public InicioControlador(List<Pregunta> modelo, InicioVista vista) {
        this.modelo = modelo;
        this.vista = vista;

        vista.getItemAdmin().addActionListener(this);
        vista.getItemPreguntas().addActionListener(this);

        vista.getLabelPreguntas().setText("Hay en el sistema " + modelo.size() +" preguntas");
    }

    public void ActualizarControlador (){
        vista.getLabelPreguntas().setText("Hay en el sistema " + modelo.size() +" preguntas");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         if (e.getSource() ==vista.getItemAdmin()) {
            System.out.println("Hola admin");

            // Crear instancia de AdminControlador y mostrar vista AdminVista
            AdminVista vistaAdmin = new AdminVista(vista);
            AdminControlador controladorAdmin = new AdminControlador(modelo, vistaAdmin, vista);

        } else if (e.getSource() == vista.getItemPreguntas()) {
            System.out.println("Hola juego");

            // Crear instancia de AdminControlador y mostrar vista AdminVista
            PreguntaVista vistaPregunta = new PreguntaVista(vista);
            
            System.out.println("Hola juego luego de crear vistaPregunta");
            PreguntaControlador controladorPregunta = new PreguntaControlador(modelo, vistaPregunta, vista);



        }
        
        vista.dispose();

       /*  try {
            vista.getFileHandler().listToFile(modelo);
            System.out.println("******************\nEl tamanio es " + modelo.size() + "\nLista guardada correctamente");
        } catch (IOException err) {
            // TODO Auto-generated catch block
            err.printStackTrace();
        }*/
    }

    public List<Pregunta> getModelo() {
        return modelo;
    }

    public void setModelo(List<Pregunta> modelo) {
        this.modelo = modelo;
    }

    public void guardarPreguntas() {
        try {
            vista.getFileHandler().listToFile(modelo);
            System.out.println("******************\nEl tamanio es " + modelo.size() + "\nLista guardada correctamente");
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    


}
