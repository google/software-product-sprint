# Sentiment Analysis

## Getting Started

This walkthrough introduces Cloud Natural Language, which lets you analyze text
and extract information like sentiment.

You can return to this walkthrough anytime by running this command:

```bash
teachme ~/software-product-sprint/walkthroughs/week-3-libraries/sentiment-analysis/sentiment-analysis-walkthrough.md
```

Click the **Start** button to begin!

## The Goal

The goal of this project is to use Cloud Natural Language to add sentiment
analysis to the comments you implemented in week 2.

This walkthrough introduces a lot of concepts, but you don't have to use all of
them. Decide what makes sense for your goal.

Don't be afraid to get creative, but focus on finishing a
[minimum viable product](https://en.wikipedia.org/wiki/Minimum_viable_product)
that proves you have all of the pieces connected, and then come back later and
add extra features if you have time left over.

## Cloud Natural Language

[Cloud Natural Language](https://cloud.google.com/natural-language) is a set of
tools designed for parsing and understanding **unstructured text**, also called
[natural language](https://en.wikipedia.org/wiki/Natural_language). For example:

-   [Sentiment analysis](https://en.wikipedia.org/wiki/Sentiment_analysis)
    measures the positivity or negativity of text. *Is this text happy or
    unhappy?*
-   [Entity recognition](https://en.wikipedia.org/wiki/Named-entity_recognition)
    extracts entities (people, places, things) from text. *Who or what does this
    text mention?*
-   [Content classification](https://en.wikipedia.org/wiki/Document_classification)
    categorizes topics form text. *What topics does this text contain?*

The Cloud Natural Language APIs use machine learning models that have already
been trained, so you can skip straight to the fun stuff. It's also possible to
train your own models (you can learn more about that
[here](https://cloud.google.com/automl/)), but this guide is going to stick with
the pre-trained models.

## Enable Cloud Natural Language API

Before you can use the Cloud Translation API, you have to enable it for your
Cloud project. Go here:

<https://console.developers.google.com/apis/library/language.googleapis.com>

Make sure your project is selected, and click the `Enable` button.

## Credentials

The Cloud Natural Language API requires your project's credentials to work. When
you deploy to App Engine this will work automatically, but when running or
deploying locally you have to set your credentials manually. Follow the steps
[here](https://cloud.google.com/docs/authentication/getting-started) to set up
your local credentials.

**Important:** Before proceeding, make sure you have your
`GOOGLE_APPLICATION_CREDENTIALS` environment variable set. Nothing will work
without this.

## Sentiment Analysis

The `examples/sentiment-analyzer` directory contains an example project that
performs [sentiment analysis](https://en.wikipedia.org/wiki/Sentiment_analysis)
on a message posted by a user. The next few steps walk through this example.

## Maven Dependency

The Cloud Natural Language API allows you to write code that analyzes text. It's
available as a web service, or as a library that can be called from many
languages. You're going to use it as a Java library.

To use the Cloud Natural Language library, first add the App Engine dependency
to your
<walkthrough-editor-open-file
    filePath="software-product-sprint/portfolio/pom.xml">
  pom.xml
</walkthrough-editor-open-file>
file:

```xml
<dependency>
  <groupId>com.google.cloud</groupId>
  <artifactId>google-cloud-language</artifactId>
  <version>1.55.0</version>
</dependency>
```

You can read the documentation for the Java library
[here](https://googleapis.dev/java/google-cloud-clients/latest/index.html?com/google/cloud/language/v1/package-summary.html),
but it's probably easiest to work through an example.

## SentimentAnalysisServlet

Read through
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-3-libraries/sentiment-analysis/examples/sentiment-analyzer/src/main/java/com/google/sps/servlets/SentimentAnalysisServlet.java">
  SentimentAnalysisServlet.java
</walkthrough-editor-open-file>
and find this code:

```java
Document doc =
    Document.newBuilder().setContent(message).setType(Document.Type.PLAIN_TEXT).build();
LanguageServiceClient languageService = LanguageServiceClient.create();
Sentiment sentiment = languageService.analyzeSentiment(doc).getDocumentSentiment();
float score = sentiment.getScore();
languageService.close();
```

This code creates a new `Document` that contains the `message` entered by the
user, and passes it into a `LanguageServiceClient`, which returns the analysis
in a `Sentiment` instance.

The code then gets the **score** of the sentiment, which is a value between `-1`
and `1`, representing how negative or positive the text is.

Run a dev server and enter different values for the `text` variable to understand
how the sentiment analysis works. What is the highest and lowest value you can
generate?

## Your Turn

Your goal is to use the concepts you just learned about to add sentiment
analysis to the comments you implemented in week 2.

Try to break this goal down into smaller steps, and then take each step on
individually.

-   Add the Cloud Natural Language dependency to your
    <walkthrough-editor-open-file
        filePath="software-product-sprint/portfolio/pom.xml">
      pom.xml
    </walkthrough-editor-open-file>
    file.
-   Modify your
    <walkthrough-editor-open-file
        filePath="software-product-sprint/portfolio/src/main/java/com/google/sps/servlets/DataServlet.java">
      DataServlet.java
    </walkthrough-editor-open-file>
    to calculate the sentiment score of the comment entered by the user.
    -   Test that this works by printing it out to the console:
        `System.out.println()` is your friend!
    -   When you get this step working, create a pull request and send it to
        your advisor for code review!
-   Add the sentiment score to your comment storage. This might mean creating a
    new class that contains the message and the score.
    -   When you get this step working, create a pull request and send it to
        your advisor for code review!
-   Add the sentiment score to the JSON returned by your servlet.
    -   Test that this works by running a dev server and viewing the JSON in your
        browser.
    -   When you get this step working, create a pull request and send it to
        your advisor for code review!
-   Add JavaScript code that reads the sentiment score from the JSON and
    displays it. To get started, consider displaying the score in parentheses
    after the text of the comment.

If you follow those steps, your portfolio should contain a working sentiment
analysis feature!

## Live Server

When you're happy with your feature and you're ready to show it to the world,
deploy it to your live server!

Your
<walkthrough-editor-open-file
    filePath="software-product-sprint/portfolio/src/main/webapp/WEB-INF/appengine-web.xml">
  appengine-web.xml
</walkthrough-editor-open-file>
file should already contain your project ID. If so, you can deploy to your live
server by executing this command:

```bash
mvn appengine:update
```

After the command successfully completes, you can navigate to
`YOUR_PROJECT_ID.appspot.com` to see your portfolio and test your feature.

When you're done, share this link in the chat and with your team!

## Congratulations

<walkthrough-conclusion-trophy></walkthrough-conclusion-trophy>

Congratulations! Your portfolio page now contains a working sentiment analysis
feature!

Make sure your code is submitted to your repo before the end of the week.

### Learn More

This walkthrough introduced one kind of natural language processing, but the
Cloud Natural Language library contains several other types of processing. If
this is interesting to you, you can find more info here:

-   [Cloud Natural Language](https://cloud.google.com/natural-language)
-   [Analyzing Entities](https://cloud.google.com/natural-language/docs/analyzing-entities)
-   [Classifying Content](https://cloud.google.com/natural-language/docs/classifying-text)
-   [Interpreting Sentiment Analysis Values](https://cloud.google.com/natural-language/docs/basics#interpreting_sentiment_analysis_values)

If you have time left over and want to go further, consider adding a feature
that allows users to filter out comments with a negative sentiment score!

To return to the libraries walkthrough, run this command:

```bash
teachme ~/software-product-sprint/walkthroughs/week-3-libraries/libraries-walkthrough.md
```