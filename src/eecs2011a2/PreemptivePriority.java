package eecs2011a2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PreemptivePriority implements Algorithms {
	List<Processes> processes = new ArrayList<Processes>();
	List<PriorityJobs> jobs = new ArrayList<PriorityJobs>();
	long totalWaitTime = 0;
	int priorityJobIndex = 0;
	int numberOfProcesses = 0;
	int IO_Counter = 0;
	long time = 0;
	long IOtime = 0;
	long processTime;
	int CPUprocessID;
	int CPUID;

	int IOprocessID;
	int IOID;

	public void runAlgorithm() {
		Test.createProcesses();
		processes = Test.getProcesses();
		CPUprocessID = 0;
		CPUID = 0;
		IOprocessID = -1;
		IOID = 0;
		List<Processes> queueCPU = new ArrayList<Processes>();
		List<Processes> queueIO = new ArrayList<Processes>();
		int i = 0;
		Processes p = processes.get(0);

		while (0 < p.getCPU_time().size()) {
			processTime = 0;
			p = processes.get(0);
			System.out.println("SIZE : " + p.getCPU_time().size());
			queueCPU.add(p);
			while (queueCPU.size() > 0) {
				for (int j = 0; j < queueCPU.get(0).getCPU_time().size(); j++) {
					if (!(queueCPU.get(0).getCPU_time().get(j).isDone())
							&& queueCPU.get(0).getCPU_time().get(j).getStr() == PType.CPU_time) {
						i = j;
						break;
					}
				}

				queueCPU.get(0).getCPU_time().get(i).setTime(-1);

				time++;

				if (queueCPU.get(0).getCPU_time().get(i).getTime()==0) {
					queueCPU.get(0).getCPU_time().get(i).setDone(true);
					// send to IO
				}
			}
			System.out.println(0);
			processes.remove(0);
			i++;
			if (Test.allProcessDone()) {
				break;
			}
		}
		System.out.println("");
		// System.out.println("Average Wait Time was: " + averageWait());
	}

	public void checkJobDone() {
		int done = 0;
		for (int i = 0; i < processes.get(priorityJobIndex).getCPU_time().size(); i++) {
			if (!(processes.get(priorityJobIndex).getCPU_time().get(i).isDone())) {
				done = 1;
				break;
			}
		}
		if (done != 0) {
			processes.get(priorityJobIndex).setState(State.Ready);
		} else {
			processes.get(priorityJobIndex).setState(State.Terminated);
		}

	}

	public int getNextPriorityReady() {
		for (int i = 0; i < jobs.size(); i++) {
			if (processes.get(jobs.get(i).getJobNo()).getState() == State.Ready) {
				return jobs.get(i).getJobNo();
			}
		}
		return 0;
	}

	public void shortestPriorityJob() {
		jobs.clear();
		System.out.println(processes.size());
		for (int i = 0; i < processes.size(); i++) {
			jobs.add(new PriorityJobs(processes.get(i).getProcessPriority(), i));
		}
		Collections.sort(jobs);
		priorityJobIndex = jobs.get(0).getJobNo();
		jobs.remove(0);
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
