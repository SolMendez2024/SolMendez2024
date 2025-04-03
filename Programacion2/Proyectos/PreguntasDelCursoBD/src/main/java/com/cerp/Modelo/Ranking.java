package com.cerp.Modelo;
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.List;

 public class Ranking {
    private int userId; // índice de la pregunta
    private int idPreg; // pregunta
    private int correccion; 

    public Ranking(){
        
    }
    
 public int getIdPreg() {
     return idPreg;
 }
 public int getUserId() {
     return userId;
 }
 public int getCorreccion() {
     return correccion;
 }
 public void setIdPreg(int idPreg) {
     this.idPreg = idPreg;
 }
 public void setUserId(int userId) {
     this.userId = userId;
 } 
 public void setCorreccion(int correccion) {
     this.correccion = correccion;
 }
}
