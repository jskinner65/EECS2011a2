package eecs2011a2;

public class TimePType {
	private int time;
	private PType str;
	private boolean done = false;

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

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
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
