package ga.lab;

import ga.lab.functions.Functions;

import java.util.ArrayList;
import java.util.HashMap;

public class Operators {

    // Відстань Хеммінга

    public static int getHammingDistance(String ch1, String ch2) {
        if (ch1.length() != ch2.length()) {
            System.out.println("The length of two chromosomes don't match");
        }
        int distance = 0;
        for (int i = 0; i < ch1.length(); i++) {
            if (ch1.charAt(i) != ch2.charAt(i))
                distance++;
        }
        return distance;
    }

    // Евклідова відстань

    // Функція співучасті

    public static double sharingFunction(String ch1, String ch2, int radius, int alpha) {
        double sharingFunction;
        if (getHammingDistance(ch1, ch2) < radius) {
            sharingFunction = (1 - (Math.pow((getHammingDistance(ch1, ch2) / radius), alpha)));
        } else {
            sharingFunction = 0;
        }
        return sharingFunction;
    }

    // Радіус ніші

    public static double calculateRadius(int dimension, int numberOfExtr, double r) {
        // я перетворила корінь степеня D в таке, бо не знайшла корінь певного
        // степеня в бібліотеці
        return r / MathOps.root(numberOfExtr, dimension);
    }

    // допоміжна функція r (поки не вшарила як її рахувати, додаю статтю де на 5 сторінці вона є 2.5 The Niche Radius problem)

    /**
     * Calculates the r-value
     *
     * @param dimension dimension space
     * @param lower lower bound of x
     * @param upper upper bound of x
     * @return r
     */
    public static double calculateR(int dimension, double lower, double upper) {
        //TODO: check
        double accum = 0;
        for (int i =0 ; i< dimension ; i++) {
            accum += Math.pow((upper - lower), 2);
        }

        double r = (1./2) * Math.sqrt(accum);

        return r;
    }

    // Число ніші

    public static double calculateNicheNumber(String ch1, ArrayList<String> selectedChList, int N, int radius, int alpha) {
        double m = 0;
        for (int i = 0; i < N; i++) {
            m = m + sharingFunction(ch1, selectedChList.get(i), radius, alpha);
        }
        return m;
    }

    // думаю, що теж варто наперед мати таблицю обрахованої функції здоров'я для всіх хромосом
    public static HashMap<Integer, Double> calcInitialFitnessMap(int n, Functions function) {
        HashMap<Integer, Double> initialFitnessMap = new HashMap<Integer, Double>();
        for (int i = 1; i <= 1000; i++) {
            initialFitnessMap.put(i, function.calculate(i / 1000.));
        }
        return initialFitnessMap;
    }

    // Пониження функції пристосованості в ніші

    // Кросинговер

    // Відбір

    // Мутація


    // відбір
    // public static ArrayList<String> selection (int dimension)
    // {
    // ArrayList<String> selectedChromosomes = new ArrayList<String>();
    // return selectedChromosomes;
    //
    // }

}
