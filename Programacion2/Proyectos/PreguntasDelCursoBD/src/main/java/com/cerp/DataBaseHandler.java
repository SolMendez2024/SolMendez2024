package com.cerp;

import com.cerp.Modelo.Pregunta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler {
    private static final String URL = "jdbc:mysql://localhost:3306/preguntas_mvc";
    private static final String USER = "root"; // Cambia esto si usas otro usuario
    private static final String PASSWORD = ""; // Asegúrate de configurar tu contraseña

    public DataBaseHandler() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insertarPregunta(Pregunta pregunta) {
        String sql = "INSERT INTO preguntas (pregunta, respuesta_correcta) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pregunta.getPregunta());
            stmt.setString(2, pregunta.getCorrecta());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int consultarUltimaPregunta(){
            int ultimoId = -1;
            String sql = "SELECT MAX(id) FROM preguntas";  // Alternativa: "SELECT LAST_INSERT_ID();"

            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                if (rs.next()) {
                    ultimoId = rs.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return ultimoId;
    }

    public void insertarRespuestas_Incorrectas(Pregunta pregunta) {
        String sql = "INSERT INTO respuestas_incorrectas (id_preg,id_resp,resp_inc) VALUES (?, ?, ?)";
        for (int i = 0; i < pregunta.getOpciones().size(); i++) {
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, this.consultarUltimaPregunta());
                stmt.setInt(2, i + 1);
                stmt.setString(3, pregunta.getOpciones().get(i));
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



   public List<Pregunta> obtenerPreguntas() {
        List<Pregunta> preguntas = new ArrayList<>();
        /*String sql = "SELECT pregunta FROM preguntas";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                preguntas.add(rs.getString("pregunta"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        return preguntas;
    }
}