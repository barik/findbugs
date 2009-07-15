

public class ICAST {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int x=20;
		byte q=10;
		short s=5;
		s=(short)(q>>>16);
		q=(byte)(q>>>16);
		x=x>>>37;
		x=x<<36;
		x = q+s+x;
		x = x >> 38;

		double y=(double)x;
		y=Math.ceil(y);
		double z=Math.ceil((double)x);	
		System.out.println(x+q+s+x+y+z);
	}

	public int roundUp(int a, int b) {
		return (int) Math.ceil(a/b);
	}

	public double doubleDivision(int a, int b) {
		return (double) (a/b);
	}
	public int round(int a, int b) {
		return Math.round(a/b);
	}

	public long convertDaysToMilliseconds(int days) {
		return days*24*60*60*1000;
	}
	public long convertDaysToMilliseconds2(int days) {
		return 24*60*60*1000*days;
	}

	public long falsePositive(byte[] value, int offset, long c) {
		c += ((long)value[offset+22]) << 56;
		return c;

	}
	public long bug(byte[] value, int offset, long c) {
		c += (value[offset+22] & 0xff) << 48;
		return c;

	}
}