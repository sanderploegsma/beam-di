package com.github.sanderploegsma.beam;

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
import java.util.function.Function;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StringReverserTest {

    @Mock
    private Function<String, String> reverser;

    private Pipeline pipeline;

    @Before
    public void setup() {
        ResourceContainer resources = mock(ResourceContainer.class);
        when(resources.getReverser()).thenReturn(reverser);

        ApplicationOptions options = PipelineOptionsFactory.as(ApplicationOptions.class);
        options.setResources(resources);

        pipeline = Pipeline.create(options);

        when(reverser.apply(anyString())).thenReturn("");
    }

    @Test
    public void it_reverses_the_input() {
        List<String> input = asList("a", "b", "c");

        pipeline.apply(Create.of(input))
                .apply(ParDo.of(new StringReverser()));

        pipeline.run()
                .waitUntilFinish();

        verify(reverser, times(1)).apply("a");
        verify(reverser, times(1)).apply("b");
        verify(reverser, times(1)).apply("c");
        verifyNoMoreInteractions(reverser);
    }
}
