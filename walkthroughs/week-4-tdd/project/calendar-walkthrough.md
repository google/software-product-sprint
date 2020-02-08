# Test Driven Development - Calendar

**Prerequisite:** If you're new to test-driven development, make sure you read
the intro walkthrough first!

## Getting Started

This week, you will be creating an
[algorithm](https://en.wikipedia.org/wiki/Algorithm) to solve a hard problem -
scheduling meetings between multiple people.

To make this easier, you will be using test-driven-development to make sure
your algorithm handles the different cases correctly.

You can return to this walkthrough anytime by running this command:

```bash
teachme ~/software-product-sprint/walkthroughs/week-4-tdd/project/calendar-walkthrough.md
```

Click the **Start** button to begin!

## Scenario

Imagine you're working on the calendar team and are responsible for adding the
"find a meeting" feature. Using the existing API, you'll need to implement a
feature that given the meeting information, it will return the times when the
meeting could happen that day.

## Background

A meeting request has:

-   a name
-   a duration in minutes
-   a collection of attendees

For a particular time slot to work, all attendees must be free to attend the
meeting. When a query is made, it will be given a collection of all known
events. Each event has:

-   a name
-   a time range
-   a collection of attendees

A time range will give you the start time, the end time, and the duration of
the event. If you want to know more, open the
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-4-tdd/project/src/main/java/com/google/sps/TimeRange.java">
  TimeRange.java
</walkthrough-editor-open-file>
file.

## Objective

Your objective is to implement `query()` in the
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-4-tdd/project/src/main/java/com/google/sps/FindMeetingQuery.java">
  FindMeetingQuery.java
</walkthrough-editor-open-file>
file.

Your implementation must pass every test in `FindMeetingQueryTests.java` before
the feature will be considered complete. Keep in mind, if a test is failing, it
means that your code needs to change, not the test.

To run the tests, `cd` into the `project` directory and then execute this
command:

```bash
mvn test
```

When all the tests pass, you can be confident that your code works!

## Pull Request

To get feedback on your code, create a pull request and send it to your advisor
for review.

Make sure your code is merged into your repo before the end of the week!

## Web Application

The code you wrote can be used in many contexts: from tests, from a desktop Java
application, from server-side Java, etc.

This project contains a bare-bones web application that uses HTML, JavaScript,
and a servlet to allow users to interact with the code you just wrote. This code
is already written!

You can imagine you worked on a team where you were responsible for the
algorithm, and somebody else was responsible for the web application that calls
your algorithm.

To run the web application, run a server:

```bash
mvn appengine:devserver
```

Then open the web preview to view a webpage that shows a user interface that
provides access to the algorithm you wrote!

## Finishing Up

<walkthrough-conclusion-trophy></walkthrough-conclusion-trophy>

You've created your own algorithm, used data structures to solve a complex
problem, and have seen how unit testing helped ensured everything works. That's
a lot for one week!

If you have time left over this week and you're looking for a challenge:

-   What's the algorithmic complexity of your algorithm?
-   What's another algorithm to solve this problem?
