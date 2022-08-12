# Customer Registration Sample Project

This is a very simple Spring Boot sample application project.

The purpose of this application is that I learn how to mock static methods in the context of Spring Boot test using [MockMvc and WebDriver](https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html#spring-mvc-test-server-htmlunit-webdriver).

For the simplicity, this application does not have persistence layer.

I learned or found the following things.

- I can mock static methods using `Mockito#mockStatic`. [This article](https://www.baeldung.com/mockito-mock-static-methods) is very helpful. However, I cannot have found the way to mock static methods in the context of Spring Boot test.
  - So, `ControllerPojoWithStaticMockTest` fails.
- I can fake static methods using `[JMokito Faking APIs](https://jmockit.github.io/tutorial/Faking.html)` in the context of Spring Boot test. However, I cannot dynamically change the return value from the faked static method. I think that if I want to change return value from the faked static method, I need to multiple fake classes which return different value.  And, I have to add not only JMockit's jar file to project folder, but also the path of jar to vm option in `build.gradle`.
- [`PowerMockito`](https://github.com/powermock/powermock) seems to be able to mock static methods. [This article](https://www.baeldung.com/intro-to-powermock) is also very helpful. (Thank you Baeldung!) However, PowerMockito does not seem to support JUnit 5 :(.
