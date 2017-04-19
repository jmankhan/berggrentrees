
public class Main {

	public Main(int cStart, int cEnd) {
		run(cStart, cEnd);
	}

	public void run(int c, int cEnd) {
		while (c < cEnd) {
			long startTime = System.currentTimeMillis();

			float cf = (float) Math.ceil(4 * Math.pow(Math.sqrt(2.0) + 1.0, 2.0) * c * c * (1 + c));
			float w3_size = (float) Math.ceil(cf / Math.sqrt(1 + c * c));
			w3_size = 1000;
			try {
				//computation loop
				for (long w3 = 1; w3 <= w3_size; w3++) {

					float w1_size = (float) Math.ceil(Math.sqrt((cf / (w3 * w3)) - (c * c)));
					w1_size = 1000;
					for (long w1 = 1; w1 <= w1_size; w1++) {

						float w2_size = (float) Math.ceil((1 / c) * Math.sqrt(cf / (w3 * w3) - (w1 * w1)));
						w2_size = 1000;
						for (int w2 = 1; w2 <= w2_size; w2++) {

							// check conditions
							if (gcdIsOne(w1, w2, w3) && qDivides(w1, w2, w3, c) && qLessThan(w1, w2, w3, c) && dMin(w1, w2, w3, c)) {
								System.out.println("The vector w = <" + w1 + ", " + w2 + ", " + w3 + "> passes all conditions at c = " + c);
							}
						}
					}
					if(w3 % 100 == 0)
						System.out.println("finished w3 = " + w3);
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("cf: " + cf);
				System.out.println("c: " + c);
				System.out.println("w3_size: " + w3_size);
				System.exit(0);
			}

			long endTime = System.currentTimeMillis();
			long elapsed = endTime - startTime;

			System.out.println("done with c = " + c + " in " + elapsed);
			c++;
		}
	}

	// d=(sqrt(2)-1)/(sqrt(1+c))
	public static boolean dMin(long w1, long w2, long w3, long c) {
		long q = w1 * w1 + c * w2 * w2 - w3 * w3;
		float d = (float) ((Math.sqrt(2.0) - 1) / Math.sqrt(1.0 + c));

		// check if d == 0
		if (Math.signum(d) == 0)
			return false;
		return w3 * w3 * (w1 * w1 + c * c * w2 * w2) <= (q * q) / (d * d);
	}

	// find if q divides 2c evenly
	public static boolean qDivides(long w1, long w2, long w3, long c) {
		long q = w1 * w1 + c * w2 * w2 - w3 * w3;
		return q % (2 * c) == 0;
	}

	// gcd(a,b,c)= gcd(a,(gcd(b,c))
	public static boolean gcdIsOne(long a, long b, long c) {
		if(a == b || b == c || a == c)
			return false;
		
		return gcd(a, gcd(b, c)) == 1;
	}

	// Euclid's algorithm
	public static long gcd(long a, long b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}
	
	// q <= 2*c
	public static boolean qLessThan(long w1, long w2, long w3, long c) {
		long q = w1 * w1 + c * w2 * w2 - w3 * w3;
		return Math.abs(q) <= 2*c;
	}

	public static void main(String args[]) {
		new Main(1, 25000);
	}
}
