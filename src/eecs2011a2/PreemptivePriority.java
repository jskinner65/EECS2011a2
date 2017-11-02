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
		while (time < 1000) {
			processTime = 0;

			process();
			processIO();

			time++;

		}
		System.out.println("");
		// System.out.println("Average Wait Time was: " + averageWait());
	}

	public void process() {
		if (processes.get(CPUprocessID).getCPU_time().get(CPUID).getStr() == PType.CPU_time
				&& processes.get(CPUprocessID).getCPU_time().get(CPUID).getTime() > 0
				&& !processes.get(CPUprocessID).getCPU_time().get(CPUID).isDone()) {
			processes.get(CPUprocessID).getCPU_time().get(0)
					.setTime(processes.get(CPUprocessID).getCPU_time().get(CPUID).getTime() - 1);
			if (processes.get(CPUprocessID).getCPU_time().get(CPUID).getTime() == 0) {
				processes.get(CPUprocessID).getCPU_time().get(CPUID).setDone(true);
				if (processes.get(CPUprocessID).doneCPU()) {
					processes.remove(CPUprocessID);
					time++;
				}
				IOprocessID = CPUprocessID;
			}
		}

	}

	public void processIO() {
		if (IOprocessID > 0) {

			if (processes.get(IOprocessID).getCPU_time().get(IOID).getStr() == PType.IO_time
					&& processes.get(IOprocessID).getCPU_time().get(IOID).getTime() > 0
					&& !processes.get(IOprocessID).getCPU_time().get(IOID).isDone()) {
				processes.get(IOprocessID).getCPU_time().get(IOID)
						.setTime(processes.get(IOprocessID).getCPU_time().get(IOID).getTime() - 1);
				if (processes.get(IOprocessID).getCPU_time().get(IOID).getTime() == 0) {
					processes.get(IOprocessID).getCPU_time().get(IOID).setDone(true);
				}
			}
			if (processes.get(IOprocessID).doneCPU()) {
				processes.remove(IOprocessID);
			}
		}
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
