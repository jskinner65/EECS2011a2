package eecs2011a2;

public class Test {
	public static Processes[] createProcesses() {
		Processes[] processes = new Processes[10];
		for (int j = 0; j < 10; j++) {
			processes[j] = new Processes((int) (Math.random() * 99) + 1, State.New);
			PType p;
			System.out.println("Start of Process " + j);
			for (int i = 0; i < (Math.random() * 10) + 1; i++) {
				if ((int) (Math.random() * 2) == 0) {
					p = PType.CPU_time;
				} else {
					p = PType.IO_time;
				}

				TimePType e = new TimePType(((int) (Math.random() * 100)), p);
				processes[j].addLast(e);

			}
			for (int i = 0; i < processes[j].getCPU_time().size(); i++) {
				System.out.println(processes[j].getCPU_time().get(i));
			}
			System.out.println("Total Time :  " + processes[j].getTotalTime());

			System.out.println("Priority of :  " + processes[j].getProcessPriority());
			System.out.println("End of Process " + j);
			System.out.println("");
		}
		return processes;
	}
}
