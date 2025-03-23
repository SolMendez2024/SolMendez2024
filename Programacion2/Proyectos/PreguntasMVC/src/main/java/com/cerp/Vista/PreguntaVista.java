package com.cerp.Vista;

import javax.swing.*;

import java.awt.*;


/**
 * @file PreguntaVista.java
 * @brief Clase que define la interfaz gráfica de usuario para mostrar preguntas y opciones de respuesta.
 * @date 2023-03-22
 * @version 1.0
 */

/**
 * @class PreguntaVista
 * @brief Clase que define la interfaz gráfica de usuario para mostrar preguntas y opciones de respuesta.
 */
public class PreguntaVista extends JFrame {
    private JLabel idLabel;
    private JLabel preguntaLabel;
    private JLabel opcionesLabel;
    private JLabel correctaLabel;
    private JRadioButton[] respuestaButtons;
    private ButtonGroup buttonGroup;
    private JButton siguienteButton;
    private JButton confirmarButton;
    private JButton atrasButton;

    private InicioVista parentWindow;
    private int correctIndex;

    public PreguntaVista(InicioVista parentWindow) {       
        this.parentWindow = parentWindow; // Asignar el objeto recibido al atributo parentWindow
        
        initComponents();
    }

    /**
     * Inicializa y configura los componentes de la interfaz gráfica.
     */
    private void initComponents() {
        // Configuración de la ventana
        setTitle("Juego de preguntas para Programación 3 - CeRP del Suroeste - Prof. Domingo Pérez");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Panel para mostrar la pregunta
        JPanel preguntaPanel = new JPanel();
        preguntaPanel.setLayout(new BoxLayout(preguntaPanel, BoxLayout.Y_AXIS));
        preguntaPanel.setPreferredSize(new Dimension(600, 50)); // especificar una altura fija

        // Configuración de los componentes
        idLabel = new JLabel("ID de pregunta: " + (1001));
        idLabel.setBounds(20, 20, 200, 20);
        preguntaPanel.add(idLabel, BorderLayout.CENTER);

        preguntaLabel = new JLabel("Pregunta:");
        preguntaLabel.setBounds(20, 50, 80, 20);
        preguntaPanel.add(preguntaLabel, BorderLayout.CENTER);

        opcionesLabel = new JLabel("Opciones:");
        opcionesLabel.setBounds(20, 80, 80, 20);
        preguntaPanel.add(opcionesLabel, BorderLayout.CENTER);

        correctaLabel = new JLabel("Respuesta correcta:");
        correctaLabel.setBounds(20, 110, 140, 20);
        preguntaPanel.add(correctaLabel, BorderLayout.CENTER);

        add(preguntaPanel, BorderLayout.CENTER);

        // Crear el panel de respuestas
        JPanel answerPanel = new JPanel();
        answerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // agregar márgenes
        answerPanel.setLayout(new BoxLayout(answerPanel, BoxLayout.Y_AXIS));

        respuestaButtons = new JRadioButton[5];
        buttonGroup = new ButtonGroup();

        for (int i = 0; i < respuestaButtons.length; i++) {
            respuestaButtons[i] = new JRadioButton("Opción " + (i + 1));
            respuestaButtons[i].setActionCommand(Integer.toString(i));
            buttonGroup.add(respuestaButtons[i]);
            answerPanel.add(respuestaButtons[i]);
        }

        add(answerPanel, BorderLayout.SOUTH);
        
        // Panel para mostrar los botones de acción
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new FlowLayout());

        siguienteButton = new JButton("Siguiente");
        siguienteButton.setBounds(150, 170, 100, 30);

        confirmarButton = new JButton("Confirmar");
        confirmarButton.setBounds(260, 170, 100, 30);

        atrasButton = new JButton("Atrás");
        atrasButton.setBounds(20, 170, 100, 30);

        actionPanel.add(siguienteButton);
        actionPanel.add(confirmarButton);
        actionPanel.add(atrasButton);

        add(actionPanel, BorderLayout.SOUTH);

        // Hacer visible la ventana
        setVisible(true);
    }

    
    public JLabel getIdLabel() {
        return idLabel;
    }
    
    public void setIdLabel(JLabel idLabel) {
        this.idLabel = idLabel;
    }
    
    public JLabel getPreguntaLabel() {
        return preguntaLabel;
    }
    
    public void setPreguntaLabel(JLabel preguntaLabel) {
        this.preguntaLabel = preguntaLabel;
    }
    
    public JLabel getOpcionesLabel() {
        return opcionesLabel;
    }
    
    public void setOpcionesLabel(JLabel opcionesLabel) {
        this.opcionesLabel = opcionesLabel;
    }
    
    public JLabel getCorrectaLabel() {
        return correctaLabel;
    }
    
    public void setCorrectaLabel(JLabel correctaLabel) {
        this.correctaLabel = correctaLabel;
    }
    
    public JRadioButton[] getRespuestaButtons() {
        return respuestaButtons;
    }
    
    public void setRespuestaButtons(JRadioButton[] respuestaButtons) {
        this.respuestaButtons = respuestaButtons;
    }
    
    public ButtonGroup getButtonGroup() {
        return buttonGroup;
    }
    
    public void setButtonGroup(ButtonGroup buttonGroup) {
        this.buttonGroup = buttonGroup;
    }
    
    public JButton getSiguienteButton() {
        return siguienteButton;
    }
    
    public void setSiguienteButton(JButton siguienteButton) {
        this.siguienteButton = siguienteButton;
    }
    
    public JButton getConfirmarButton() {
        return confirmarButton;
    }
    
    public void setConfirmarButton(JButton confirmarButton) {
        this.confirmarButton = confirmarButton;
    }
    
    public JButton getAtrasButton() {
        return atrasButton;
    }
    
    public void setAtrasButton(JButton atrasButton) {
        this.atrasButton = atrasButton;
    }
    
    public InicioVista getParentWindow() {
        return parentWindow;
    }
    
    public void setParentWindow(InicioVista parentWindow) {
        this.parentWindow = parentWindow;
    }
    
    public int getCorrectIndex() {
        return correctIndex;
    }
    
    public void setCorrectIndex(int correctIndex) {
        this.correctIndex = correctIndex;
    }

    /**
     * Muestra un mensaje de error en la interfaz gráfica.
     * @param mensaje El mensaje de error a mostrar.
     */
    public void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Muestra un mensaje de confirmación en la vista.
     * 
     * @param mensaje el mensaje a mostrar.
     */
    public void mostrarMensajeConfirmacion(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Confirmación", JOptionPane.INFORMATION_MESSAGE);
    } 
        
}



/*package PreguntasDelCursoMVC;

import javax.swing.*;

import PreguntasDelCursoMVC.Modelo.Pregunta;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PreguntaVista extends JFrame implements ActionListener {
    private JLabel idLabel;
    private JLabel preguntaLabel;
    private JLabel opcionesLabel;
    private JLabel correctaLabel;
    private JButton siguienteButton;
    private JButton confirmarButton;
    private JButton atrasButton;

    private List<Pregunta> modelo;
    // Agregar atributo InicioVista llamado parentWindow
    private InicioVista parentWindow;


    private JRadioButton[] respuestaButtons;
    private ButtonGroup buttonGroup;

    private int correctIndex;


    public PreguntaVista(FileHandler<Pregunta> fileHandler, InicioVista parentWindow) {
        List<Pregunta> modelo = new ArrayList<Pregunta>();
        
        // Cargar las preguntas desde preguntas.txt
        try {
            modelo = fileHandler.fileToList();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        this.modelo = modelo;
        this.parentWindow = parentWindow; // Asignar el objeto recibido al atributo parentWindow


        // Configuración de la ventana
        setTitle("Juego de preguntas para Programación 3 - CeRP del Suroeste - Prof. Domingo Pérez");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Panel para mostrar la pregunta
        JPanel preguntaPanel = new JPanel();
        preguntaPanel.setLayout(new BoxLayout(preguntaPanel, BoxLayout.Y_AXIS));
        preguntaPanel.setPreferredSize(new Dimension(600, 50)); // especificar una altura fija

        // Configuración de los componentes
        idLabel = new JLabel("ID de pregunta: " + "num");
        idLabel.setBounds(20, 20, 200, 20);
        preguntaPanel.add(idLabel, BorderLayout.CENTER);

        preguntaLabel = new JLabel("Pregunta:");
        preguntaLabel.setBounds(20, 50, 80, 20);
        preguntaPanel.add(preguntaLabel, BorderLayout.CENTER);


        opcionesLabel = new JLabel("Opciones:");
        opcionesLabel.setBounds(20, 80, 80, 20);
        preguntaPanel.add(opcionesLabel, BorderLayout.CENTER);

        correctaLabel = new JLabel("Respuesta correcta:");
        correctaLabel.setBounds(20, 110, 140, 20);
        preguntaPanel.add(correctaLabel, BorderLayout.CENTER);


        add(preguntaPanel, BorderLayout.CENTER);

        // Crear el panel de respuestas
        JPanel answerPanel = new JPanel();
        answerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // agregar márgenes
        answerPanel.setLayout(new BoxLayout(answerPanel, BoxLayout.Y_AXIS));

        respuestaButtons = new JRadioButton[5];
        buttonGroup = new ButtonGroup();

        for (int i = 0; i < respuestaButtons.length; i++) {
            respuestaButtons[i] = new JRadioButton("Opción " + (i + 1));
            respuestaButtons[i].setActionCommand(Integer.toString(i));
            buttonGroup.add(respuestaButtons[i]);
            answerPanel.add(respuestaButtons[i]);
        }

        add(answerPanel, BorderLayout.SOUTH);

        //Primer pregunta
        // Crear un objeto com.cerp.Randomizer para obtener un elemento aleatorio de la lista
        com.cerp.Randomizer<Pregunta> randomizer = new com.cerp.Randomizer<>();
        // Obtener un elemento aleatorio de la lista de preguntas
        Pregunta currentQuestion = randomizer.getRandomElement(modelo);
        
        idLabel.setText("ID de pregunta: " + currentQuestion.getIdPregunta());
        String correctAnswer = currentQuestion.getCorrecta();
        preguntaLabel.setText(currentQuestion.getPregunta());
        int correctButtonIndex = com.cerp.Randomizer.getRandomIndex(0, respuestaButtons.length - 1);
        //int correctButtonIndex = (int) (Math.random() * (respuestaButtons.length));
        respuestaButtons[correctButtonIndex].setText(correctAnswer);
        int optionIndex = 0;
        for (int i = 0; i < respuestaButtons.length; i++) {
            if (i == correctButtonIndex) {
                continue;
            }
            String option = currentQuestion.getOpciones()[optionIndex];
            respuestaButtons[i].setText(option);  

            optionIndex++;
        }

        
        // Panel para mostrar los botones de acción
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new FlowLayout());

        siguienteButton = new JButton("Siguiente");
        siguienteButton.setBounds(150, 170, 100, 30);
        siguienteButton.addActionListener(this);

        confirmarButton = new JButton("Confirmar");
        confirmarButton.setBounds(260, 170, 100, 30);
        confirmarButton.addActionListener(this);

        atrasButton = new JButton("Atrás");
        atrasButton.setBounds(20, 170, 100, 30);
        atrasButton.addActionListener(this);

        actionPanel.add(siguienteButton);
        actionPanel.add(confirmarButton);
        actionPanel.add(atrasButton);

        add(actionPanel, BorderLayout.SOUTH);


    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == siguienteButton) {
            int numPregunta = com.cerp.Randomizer.getRandomIndex(0, modelo.size() - 1);
            Pregunta currentQuestion = modelo.get(numPregunta);
            String correctAnswer = currentQuestion.getCorrecta();
            idLabel.setText("ID de pregunta: " + currentQuestion.getIdPregunta());
            preguntaLabel.setText(currentQuestion.getPregunta());
            int correctButtonIndex = com.cerp.Randomizer.getRandomIndex(0, respuestaButtons.length - 1);
            this.correctIndex = correctButtonIndex;
            //int correctButtonIndex = (int) (Math.random() * (respuestaButtons.length));
            respuestaButtons[correctButtonIndex].setText(correctAnswer);
            int optionIndex = 0;
            for (int i = 0; i < respuestaButtons.length; i++) {
                if (i == correctButtonIndex) {
                    continue;
                }
                String option = currentQuestion.getOpciones()[optionIndex];
                if (option.equals(correctAnswer)) {
                    optionIndex++;
                }
                respuestaButtons[i].setText(option);
                optionIndex++;
            }
                
            buttonGroup.clearSelection();

        } else if (e.getSource() == confirmarButton) {
            int selectedAnswer = Integer.parseInt(buttonGroup.getSelection().getActionCommand());
                if (selectedAnswer==this.correctIndex){
                    JOptionPane.showMessageDialog(PreguntaVista.this, "¡Correcto!", "Resultado", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(PreguntaVista.this, "¡Incorrecto!" + selectedAnswer + this.correctIndex , "Resultado", JOptionPane.ERROR_MESSAGE);
                }
            
        } else if (e.getSource() == atrasButton) {
            parentWindow.setVisible(true); // Hacer visible la ventana InicioVista
            dispose();
        }
        //pack(); // Ajustar tamaño al contenido
        //setResizable(false); // No permitir redimensionar
    }

        
}*/