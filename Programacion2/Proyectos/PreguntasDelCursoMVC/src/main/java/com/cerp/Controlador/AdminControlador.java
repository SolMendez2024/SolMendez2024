package com.cerp.Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;

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
    private FileHandler<Pregunta> fileHandlerC;

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
        this.vista.actualizarOpcionesField();
        this.vista.actualizarCorrectaField();

        this.vista.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                vistaInicio.getControlador().guardarPreguntas();
                System.exit(0);
            }
        });

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getConfirmarButton()) {
            if (vista.getPreguntaField().getText().isEmpty() || vista.getOpcionesField().getText().isEmpty() || vista.getCorrectaField().getText().isEmpty()) {
                vista.mostrarMensajeError("Debe completar todos los campos.");
            } else {
                vista.getConfirmarButton().setEnabled(false); // Desactivar el botón de confirmar temporalmente

                String pregunta = vista.getPreguntaField().getText();
                String opcionesString = vista.getOpcionesField().getText();
                String correcta = vista.getCorrectaField().getText();

                String[] opciones = opcionesString.split(",");
                Pregunta nuevaPregunta = new Pregunta(modelo.size() + 1, pregunta, correcta, opciones);

                this.modelo.add(nuevaPregunta);
                //vistaInicio.getControlador().setModelo(modelo);
                vistaInicio.getControlador().ActualizarControlador();
                
                System.out.println("DENTRO de ADMIN El tamanio es " + modelo.size());
                

                vista.mostrarMensajeConfirmacion("Pregunta agregada correctamente.");

                vista.getIdLabel().setText("ID de pregunta: " + (modelo.size() + 1));

                vista.actualizarPreguntaField();
                vista.actualizarOpcionesField();
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
