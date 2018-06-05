package com.github.sanderploegsma.beam.functions;

import com.github.sanderploegsma.beam.ApplicationOptions;
import com.github.sanderploegsma.beam.ResourceContainer;
import com.github.sanderploegsma.beam.functions.LoggingOutput;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.ParDo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.function.Consumer;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LoggingOutputTest {

    @Mock
    private Consumer<String> output;

    private Pipeline pipeline;

    @Before
    public void setup() {
        ResourceContainer resources = mock(ResourceContainer.class);
        when(resources.getOutput()).thenReturn(output);

        ApplicationOptions options = PipelineOptionsFactory.as(ApplicationOptions.class);
        options.setResources(resources);

        pipeline = Pipeline.create(options);
    }

    @Test
    public void it_outputs_the_input() {
        List<String> input = asList("a", "b", "c");

        pipeline.apply(Create.of(input))
                .apply(ParDo.of(new LoggingOutput()));

        pipeline.run()
                .waitUntilFinish();

        verify(output, times(1)).accept("a");
        verify(output, times(1)).accept("b");
        verify(output, times(1)).accept("c");
        verifyNoMoreInteractions(output);
    }
}
