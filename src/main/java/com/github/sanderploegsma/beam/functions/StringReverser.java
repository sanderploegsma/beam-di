package com.github.sanderploegsma.beam.functions;

import com.github.sanderploegsma.beam.ApplicationOptions;
import org.apache.beam.sdk.transforms.DoFn;

import java.util.function.Function;

public class StringReverser extends DoFn<String, String> {

    private Function<String, String> reverser;

    @StartBundle
    public void init(StartBundleContext ctx) {
        ApplicationOptions options = ctx.getPipelineOptions().as(ApplicationOptions.class);
        reverser = options.getResources().getReverser();
    }

    @ProcessElement
    public void process(ProcessContext ctx) {
        String input = ctx.element();
        ctx.output(reverser.apply(input));
    }
}
