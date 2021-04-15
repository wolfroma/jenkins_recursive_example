package recursiveParallel

class SequentialStage  implements IStage{
	Script script;
	
	List<IStage> stages;
	String name;
	
	void run() {
		script.stage(name) {
			for (IStage st: stages) {
				System.out.println("Before running a stage sequentially: "+ st.toString());
				st.run();
			}
		}
	}

	@Override
	public String toString() {
		return "SequentialStage [name=" + name + ", stages=" + stages + "]";
	}
	
}
