package com.github.sanderploegsma.beam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.PipelineOptions;

public interface ApplicationOptions extends PipelineOptions {

    @JsonIgnore
    @Default.InstanceFactory(ResourceContainer.Factory.class)
    ResourceContainer getResources();

    void setResources(ResourceContainer resources);
}
