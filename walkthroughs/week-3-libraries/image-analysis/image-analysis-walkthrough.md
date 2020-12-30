# Image Analysis

## Getting Started

This walkthrough introduces Cloud Vision, which lets you analyze images.

You can return to this walkthrough anytime by running this command:

```bash
teachme ~/software-product-sprint/walkthroughs/week-3-libraries/image-analysis/image-analysis-walkthrough.md
```

Click the **Start** button to begin!

## The Goal

The goal of this project is to use Cloud Vision to add an image analysis feature
to your portfolio.

**Note:** This project requires Cloud Storage. Make sure you work through the
Cloud Storage project first! You should already have image uploads working
before continuing with this walkthrough.

This walkthrough introduces a lot of concepts, but you don't have to use all of
them. Decide what makes sense for your goal.

Don't be afraid to get creative, but focus on finishing a
[minimum viable product](https://en.wikipedia.org/wiki/Minimum_viable_product)
that proves you have all of the pieces connected, and then come back later and
add extra features if you have time left over.

## Cloud Vision

[Cloud Vision](https://cloud.google.com/vision/) is a set of tools designed for
image analysis.

The Cloud Vision APIs use machine learning models that have already been
trained, so you can skip straight to the fun stuff. It's also possible to train
your own models (you can learn more about that
[here](https://cloud.google.com/automl/)), but this guide is going to stick with
the pre-trained models.

## Enable Cloud Vision API

Before you can use the Cloud Vision API, you have to enable it. Go here:

<https://console.cloud.google.com/flows/enableapi?apiid=vision.googleapis.com>

Make sure your project is selected, and click the `Enable` button.

## Credentials

The Cloud Vision API requires your Cloud project's credentials to work. When you
deploy to App Engine this will work automatically, but when deploying to a dev
server you have to set your credentials manually. Follow the steps
[here](https://cloud.google.com/docs/authentication/getting-started) to set up
your local credentials.

**Important:** Before proceeding, make sure you have your
`GOOGLE_APPLICATION_CREDENTIALS` environment variable set. Nothing will work
without this.

## Maven Dependency

The Cloud Vision API is available as a web service, or as a library that can be
called from many languages. You're going to use it as a Java library. To add the
library to your classpath, add this Maven dependency:

```xml
<dependency>
  <groupId>com.google.cloud</groupId>
  <artifactId>google-cloud-vision</artifactId>
  <version>1.100.0</version>
</dependency>
```

Add this dependency to the
<walkthrough-editor-open-file
    filePath="software-product-sprint/portfolio/pom.xml">
  pom.xml
</walkthrough-editor-open-file>
file in your portfolio directory.

## Example

The `image-analyzer` directory contains an example project that generates labels
for an image uploaded by a user.

The
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-3-libraries/image-analysis/examples/image-analyzer/src/main/webapp/index.html">
  index.html
</walkthrough-editor-open-file>
file contains a `<form>` that lets a user to upload an image. That upload is
sent to the `/image-analysis` URL, which maps to the
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-3-libraries/image-analysis/examples/image-analyzer/src/main/java/com/google/sps/servlets/ImageAnalysisServlet.java">
  ImageAnalysisServlet.java
</walkthrough-editor-open-file>
file.

The
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-3-libraries/image-analysis/examples/image-analyzer/src/main/java/com/google/sps/servlets/ImageAnalysisServlet.java">
  ImageAnalysisServlet.java
</walkthrough-editor-open-file>
file contains a few interesting lines:

```java
// Get the labels of the image that the user uploaded.
List<EntityAnnotation> imageLabels = getImageLabels(imageBytes);
```

This line of code calls the `getImageLabels()` function, which uses Cloud Vision
to extract labels from the image.

```
for(EntityAnnotation label : imageLabels){
  out.println("<li>" + label.getDescription() + " " + label.getScore());
}
```

This `for` loop iterates over the image labels and outputs them as HTML.

The `getImageLabels()` function contains all of the logic for sending a request
to the Cloud Vision API and processing the response to get the labels for an
uploaded image.

To run this example, first make sure your `GOOGLE_APPLICATION_CREDENTIALS`
environment variable is set and that you've enabled the
[Vision API](https://console.cloud.google.com/flows/enableapi?apiid=vision.googleapis.com),
and then run a dev server.

## Associating Data

In this example, the image labels are never stored. That's okay if you only want
to display the labels once, right after the user uploads the image. But if you
want to associate an image and its labels with other data (like a username or a
caption) to display later, then consider working through the Datastore project!

At a high level, you'd store the image URL, its labels, and any other associated
data as an entity in Datastore, and then you'd query the stored entities to
output them as HTML.

## Learn More

Check out these resources for more information:

-   [Cloud Vision API](https://cloud.google.com/vision/)
-   [Cloud Vision API Quickstart](https://cloud.google.com/vision/docs/quickstart-client-libraries#client-libraries-install-java)
-   [Cloud Vision API Client Libraries](https://cloud.google.com/vision/docs/libraries)
-   [Cloud Vision API How-to Guides](https://cloud.google.com/vision/docs/how-to)

## Your Turn

Your goal is to use the concepts you just learned about to add image analysis to
your portfolio. You might ask users to send you their favorite cat pictures, or
you might store your own images that you then organize by extracted labels.

Try to break your goal down into smaller steps, and then take each step on
individually. At a high level, you might approach it this way:

-   Add the Cloud Vision dependency to your
    <walkthrough-editor-open-file
        filePath="software-product-sprint/portfolio/pom.xml">
      pom.xml
    </walkthrough-editor-open-file>
    file.
-   Modify your servlet to handle file upload requests and to fetch labels
    for the uploaded image.
    -   Test that this works by printing the labels to the console.
-   Use Datastore to store the image URLs and their labels as entities.
-   Create a servlet that reads from Datastore to return the image URLs and
    their labels as JSON.
-   Add JavaScript code that reads the labels from the JSON and displays them
    using the `<img>` tag.

If you follow those steps, your portfolio should contain a working image
analysis feature!

## Live Server

When you're happy with your feature and you're ready to show it to the world,
you can deploy it to your live server!

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

Congratulations! Your portfolio page should now support image analysis.

Make sure your code is submitted to your repo before the end of the week.

To return to the libraries walkthrough, run this command:

```bash
teachme ~/software-product-sprint/walkthroughs/week-3-libraries/libraries-walkthrough.md
```
