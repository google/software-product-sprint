# Intro to Fetch

## Getting Started

This walkthrough introduces the `fetch()` function, which lets you write
JavaScript code that sends requests to a server.

You can return to this walkthrough anytime by running this command:

```bash
teachme step-2-fetch-walkthrough.md
```

Click the **Start** button to begin!

## Requests

You've now seen `.html` files that contain HTML, and you've seen servlets that
generate HTML. So far, you've **requested** that HTML by pointing your web
browser at a URL. The server responds to that request by looking at the URL,
finding the file or servlet it maps to, and returning the corresponding content.

Pointing your web browser at a URL is one way to request content from a server.
This walkthrough introduces another way to request content: the `fetch()`
function!

The `fetch()` function lets you write JavaScript code that requests content from
a particular URL. This is useful for separating your static and dynamic content,
because it means you can put static content in an HTML file, and you can use the
`fetch()` function to request dynamic content from the server.

Let's look at an example of this in action.

## Random Quote Generator

Open up the `examples/random-quotes` directory. This directory contains a webapp
that shows a button that fetches a random quote from the server.

The project contains a few interesting files:

-   `RandomQuoteServlet.java` is a servlet that responds with a random quote
    when a client requests the `/random-quote` URL.
-   `index.html` is the static HTML content, including the button.
-   `script.js` is JavaScript that fetches content from the server and
    adds it to the DOM.

The `script.js` file is a lot of code, but we'll break it down in the next few
steps.

## Server Content

The `RandomQuoteServlet` class responds with a random quote when the client
requests the `/random-quote` URL.

Confirm that this works by `cd`-ing into the `random-quotes` directory and
running a dev server:

```bash
mvn appengine:devserver
```

Run this command and then click the
<walkthrough-web-preview-icon></walkthrough-web-preview-icon> icon. Then select
**Preview on port 8080** to open a tab that displays the `index.html` file.
Navigate to `/random-quote` to see the content that's coming from the
`RandomQuoteServlet.java` file.

You should see a random quote that changes whenever you refresh the page.

## Fetch

A page that shows a quote by itself isn't very interesting. The page needs more
HTML to display content to the user.

You could output HTML directly from the servlet, but that's hard to maintain.
Imagine writing a whole HTML page using Java print statements!

Instead, you can write client-side JavaScript code that **fetches** the content
from the server and adds it to an existing HTML file.

Open the `script.js` file inside the `random-quotes` directory to see an
example.

This is a lot of code, but let's read through it line-by-line to understand it.

### getRandomQuote

The `getRandomQuote()` function is called when a user clicks the button in
`index.html`.

`fetch('/random-quote')` sends a request to the `/random-quote` URL. The server
responds to this request with a random quote, exactly like when you navigated to
the URL with your browser.

The `fetch()` function makes a request in the background, so it returns a
`Promise`, which is stored in the `responsePromise` variable.

The `responsePromise` variable points to a `Promise`, and the code calls its
`then()` function, and passes the `handleResponse` function in as an argument.
This syntax might look a little strange, but in JavaScript you can reference
a function using its name.

The `responsePromise.then(handleResponse);` line tells JavaScript to call the
`handleResponse()` function when the server returns a response.

If you're new to the concept of promises, read through these resources to learn
more:

-   [JavaScript Promises: an Introduction](https://developers.google.com/web/fundamentals/primers/promises)
-   [Using Promises - MDN](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Using_promises)

If you want more help, try searching "JavaScript Promises" on Google, or ask in
the chat!

### handleResponse

The server sends back a response stream, which needs to be converted into text
content, so the `handleResponse()` function takes the response and calls its
`text()` function.

For large content, the `text()` function can take a long time, so it returns
another `Promise`. The `textPromise.then(addQuoteToDom);` line tells JavaScript
to call the `addQuoteToDom()` function after the text conversion is complete.

### addQuoteToDom

The `addQuoteToDom()` function finds the element with an ID of `quote-container`
and adds the quote that came from the server to that element.

Run a dev server and navigate to `index.html` to see the button. Click it to
fetch a random quote from the server!

**Tip:** Open your JavaScript console before you click the button to see print
statements. Add more print statements to help you understand what the code is
doing.

## Arrow Functions

The code you looked at so far was purposely written out so each step was on its
own line. But you can use
[arrow functions](https://www.w3schools.com/js/js_arrow_function.asp) to shorten
your code. The `getRandomQuoteUsingArrowFunctions()` function is an example of
this approach. It uses arrow functions to do all of the work in 5 lines of code.

You can use whichever syntax makes the most sense to you, but most tutorials you
find on the internet will use the condensed arrow function syntax.

Try changing the `onclick` attribute of the button to call the
`getRandomQuoteUsingArrowFunctions()` function.

## Async and Await

Another way to use the `fetch()` function is by using the `async` and `await`
keywords. This allows you to use the return values directly instead of going
through `Promise` objects.

The `getRandomQuoteUsingAsyncAwait()` function is an example of this approach.

## Your Turn

At this point, your `portfolio` directory should contain a `DataServlet` that
responds with `"Hello [YourName]!"` when a client requests the `/data` URL.

Modify `index.html` and `script.js` to use `fetch()` to request this content
from the server and add it to the page. You can use a button like the example
above, or you can use the `onload` attribute of the `<body>` element to fetch
the content when the page loads.

When you're finished, you should have a page that fetches a message from the
server and adds it to the DOM. It's okay if the message is hard-coded for now.

## Pull Request

<walkthrough-conclusion-trophy></walkthrough-conclusion-trophy>

Congratulations! You now have the power to write JavaScript code that fetches
data from your server.

After running a dev server to confirm that everything works, create a pull
request from what you have so far, and send that to your advisor for review!
