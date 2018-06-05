package com.github.sanderploegsma.beam;

import org.apache.beam.sdk.options.DefaultValueFactory;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.repackaged.org.apache.commons.lang3.StringUtils;

import java.util.function.Consumer;
import java.util.function.Function;

public class ResourceContainer {

    private final ApplicationOptions options;

    public ResourceContainer(ApplicationOptions options) {
        this.options = options;
    }

    public Function<String, String> getReverser() {
        return StringUtils::reverse;
    }

    public Consumer<String> getOutput() {
        return System.out::println;
    }

    public static class Factory implements DefaultValueFactory<ResourceContainer> {
        @Override
        public ResourceContainer create(PipelineOptions options) {
            return new ResourceContainer(options.as(ApplicationOptions.class));
        }
    }
}
