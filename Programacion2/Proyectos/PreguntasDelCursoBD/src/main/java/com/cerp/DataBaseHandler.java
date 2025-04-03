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
        String sql = "INSERT INTO preguntas (id, pregunta, respuesta) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1,pregunta.getIdPregunta());    
            stmt.setString(2, pregunta.getPregunta());
            stmt.setString(3, pregunta.getCorrecta());
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


//SELECT * FROM preguntas JOIN respuestas_incorrectas ON preguntas.id = respuestas_incorrectas.id_resp
   public List<Pregunta> obtenerPreguntas() {
        List<Pregunta> preguntas = new ArrayList<>();
        String sql = "SELECT\r\n" + //
                        "    preguntas.id,\r\n" + //
                        "    preguntas.pregunta,\r\n" + //
                        "    preguntas.respuesta,\r\n" + //
                        "    GROUP_CONCAT(\r\n" + //
                        "        CASE WHEN respuestas_incorrectas.id_resp = 1 THEN respuestas_incorrectas.resp_inc END\r\n" + //
                        "    ) AS resp_inc1,\r\n" + //
                        "    GROUP_CONCAT(\r\n" + //
                        "        CASE WHEN respuestas_incorrectas.id_resp = 2 THEN respuestas_incorrectas.resp_inc END\r\n" + //
                        "    ) AS resp_inc2,\r\n" + //
                        "    GROUP_CONCAT(\r\n" + //
                        "        CASE WHEN respuestas_incorrectas.id_resp = 3 THEN respuestas_incorrectas.resp_inc END\r\n" + //
                        "    ) AS resp_inc3,\r\n" + //
                        "    GROUP_CONCAT(\r\n" + //
                        "        CASE WHEN respuestas_incorrectas.id_resp = 4 THEN respuestas_incorrectas.resp_inc END\r\n" + //
                        "    ) AS resp_inc4\r\n" + //
                        "FROM\r\n" + //
                        "    preguntas\r\n" + //
                        "JOIN\r\n" + //
                        "    respuestas_incorrectas ON preguntas.id = respuestas_incorrectas.id_preg\r\n" + //
                        "GROUP BY\r\n" + //
                        "    preguntas.id, preguntas.pregunta, preguntas.respuesta;";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Pregunta pregunta = new Pregunta() ;
                pregunta.setIdPregunta(rs.getInt(1));
                pregunta.setPregunta(rs.getString(2));
                pregunta.setCorrecta(rs.getString(3));
             
            List<String> incorrectas = new ArrayList<>();
            incorrectas.add(rs.getString(4));
            incorrectas.add(rs.getString(5));
            incorrectas.add(rs.getString(6));  
            incorrectas.add(rs.getString(7));
            pregunta.setOpciones(incorrectas);        
            preguntas.add(pregunta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preguntas;
    }
}