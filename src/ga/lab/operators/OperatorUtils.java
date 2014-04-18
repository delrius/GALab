package ga.lab.operators;

import ga.lab.entities.*;

public class OperatorUtils {

//    public static void main(String[] args) {
//        String s = "rdrdrdrdrd--RDRDRDRDRDRDRDRDRDRD--rdrdrdrdrdFFFFFFFFFF--GGGGGGGGGG";
//        final Pair<String, String> diploidFromString = getDiploidFromString(s);
//        System.out.println(diploidFromString.getVal1());
//        System.out.println(diploidFromString.getVal2());
//    }


    public static Pair<String, String> getDiploidFromString(String rep) {
        StringBuilder str1 = new StringBuilder();
        StringBuilder str2 = new StringBuilder();
        int lengthOfChr = Chromosome.LENGTH * 2 + 2;
        int number = rep.length() / lengthOfChr;
        for (int i = 0; i < number ; i++) {
            final String whole = rep.substring(i * lengthOfChr, (i+1) * lengthOfChr);
            String [] split = whole.split("--");
            str1.append(split[0]);
            str2.append(split[1]);
        }
        return new Pair<>(str1.toString(), str2.toString());
    }

    public static Individual extractFromString(String string) {
        int chrLength = Chromosome.LENGTH;
        int numberOfChromosomes = string.length() / chrLength;
        Chromosome[] list = new Chromosome[numberOfChromosomes];
        for (int i = 0; i < numberOfChromosomes; i++) {
            final String chr = string.substring(i * chrLength, i * chrLength
                    + chrLength);
            list[i] = SimpleChromosome.buildFromString(chr);
        }
        return new Individual(list);
    }

    public static Individual extractFromString(String str1, String str2) {
        int chrLength = Chromosome.LENGTH;
        int numberOfChromosomes = str1.length() / chrLength;
        Chromosome[] list = new Chromosome[numberOfChromosomes];
        for (int i = 0; i < numberOfChromosomes; i++) {
            final String chr1 = str1.substring(i * chrLength, i * chrLength
                    + chrLength);
            final String chr2 = str2.substring(i * chrLength, i * chrLength
                    + chrLength);
            list[i] = DiploidChromosome.buildFromString(chr1, chr2);
        }
        return new Individual(list);
    }

    public static double minInPop(Individual[] pop, int k) {
        double min = 1.0;

        for (int i = 0; i < pop.length; i++) {
            Chromosome[] chr = pop[i].getChromosomes();
            if (chr[k].getValue() < min) {
                min = chr[k].getValue();
            }
        }
        return min;
    }

    public static double maxInPop(Individual[] pop, int k) {
        double max = 0.0;

        for (int i = 0; i < pop.length; i++) {
            Chromosome[] chr = pop[i].getChromosomes();
            if (chr[k].getValue() > max) {
                max = chr[k].getValue();
            }
        }
        return max;
    }
}
