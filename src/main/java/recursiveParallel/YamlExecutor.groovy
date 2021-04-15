package recursiveParallel

import org.yaml.snakeyaml.Yaml

class YamlExecutor {
	
	void run(Script script, String text) {
		Yaml parser = new Yaml()
		Map root = parser.load(text)
				
		for (Map stageMap: root["stages"]) {
			def stage = createStage(script, stageMap);
			System.out.println(stage.toString());
			
			stage.run()
		}
	}
	
	
	IStage createStage(Script script, Map stageMap) {
		if (stageMap.jobName || !stageMap.stages) {
			JobStage jobStage = new JobStage(); 
			jobStage.name = stageMap.name;
			jobStage.jobName = stageMap.jobName
			jobStage.script = script;
			return jobStage;
		} else if (stageMap.parallel) {
			ParallelStage stage = new ParallelStage();
			stage.setName(stageMap.name)
			stage.script = script;
			stage.setStages([])
			for (Map subStage: stageMap.stages) {
				stage.stages.add(createStage(script, subStage))
			}
			return stage;
		} else {
			SequentialStage stage = new SequentialStage();
			stage.setName(stageMap.name)
			stage.script = script;
			stage.setStages([])
			for (Map subStage: stageMap.stages) {
				stage.stages.add(createStage(script, subStage))
			}
			return stage;
		}
	}
	
}
