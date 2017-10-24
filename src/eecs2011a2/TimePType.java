package eecs2011a2;

public class TimePType {
	private int time;
	private PType str;

	public TimePType(int t, PType s) {
		time = t;
		str = s;
	}

	public PType getStr() {
		return str;
	}

	public void setStr(PType str) {
		this.str = str;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String toString() {
		return "time: " + time + "    str: " + str;
	}
}
