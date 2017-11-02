package eecs2011a2;

import java.util.ArrayList;
import java.util.List;

public class Processes {
	/*************************************************************************/
	private List<TimePType> CPU_time = new ArrayList<TimePType>();

	private int processPriority = 1;
	private State state = State.Ready;

	/****** Needed for calculations ******/
	private long queuedTime;
	private long waitTime;
	private long currentIOWait;

	/*************************************************************************/
	public Processes(int priority, State state, long queuedTime) {
		this.processPriority = priority;
		this.state = state;
		this.queuedTime = queuedTime;
	}

	public long getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(long waitTime) {
		this.waitTime = waitTime;
	}

	public long getQueuedTime() {
		return queuedTime;
	}

	public void setState(State state) {
		this.state = state;
	}

	public State getState() {
		return state;
	}

	public List<TimePType> getCPU_time() {
		return CPU_time;
	}

	public TimePType removeFirst() {
		TimePType temp = CPU_time.get(0);
		CPU_time.remove(0);
		return temp;
	}

	public int getTotalTime() {
		int time = 0;
		for (int i = 0; i < CPU_time.size(); i++) {
			time += CPU_time.get(i).getTime();
		}
		return time;
	}

	public int getTotalCPUTime() {
		int time = 0;
		for (int i = 0; i < CPU_time.size(); i++) {
			if (CPU_time.get(i).getStr() == PType.CPU_time) {
				time += CPU_time.get(i).getTime();
			}
		}
		return time;
	}

	public int getTotalIOTime() {
		int time = 0;
		for (int i = 0; i < CPU_time.size(); i++) {
			if (CPU_time.get(i).getStr() == PType.IO_time) {
				time += CPU_time.get(i).getTime();
			}
		}
		return time;
	}

	public TimePType removeLast() {

		TimePType temp = CPU_time.get(CPU_time.size() - 1);
		CPU_time.remove(CPU_time.size() - 1);
		return temp;
	}

	public void addLast(TimePType timeStr) {
		CPU_time.add(timeStr);
	}

	public void run() {

		state = State.Running;
	}

	public boolean isRunning() {
		return (state == State.Running);
	}

	public int getProcessPriority() {
		return processPriority;
	}

	public void setProcessPriority(int processPriority) {
		this.processPriority = processPriority;
	}

	public static void main(String[] args) {
		Test.createProcesses();
	}

	public long getCurrentIOWait() {
		return currentIOWait;
	}

	public void setCurrentIOWait(long currentIOWait) {
		this.currentIOWait = currentIOWait;
	}
	public boolean doneCPU() {
		for (int i =0 ; i<CPU_time.size(); i++) {
			if (!CPU_time.get(i).isDone()){
				return false;
			}
		}
		return true;
	}
}
