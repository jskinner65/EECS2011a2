package eecs2011a2;
//creates the test processes randomly
import java.util.ArrayList;
import java.util.List;

public class Test {
	static List<Processes> processes = new ArrayList<Processes>();

	public static void createProcesses() {
		for (int j = 0; j < 5; j++) {
			processes.add(new Processes((int) (Math.random() * 99) + 1, State.New, 10 * j)); // After
																								// 10
																								// cycles
																								// add
																								// new
																								// process
			PType p;
			System.out.println("Start of Process " + j);
			for (int i = 0; i < (Math.random() * 5) + 1; i++) {

				p = PType.CPU_time;
				TimePType e = new TimePType(((int) (Math.random() * 100)), p);
				processes.get(j).addLast(e);
				p = PType.IO_time;

				e = new TimePType(((int) (Math.random() * 100)), p);
				processes.get(j).addLast(e);

			}
			for (int i = 0; i < processes.get(j).getCPU_time().size(); i++) {
				System.out.println(processes.get(j).getCPU_time().get(i));
			}
			System.out.println("Total Time :  " + processes.get(j).getTotalTime());

			System.out.println("Priority of :  " + processes.get(j).getProcessPriority());
			System.out.println("End of Process " + j);
			System.out.println("");
		}
	}

	public static List<Processes> getProcesses() {
		return processes;
	}

	public static boolean allProcessDone() {
		boolean done = true;
		for (int i = 0; i < processes.size(); i++) {
			if (processes.get(i).getState() != State.Terminated) {
				done = false;
				break;
			}
		}
		return done;
	}

}
