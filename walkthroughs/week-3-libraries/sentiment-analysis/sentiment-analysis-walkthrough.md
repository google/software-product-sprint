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

The goal of this project is to use Cloud Natural Language to add a sentiment
analysis feature to your portfolio.

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
[here](https://cloud.google.com/automl/), but this guide is going to stick with
the pre-trained models.

## Enable Cloud Natural Language API

Before you can use the Cloud Natural Language API, you have to enable it for your
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

The Cloud Natural Language API lets you write code that analyzes text. It's
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

Your goal is to use the concepts you just learned about to add a sentiment
analysis to your portfolio.

It's up to you exactly what that means. You could add something similar to the
sentiment analysis example to your page. Or you could do something like
analyze your "about me" section, or different famouse speeches.

Try to break this goal down into smaller steps, and then take each step on
individually.

-   Add the Cloud Natural Language dependency to your
    <walkthrough-editor-open-file
        filePath="software-product-sprint/portfolio/pom.xml">
      pom.xml
    </walkthrough-editor-open-file>
    file.
-   Add some HTML or JavaScript to your portfolio page that requests
    sentiment scores from your server, either of hard-coded content (like your
    "about me" section) or of user-entered content.
-   Add a servlet that handles that request by analyzing the text and writing
    the result to the response.
-   Add JavaScript code that reads the sentiment score from the response and
    displays it.

Again, it's up to you exactly what you do with your sentiment analysis feature.
If you're stuck, talk to your team and your advisor to help you brainstorm
ideas!

## Pull Requests

Your advisor will be reviewing your code to provide feedback and support.
Create a pull request from your code so your advisor can review it.

You don't have to wait until you're finished to send your code for review. In
fact, it's better if you send multiple small pull requests instead of one big
one!

Make sure your code is reviewed and merged into your repo before the end of the
week.

## Live Server

When you're happy with your feature and you're ready to show it to the world,
deploy it to your live server!

Your
<walkthrough-editor-open-file
    filePath="software-product-sprint/portfolio/pom.xml">
  pom.xml
</walkthrough-editor-open-file>
file should already contain your project ID. If so, you can deploy to your live
server by executing this command:

```bash
mvn package appengine:deploy
```

After the command successfully completes, you can navigate to
`YOUR_PROJECT_ID.appspot.com` to see your portfolio and test your feature.

When you're done, share this link in the chat and with your team!

## Congratulations

<walkthrough-conclusion-trophy></walkthrough-conclusion-trophy>

Congratulations! Your portfolio page now contains a working sentiment analysis
feature!

Make sure your code is reviewed and submitted to your repo before the end of the
week.

### Learn More

This walkthrough introduced one kind of natural language processing, but the
Cloud Natural Language library contains several other types of processing. If
this is interesting to you, you can find more info here:

-   [Cloud Natural Language](https://cloud.google.com/natural-language)
-   [Analyzing Entities](https://cloud.google.com/natural-language/docs/analyzing-entities)
-   [Classifying Content](https://cloud.google.com/natural-language/docs/classifying-text)
-   [Interpreting Sentiment Analysis Values](https://cloud.google.com/natural-language/docs/basics#interpreting_sentiment_analysis_values)

To return to the libraries walkthrough, run this command:

```bash
teachme ~/software-product-sprint/walkthroughs/week-3-libraries/libraries-walkthrough.md
```
