package eecs2011a2;

import java.util.ArrayList;
import java.util.List;
//runs the algorithms on the processes and ouputs the Wait times etc for each process
//based on Priority
public class NonpreemptivePriority implements Algorithms{
	List<Processes> processes = new ArrayList<Processes>();
	long totalWaitTime = 0;
	int priorityJobIndex = 0;
	int numberOfProcesses = 0;

	public void runAlgorithm() {
		long time = 0;
		long processTime;
		Test.createProcesses();
		processes = Test.getProcesses();
		while (!Test.allProcessDone()) {
			processTime = 0;
			System.out.println(priorityJobIndex);
			System.out.println(processes.get(priorityJobIndex).getProcessPriority());
			System.out.println("Wait time was: " + waitTime(time));
			System.out.println("StartTime: " + time);
			while (processTime < processes.get(priorityJobIndex).getTotalTime()) {
				processTime++;
				time++;
			}
			System.out.println("EndTime: " + time);
			System.out.println("");
			processes.remove(priorityJobIndex);
			if (!Test.allProcessDone()) {
				shortestPriorityJob();
			}
		}
		System.out.println("");
		System.out.println("Average Wait Time was:  " + averageWait());
	}

	public void shortestPriorityJob() {
		priorityJobIndex = 0;
		int shortPriority = processes.get(0).getProcessPriority();
		for (int i = 1; i < processes.size(); i++) {
			if (processes.get(i).getProcessPriority() > shortPriority) {
				shortPriority = processes.get(i).getProcessPriority();
				priorityJobIndex = i;
			}
		}
	}

	@Override
	public long waitTime(long time) {
		long waitTime = (time - processes.get(priorityJobIndex).getQueuedTime());
		processes.get(priorityJobIndex).setWaitTime(waitTime);
		totalWaitTime += waitTime;
		numberOfProcesses++;
		return waitTime;
	}

	@Override
	public long averageWait() {
		return (long) (totalWaitTime / numberOfProcesses);
	}

	public static void main(String[] args) {
		NonpreemptivePriority start = new NonpreemptivePriority();
		start.runAlgorithm();
	}
}
