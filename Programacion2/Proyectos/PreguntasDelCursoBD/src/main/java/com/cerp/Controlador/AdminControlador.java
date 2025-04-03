package com.cerp.Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.cerp.DataBaseHandler;
import com.cerp.FileHandler;
import com.cerp.Modelo.Pregunta;
import com.cerp.Vista.AdminVista;
import com.cerp.Vista.InicioVista;
import com.cerp.Modelo.Pregunta;
import com.cerp.Vista.AdminVista;
import com.cerp.Vista.InicioVista;

/**
 * Clase para representar el controlador de la interfaz gráfica de administración de preguntas.
 */
public class AdminControlador implements ActionListener {
    private List<Pregunta> modelo;
    private AdminVista vista;
    private InicioVista vistaInicio;
    //private FileHandler<Pregunta> fileHandlerC;
    private DataBaseHandler dataBaseHandler;

    public AdminControlador(List<Pregunta> modelo, AdminVista vista, InicioVista vistaInicio) {
        this.modelo = modelo;
        this.vista = vista;
        this.vistaInicio = vistaInicio;

        vista.getConfirmarButton().addActionListener(this);
        vista.getAtrasButton().addActionListener(this);
        vista.getLimpiarButton().addActionListener(this);
        
        // Cargar la primera pregunta en la vista
        this.vista.actualizarIdLabel(modelo.size());
        this.vista.actualizarPreguntaField();
        this.vista.actualizarIncorrectas1Field();
        this.vista.actualizarCorrectaField();

        this.vista.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //vistaInicio.getControlador().guardarPreguntas();
                System.exit(0);
            }
        });

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getConfirmarButton()) {
            if (vista.getPreguntaField().getText().isEmpty() || vista.getIncorrecta1Field().getText().isEmpty() || vista.getCorrectaField().getText().isEmpty()) {
                vista.mostrarMensajeError("Debe completar todos los campos.");
            } else {
                vista.getConfirmarButton().setEnabled(false); // Desactivar el botón de confirmar temporalmente

                String pregunta = vista.getPreguntaField().getText();
                String incorrecta1 = vista.getIncorrecta1Field().getText();
                String correcta = vista.getCorrectaField().getText();

                List<String> opciones = new ArrayList<>();
                opciones.add(incorrecta1);
                // van el resto de incorrectas
                Pregunta nuevaPregunta = new Pregunta(modelo.size() + 1, pregunta, correcta, opciones);

                this.modelo.add(nuevaPregunta);
                //vistaInicio.getControlador().setModelo(modelo);
                this.dataBaseHandler.insertarPregunta(nuevaPregunta);
                this.dataBaseHandler.insertarRespuestas_Incorrectas(nuevaPregunta);



                // aca hay que ver el tema de insercion en la BD
                vistaInicio.getControlador().ActualizarControlador();
                
                System.out.println("DENTRO de ADMIN El tamanio es " + modelo.size());
                

                vista.mostrarMensajeConfirmacion("Pregunta agregada correctamente.");

                vista.getIdLabel().setText("ID de pregunta: " + (modelo.size() + 1));

                vista.actualizarPreguntaField();
                vista.actualizarIncorrectas1Field();
                vista.actualizarCorrectaField();

                //cargar archivo con la lista de preguntas
               /* try {
                    try {
                        fileHandlerC.limpiarArchivo();
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    fileHandlerC.listToFile(modelo);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }*/

                vista.getConfirmarButton().setEnabled(true); // Volver a activar el botón de confirmar
            }
        } else if (e.getSource() == vista.getAtrasButton()) {
            vista.getParentWindow().setVisible(true);
            vista.dispose();
            System.out.println("DENTRO de ADMIN pero ATRAS El tamanio es " + modelo.size());
        } else if (e.getSource() == vista.getLimpiarButton()) {
            modelo.clear();
            vista.getParentWindow().setVisible(true);
            vista.dispose();
        }
    }
}
