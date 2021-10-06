package myTestRunner;

import org.testng.annotations.Listeners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

 
@Listeners(com.pratesting.Utils.ListenerUtils.class)
@CucumberOptions(
        features = "C:\\Users\\sriabishek\\com.pratesting\\src\\test\\java\\feature\\Login.feature",
        glue={"stepDefinitions"},
        strict=true
        //format={"pretty","html:test-output"}
        )
public class RunTestNGTest extends AbstractTestNGCucumberTests {
 
}