package com.github.sanderploegsma.beam;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.ParDo;

public class Application {

    public static void main(String[] args) {
        ApplicationOptions options = PipelineOptionsFactory.as(ApplicationOptions.class);
        Pipeline pipeline = Pipeline.create(options);

        pipeline.apply("Input", Create.of("foo", "bar", "baz"))
                .apply("Reverse", ParDo.of(new StringReverser()))
                .apply("Output", ParDo.of(new LoggingOutput()));

        pipeline.run();
    }

}
