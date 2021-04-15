package recursiveParallel

class JobStage implements IStage{
	Script script;
	
	String name;
	String jobName;
	
	void run() {
		script.stage(name) {
			System.out.println("Executing job:" + jobName);
		}
	}

	@Override
	public String toString() {
		return "JobStage [name=" + name + ", jobName=" + jobName + "]";
	}
	
}
