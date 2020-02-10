# Translation

## Getting Started

This walkthrough introduces Cloud Translation, which lets you translate text
from one language to another.

You can return to this walkthrough anytime by running this command:

```bash
teachme ~/software-product-sprint/walkthroughs/week-3-libraries/translation/translation-walkthrough.md
```

Click the **Start** button to begin!

## The Goal

The goal of this project is to use Cloud Translation to add translations to the
comments you implemented in week 2.

This walkthrough introduces a lot of concepts, but you don't have to use all of
them. Decide what makes sense for your goal.

Don't be afraid to get creative, but focus on creating a
[minimum viable product](https://en.wikipedia.org/wiki/Minimum_viable_product)
that proves you have all of the pieces connected, and then come back later and
add extra features if you have time left over.

## Cloud Translation

[Cloud Translation](https://cloud.google.com/translate/) is a set of tools
designed for translating text. These are the tools that
[Google Translate](https://translate.google.com/) is built with, and the API
allows us to programmatically translate text.

The Cloud Translation APIs use machine learning models that have already been
trained, so you can skip straight to the fun stuff. It's also possible to train
your own models (you can learn more about that
[here](https://cloud.google.com/automl/)), but this guide is going to stick with
the pre-trained models.

## Enable Cloud Translation API

Before you can use the Cloud Translation API, you have to enable it. Go here:

<https://console.cloud.google.com/apis/library/translate.googleapis.com>

Make sure your project is selected, and click the `Enable` button.

## Credentials

The Cloud Translation API requires your Cloud project's credentials to work.
When you deploy to App Engine this will work automatically, but when running or
deploying in Google Cloud Shell you have to set your credentials manually.
Follow the steps
[here](https://cloud.google.com/docs/authentication/getting-started) to set up
your local credentials.

**Important:** Before proceeding, make sure you have your
`GOOGLE_APPLICATION_CREDENTIALS` environment variable set. Nothing will work
without this.

## Minimal Google Translate

The `examples/minimal-google-translate` directory contains an example project
that translates a message posted by a user. The next few steps walk you through
this example project.

## Maven Dependency

As mentioned above, the Cloud Translation API allows you to write code that
translates text. It's available as a web service, or as a library that can be
called from many languages. You're going to use it as a Java library.

To use the Cloud Translation library, first add the dependency to your
<walkthrough-editor-open-file
    filePath="software-product-sprint/portfolio/pom.xml">
  pom.xml
</walkthrough-editor-open-file>
file.

```xml
<dependency>
  <groupId>com.google.cloud</groupId>
  <artifactId>google-cloud-translate</artifactId>
  <version>1.70.0</version>
</dependency>
```

You can read the documentation for the Java library
[here](http://googleapis.github.io/google-cloud-java/google-cloud-clients/apidocs/com/google/cloud/translate/package-summary.html),
and the next few steps work through an example.

## TranslationServlet

Read through
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-3-libraries/translation/examples/minimal-google-translate/src/main/java/com/google/sps/servlets/TranslationServlet.java">
  TranslationServlet.java
</walkthrough-editor-open-file>
and find this code:

```java
String originalText = request.getParameter("text");
String languageCode = request.getParameter("languageCode");

// Do the translation.
Translate translate = TranslateOptions.getDefaultInstance().getService();
Translation translation =
    translate.translate(originalText, Translate.TranslateOption.targetLanguage(languageCode));
String translatedText = translation.getTranslatedText();
```

This code gets the `originalText` and `languageCode` parameters from the
request, and uses Cloud Translate to translate the text.

## Language Codes

To use Cloud Translate, you need to pass it a language code.

These language codes come from [ISO 639](https://en.wikipedia.org/wiki/ISO_639),
a standard for language names. A language code is a 2-letter abbreviation that
stands for a specific language. You can view a full list
[here](https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes), but here are some
examples:

-   `zh`: Chinese
-   `en`: English
-   `hi`: Hindi
-   `es`: Spanish
-   `ar`: Arabic

## Your Turn

Your goal is to use the concepts you just learned about to translate the
comments you implemented in week 2.

Try to break this goal down into smaller steps, and then take each step on
individually.

-   Add the Cloud Translate dependency to your
    <walkthrough-editor-open-file
        filePath="software-product-sprint/portfolio/pom.xml">
      pom.xml
    </walkthrough-editor-open-file> file.
-   Add a language dropdown to your portfolio page. When the user changes the
    dropdown, resend the `GET` request for the comments JSON. Add the language
	code to the request using a
    [query string](https://en.wikipedia.org/wiki/Query_string).
    -   Test that this works by getting the value of the language code on the
        servlet and printing it to the console. `System.out.println()` is your
        friend!
    -   When you get this step working, create a pull request and send it to
        your advisor for code review!
-   In your
    <walkthrough-editor-open-file
        filePath="software-product-sprint/portfolio/src/main/java/com/google/sps/servlets/DataServlet.java">
      DataServlet.java
    </walkthrough-editor-open-file>
    file, use the language code to translate each message before you convert it
    to JSON.
    -   Test that this works by running a dev server and viewing the JSON in your
        browser.
    -   When you get this step working, create a pull request and send it to
        your advisor for code review!

If you follow those steps, your portfolio should contain a dropdown that allows
users to translate comments!

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

Congratulations! Your portfolio page now contains a working translation feature!

Make sure your code is submitted to your repo before the end of the week.

To return to the libraries walkthrough, run this command:

```bash
teachme ~/software-product-sprint/walkthroughs/week-3-libraries/libraries-walkthrough.md
```