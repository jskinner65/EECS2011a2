package eecs2011a2;

import java.util.ArrayList;
import java.util.List;

public class Processes {
	static List<TimeStr> CPU_time = new ArrayList<TimeStr>();
	int processPriority = 1;
	boolean running = false;

	
	
	
	
	
	
	
	public static void main(String[] args) {

		TimeStr e = new TimeStr(10, "CPU");
		TimeStr f = new TimeStr(9, "IO");

		CPU_time.add(e);
		CPU_time.add(e);
		CPU_time.add(f);
		CPU_time.add(e);
		for (int i = 0; i < CPU_time.size(); i++) {
			System.out.println(CPU_time.get(i));
		}
		
		CPU_time.remove(0);
		CPU_time.remove(0);
		CPU_time.remove(0);	
		for (int i = 0; i < CPU_time.size(); i++) {
			System.out.println(CPU_time.get(i));
		}
	}
}
