package net.avh4.ide.features;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import net.avh4.ide.IDE;
import net.avh4.math.geometry.Point;
import net.avh4.math.geometry.Rect;
import net.avh4.sandbox.Sandbox;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;

import java.io.IOException;

import static net.avh4.util.imagecomparison.hamcrest.ImageComparisonMatchers.looksLike;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SuppressWarnings("UnusedDeclaration")
public class IdeSteps {

    private IDE ide;
    private Sandbox sandbox;
    private Rect bounds = Rect.ofSize(800, 600);

    @Given("a new project")
    public void givenANewProject() {
        sandbox = new Sandbox();
        ide = new IDE(sandbox.getRoot());
    }

    @When("I create a new story named \"Features\"")
    public void whenICreateANewStoryNamedFeatures() {
        ide.click(bounds, Point.at(10, 5));
        type("Features\n");
    }

    private void type(final String string) {
        ide.type(string);
    }

    @When("I create a new scenario in \"$story\" named \"$scenario\"")
    public void whenICreateANewScenario(final String story,
                                        final String scenario) {
        assertThat(ide.getSelectedFeature().getName(), is(story));
        ide.click(bounds, Point.at(110, 5));
        type(scenario);
    }

    @When("I run the integration tests")
    public void whenIRunTheIntegrationTests() {
        ide.click(bounds, Point.at(310, 5));
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
