
public class Main {

	private int cStart, cEnd;
	private final int UPPERBOUND = 1000;

	public Main(int cStart, int cEnd) {
		this.cStart = cStart;
		this.cEnd = cEnd;

		run();
	}

	public void run() {
		int c = this.cStart;
		System.out.println("begin running c from " + c + " to " + this.cEnd);

		while(c < this.cEnd) {

			long startTime = System.currentTimeMillis();

			for(int w1=1; w1<UPPERBOUND; w1++) {
				for(int w2=1; w2<UPPERBOUND; w2++) {
					for(int w3=1; w3<UPPERBOUND; w3++) {

						//check conditions
						if( gcdIsOne(w1, w2, w3)
								&& qDivides(w1, w2, w3, c)
								&& dMin(w1, w2, w3, c)) {
							System.out.println("The vector w = <" + w1 + ", " + w2 + ", " + w3 + "> passes all conditions!");
						}
					}
				}
			}

			long endTime = System.currentTimeMillis();
			long elapsed = endTime - startTime;

			System.out.println("c: " + c + " time: " + elapsed);
			c++;
		}
	}

	//	d=(sqrt(2)-1)/(sqrt(1+c))
	public boolean dMin(int w1, int w2, int w3, int c) {
		int q = w1*w1 + c*w2*w2 - w3*w3;
		int d = (int) ((Math.sqrt(2.0)-1) / Math.sqrt(1.0 + c));

		if (d == 0) return false;
		return w3*w3 * (w1*w1 + c*c*w2*w2) <= (q*q) / d;
	}

	// find if q divides 2c evenly
	public boolean qDivides(int w1, int w2, int w3, int c) {
		int q = w1*w1 + c*w2*w2 - w3*w3;
		return q % (2*c) == 0;
	}

	//	gcd(a,b,c)=gcd(a,(gcd(b,c))
	public boolean gcdIsOne(int a, int b, int c) {
		return gcd(a, gcd(b, c)) == 1;
	}

	// Euclid's algorithm
	public int gcd(int a, int b) {
		if(b==0) return a;
		return gcd(b, a%b);
	}

	public static void main(String args[]) {
		new Main(1, 10);
	}
}
