# Spring Native XML Test

This project is used to demonstrate a problem when compiling a simple SpringBoot project to native.
This seems to be caused by the usage of JAXB / Spring OXM marshalling.

Normal execution works fine:

```
$ ./mvnw spring-boot:run
```

Native compilation works also fine:

```
$ ./mvnw spring-boot:build-image -Pnative
```

BUT: Execution of native image fails:

```
$ docker run --rm -p 8080:8080 spring-native-xml:0.0.1-SNAPSHOT

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.0.3)

2023-02-23T20:22:00.351Z  INFO 1 --- [           main] s.p.s.SpringNativeXmlApplicationKt       : Starting AOT-processed SpringNativeXmlApplicationKt using Java 17.0.6 with PID 1 (/workspace/services.progressit.springnativexml.SpringNativeXmlApplicationKt started by cnb in /workspace)
2023-02-23T20:22:00.351Z  INFO 1 --- [           main] s.p.s.SpringNativeXmlApplicationKt       : No active profile set, falling back to 1 default profile: "default"
2023-02-23T20:22:00.374Z  INFO 1 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2023-02-23T20:22:00.375Z  INFO 1 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2023-02-23T20:22:00.375Z  INFO 1 --- [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.5]
2023-02-23T20:22:00.382Z  INFO 1 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2023-02-23T20:22:00.382Z  INFO 1 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 31 ms
2023-02-23T20:22:00.402Z  WARN 1 --- [           main] w.s.c.ServletWebServerApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'helloXmlService': Unsatisfied dependency expressed through constructor parameter 0: Error creating bean with name 'createJaxbMarshaller': Proxy class defined by interfaces [interface jakarta.xml.bind.annotation.XmlAccessorType, interface org.glassfish.jaxb.core.v2.model.annotation.Locatable] not found. Generating proxy classes at runtime is not supported. Proxy classes need to be defined at image build time by specifying the list of interfaces that they implement. To define proxy classes use -H:DynamicProxyConfigurationFiles=<comma-separated-config-files> and -H:DynamicProxyConfigurationResources=<comma-separated-config-resources> options.
2023-02-23T20:22:00.403Z  INFO 1 --- [           main] o.apache.catalina.core.StandardService   : Stopping service [Tomcat]
2023-02-23T20:22:00.404Z ERROR 1 --- [           main] o.s.boot.SpringApplication               : Application run failed

org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'helloXmlService': Unsatisfied dependency expressed through constructor parameter 0: Error creating bean with name 'createJaxbMarshaller': Proxy class defined by interfaces [interface jakarta.xml.bind.annotation.XmlAccessorType, interface org.glassfish.jaxb.core.v2.model.annotation.Locatable] not found. Generating proxy classes at runtime is not supported. Proxy classes need to be defined at image build time by specifying the list of interfaces that they implement. To define proxy classes use -H:DynamicProxyConfigurationFiles=<comma-separated-config-files> and -H:DynamicProxyConfigurationResources=<comma-separated-config-resources> options.
        at org.springframework.beans.factory.aot.BeanInstanceSupplier.resolveArgument(BeanInstanceSupplier.java:351) ~[na:na]
        at org.springframework.beans.factory.aot.BeanInstanceSupplier.resolveArguments(BeanInstanceSupplier.java:271) ~[na:na]
        at org.springframework.beans.factory.aot.BeanInstanceSupplier.get(BeanInstanceSupplier.java:206) ~[na:na]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.obtainInstanceFromSupplier(AbstractAutowireCapableBeanFactory.java:1226) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:6.0.5]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.obtainFromSupplier(AbstractAutowireCapableBeanFactory.java:1211) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:6.0.5]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1158) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:6.0.5]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:562) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:6.0.5]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:522) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:6.0.5]
        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:326) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:6.0.5]
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:6.0.5]
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:324) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:6.0.5]
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:200) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:6.0.5]
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:961) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:6.0.5]
        at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:917) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:6.0.5]
        at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:584) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:6.0.5]
        at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:146) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:3.0.3]
        at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:732) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:3.0.3]
        at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:434) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:3.0.3]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:310) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:3.0.3]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1304) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:3.0.3]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1293) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:3.0.3]
        at services.progressit.springnativexml.SpringNativeXmlApplicationKt.main(SpringNativeXmlApplication.kt:13) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:na]
Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'createJaxbMarshaller': Proxy class defined by interfaces [interface jakarta.xml.bind.annotation.XmlAccessorType, interface org.glassfish.jaxb.core.v2.model.annotation.Locatable] not found. Generating proxy classes at runtime is not supported. Proxy classes need to be defined at image build time by specifying the list of interfaces that they implement. To define proxy classes use -H:DynamicProxyConfigurationFiles=<comma-separated-config-files> and -H:DynamicProxyConfigurationResources=<comma-separated-config-resources> options.
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1752) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:6.0.5]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:600) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:6.0.5]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:522) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:6.0.5]
        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:326) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:6.0.5]
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:6.0.5]
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:324) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:6.0.5]
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:200) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:6.0.5]
        at org.springframework.beans.factory.config.DependencyDescriptor.resolveCandidate(DependencyDescriptor.java:254) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:6.0.5]
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1405) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:6.0.5]
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1325) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:6.0.5]
        at org.springframework.beans.factory.aot.BeanInstanceSupplier.resolveArgument(BeanInstanceSupplier.java:334) ~[na:na]
        ... 21 common frames omitted
Caused by: com.oracle.svm.core.jdk.UnsupportedFeatureError: Proxy class defined by interfaces [interface jakarta.xml.bind.annotation.XmlAccessorType, interface org.glassfish.jaxb.core.v2.model.annotation.Locatable] not found. Generating proxy classes at runtime is not supported. Proxy classes need to be defined at image build time by specifying the list of interfaces that they implement. To define proxy classes use -H:DynamicProxyConfigurationFiles=<comma-separated-config-files> and -H:DynamicProxyConfigurationResources=<comma-separated-config-resources> options.
        at com.oracle.svm.core.util.VMError.unsupportedFeature(VMError.java:89) ~[na:na]
        at com.oracle.svm.core.reflect.proxy.DynamicProxySupport.getProxyClass(DynamicProxySupport.java:171) ~[na:na]
        at java.base@17.0.6/java.lang.reflect.Proxy.getProxyConstructor(Proxy.java:47) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:na]
        at java.base@17.0.6/java.lang.reflect.Proxy.newProxyInstance(Proxy.java:1037) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:na]
        at org.glassfish.jaxb.runtime.v2.model.annotation.LocatableAnnotation.create(LocatableAnnotation.java:53) ~[na:na]
        at org.glassfish.jaxb.runtime.v2.model.annotation.RuntimeInlineAnnotationReader.getClassAnnotation(RuntimeInlineAnnotationReader.java:92) ~[na:na]
        at org.glassfish.jaxb.runtime.v2.model.annotation.RuntimeInlineAnnotationReader.getClassAnnotation(RuntimeInlineAnnotationReader.java:29) ~[na:na]
        at org.glassfish.jaxb.runtime.v2.model.impl.ClassInfoImpl.getClassOrPackageAnnotation(ClassInfoImpl.java:434) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:4.0.2 - d104f19]
        at org.glassfish.jaxb.runtime.v2.model.impl.ClassInfoImpl.getAccessType(ClassInfoImpl.java:446) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:4.0.2 - d104f19]
        at org.glassfish.jaxb.runtime.v2.model.impl.ClassInfoImpl.getProperties(ClassInfoImpl.java:293) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:4.0.2 - d104f19]
        at org.glassfish.jaxb.runtime.v2.model.impl.RuntimeClassInfoImpl.getProperties(RuntimeClassInfoImpl.java:159) ~[na:na]
        at org.glassfish.jaxb.runtime.v2.model.impl.ModelBuilder.getClassInfo(ModelBuilder.java:219) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:4.0.2 - d104f19]
        at org.glassfish.jaxb.runtime.v2.model.impl.RuntimeModelBuilder.getClassInfo(RuntimeModelBuilder.java:72) ~[na:na]
        at org.glassfish.jaxb.runtime.v2.model.impl.RuntimeModelBuilder.getClassInfo(RuntimeModelBuilder.java:52) ~[na:na]
        at org.glassfish.jaxb.runtime.v2.model.impl.ModelBuilder.getClassInfo(ModelBuilder.java:185) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:4.0.2 - d104f19]
        at org.glassfish.jaxb.runtime.v2.model.impl.RuntimeModelBuilder.getClassInfo(RuntimeModelBuilder.java:67) ~[na:na]
        at org.glassfish.jaxb.runtime.v2.model.impl.RuntimeModelBuilder.getClassInfo(RuntimeModelBuilder.java:52) ~[na:na]
        at org.glassfish.jaxb.runtime.v2.model.impl.ModelBuilder.getTypeInfo(ModelBuilder.java:332) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:4.0.2 - d104f19]
        at org.glassfish.jaxb.runtime.v2.model.impl.ModelBuilder.getTypeInfo(ModelBuilder.java:347) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:4.0.2 - d104f19]
        at org.glassfish.jaxb.runtime.v2.runtime.JAXBContextImpl.getTypeInfoSet(JAXBContextImpl.java:415) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:4.0.2 - d104f19]
        at org.glassfish.jaxb.runtime.v2.runtime.JAXBContextImpl.<init>(JAXBContextImpl.java:255) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:4.0.2 - d104f19]
        at org.glassfish.jaxb.runtime.v2.runtime.JAXBContextImpl$JAXBContextBuilder.build(JAXBContextImpl.java:1115) ~[na:na]
        at org.glassfish.jaxb.runtime.v2.ContextFactory.createContext(ContextFactory.java:144) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:4.0.2 - d104f19]
        at org.glassfish.jaxb.runtime.v2.JAXBContextFactory.createContext(JAXBContextFactory.java:44) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:4.0.2 - d104f19]
        at jakarta.xml.bind.ContextFinder.find(ContextFinder.java:368) ~[na:na]
        at jakarta.xml.bind.JAXBContext.newInstance(JAXBContext.java:605) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:4.0.0]
        at jakarta.xml.bind.JAXBContext.newInstance(JAXBContext.java:546) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:4.0.0]
        at org.springframework.oxm.jaxb.Jaxb2Marshaller.createJaxbContextFromClasses(Jaxb2Marshaller.java:559) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:6.0.5]
        at org.springframework.oxm.jaxb.Jaxb2Marshaller.getJaxbContext(Jaxb2Marshaller.java:508) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:6.0.5]
        at org.springframework.oxm.jaxb.Jaxb2Marshaller.afterPropertiesSet(Jaxb2Marshaller.java:485) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:6.0.5]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1798) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:6.0.5]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1748) ~[services.progressit.springnativexml.SpringNativeXmlApplicationKt:6.0.5]
        ... 31 common frames omitted
```
