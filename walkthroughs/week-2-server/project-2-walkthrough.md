# Project 2: Server Programming - Contact Me

## Getting Started

In the previous project, you wrote client-side JavaScript and server-side Java
to show a random message in your portfolio.

That project showed you how to send data from the server to the client. That's
useful for tasks like loading data that's stored on a server, or for building a
dynamic webpage from multiple sources.

This next project shows you how to send data from the client to the server. This
is useful for getting information from users, or for modifying data stored on
the server.

You can return to this walkthrough anytime by running this command:

```bash
teachme ~/software-product-sprint/walkthroughs/week-2-server/project-2-walkthrough.md
```

Click the **Start** button to begin!

## The Goal

The goal of this project is to add a form to your portfolio that lets a user
send you a message, and to write server-side code that logs that message for you
to view later.

How you present the form is up to you. You might use it to ask recruiters to
contact you, or you might use it to get feedback from people visiting your
portfolio. This is your portfolio, so make it your own!

Focus on finishing a
[minimum viable product](https://en.wikipedia.org/wiki/Minimum_viable_product)
that proves you have all of the pieces connected, and then come back later and
add extra features if you have time left over.

Each step is designed to take about an hour. Make sure you leave time for code
reviews!

## Requests

At this point, you've seen two ways to send requests: by typing a URL into your
browser, or by using the `fetch()` function.

The requests you've seen so far are called `GET` requests, because they're
requesting to **get** data from the server.

There's another kind of request called a `POST` request, which lets you **send**
data to the server.

## HTML Forms

One common way to send `POST` requests is by using the HTML `<form>` tag. Here's
an example:

```html
<form method="POST">
  <p>Enter some text here:</p>
  <textarea name="text-input">hello world</textarea>
  <br/>
  <input type="submit" />
</form>
```

Add this inside the `<body>` section of your portfolio's HTML and run a dev
server. You should see a form that contains a text area and a submit button.

Fow now, clicking the submit button will refresh the page. You'll change that
soon!

### Learn More

For more information, check out
[MDN](https://developer.mozilla.org/en-US/docs/Learn/HTML/Forms) or
[W3Schools](https://www.w3schools.com/html/html_forms.asp), or try googling
"HTML form tutorial" for more resources.

## POST Requests

This week's first project introduced servlets, which run Java code when a client
sends a request for a specific URL. So far you've seen the `doGet()` function,
which handles `GET` requests.

To handle `POST` requests, create a servlet that contains a `doPost()` function.
Start by creating a new `FormHandlerServlet.java` file and copying this code
into it:

```java
package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Get the value entered in the form.
    String textValue = request.getParameter("text-input");

    // Print the value so you can see it in the server logs.
    System.out.println("You submitted: " + textValue);

    // Write the value to the response so the user can see it.
    response.getWriter().println("You submitted: " + textValue);
  }
}
```

This code handles a `POST` request and gets the value of the parameter named
`"text-input"` (notice that this name matches the name in the HTML form). The
code then prints the value to both the server logs and as the response.

Your form doesn't actually trigger this servlet yet. Let's connect them now!

## Connecting Forms to Servlets

So far, you have a `<form>` element in your HTML, and a servlet that handles
`POST` requests. But your form isn't connected to the servlet yet.

Remember that servlets handle requests sent to specific URLs, so to connect your
form to your servlet, you need to tell the form to submit its data to the
servlet's URL.

You do this by adding an `action` attribute to your `<form>` tag, with a value
that tells the form which URL to send its data to:

```html
<form action="/form-handler" method="POST">
  ...
```

Notice that the form's action attribute matches the servlet's URL specified in
its `@WebServlet` annotation:

```java
@WebServlet("/form-handler")
```

When the user clicks the `Submit` button, the form sends a `POST` request to the
URL specified in the form's `action` attribute. The server looks for a servlet
that maps to that URL, and then runs its `doPost()` function.

Run your dev server again. Now when you submit your form, your servlet's
`doPost()` function should run.

## Viewing Logs

The example servlet above uses the `System.out.println()` function to print the
user's value to the server logs.

When you run a dev server, these logs are printed directly to the console that
you ran your server from.

Logs for your live server are shown in Google Cloud's Logs Explorer. To explore
the logs of your live server, go here: https://console.cloud.google.com/logs

Make sure your project is selected, and click the `Logs Explorer` tab in the
left navigation bar. The Logs Explorer shows information about each request sent
to your server, including any logs you've printed.

Deploy what you have so far to your live server, and then submit some data to
your form. Find that `POST` request in your Logs Explorer, and confirm that your
print statement shows up.

The Logs Explorer also shows errors that your live server encounters. So if
something isn't working with your live server, don't forget to check the logs!

## Example

To see an example of a form that contains multiple input elements, explore the
`text-processor` directory and open the
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-2-server/examples/text-processor/src/main/webapp/index.html">
  index.html
</walkthrough-editor-open-file>
file. This file includes a form, which contains a text input, two checkboxes,
and a submit button. Then open the
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-2-server/examples/text-processor/src/main/java/com/google/sps/servlets/TextProcessorServlet.java">
  TextProcessorServlet.java
</walkthrough-editor-open-file>
file to see the servlet that handles the request from the form.

The user can enter data, and when they click the submit button, the browser
sends a `POST` request to the URL specified in the form's `action` attribute.

To see this example in action, `cd` into the `text-processor` directory and
then run a development server:

```bash
mvn package exec:java
```

Try submitting different values in the form and see how they're handled on the
server.

## Redirects

The examples you've seen so far take the input from the `POST` request and
output it directly in the page as the response to that request.

But most of the time, you'll probably want to process or store the data, and
then **redirect** the user to another page. For example, when a user submits a
message, you probably want to redirect them back to the page they were on when
they submitted it.

A redirect tells the client which URL to visit next. To redirect the user, you
can use the `response.sendRedirect()` function:

```java
response.sendRedirect("https://google.com");
```

This line of code redirects the client to `https://google.com`, which sends
their browser to Google. Typically you'll want to redirect to a URL within your
webapp.

It's up to you whether you want to redirect your user or not. It's your
portfolio, so make it your own!

## Your Turn

Your goal is to add a form to your portfolio that lets users contact you. It's
up to you what the form includes: you might ask recruiters to send you their
email address, or you might let users tell you their favorite jokes.

Write a servlet that handles the input from the form and outputs the information
in your server logs so you can view it later.

Next week you'll learn about other things you can do with data on your server,
including storing it permanently and processing it with machine learning
algorithms. But for now, focus on familiarizing yourself with the connection
between your HTML and your server, as well as the Logs Explorer!

## Pull Request

<walkthrough-conclusion-trophy></walkthrough-conclusion-trophy>

Congratulations!

At this point your portfolio should contain a form that lets users message you!
Run a dev server to confirm that everything works, and then run a live server
and confirm that you can see the logs in the Logs Explorer. When everything
works, create a pull request and send that to your advisor for a code review!

When you submit your code, you're done with this week's projects!

To start next week's project, run the libraries walkthrough:

```bash
teachme ~/software-product-sprint/walkthroughs/week-3-libraries/libraries-walkthrough.md
```
