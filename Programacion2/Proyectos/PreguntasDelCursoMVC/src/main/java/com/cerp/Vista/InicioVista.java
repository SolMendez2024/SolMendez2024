package com.cerp.Vista;

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import com.cerp.*;
import com.cerp.Controlador.InicioControlador;
import com.cerp.Modelo.Pregunta;

/**
 * @file InicioVista.java
 * @brief Clase para la vista principal de la aplicación.
 * @date 20/03/2023
 * @version 1.0
 */  
 
 public class InicioVista extends JFrame {
     private JMenuBar menuBar;
     private JMenuItem menuItemAdmin;
     private JMenuItem menuItemPreguntas;
     private JLabel numPreguntas; 

    private FileHandler<Pregunta> fileHandler;
    private InicioControlador controlador;
 
     public InicioVista(FileHandler<Pregunta> fileHandler) {
         super("Menú Principal");

         this.fileHandler = fileHandler;
         
         initComponents();

         this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controlador.guardarPreguntas();
                System.exit(0);
            }
        });
     }
 
     private void initComponents() {
         // Crear la barra de menú y agregarla a la ventana
         menuBar = new JMenuBar();
         setJMenuBar(menuBar);
 
         // Crear los elementos del menú y agregarlos directamente a la barra de menú
         menuItemAdmin = new JMenuItem("Administración");
         //menuItemAdmin.addActionListener(this);
         menuBar.add(menuItemAdmin);
 
         menuItemPreguntas = new JMenuItem("Jugar");
         //menuItemPreguntas.addActionListener(this);
         menuBar.add(menuItemPreguntas);
 
         // Panel para mostrar la pregunta
         JPanel inicioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
 
         numPreguntas = new JLabel("Hay en el sistema " + 1001 +" preguntas");
         numPreguntas.setBounds(0, 180, 100, 200);
         inicioPanel.add(numPreguntas);
 
         add(inicioPanel);
 
         setSize(800, 400);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setLocationRelativeTo(null);
         setVisible(true);
     }
  


    
    public FileHandler<Pregunta> getFileHandler() {
        return fileHandler;
    }

    public void setFileHandler(FileHandler<Pregunta> fileHandler) {
        this.fileHandler = fileHandler;
    }

    public void setControlador(InicioControlador controlador) {
        this.controlador = controlador;
    }

    public InicioControlador getControlador(){
        return this.controlador;
    }

    /*public JMenuBar getJMenuBar() {
        return menuBar;
    }

    public void setJMenuBar(JMenuBar menuBar){
        this.menuBar = menuBar;
    }*/

    public JMenuItem getItemAdmin() {
        return menuItemAdmin;
    }

   /* public void setItemAdmin(JMenuItem menuItemAdmin){
        this.menuItemAdmin = menuItemAdmin;
    }*/

    public JMenuItem getItemPreguntas() {
        return menuItemPreguntas;
    }

    /*public void setItemPreguntas(JMenuItem menuItemPreguntas){
        this.menuItemPreguntas = menuItemPreguntas;
    }*/

    public JLabel getLabelPreguntas() {
        return numPreguntas;
    }

    public void setLabelPreguntas(JLabel  numPreguntas){
        this.numPreguntas =  numPreguntas;
    }

}



/*
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuItemAdmin) {
            // Crear instancia de AdminControlador y mostrar vista AdminVista
            AdminVista vistaAdmin = new AdminVista(fileHandler, this);
            AdminControlador controladorAdmin = new AdminControlador(vistaAdmin);
            // Asociar el controlador como listener de los componentes de la vista
            vistaAdmin.getConfirmarButton().addActionListener(controladorAdmin);
            vistaAdmin.getAtrasButton().addActionListener(controladorAdmin);
            vistaAdmin.getLimpiarButton().addActionListener(controladorAdmin);

        } else if (e.getSource() == menuItemPreguntas) {
            PreguntaVista preguntaVista = new PreguntaVista(fileHandler, this);
            preguntaVista.setVisible(true);
        }
        
        dispose();
    }
}
*/