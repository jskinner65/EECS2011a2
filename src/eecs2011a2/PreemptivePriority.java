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
	
	
	public void runAlgorithm() {
		Test.createProcesses();
		processes = Test.getProcesses();
		while (!Test.allProcessDone()) {
			processTime = 0;
			System.out.println(priorityJobIndex);
			System.out.println("Priority " + processes.get(priorityJobIndex).getProcessPriority());
			System.out.println("Wait time was: " + waitTime(time));
			System.out.println("StartTime: " + time);

			
			process();
			
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
	
	
	public void process(){
		for (int i = 0; i < processes.get(priorityJobIndex).getCPU_time().size(); i++) {
			processTime = 0;
			if ((processes.get(priorityJobIndex).getCPU_time().get(i).getStr() == PType.CPU_time)
					&& !(processes.get(priorityJobIndex).getCPU_time().get(i).isDone())) {
				while (processTime < processes.get(priorityJobIndex).getCPU_time().get(i).getTime()) {
					processes.get(priorityJobIndex).setState(State.Running);
					processTime++;
					time++;
					IOtime++;
				}
				processes.get(priorityJobIndex).setState(State.Ready);
				processes.get(priorityJobIndex).getCPU_time().get(i).setDone(true);
				checkJobDone();
			} else {
				processes.get(priorityJobIndex)
						.setCurrentIOWait(processes.get(priorityJobIndex).getCPU_time().get(i).getTime());
				processes.get(priorityJobIndex).setState(State.Terminated);
			}
		}
					
	}
	
	public void checkJobDone(){
		int done = 0;
		for (int i = 0; i< processes.get(priorityJobIndex).getCPU_time().size(); i++){
			if (!(processes.get(priorityJobIndex).getCPU_time().get(i).isDone())){
				done = 1;
				break;
			}
		}
		if (done != 0){
			processes.get(priorityJobIndex).setState(State.Ready);
		}
		else{
			processes.get(priorityJobIndex).setState(State.Terminated);
		}
			
	}
	
	public int getNextPriorityReady(){
		for (int i=0; i<jobs.size(); i++){
			if (processes.get(jobs.get(i).getJobNo()).getState()==State.Ready){
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
