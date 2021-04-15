package recursiveParallel

class ParallelStage  implements IStage{
	Script script;
	
	List<IStage> stages;
	String name;
	
	void run() {
		def allStages = [:]
		script.stage(name) {
			for (IStage st: stages) {
				def embeddedSt = st; // to function properly
				allStages[st.name] = {
					System.out.println("Before running a stage parallel :"+ st.toString());
					embeddedSt.run()
				}
			}
			System.out.println("Before running parallel:"+ allStages.toString());
			script.parallel allStages
		}
	}

	@Override
	public String toString() {
		return "ParallelStage [name=" + name + ", stages=" + stages + "]";
	}
	
}
