# Fantastic Java Contracts Demos

## Demo: Editor

Using requirements and capabilities to resolve a standard Java application.

 - `calc-api` - a very simple contract artifact. Contains one interface defining the "calculator contract". It uses `@Export` package-level annotation to export the package containing the contract. The `<em:module/>` tells EM to put this information in `MANIFEST.MF`. No dependencies (apart from annotations).
 - `calc-simple` - a naive provider of `calc-api` contract. It uses `@Provides` class-level annotation to specify it fulfills the `fantastic.calculator` contract. It uses [Java's SPI](https://docs.oracle.com/javase/tutorial/ext/basics/spi.html#packaging-the-dictionary-service-in-a-jar-file) approach to register the service. The `<em:module />` tells EM to put this information in `MANIFEST.MF`. It only depends on `calc-api` (apart from annotations).
 - `calc-fancy` - a better provider of `calc-api` contract based on Parsii library. It also uses `@Provides` class-level annotation to specify it fulfills the `fantastic.calculator` contract but it also declares it supports specific math operations. It uses [Java's SPI](https://docs.oracle.com/javase/tutorial/ext/basics/spi.html#packaging-the-dictionary-service-in-a-jar-file) approach to register the service. The `<em:module />` tells EM to put this information in `MANIFEST.MF`. It only depends on `calc-api` (apart from annotations).
 - `markup` - fake markup language that can calculate math expressions inside `<math>` tags. It only depends on `calc-api` (apart from annotations). It loads a provider of the `fantastic.calculator` via [Java's native service loader](https://docs.oracle.com/javase/tutorial/ext/basics/spi.html#packaging-the-dictionary-service-in-a-jar-file). It uses `@Requires` class-level annotation to specify its requirements. The `<em:module />` tells EM to put this information in `MANIFEST.MF`.
 - `editor` - A Simple text editor (Java Swing desktop application) that understands the `math` markup. It only depends on `markup`. It uses [maven-shade-plugin](https://maven.apache.org/plugins/maven-shade-plugin/) to build a single executable JAR. To avoid adding dependency on specific provider it uses `<em:resolve />` which finds a provider of the `fantastic.calculator` among provided contractors. It then uses a custom transformer for maven-shade-plugin to add discovered artifacts to the standalone executable.

## Demo: RESTful service

This uses calc jars above to build up a standalone RESTful math service. It uses [Declarative Services](http://enroute.osgi.org/services/org.osgi.service.component.html) annotations to wire components together.  

 - `rest` - A Simple RESTful service using standard Java JAX-RS. It only depends on `calc-api` and `javax.ws.rs-api` (apart from annotations and indexes). It uses OOTB `@RequiresJaxrsServer` and custom `@RequiresPowerCalculator` annotations to declare it requires a JAX-RS implementation (one that supports whiteboard pattern) and advanced calculator.
   * normal build uses default profile where `<em:executable />` property tells EM to resolve the artifacts from provided contractors list and build a standalone executable.
   * building with `-P liferay` uses `<em:resolve> ... </em:resolve>` property to tell EM to resolve against running Liferay instance and prepare set of artifacts to be deployed. 

See the source code for details.
