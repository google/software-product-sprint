# Test Driven Development - Intro

## Getting Started

Last week, you wrote and deployed server code, allowing your client-code to
interact with your server.

This week, you'll learn about test-driven development, a tool that will make it
easier to write more complicated code safely.

You can return to this walkthrough anytime by running this command:

```bash
teachme tdd-intro-walkthrough.md
```

Click the **Start** button to begin!

## Unit Testing

As you wrote your code last week, you probably tested it manually by:

1. running a server
2. interacting with your HTML page
3. submitting data
4. confirming that your code did what you expected

That is a lot of work to confirm one little change, and it becomes less
effective the larger your project becomes. Imagine it's not just you working on
this project, but a team of 10 people. How would you know that your change
didn't break something they added?

[Unit testing](https://en.wikipedia.org/wiki/Unit_testing) is a way to confirm
that the small pieces that make up your code (e.g. each function) work as
expected. Each **unit test** is a small program that calls some code from your
project then tests the actual output against the expected output.

For example, open the `Greeter.java` file. This class creates greeting messages.
How can we confirm that this code does what we expect?

Next, open the `GreeterTest.java` file. This class contains a unit test that
confirms the behavior of the `Greeter` class.

To run the test, execute this command:

```bash
mvn test
```

This command will compile all the code, and then run the tests in the
`GreeterTest` class. You should see something like:

```none
Results :
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

This means that there is one test ("Test run: 1") and that all tests passed
("Failures: 0").

## Test-driven Development

[Test-driven development](https://en.wikipedia.org/wiki/Test-driven_development)
is used by many teams at Google. In test-driven development, we use tests to
define the correct behavior of code, and then we write code that pass the tests.

Add this function to your `GreeterTest` class:

```java
@Test
public void testGreetingTrimsWhitespace() {
  Greeter greeter = new Greeter();

  String greeting = greeter.greet("   Ada   ");

  // Whitespace should be trimmed
  Assert.assertEquals("Hello Ada", greeting);
}
```

This function tests whether whitespace is trimmed from the `name` parameter.
Save the file and run the tests again:

```bash
mvn test
```

You should now see this output:

```none
Results :
Failed tests:   testGreetingWhitespace(com.google.sps.GreeterTest): expected:<Hello [Ada]> but was:<Hello [   Ada   ]>
Tests run: 2, Failures: 1, Errors: 0, Skipped: 0
BUILD FAILURE
```

This tells you that the `testGreetingWhitespace` test failed. The expected
output was `"Hello Ada"` but the actual output was `"Hello Ada "`. This means
something in the code is not working as expected.

The problem is that the `Greeter` class is not removing the extra whitespace.
Modify `Greeter` to trim the whitespace. After doing that, run the tests again.
You should see them passing now.

Tests are useful for defining new desired behavior, and to confirm that new
code doesn't break existing behavior.

**Practice:** If you want more practice, try writing a new test that confirms
that symbols like `@#$%` are removed from the input, and then modify the code so
the tests pass.

## Learn More

<walkthrough-conclusion-trophy></walkthrough-conclusion-trophy>

The code this week uses a testing library called **JUnit**. You can learn more
about it [here](https://junit.org/junit4/) or by Googling "JUnit tutorials". If
you want to know how to test for something with JUnit, for example, checking if
an exception was thrown, include that in your search (e.g. "JUnit confirm
exception thrown").
