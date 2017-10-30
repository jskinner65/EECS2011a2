package eecs2011a2;

import java.util.ArrayList;
import java.util.List;

public class PreemptivePriority implements Algorithms {
	List<Processes> processes = new ArrayList<Processes>();
	long totalWaitTime = 0;
	int priorityJobIndex = 0;
	int numberOfProcesses = 0;
	int IO_Counter = 0;
	long time = 0;
	public void runAlgorithm() {
		
		long processTime;
		Test.createProcesses();
		processes = Test.getProcesses();
		while (!Test.allProcessDone()) {
			processTime = 0;
			System.out.println(priorityJobIndex);
			System.out.println(processes.get(priorityJobIndex).getProcessPriority());
			System.out.println("Wait time was: " + waitTime(time));
			System.out.println("StartTime: " + time);

			
			for (int i = 0; i < processes.get(priorityJobIndex).getCPU_time().size(); i++) {
				processTime = 0;
				if ((processes.get(priorityJobIndex).getCPU_time().get(i).getStr() == PType.CPU_time)
						&& !(processes.get(priorityJobIndex).getCPU_time().get(i).isDone())) {
					while (processTime < processes.get(priorityJobIndex).getCPU_time().get(i).getTime()) {
						processTime++;
						time++;
					}
					processes.get(priorityJobIndex).getCPU_time().get(i).setDone(true);
				} else {
					processes.get(priorityJobIndex)
							.setCurrentIOWait(processes.get(priorityJobIndex).getCPU_time().get(i).getTime());
					processes.get(priorityJobIndex).setState(State.Waiting_for_IO);
				}

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
		PreemptivePriority start = new PreemptivePriority();
		start.runAlgorithm();
	}
}
