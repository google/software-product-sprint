# Intro to Servlets

## Getting Started

Last week you created a portfolio page and deployed a live server, which meant
people could view your portfolio on the web. In this project, you'll extend your
portfolio by writing client-side JavaScript and server-side Java to show a
random message in your portfolio.

You can return to this walkthrough anytime by running this command:

```bash
teachme ~/software-product-sprint/walkthroughs/week-2-server/step-1-servlets-walkthrough.md
```

Click the **Start** button to begin!

## The Goal

The goal of this project is to write client-side JavaScript and server-side Java
to show a random message in your portfolio.

The messages can be anything you want. They could be random facts about
yourself, or your favorite movie quotes, or song lyrics. This is your portfolio,
so make it your own!

Focus on finishing a
[minimum viable product](https://en.wikipedia.org/wiki/Minimum_viable_product)
that proves you have all of the pieces connected, and then come back later and
add extra features if you have time left over.

To accomplish this goal, follow the steps in this walkthrough. Each step has its
own walkthrough that introduces concepts and shows you examples.

Each step is designed to take about an hour. Make sure you leave time for code
reviews!

## Clients and Servers

You'll hear these words a lot, so let's start by defining them.

A [client](https://en.wikipedia.org/wiki/Client_\(computing\)) is whatever
you're using to interact with the internet. It's the web browser you're using to
read this page. The web browser on your computer is one client, the web browser
on your phone is another client. There are other types of clients (like watching
Netflix or listening to Spotify), but we'll focus on web browsers for now.

One important property of a client is that they run **locally**, on your
computer (or phone, or whatever device you're using). When we talk about
"client-side", or something that happens "in the client", we mean it happens on
your device.

A [server](https://en.wikipedia.org/wiki/Server_\(computing\)) is a computer
that **serves** content based on a request. The server is the computer that your
browser is talking to when your browser asks for a particular URL.

This project uses Google Cloud, so the server computer happens to be in a Google
[data center](https://www.google.com/about/datacenters/).

Servers can serve static files like you created last week, but they can also
execute code to decide what content to send back to the client. The rest of this
walkthrough introduces that type of server-side code.

## Servlets

A servlet is a Java class with functions that run when a client requests a
particular URL. These functions run automatically, similar to how the `main()`
function runs when you execute a core Java program.

To see an example, explore the `examples/page-view-counter` directory.

Specifically, open the
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-2-server/examples/page-view-counter/src/main/java/com/google/sps/servlets/PageViewServlet.java">
  PageViewServlet.java
</walkthrough-editor-open-file>
file. This is a servlet class that handles the `/page-views` URL and responds
with HTML that contains a count of how many times the page has been loaded.

This file contains a few interesting lines:

-   `@WebServlet("/page-views")` is an
    [annotation](https://en.wikipedia.org/wiki/Java_annotation) that tells our
    server which URL this servlet maps to. When a client requests the
    `/page-views` URL, this servlet is triggered.
-   The `doGet()` function runs whenever a client sends a `GET` request to the
    servlet's URL. (Your browser sends a `GET` request whenever you visit a
    URL.)
-   `pageViews++;` increments the `pageViews` variable. Since the `doGet()`
    function runs every time the `/page-views` URL is requested, this means the
    `pageViews` variable tracks how many times the page has been viewed.
-   `response.setContentType("text/html;");` specifies what type of content the
    client should expect.
-   `response.getWriter().println("<h1>Page Views</h1>");` prints an `<h1>` tag
    to the response.
-   `response.getWriter().println("<p>This page has been viewed " + pageViews +
    " times.</p>");` prints the page view count to the response.

**Remember:** The code in a servlet's `doGet()` function runs every time a
client requests its URL. That means you can write Java code that generates
different content for each request! You can use `if` statements, `for` loops,
objects, functions, etc. Servlets are Java, so any code that works in Java will
work in a servlet!

### URLs

The example servlet maps to the `/page-views` URL. You can change the annotation
so it maps to a different URL, like `/content` or `/mypage.html`.

## Your Turn

Your goal is to modify the servlet *in your portfolio* (not in the sample
PageViewServlet) to write a different
hard-coded string as the response when a user requests its URL.

Right now the servlet writes `"Hello world!"` as the response. Try changing that
to `"Hello [your name]!"`.  (Be sure to deploy your changes and access the
appropriate URL to make sure it works!)

In the next steps you'll modify this code to return a list of random strings,
but working iteratively in small steps like this is a good habit to get into!
Working one step at a time makes it much easier to spot errors than if you try
to do everything all at once. It also makes your code reviews easier!

## Why Servlets?

Most large projects use a framework like Spring, Grails, or Flask. So why are we
using servlets?

First, learning the fundamentals is important for understanding the higher-level
concepts used by a framework. It's hard to learn a framework before
understanding how requests work.

Second, using a framework involves a lot of setup and configuration. Code is
hard enough to debug, even without fighting with framework config files!

Third, most of the Google Cloud tutorials use servlets. So anything you find in
a Google Cloud tutorial should "just work" with the code you write during SPS.

All of that said, if using a framework is interesting to you, consider exploring
that during the open project!

## Pull Request

<walkthrough-conclusion-trophy></walkthrough-conclusion-trophy>

Congratulations! What you have so far might seem like a small change, but that's
a good thing! Working in small steps like this makes it easier to understand how
everything works, improves debugging, and simplifies code reviews.

In other words, it's a good idea to send pull requests early and often! Before
continuing, create a pull request from what you have so far and send that to
your advisor for a code review.

Then continue with step 2:

```bash
teachme ~/software-product-sprint/walkthroughs/week-2-server/step-2-fetch-walkthrough.md
```
