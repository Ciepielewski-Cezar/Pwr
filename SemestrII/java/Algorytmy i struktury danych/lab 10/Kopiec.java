/*
 * Zaimplementuj kopiec minimalny na bazie tablicy oraz metody:
 * 1.enqueue() i deque() kolejki priorytetowej
 * 2.Wykorzystać kopiec do posortowania tablicy elementów (w miejscu)
 * 3.metodę zmieniającą priorytet elementu o podanym indeksie
 */

import java.util.Comparator;
import java.util.Arrays;

public class Kopiec <T> {
    private Object[] kopiec;
    private int size;
    private Comparator<T> komp;

    public Kopiec (int initSize, Comparator<T> komp) {
        kopiec = new Object[initSize];
        size = 0;
        this.komp = komp;
    }

    //1 a) metoda enqueue
    public void enqueue(T nowy){
        if(size == kopiec.length)
            powieksz();

        kopiec[size++] = nowy;
    }
    //1 b) metoda dequeue
    public T dequeue(){
        T zwracany =(T) kopiec[0];
        kopiec[0] = kopiec[--size];
        przesunDol(0);
        return zwracany;
    }

    //2. Sortowanie
    public static <T extends Comparable<T>> void sort (T[] tab) {
        sort(tab, T::compareTo);
    }

    public static <T> void sort (T[] tab, Comparator<T> komp) {
        Kopiec<T> kopiec = new Kopiec<T>(tab.length, komp);

        for (T t : tab){
            kopiec.enqueue(t);
        }
        for (int i = 0; i < tab.length; i++){
            tab[i] = kopiec.dequeue();
        }
    }

    //3.zmiana priorytetu
    public void priorytet(int indeks, T x){
        if(indeks >= size || indeks < 0){
            throw new ArrayIndexOutOfBoundsException(indeks);
        }

        kopiec[indeks] = x;
        zmiana(przesunGora(indeks));
    }

    //metody pomocnicze
    private void powieksz(){
        Object[] nowyKopiec = new Object[kopiec.length * 2];

        for(int i = 0; i < kopiec.length; i++)
            nowyKopiec[i] = kopiec[i];

        kopiec = nowyKopiec;
    }

    private void przesunDol(int i){
        int dzieckoI = -1;

        if (i * 2 + 2 < size)
            dzieckoI = porownaj(i * 2 + 1, i * 2 + 2);
        else if (i* 2 + 1 < size)
            dzieckoI = i * 2 + 1;

        if (dzieckoI != -1){
            T parent = (T) kopiec[i];
            T child = (T) kopiec[dzieckoI];

            if (komp.compare(child, parent) < 0)
                zamien(i, dzieckoI);

            przesunDol(dzieckoI);
        }
    }

    private int przesunGora(int indeks){
        if(indeks == 0){
            return 0;
        }

        int rodzicIndeks = (indeks - 1) / 2;
        T rodzic = (T) kopiec[rodzicIndeks];
        T x = (T) kopiec[indeks];

        if(komp.compare(x, rodzic) < 0){
            zamien(rodzicIndeks, indeks);
            return przesunGora(rodzicIndeks);
        }
        return indeks;
    }

    private void zmiana(int i){
        if(i >= size){
            return;
        }
        int dzieckoIndeks = -1;

        if (i * 2 + 2 < size){
            dzieckoIndeks = porownaj(i*2+1, i*2+2);
        }
        else if (i * 2 + 1 < size){
            dzieckoIndeks = i*2+1;
        }

        if (dzieckoIndeks != -1) {
            T rodzic = (T) kopiec[i];
            T dziecko = (T) kopiec[dzieckoIndeks];

            if (komp.compare(dziecko, rodzic) < 0)
                zamien(i, dzieckoIndeks);

            zmiana(i * 2 + 1);
            zmiana(i * 2 + 2);
        }
    }
    private int porownaj(int x, int y){
        T X = (T) kopiec[x];
        T Y = (T) kopiec[y];

        if (komp.compare(X, Y) < 0){
            return x;
        } else{
            return y;
        }
    }

    private void zamien(int x, int y) {
        Object temp = kopiec[x];
        kopiec[x] = kopiec[y];
        kopiec[y] = temp;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty () {
        return size == 0;
    }

    public void wyswietl(){
        for (int i = 0; i < size; i++) {
            System.out.println(kopiec[i]);
        }
    }
}