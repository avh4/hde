package net.avh4.ide;

import java.util.Arrays;
import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;

public class IdeScenarios extends JUnitStories {

	@Override
	public Configuration configuration() {
		return new MostUsefulConfiguration()
				.useStoryReporterBuilder(new StoryReporterBuilder()
						.withDefaultFormats()
						.withFormats(Format.CONSOLE, Format.TXT)
						.withFailureTrace(true));
	}

	@Override
	public List<CandidateSteps> candidateSteps() {
		return Arrays.asList((CandidateSteps) new IdeSteps());
	}

	@Override
	protected List<String> storyPaths() {
		return Arrays.asList("features.story");
	}

}
