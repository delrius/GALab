package ga.lab;

import java.util.ArrayList;
import java.util.HashMap;

public class Operators {

	// ³������ ������

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

	// �������� �������

	// ������� ��������

	public static double sharingFunction(String ch1, String ch2, int radius, int alpha) {
		double sharingFunction;
		if (getHammingDistance(ch1, ch2) < radius) {
			sharingFunction = (1 - (Math.pow((getHammingDistance(ch1, ch2) / radius), alpha)));
		} else {
			sharingFunction = 0;
		}
		return sharingFunction;
	}

	// ����� ���

	public static double calculateRadius(int dimension, int numberOfExtr, double r) {
		// � ����������� ����� ������� D � ����, �� �� ������������ �������
		// ������� � ��������
		return r / Math.sqrt(Math.pow(numberOfExtr, 1 / dimension));
	}

	// �������� ������� r (���� �� ������� �� �� ��������, ����� ������ �� �� 5 ������� ���� � 2.5 The Niche Radius Problem)
	public static double calculateR(int dimension, int numberOfExtr, double r) {
		return 0;
	}

	// ����� ���

	public static double calculateNicheNumber(String ch1, ArrayList<String> selectedChList, int N, int radius, int alpha) {
		double m = 0;
		for (int i = 0; i < N; i++) {
			m = m + sharingFunction(ch1, selectedChList.get(i), radius, alpha);
		}
		return m;
	}

	// ������ - ������� ������'�
	// ��! �� ���� �� ������ ���������� ���������� ������� �������� %) ����
	// ���� ��� ���� ����� ������� ������ ��� ��� ������� F15, F16, F18 �� F19
	public static double fitnessFunctionF15(int n, double x) {
		double fitness = 0;
		for (int i = 0; i < n; i++) {
			fitness = Math.pow(Math.sin(5 * Math.PI * x), 6);
		}
		return fitness = 1 / n * fitness;
	}

	//����� �� ��� ����� ������� ���� ������� ��������� ������� ������'� ��� ��� ��������? �� ������?
	public static HashMap<Integer, Double> calcInitialFitnessMap(int n, String funcName) {
		HashMap<Integer, Double> initialFitnessMap = new HashMap<Integer, Double>();
		for (int i = 1; i <= 1000; i++) {
			if (funcName.equals("F15")) {
				initialFitnessMap.put(i, fitnessFunctionF15(n, i / 1000));
			} else if (funcName.equals("F16")) { // to do
			} else if (funcName.equals("F18")) { // to do
			} else if (funcName.equals("F19")) { // to do
			}

		}
		return initialFitnessMap;
	}
	
	//��������� ������� �������������� � ���
	
	//�����������
	
	//³���
	
	//�������
	
	
	
	// ����
	// public static ArrayList<String> selection (int dimension)
	// {
	// ArrayList<String> selectedChromosomes = new ArrayList<String>();
	// return selectedChromosomes;
	//
	// }

}
