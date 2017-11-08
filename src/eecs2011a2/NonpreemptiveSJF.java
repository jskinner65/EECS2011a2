package eecs2011a2;

import java.util.ArrayList;
import java.util.List;
//runs the algorithms on the processes and ouputs the Wait times etc for each process
// based on Shorted Job First
public class NonpreemptiveSJF implements Algorithms {
	List<Processes> processes = new ArrayList<Processes>();
	long totalWaitTime = 0;
	int shortestJobIndex = 0;
	int numberOfProcesses = 0;

	public void runAlgorithm() {
		long time = 0;
		long processTime;
		Test.createProcesses();
		processes = Test.getProcesses();
		while (!Test.allProcessDone()) {
			processTime = 0;
			System.out.println(shortestJobIndex);
			System.out.println(processes.get(shortestJobIndex).getTotalTime());
			System.out.println("Wait time was: " + waitTime(time));
			System.out.println("StartTime: " + time);
			while (processTime < processes.get(shortestJobIndex).getTotalTime()) {
				processTime++;
				time++;
			}
			System.out.println("EndTime: " + time);
			System.out.println("");
			processes.remove(shortestJobIndex);
			if (!Test.allProcessDone()) {
				shortestJob();
			}
		}
		System.out.println("");
		System.out.println("Average Wait Time was:  " + averageWait());
	}

	public void shortestJob() {
		shortestJobIndex = 0;
		int shortTime = processes.get(0).getTotalTime();
		for (int i = 1; i < processes.size(); i++) {
			if (processes.get(i).getTotalTime() < shortTime) {
				shortTime = processes.get(i).getTotalTime();
				shortestJobIndex = i;
			}
		}
	}

	@Override
	public long waitTime(long time) {
		long waitTime = (time - processes.get(shortestJobIndex).getQueuedTime());
		processes.get(shortestJobIndex).setWaitTime(waitTime);
		totalWaitTime += waitTime;
		numberOfProcesses++;
		return waitTime;
	}

	@Override
	public long averageWait() {
		return (long) (totalWaitTime / numberOfProcesses);
	}

	public static void main(String[] args) {
		NonpreemptiveSJF start = new NonpreemptiveSJF();
		start.runAlgorithm();
	}

}
