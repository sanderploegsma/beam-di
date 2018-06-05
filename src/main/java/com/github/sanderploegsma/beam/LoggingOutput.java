package com.github.sanderploegsma.beam;

import org.apache.beam.sdk.transforms.DoFn;

import java.util.function.Consumer;

public class LoggingOutput extends DoFn<String, Void> {

    private Consumer<String> output;

    @StartBundle
    public void init(StartBundleContext ctx) {
        ApplicationOptions options = ctx.getPipelineOptions().as(ApplicationOptions.class);
        output = options.getResources().getOutput();
    }

    @ProcessElement
    public void process(ProcessContext ctx) {
        output.accept(ctx.element());
    }

}
