package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

public class TestRunner {
    @CucumberOptions(
            features = "src/test/java/features/",
            glue="stepDefinitions")

    public class runTest extends AbstractTestNGCucumberTests { }
}
