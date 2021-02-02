# Cloud Storage

## Getting Started

This walkthrough introduces Cloud Storage, which lets you store files like
images.

You can return to this walkthrough anytime by running this command:

```bash
teachme ~/software-product-sprint/walkthroughs/week-3-libraries/cloud-storage/cloud-storage-walkthrough.md
```

Click the **Start** button to begin!

## The Goal

The goal of this project is to use Cloud Storage to add image uploads to your
portfolio.

This walkthrough introduces a lot of concepts, but you don't have to use all of
them. Decide what makes sense for your goal.

Don't be afraid to get creative, but focus on finishing a
[minimum viable product](https://en.wikipedia.org/wiki/Minimum_viable_product)
that proves you have all of the pieces connected, and then come back later and
add extra features if you have time left over.

## File Storage

So far, you've learned how to use `POST` requests to let users submit data,
and you've either outputted that data in logs or stored it in Datastore.

That approach will work for data that can be represented as text like strings or
numbers. But if you want to store **binary data** like files, you can use a
Google Cloud API called [Cloud Storage](https://cloud.google.com/storage). For
example, you can use Cloud Storage to let users upload images or videos.

## Permissions

Before you get started, you need to enable some permissions so that other
people can see the files you upload to Cloud Storage.

-   Navigate to https://console.cloud.google.com/storage/browser
-   Click the bucket named `YOUR_PROJECT_ID.appspot.com`
-   Click the `Permissions` tab
-   Click the `Add` button
-   In the `New members` text field, type `allUsers`
-   In the `Role` dropdown, select `Cloud Storage > Storage Object Viewer`
-   Click `Save`
-   In the dialog that pops up, click `Allow Public Access`

Now people can see the files you uploaded. Make sure you only enable this for
buckets you're planning on using for public files!

## Example

The `hello-world` directory contains an example project that uses Cloud Storage
to store images uploaded by the user. The next few steps will walk you through
this example project.

## Maven Dependency

To use Cloud Storage, first add the Cloud Storage dependency to your
<walkthrough-editor-open-file
    filePath="software-product-sprint/portfolio/pom.xml">
  pom.xml
</walkthrough-editor-open-file>
file:

```xml
<dependency>
  <groupId>com.google.cloud</groupId>
  <artifactId>google-cloud-storage</artifactId>
  <version>1.113.0</version>
</dependency>
```

This dependency lets you reference the classes that come with the Cloud Storage
libary.

## File Input

The
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-3-libraries/cloud-storage/examples/hello-world/src/main/webapp/index.html">
  index.html
</walkthrough-editor-open-file>
file contains a couple interesting lines.

```html
<form action="/upload" method="POST" enctype="multipart/form-data">
```

This line creates a `<form>` element that sends a `POST` request to the
`/upload` URL. Notice the `enctype="multipart/form-data"` attribute, which is
required for file uploads.

```html
<input type="file" name="image">
```

This line creates an `<input>` element that accepts files. This renders as a
button that lets users choose a file from their hard drive.

## Uploading Files

The
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-3-libraries/cloud-storage/examples/hello-world/src/main/java/com/google/sps/servlets/FormHandlerServlet.java">
  FormHandlerServlet.java
</walkthrough-editor-open-file>
file has a `doPost()` function that's triggered when the user submits the form.
The `doPost()` function gets the value entered in the text area, and it then
uploads the image to Cloud Storage and gets its URL, which it outputs as HTML
in the response.

Notice that this file also uses your project ID. Don't forget to change this!

## Listing Files

The
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-3-libraries/cloud-storage/examples/hello-world/src/main/java/com/google/sps/servlets/ListImagesServlet.java">
  ListImagesServlet.java
</walkthrough-editor-open-file>
file has a `doGet()` function that's triggered when the user visits the
`/list-images` URL. The `doGet()` function gets the URL of every image uploaded
to your Cloud Storage bucket, and then uses them to build an HTML page.

Notice that this file also uses your project ID. Don't forget to change this!

You could also return the URLs as JSON and build the HTML using JavaScript.

## Associating Data

In this example, the text entered by the user is never stored. If you want to
associate other data with an image (like a username or a caption), then consider
working through the Datastore project!

At a high level, you'd store the image URL and any other associated data as an
entity in Datastore, and then you'd query the stored entities to output them.

## Unique Names

The above example uses the original file name as the uploaded file name. But you
might run into problems if users upload multiple files with the same name. If
Ada uploads a file named `cat.png`, and then Grace uploads a different file
named `cat.png`, Grace's file will overwrite Ada's file.

To fix this, you need to give each file a unique name. There are a few ways to do that:

-   Add the current time to the name:
    `String fileName = filePart.getSubmittedFileName() + System.currentTimeMillis();`
-   Use the
    [UUID](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/UUID.html)
    class to generate a unique ID:
    `String fileName = UUID.randomUUID().toString();`
-   Come up with your own system for generating unique IDs:
    `String fileName = getNextFileName();` (You might do this by storing
    previous IDs in Datastore.)

Another option is to use
[object versioning](https://cloud.google.com/storage/docs/object-versioning),
but that's likely overkill for most purposes. If your uploaded files have
unique names, then you don't have to worry about it.

## Cloud Storage Browser

The [Cloud Storage browser](https://console.cloud.google.com/storage/browser)
lets you view the files in your Cloud Storage buckets, as well as upload new
files and delete old ones.

This comes in handy for debugging, and for managing your data.

## Your Turn

Your goal is to use the concepts you just learned to add image uploads to your
porfolio. You might ask users to send you their favorite cat pictures, or you
might store your own images that you then display on your portfolio.

Try to break your goal down into smaller steps, and then take each step on
individually. At a high level, you might approach it this way:

-   Add the Cloud Storage dependency to your
    <walkthrough-editor-open-file
        filePath="software-product-sprint/portfolio/pom.xml">
      pom.xml
    </walkthrough-editor-open-file>
    file.
-   Add a file upload input to your HTML. Test that this works by running a dev
    server.
-   Add a servlet that handles file upload requests.
    -   Test that this works by making sure you see the uploaded file in your
        [Cloud Storage browser](https://console.cloud.google.com/storage/browser).
-   Add a servlet that lists all of the uploaded images. You can output the HTML
    directly, or you can output the list of image URLs as JSON.
-   Modify your HTML to link to the servlet's URL, or use JavaScript to fetch
    the JSON from the servlet to build the page.

If you follow those steps, your portfolio should contain a working image upload
feature!

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

Congratulations! Your portfolio page should now support image uploads.

Make sure your code is submitted to your repo before the end of the week.

To return to the libraries walkthrough, run this command:

```bash
teachme ~/software-product-sprint/walkthroughs/week-3-libraries/libraries-walkthrough.md
```
