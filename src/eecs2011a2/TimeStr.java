package eecs2011a2;

public class TimeStr {
	private int time;
	private String str;

	
	public TimeStr(int t, String s){
		time = t;
		str = s;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String toString(){
		return "time: " + time+ "    str: " + str;
	}
}
