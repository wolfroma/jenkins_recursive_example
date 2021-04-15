import org.junit.*
import recursiveParallel.YamlExecutor
import com.lesfurets.jenkins.unit.*
import static groovy.test.GroovyAssert.*

class RecursiveParalelTest extends BasePipelineTest {
    def stub

    @Before
    void setUp() {
        super.setUp()
        // load toAlphanumeric
        stub = loadScript("vars/toAlphanumeric.groovy")
    }

    @Test
    void testCall() {
        // call toAlphanumeric and check result
		def executor = new YamlExecutor();
		executor.run(stub, ("example.yaml" as File).text) 
		
    }
}