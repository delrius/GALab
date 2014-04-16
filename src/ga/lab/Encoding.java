package ga.lab;

import java.util.HashMap;

public class Encoding {

    // static int par1 = 10;
//deprecated
    public static HashMap<Integer, String> encode() {
        HashMap<Integer, String> encodeResult = new HashMap<>();
        for (int i = 0; i <= 1000; i++) {
            String key;
            if (Integer.toBinaryString(i).length() == 10) {
                key = Integer.toBinaryString(i);
            } else {
                String temp1 = "0000000000"
                        + Integer.toBinaryString(i);
                key = (temp1).substring(temp1.length() - 10, temp1.length());
            }

            encodeResult.put(i, key);
        }
        return encodeResult;
    }

    public static void main(String[] args) {

        HashMap<Integer, String> res = encode();
        System.out.println(res.get(55));
        System.out.println(res.get(1000));
        int i = 99;
        System.out.println((double) (i) / 1000);
        // System.out.println(Operators.getHammingDistance(res.get(55),
        // res.get(1000)));
        // System.out.println(Operators.mutate(res.get(55)).toCharArray());
        // res.get(55)
        // Chromosome a=new Chromosome(10, Functions.F15);
        // System.out.println(a.getChromosome());
        // System.out.println(Integer.valueOf(a.getChromosome()));
        // System.out.println(a.getFitness());
        // System.out.println(Chromosome.decodeChromosome(a.getChromosome()));
        // HashMap<Chromosome, Double> b = Algorithm.encodeMap(Functions.F15);
        // System.out.println(b.get(a));
        
        //int n1=867;
        //int n2=473;
        Chromosome chr1 = new Chromosome(867);
        Chromosome chr2 = new Chromosome(473);
        System.out.println(chr1.getChromosome());
        System.out.println(chr2.getChromosome());
        Chromosome [] arr =chr1.crossover(chr2);
        for (int j=0; j<arr.length;j++) {
        	   System.out.println(arr[j].getChromosome());
        }
        System.out.println(Operators.calculateR(1, 0, 1));
    }
}