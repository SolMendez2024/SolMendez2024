package com.cerp.Controlador;


import com.cerp.Randomizer;
import com.cerp.Modelo.Pregunta;
import com.cerp.Vista.InicioVista;
import com.cerp.Vista.PreguntaVista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import com.cerp.Modelo.Ranking;

/**
 * @file PreguntaControlador.java
 * @brief Clase que define la interfaz gráfica de usuario para mostrar preguntas y opciones de respuesta.
 * @date 2023-03-22
 * @version 1.0
 */

/**
 * Clase para representar el controlador de la interfaz gráfica de preguntas.
 */
public class PreguntaControlador implements ActionListener {
    private List<Pregunta> modelo;
    private PreguntaVista vista;
    private InicioVista vistaInicio;
    private int correctIndex;
    private Pregunta preguntaVisible;

    public PreguntaControlador(List<Pregunta> modelo, PreguntaVista vista, InicioVista vistaInicio) {
        this.modelo = modelo;
        this.vista = vista;
        this.vistaInicio = vistaInicio;
        this.preguntaVisible = new Pregunta ();

        vista.getConfirmarButton().addActionListener(this);
        vista.getAtrasButton().addActionListener(this);
        vista.getSiguienteButton().addActionListener(this);

        vista.getIdLabel().setText("ID de pregunta: Numero");
        vista.getPreguntaLabel().setText("aca otra cosa");
        for (int i = 0; i < vista.getRespuestaButtons().length; i++) {
            vista.getRespuestaButtons()[i].setText("aca otra opcion");  
        }

        this.correctIndex = cargarPregunta();

        this.vista.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
               // vistaInicio.getControlador().guardarPreguntas();
                System.exit(0);
            }
        });
    }

    private int cargarPregunta(){
        //Primer pregunta
        // Crear un objeto com.cerp.Randomizer para obtener un elemento aleatorio de la lista
        Randomizer<Pregunta> randomizer = new Randomizer<>();
        // Obtener un elemento aleatorio de la lista de preguntas
        Pregunta preguntaVisible = randomizer.getRandomElement(modelo);
        this.preguntaVisible = preguntaVisible;
        vista.getIdLabel().setText("ID de pregunta: " + preguntaVisible.getIdPregunta());
        String correctAnswer = preguntaVisible.getCorrecta();
        vista.getPreguntaLabel().setText(preguntaVisible.getPregunta());
        int correctButtonIndex = Randomizer.getRandomIndex(0, vista.getRespuestaButtons().length - 1);
        //int correctButtonIndex = (int) (Math.random() * (respuestaButtons.length));
        vista.getRespuestaButtons()[correctButtonIndex].setText(correctAnswer);
        int optionIndex = 0;
        for (int i = 0; i < vista.getRespuestaButtons().length; i++) {
            if (i == correctButtonIndex) {
                continue;
            }

            String option = preguntaVisible.getOpciones().get(optionIndex);
            vista.getRespuestaButtons()[i].setText(option);  

            optionIndex++;
        }

        return correctButtonIndex;
    }
        

    /**
     * Acción a realizar cuando se presiona un botón en la interfaz.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == vista.getSiguienteButton()) {
            this.correctIndex = cargarPregunta();

            
            vista.getButtonGroup().clearSelection();

        } else if (e.getSource() == vista.getConfirmarButton()) {
            int selectedAnswer = Integer.parseInt(vista.getButtonGroup().getSelection().getActionCommand());
            Ranking ranking = new Ranking();
            ranking.setUserId(1);
            ranking.setIdPreg(this.preguntaVisible.getIdPregunta());
       
                if (selectedAnswer==this.correctIndex){
                ranking.setCorreccion(1);
                    vista.mostrarMensajeConfirmacion("Respuesta correcta.");
                    //JOptionPane.showMessageDialog(vista, "¡Correcto!", "Resultado", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    ranking.setCorreccion(0);
                    vista.mostrarMensajeError("Respuesta Incorrecta, vuelve a intentarlo!");
                    //JOptionPane.showMessageDialog(vista, "¡Incorrecto!" + selectedAnswer + this.correctIndex , "Resultado", JOptionPane.ERROR_MESSAGE);
                }
        this.vistaInicio.getDatabaseHandler().insertarRanking(ranking);
        } else if (e.getSource() == vista.getAtrasButton()) {
            vista.getParentWindow().setVisible(true);
            vista.dispose();
        
        }
        //pack(); // Ajustar tamaño al contenido
        //setResizable(false); // No permitir redimensionar
    }   
}
