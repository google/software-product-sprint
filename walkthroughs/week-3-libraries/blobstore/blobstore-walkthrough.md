# Blobstore

## Getting Started

This walkthrough introduces Blobstore, which lets you store files like images.

You can return to this walkthrough anytime by running this command:

```bash
teachme ~/software-product-sprint/walkthroughs/week-3-libraries/blobstore/blobstore-walkthrough.md
```

Click the **Start** button to begin!

## The Goal

The goal of this project is to use Blobstore to add image uploads to the
comments you implemented in week 2.

This walkthrough introduces a lot of concepts, but you don't have to use all of
them. Decide what makes sense for your goal.

Don't be afraid to get creative, but focus on finishing a
[minimum viable product](https://en.wikipedia.org/wiki/Minimum_viable_product)
that proves you have all of the pieces connected, and then come back later and
add extra features if you have time left over.

## File Storage

So far, you've learned how to use `POST` requests to allow users to submit data,
and you've stored that data in a data structure like an `ArrayList`.

That approach will work for basic data like strings or numbers, but it does not
support storing **binary data** like files. To store files, you can use a Google
Cloud API called **Blobstore**.

[Blobstore](https://cloud.google.com/appengine/docs/standard/java/blobstore/)
allows you to store [blobs](https://en.wikipedia.org/wiki/Binary_large_object)
(**B**inary **L**arge **OB**jects), which is just a nerdy way to say "file". For
example, you can use Blobstore to allow users to upload images or videos.

## Maven Dependency

Blobstore comes with the App Engine environment, so to use Blobstore, first add
the App Engine dependency to your
<walkthrough-editor-open-file
    filePath="software-product-sprint/portfolio/pom.xml">
  pom.xml
</walkthrough-editor-open-file>
file:

```xml
<dependency>
  <groupId>com.google.appengine</groupId>
  <artifactId>appengine-api-1.0-sdk</artifactId>
  <version>1.9.59</version>
</dependency>
```

This dependency allows you to reference the classes that come with the App
Engine SDK, which includes Blobstore.

## File Uploads

To understand what Blobstore is doing for you, think about how you'd implement
file uploads without using Blobstore.

On the client side, you can use `<input type="file">` to show a file selector.
(You can play with an example
[here](https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_input_type_file).)
You can put this inside a `<form>` element, just like any other `<input>`
element.

When the user submits the form, the request triggers a servlet. The file would
be sent to the servlet as a stream of data, which you would have to parse.
[This](https://stackoverflow.com/a/2424824/873165) Stack Overflow answer
summarizes what you'd have to do just to parse the data.

After you parsed the data, you'd have to store the file somewhere. You can't
store the file directly on your server because you'd clutter up your hosting
server (and App Engine disallows it anyway), so you'd have to store the file
data in something like a database. So you'd have to write code that stores your
parsed file data in some storage format.

After you stored the data, you'd need a way to access it from the client. For
example, if the file was an image, you'd need a way to access it via a URL that
you could use in an `<img>` tag. This would involve creating a new servlet,
mapping URLs to it, understanding which image the URL was requesting, and
responding to the request with the file data you fetched from the database.

If this sounds like a lot of work, that's because it is. Luckily, Blobstore
handles almost all of this for you. You submit the form to Blobstore, and
Blobstore takes care of parsing, storing, and hosting the file. It then forwards
the request to your servlet, and it gives you a handy URL that you can use to
access the uploaded file. You can then store that URL alongside your other
data without worrying about the underlying file hosting.

## Requests

The Blobstore API works by adding an extra step in a request. Think about what
happens when a user submits a form, without using Blobstore:

[![client-server request](https://github.com/google/software-product-sprint/blob/master/walkthroughs/week-3-libraries/blobstore/blobstore-1.png?raw=true)
](https://github.com/google/software-product-sprint/blob/master/walkthroughs/week-3-libraries/blobstore/blobstore-1.png)

Normally, the `action` of the form is a URL that maps to a servlet on the
server. When the user submits the form, the servlet can get the values entered
by the user through the `request.getParameter()` function, and can then store
those values.

But when you use Blobstore, the `action` of the form is a URL that points to
Blobstore. When the user submits the form, the request first goes to Blobstore.
Blobstore takes care of handling the file uploaded by the user, and then
forwards the request to your servlet. Your servlet can then get the data entered
by the user, including the URL of the file that Blobstore handled.

[![client-blobstore request](https://github.com/google/software-product-sprint/blob/master/walkthroughs/week-3-libraries/blobstore/blobstore-2.png?raw=true)
](https://github.com/google/software-product-sprint/blob/master/walkthroughs/week-3-libraries/blobstore/blobstore-2.png)


This extra step can be confusing, but it saves you all the work of parsing,
storing, and hosting the file.

# Example

The `examples/hello-world` directory contains an example project that allows a
user to upload an image.

### HomeServlet

The
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-3-libraries/blobstore/examples/hello-world/src/main/java/com/google/sps/servlets/HomeServlet.java">
  HomeServlet.java
</walkthrough-editor-open-file>
file defines a `doGet()` function, which is triggered when users navigate to
the `/home` URL. Generally you probably don't want to build HTML this way
because it's annoying to work with, but this demonstrates the overall approach.
We'll see more realistic examples in following steps.

The `doGet()` function uses `BlobstoreService` to get a URL that points to
Blobstore. Its value is something like this:
`http://localhost:8080/_ah/upload/ag9ZT1VSCgCAw` (try running a dev server and
printing it out to see).

Then it uses that URL to create a form, which contains a text area, file
selector, and submit button.

If you send a `POST` request to that URL (for example by using it as a form's
`action` attribute), your data is first sent to Blobstore. Blobstore will
process any files included in the request, and then it will add information
about the uploaded files to the request, and forward the request on to whatever
URL you passed into the `createUploadUrl()` function (in the `HomeServlet`
example, that's `/my-form-handler`).

In other words, the request is forwarded to the `FormHandlerServlet` class.

### FormHandlerServlet

The
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-3-libraries/blobstore/examples/hello-world/src/main/java/com/google/sps/servlets/FormHandlerServlet.java">
  FormHandlerServlet.java
</walkthrough-editor-open-file>
file has a `doPost()` function that's triggered when Blobstore forwards the
request to the `/my-form-handler` URL. The `doPost()` function gets the value
entered in the text area, and it then gets the URL for the uploaded image. The
`getUploadedFileUrl()` function might seem intimidating, but most of that is
checking for corner cases. The core of the logic for getting the image URL is
in the last 3 lines of code.

You can then use that URL to create an `<img>` element. In a real project, you'd
probably do something like store the image URL in an `ArrayList` or in
Datastore.

Try running this example and printing out the values for `uploadUrl` and
`imageUrl` to get an idea of how Blobstore works. Try visiting `imageUrl`
directly in your browser to understand how the image hosting works.

## Getting the Blobstore Upload URL

Now you know how to use Blobstore to allow users to upload an image:

1.  Call `blobstoreService.createUploadUrl()` to get the Blobstore upload URL.
2.  Use that URL as the `action` attribute of a `<form>` element that contains a
    file selector input.
3.  Create a servlet that handles the `POST` request after Blobstore processes
    the upload.
4.  Call `imagesService.getServingUrl()` to get the URL of the uploaded image.
5.  Store the image URL in Datastore, or use it to output an `<img>` element.

Let's look a little closer at step 2. You have HTML and JavaScript that runs on
the client, but you need to get a URL from `blobstoreService.createUploadUrl()`
which can only run on the server. How do you get that value from the server into
the `<form>` element in your client-side HTML?

The `hello-world` example handled this by using a servlet to output the HTML
directly. This is a hack that allowed us to output the upload URL easily, but
this is **not** how you'd do it in a real codebase! Having all of your HTML
inside `String` values in Java code is really annoying to work with and prone to
bugs.

The next two steps outline other approaches you might take instead.

## Fetch

The `hello-world-fetch` directory contains an example that uses `fetch()` to get
the Blobstore upload URL from the server.

It works like this:

1.  The servlet inside the
    <walkthrough-editor-open-file
        filePath="software-product-sprint/walkthroughs/week-3-libraries/blobstore/examples/hello-world-fetch/src/main/java/com/google/sps/servlets/BlobstoreUploadUrlServlet.java">
      BlobstoreUploadUrlServlet.java
    </walkthrough-editor-open-file>
    file maps to `/blobstore-upload-url`.
2.  The `doGet()` function for that servlet calls the
    `blobstoreService.createUploadUrl()` function, and returns the result as the
    response to the request. Try running a dev server and navigating to
    `/blobstore-upload-url` to see this yourself!
3.  On the client side, the
    <walkthrough-editor-open-file
        filePath="software-product-sprint/walkthroughs/week-3-libraries/blobstore/examples/hello-world-fetch/src/main/webapp/script.js">
      script.js
    </walkthrough-editor-open-file>
    file uses the `fetch()` function to make a `GET` request to
    `/blobstore-upload-url`.
4.  The response to that request will be the Blobstore upload URL, which is then
    used to set the `action` of the form from JavaScript.

## JSP

Another approach you could take is to server-side render the form using JSP, that
way you can directly embed the URL in the form. The `hello-world-jsp` directory
contains an example that takes this approach.

The
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-3-libraries/blobstore/examples/hello-world-jsp/src/main/webapp/index.jsp">
  index.jsp
</walkthrough-editor-open-file>
file runs on the server. The Java code at the top calls the
`blobstoreService.createUploadUrl()` function, which it then embeds directly in
the `action` attribute of the `<form>` element.

Then the HTML content, including the `action` attribute of the `<form>` element,
is sent to the client as the response. The client treats this just like it would
any other HTML content (because that's what it is), but you don't have to fetch
the Blobstore upload URL, because it's already included.

## Your Turn

Your goal is to use the concepts you just learned to add image uploads to the
comments you implemented in week 2.

Try to break this goal down into smaller steps, and then take each step on
individually.

-   Add the App Engine dependency to your
    <walkthrough-editor-open-file
        filePath="software-product-sprint/portfolio/pom.xml">
      pom.xml
    </walkthrough-editor-open-file>
    file.
-   Decide how you want to reference the Blobstore upload URL in your HTML:
    either by outputting the HTML directly from a servlet, or by using
    `fetch()`, or by using JSP.
-   Add a file upload input to your comments form.
    -   Test that this works by running a dev server and inspecting the page. You
        should see the Blobstore upload URL and file input in your HTML.
    -   When you get this step working, create a pull request and send it to
        your advisor for review!
-   Add a servlet that handles file upload requests. Remember: you should not
    `POST` directly to this servlet! You should pass its URL into Blobstore, and
    then handle the request after Blobstore processes it.
    -   Test that this works by printing the file URL to the console.
    -   When you get this step working, create a pull request and send it to
        your advisor for review!
-   Add the file URL to the JSON returned by your comments servlet.
    -   Test that this works by running a dev server and viewing the JSON in your
        browser.
    -   When you get this step working, create a pull request and send it to
        your advisor for code review!
-   Add JavaScript code that reads the file URL from the JSON and displays it in
    an `<img>` tag.

If you follow those steps, your portfolio should contain a working image upload
feature!

## Live Server

When you're happy with your feature and you're ready to show it to the world,
you can deploy it to your live server!

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

Congratulations! Your portfolio page should now support image uploads.

Make sure your code is submitted to your repo before the end of the week.

To return to the libraries walkthrough, run this command:

```bash
teachme ~/software-product-sprint/walkthroughs/week-3-libraries/libraries-walkthrough.md
```