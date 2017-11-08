package eecs2011a2;
//  uses the processes to evaluate the priority and puts them in order 
public class PriorityJobs implements Comparable<Object>{
	private int priority;
	private int jobNo;
	
	
	public PriorityJobs(int priority,int  jobNo){
		this.setPriority(priority);
		this.setJobNo(jobNo);
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int getJobNo() {
		return jobNo;
	}
	public void setJobNo(int jobNo) {
		this.jobNo = jobNo;
	}
	public String toString(){
		return "P: = " + priority + "   J: " +  jobNo;
	}
	@Override
	public int compareTo(Object o) {
		PriorityJobs oth = (PriorityJobs) o;
		return  oth.priority - this.priority;
	}
}
