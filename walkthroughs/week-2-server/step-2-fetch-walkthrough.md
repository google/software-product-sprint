# Intro to Fetch

## Getting Started

This walkthrough introduces the `fetch()` function, which lets you write
JavaScript code that sends requests to a server.

You can return to this walkthrough anytime by running this command:

```bash
teachme ~/software-product-sprint/walkthroughs/week-2-server/step-2-fetch-walkthrough.md
```

Click the **Start** button to begin!

## Requests

You've now seen `.html` files that contain HTML, and you've seen servlets that
generate text content. So far, you've **requested** that text content by
pointing your web browser at a URL. The server responds to that request by
looking at the URL, finding the file or servlet it maps to, and returning the
corresponding content.

Pointing your web browser at a URL is one way to request content from a server.
This walkthrough introduces another way to request content: the `fetch()`
function!

The `fetch()` function lets you write JavaScript code that requests content from
a particular URL. This is useful for separating your static and dynamic content,
because it means you can put static content in an HTML file, and you can use the
`fetch()` function to request dynamic content from the server.

Let's look at an example!

## Current Server Date

The `examples/server-date` directory contains a webapp that shows a button that
fetches the current date from the server.

The project contains a few interesting files:

-   <walkthrough-editor-open-file
        filePath="software-product-sprint/walkthroughs/week-2-server/examples/server-date/src/main/java/com/google/sps/servlets/DateServlet.java">
      DateServlet.java
    </walkthrough-editor-open-file>
    is a servlet that responds with the current date when a client requests the
    `/date` URL.
-   <walkthrough-editor-open-file
        filePath="software-product-sprint/walkthroughs/week-2-server/examples/server-date/src/main/webapp/index.html">
      index.html
    </walkthrough-editor-open-file>
    is the static HTML content, which includes some text and a button.
-   <walkthrough-editor-open-file
        filePath="software-product-sprint/walkthroughs/week-2-server/examples/server-date/src/main/webapp/script.js">
      script.js
    </walkthrough-editor-open-file>
    is JavaScript that fetches content from the server and adds it to the DOM.

Run this example by `cd`-ing into the `server-date` directory and running a dev
server:

```bash
mvn package exec:java
```

Run this command and then click the
<walkthrough-web-preview-icon></walkthrough-web-preview-icon> icon. Then select
**Preview on port 8080** to open a tab that displays the `index.html` file. You
should see a page that shows a button. Click the button to see the current
server date.

Navigate to `/date` to see the content that's coming from the `DateServlet.java`
file.

The next few steps walk through how this works in more detail.

## Server Content

<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-2-server/examples/server-date/src/main/java/com/google/sps/servlets/DateServlet.java">
  DateServlet.java
</walkthrough-editor-open-file>
responds with the server's current date when the client requests the `/date`
URL.

When you navigate to the `/date` URL, your browser sends a request for that URL,
and your server routes that request to the `DateServlet.java` file. The
`doGet()` function writes the current date to the response, and your browser
renders that response.

This is very similar to the way the servlet you recently added to your portfolio
works.

## script.js

A page that shows the server's current date by itself isn't very interesting.
The page needs more HTML to display content to the user.

You could output HTML directly from the servlet, but that's hard to maintain.
Imagine writing a whole HTML page using Java print statements!

Instead, you can write JavaScript code that **fetches** the content from the
server and adds it to the webpage after the browser loads the original HTML.

Open the
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-2-server/examples/server-date/src/main/webapp/script.js">
  script.js
</walkthrough-editor-open-file>
file to see an example.

Let's read through it line-by-line to understand it.

### Fetch

The `fetch()` function sends a request to the server, and it doesn't know how
long it'll take to get a response back. Instead of slowing down your code, the
`fetch()` function runs *asynchronously*, which means that it sends the request
in the background while the rest of your code keeps running.

### Async and Await

There are a few ways to get the response from an asynchronous function. This
example uses the `async` and `await` keywords.

The `async` keyword tells JavaScript that a function is going to call an
asynchronous function. That's why it's included in the function declaration:

```javascript
async function showServerTime() {
```

Because the `showServerTime()` function is `async`, code inside it can use the
`await` keyword to tell JavaScript to wait for the response from an asynchronous
function rather than sending the request in the background.

```javascript
const responseFromServer = await fetch('/date');
```

The above line of code tells JavaScript to wait for the value returned from the
`fetch()` function. The `fetch()` function sends a request to the `/date` URL,
and when the response returns, it's stored in the `responseFromServer` variable.

### Response

The `responseFromServer` variable contains a response object, which provides a
few functions that let you access different types of content. In this case the
reponse is plain text, so the code calls the `text()` function:

```javascript
const textFromResponse = await responseFromServer.text();
```

This line of code tells JavaScript to wait for the text content that's included
in the response.

### DOM Manipulation

Now that the `textFromResponse` variable contains the text content, that text
can be added to the webpage:

```javascript
const dateContainer = document.getElementById('date-container');
dateContainer.innerText = textFromResponse;
}
```

This code first gets the element with an ID of `date-container`, and then sets
the text of that element to the text from the response.

All of this happens when the you click the button in the page. In other words,
when you click the button, the `showServerTime()` function in `script.js` runs,
and it calls the `fetch()` function to get the current date from the server,
which it then adds to the page.

## index.html

Now that you've seen how the `script.js` file works, let's take a closer look at
the
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-2-server/examples/server-date/src/main/webapp/script.js">
  index.html
</walkthrough-editor-open-file>
file.

This file contains a few interesting lines:

```html
<script src="script.js"></script>
```

This line tells the browser to load the JavaScript that's in the `script.js`
file when it loads the `index.html` webpage.

```html
<button onclick="showServerTime();">
  Get date
</button>
```

These lines create a button that contains `Get date` as a label. When the button
is clicked, the browser will call the `showServerTime()` function, which is
specified in the `script.js` file you already saw.

```html
<p id="date-container"></p>
```

This line creates an empty paragraph element with an ID of `date-container`. The
`showServerTime()` function finds this element and then displays the date inside
of it.

Pay attention to how the `index.html` file and the `script.js` file work
together. The HTML loads the `script.js` file and then calls its functions, and
those functions refer to elements specified by `index.html`.

## Your Turn

At this point, your `portfolio` directory should contain a servlet that responds
with a hardcoded string when a client requests its URL.

Next, add some HTML and JavaScript to your `portfolio` directory to fetch this
hard-coded string and display it in the page.

1. Create a new `script.js` file and write a function that calls `fetch()` and
   adds the response to the webpage.
2. Modify your portfolio's `index.html` page (or whatever your HTML file is
   named) to load your `script.js` file and call the function you wrote. Don't
   forget to add an element for your JavaScript to use as a container for the
   text.

You can use a button like the example above, or you can use the `onload`
attribute of the `<body>` element to fetch the content when the page loads.

When you're finished, you should have a page that fetches a message from the
server and adds it to the DOM. It's okay if the message is hard-coded for now!
The important thing is to get your client and your server working together using
the `fetch()` function.

## Pull Request

<walkthrough-conclusion-trophy></walkthrough-conclusion-trophy>

Congratulations! You now have the power to write JavaScript code that fetches
data from your server.

After running a dev server to confirm that everything works, create a pull
request from what you have so far, and send that to your advisor for review!

Then go back to the project 1 walkthrough to continue:

```bash
teachme ~/software-product-sprint/walkthroughs/week-2-server/project-1-walkthrough.md
```
