package net.avh4.ide;

import net.avh4.sandbox.Sandbox;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import java.io.IOException;

import static net.avh4.util.imagecomparison.Matchers.looksLike;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class IdeSteps extends Steps {

    private IDE ide;
    private Sandbox sandbox;

    @Given("a new project")
    public void givenANewProject() {
        sandbox = new Sandbox();
        ide = new IDE(sandbox.getRoot());
    }

    @When("I create a new story named \"Features\"")
    public void whenICreateANewStoryNamedFeatures() {
        ide.click(10, 5);
        type("Features\n");
    }

    private void type(final String string) {
        ide.type(string);
    }

    @When("I create a new scenario in \"$story\" named \"$scenario\"")
    public void whenICreateANewScenario(final String story,
                                        final String scenario) {
        assertThat(ide.getSelectedFeature().getName(), is(story));
        ide.click(110, 5);
        type(scenario);
    }

    @When("I run the integration tests")
    public void whenIRunTheIntegrationTests() {
        ide.click(310, 5);
    }

    @Then("I should see test results\n$what")
    public void thenIShouldSeeTestResults(final String what) throws Exception {
        final String hash = DigestUtils.md5Hex(what);
        assertThat(ide.getTestResultsView(), looksLike(hash + ".png"));
    }

    @Then("the project should include\n$files")
    public void thenTheProjectShouldInclude(final String files) {
        assertThat(files + " should exist", sandbox.newFile(files).exists(),
                is(true));
    }

    @Then("the file $file should read:\n$contents")
    public void thenTheFileShouldRead(final String file, final String expected)
            throws IOException {
        final String actual = FileUtils.readFileToString(sandbox.newFile(file));
        assertThat(actual, is(expected));
    }

}
