
public class Main {

	private int cStart, cEnd;
	private final int UPPERBOUND = 1000;

	public Main(int cStart, int cEnd) {
		this.cStart = cStart;
		this.cEnd = cEnd;

		run(cStart, cEnd);
	}

	public void run(int c, int cEnd) {
		System.out.println("begin running c from " + c + " to " + this.cEnd);

		while (c < cEnd) {

			long startTime = System.currentTimeMillis();

			long cf = (int) Math.ceil(4 * Math.pow(Math.sqrt(2.0) + 1.0, 2.0) * c * c * (1 + c));
			long w3_size = (int) Math.ceil(cf / Math.sqrt(1 + c * c));

//			System.out.println("c: " + c);
//			System.out.println("cf: " + cf);
			for (long w3 = 1; w3 <= w3_size; w3++) {

				try {
					long w1_size = (int) Math.ceil(Math.sqrt((cf / (w3 * w3)) - (c * c)));
					for (long w1 = 1; w1 <= w1_size; w1++) {

						long w2_size = (int) Math.ceil(1 / c * Math.sqrt(cf / (w3 * w3) - (w1 * w1)));
						for (int w2 = 1; w2 <= w2_size; w2++) {
							// check conditions
							if (gcdIsOne(w1, w2, w3) && qDivides(w1, w2, w3, c) && dMin(w1, w2, w3, c)) {
								System.out.println(
										"The vector w = <" + w1 + ", " + w2 + ", " + w3 + "> passes all conditions!");
							}
						}
//						System.out.println("w2: " + w2_size);
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("cf: " + cf);
					System.out.println("c: " + c);
					System.out.println("w3_size: " + w3_size);
					System.out.println("w3:" + w3);
					System.out.println((cf / (w3 * w3)));
					System.exit(0);
				}
//				System.out.println("w1: " + w1_size);

			}

//			System.out.println("w3: " + w3_size);

			long endTime = System.currentTimeMillis();
			long elapsed = endTime - startTime;

			System.out.println("c: " + c + " time: " + elapsed);
			c++;
		}
	}

	// d=(sqrt(2)-1)/(sqrt(1+c))
	public boolean dMin(long w1, long w2, long w3, long c) {
		long q = w1 * w1 + c * w2 * w2 - w3 * w3;
		long d = (int) ((Math.sqrt(2.0) - 1) / Math.sqrt(1.0 + c));

		if (d == 0)
			return false;
		return w3 * w3 * (w1 * w1 + c * c * w2 * w2) <= (q * q) / d;
	}

	// find if q divides 2c evenly
	public boolean qDivides(long w1, long w2, long w3, long c) {
		long q = w1 * w1 + c * w2 * w2 - w3 * w3;
		return q % (2 * c) == 0;
	}

	// gcd(a,b,c)=gcd(a,(gcd(b,c))
	public boolean gcdIsOne(long a, long b, long c) {
		return gcd(a, gcd(b, c)) == 1;
	}

	// Euclid's algorithm
	public long gcd(long a, long b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}

	public static void main(String args[]) {
		new Main(1, 10000);
	}
}
