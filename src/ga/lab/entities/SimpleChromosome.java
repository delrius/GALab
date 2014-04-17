package ga.lab.entities;

public class SimpleChromosome extends Chromosome {

	private int value;
	private String representation;

	private SimpleChromosome() {
	}

	// TODO: check for range
	public SimpleChromosome(String value) {
		this.representation = value;
		this.value = grayDecode(Integer.valueOf(value));
	}

	public SimpleChromosome(int value) {
		this.value = value;
		this.representation = encodeChromosome(value);
	}

	public static int grayEncode(Integer ch) {
		return ch ^ (ch >>> 1);
	}

	public static int grayDecode(int n) {
		int p = n;
		while ((n >>>= 1) != 0)
			p ^= n;
		return p;
	}

	// encode chromosome
	public static String encodeChromosome(Integer ch) {
		String res = Integer.toBinaryString(grayEncode(ch));
		if (res.length() != LENGTH) {
			String temp1 = "0000000000" + res;
			res = temp1.substring(temp1.length() - 10, temp1.length());
		}
		return res;
	}

	@Override
	public String build() {
		return representation;
	}

	public double getValue() {
		return value / 1000.;
	}

}
