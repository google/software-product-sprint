# Intro to Post Requests

## Getting Started

This walkthrough introduces `POST` requests, which let you send data to your
server.

You can return to this walkthrough anytime by running this command:

```bash
teachme post-walkthrough.md
```

Click the **Start** button to begin!

## Requests

At this point, you've seen two ways to send requests: by typing a URL into your
browser, or by using the `fetch()` function.

The requests you've seen so far are called `GET` requests, because they're
requesting to **get** data from the server.

There's another kind of request called a `POST` request, which lets you **send**
data to the server.

## HTML Forms

One common way to send `POST` requests is by using the HTML `<form>` tag.

A `<form>` element has attributes that tell the page where to send the data, and
contains input elements that let the user specify the data to send.

To see an example, explore the `text-processor` directory and open the
`index.html` file. This file includes a form, which contains a text input, two
checkboxes, and a submit button.

The user can enter data, and when they click the submit button, the browser
sends a `POST` request to the URL specified in the form's `action` attribute.

### Learn More

For more information, check out
[MDN](https://developer.mozilla.org/en-US/docs/Learn/HTML/Forms) or
[W3Schools](https://www.w3schools.com/html/html_forms.asp), or try googling
"HTML form tutorial" for more resources.

## The `doPost()` Function

When the user clicks the `Submit` button, the form sends a `POST` request to the
URL specified in the form's `action` attribute. The server looks for a servlet
that maps to that URL, and then runs its `doPost()` function.

Open the `TextProcessorServlet.java` file to see that code.

To get the values from a submitted form, use the `request.getParameter()`
function. The request contains parameters with names that match the names of the
input elements in the HTML form. For example, the HTML form contains an `input`
with a name of `text-input`, so on the server, you can call
`request.getParameter("text-input")` to get the value entered by the user into
that input.

Run a development server:

```bash
mvn appengine:devserver
```

Try submitting different values in the form and see how they're handled on the
server.

## Redirects

The `text-processor` example code takes the input from the `POST` request and
outputs it directly in the page as the response to that request.

But most of the time, you'll probably want to process or store the data, and
then **redirect** the user to another page. For example, when a user leaves a
comment, you probably want to redirect them back to the page they commented on.

A redirect tells the client which URL to visit next. To redirect the user, you
can use the `response.sendRedirect()` function:

```java
response.sendRedirect("https://google.com");
```

This line of code redirects the client to `https://google.com`, which sends
their browser to Google. Typically you'll want to redirect to a URL within your
webapp. Let's look at an example.

### Mixing GET and POST

Explore the `subtraction-game` directory to see an example that uses POST
requests, redirects, GET requests, and JSON to implement the
[subtraction game](https://en.wikipedia.org/wiki/Nim#The_subtraction_game_S\(1,_2,_._._.,_k\)).

The `SubtractionServlet` class contains a `doPost()` function that takes user
input and changes the state of the game, and a `doGet()` function that returns
the current state of the game.

The `index.html` file contains HTML that includes a form that lets the user send
a value to the server. The `<body>` element has an `onload` attribute that
points to the `getSubtractionForm()` function, which tells JavaScript to call
that function when the page loads.

The `script.js` file contains JavaScript that fetches the state of the game and
builds the UI.

All of these files work together to implement the subtraction game, and they
demonstrate a common pattern:

-   The user loads a page.
-   The page contains a form that posts data to the server.
-   The server processes or stores the data.
-   The server redirects back to the same page.
-   The page gets the processed data from the server and builds a UI.

You can use this pattern to implement comments, and many other features!

## Your Turn

To complete your comments feature, add a `<form>` element to your portfolio
page. It's up to you what input elements are in the form, but at a minimum you
probably want a text input for the user to add a comment. Then add a `doPost()`
function to your `DataServlet` class that takes the data from the request to
populate your comments data structure. An `ArrayList` of `String` values is
fine.

Don't forget to remove the hard-coded data you added in the previous step.

## Pull Request

<walkthrough-conclusion-trophy></walkthrough-conclusion-trophy>

Congratulations!

At this point you should have a fully functional comments feature! Run a
dev server to confirm that everything works, and then create a pull request and
send that to your advisor for a code review!
