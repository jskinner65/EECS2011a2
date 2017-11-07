package eecs2011a2;

import java.util.ArrayList;
import java.util.List;

public class PreemptiveRoundRobin implements Algorithms  {

	List<Processes> processes = new ArrayList<Processes>();
	long totalWaitTime = 0;
	int numberOfProcesses = 0;
	public void runAlgorithm(int burstTime) {
		long time = 0;
		long processTime;
		Test.createProcesses();
		processes = Test.getProcesses();
		while (!Test.allProcessDone()) {
			processTime = 0;
			System.out.println("Wait time was: " +waitTime(time));
			System.out.println("StartTime: " + time);
			while (processTime < processes.get(0).getTotalTime()) {
				processTime++;
				time++;
			}
			System.out.println("EndTime: " + time);
			System.out.println("");
			processes.remove(0);
		}
		System.out.println("");
		System.out.println("Average Wait Time was:  " + averageWait());
	}

	@Override
	public long waitTime(long time) {
		long waitTime = (time - processes.get(0).getQueuedTime());
		processes.get(0).setWaitTime(waitTime);
		totalWaitTime += waitTime;
		numberOfProcesses++;
		return waitTime;
	}

	@Override
	public long averageWait() {
		return (long) (totalWaitTime / numberOfProcesses);
	}

	public static void main(String[] args) {
		PreemptiveRoundRobin start = new PreemptiveRoundRobin();
		start.runAlgorithm(4);
	}

	@Override
	public void runAlgorithm() {
		; // Intentionally left blank
		
	}

}
