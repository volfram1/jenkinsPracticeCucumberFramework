package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/", // features we use to provide the path to feature files
        glue = "steps", // glue is where we find implementation for gherkin steps
        // we provide the path of package to get all the steps definitions

        dryRun = false,  //dryRun is set as default to false it means everything will be executed as usual
        // when set to true it will identify the unimplemented steps and provides snippets, it stops actual execution
        // to execute the script in real time we should set the value to false

        monochrome = true,  // it means the console output for cucumber test is having irrelevant information
        // when we set it to true , it simply removes all the irrelevant information

        tags = "@regression"
)

public class Regression {
}
