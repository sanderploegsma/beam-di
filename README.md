# Dependency Injection in Apache Beam
This is an example implementation of the Dependency Injection approach as described in https://www.tikalk.com/posts/2017/05/24/apache-beam-testing/

### Caveats
It seems to be impossible to combine this approach with Apache Beam's `TestPipeline`, so you cannot use `PAssert` anywhere. This is because any `PipelineOptions` you pass to the `TestPipeline` are not used directly, but serialized and deserialized instead (I guess). 

If you happen to know how to solve this, feel free to let me know in the issues.