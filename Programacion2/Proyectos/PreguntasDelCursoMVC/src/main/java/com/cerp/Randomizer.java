package com.cerp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase com.cerp.Randomizer para obtener índices de forma aleatoria
 * 
 * @param <T> tipo de dato genérico
 */
public class Randomizer<T> {

    /**
     * Obtiene un índice aleatorio en un rango específico
     * 
     * @param min índice mínimo
     * @param max índice máximo
     * @return índice aleatorio en el rango especificado
     */
    public static int getRandomIndex(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    /**
     * Obtiene un elemento aleatorio de una lista
     * 
     * @param list lista de elementos
     * @return elemento aleatorio de la lista
     */
    public  T getRandomElement(List<T> list) {
        int index = getRandomIndex(0, list.size() - 1);
        return list.get(index);
    }

    /**
     * Obtiene un elemento aleatorio de un ArrayList
     *
     * @param list ArrayList de elementos
     * @return elemento aleatorio del ArrayList
     */
    public T getRandomElement(ArrayList<T> list) {
        int index = getRandomIndex(0, list.size() - 1);
        return list.get(index);
    }

    /**
     * Obtiene un elemento aleatorio de una LinkedList
     *
     * @param list LinkedList de elementos
     * @return elemento aleatorio de la LinkedList
     */
    public T getRandomElement(LinkedList<T> list) {
        int index = getRandomIndex(0, list.size() - 1);
        return list.get(index);
    }
}
