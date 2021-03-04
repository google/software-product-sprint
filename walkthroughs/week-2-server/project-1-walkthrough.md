# Project 1: Server Programming - Random Message

## Getting Started

Last week you created a portfolio page and deployed a live server, which meant
people could view your portfolio on the web. In this project, you'll extend your
portfolio by writing client-side JavaScript and server-side Java to show a
random message in your portfolio.

You can return to this walkthrough anytime by running this command:

```bash
teachme ~/software-product-sprint/walkthroughs/week-2-server/project-1-walkthrough.md
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

## Step 1: Servlets

Servlets let you write Java code that runs on a server.

To learn about servlets, follow the `step-1-servlets-walkthrough.md`
walkthrough:

```bash
teachme ~/software-product-sprint/walkthroughs/week-2-server/step-1-servlets-walkthrough.md
```

Work through the servlets walkthrough and then return here.

### Pull Request

After the servlets walkthrough, your portfolio directory should contain a
servlet file with a `doGet()` function that responds with a hard-coded string.
Make sure you run a development server to make sure this works.

This is the first step towards implementing a full server that returns a list of
messages. Create a pull request from what you have so far, and send that to your
advisor for a code review!

## Step 2: Fetch

JavaScript's `fetch()` function requests data from a server, which you can use
to build a webpage.

To learn about the `fetch()` function, follow the `step-2-fetch-walkthrough.md`
walkthrough:

```bash
teachme ~/software-product-sprint/walkthroughs/week-2-server/step-2-fetch-walkthrough.md
```

Work through the fetch walkthrough and then return here.

### Pull Request

After completing the fetch walkthrough, your portfolio page should use the
`fetch()` function to request a message from the servlet, and then add that
message to the DOM after the page loads or after the user clicks a button. It's
okay if the message is still a hard-coded string.

It might not feel like you're implementing a full feature yet, but engineers
often work in small steps like this to confirm that each piece works and that
everything is properly connected. Make sure you run a development server to make
sure everything works.

After confirming that everything works, create a pull request from what you have
so far, and send that to your advisor for a code review!

## Step 3: JSON

JSON is a data format that JavaScript can parse without requiring any external
libraries, which makes it a popular choice for representing data in JavaScript.

To learn about JSON, follow the `step-3-json-walkthrough.md` walkthrough:

```bash
teachme ~/software-product-sprint/walkthroughs/week-2-server/step-3-json-walkthrough.md
```

Work through the JSON walkthrough and then return here.

### Pull Request

After the JSON walkthrough, your portfolio should use the `fetch()` function to
request data from the server, and your server should respond with a list of
messages. Your JavaScript should choose a random message and add it to the page
when it loads or when you click a button.

After running a dev server and confirming that everything works, create a pull
request from what you have so far, and send that to your advisor for a code
review!

## Motivation

You might be wondering why you needed to use a server instead of storing the
messages in JavaScript, or why your server returned a list of messages instead
of choosing a random message itself.

The goal of this project is to introduce you to concepts often used in industry:
clients and servers, requests and responses, communication between different
systems, and creating and parsing messages to build a user experience.

So although there are other approaches that would have worked for this specific
project, this was a peek into how we organize our code in industry. For example,
you might imagine that you're working on a team, where one person or team is
responsible for the server code, and another person or team is responsible for
the JavaScript code. They'd use the same concepts you just learned!

You'll also see examples that use more complex server code next week!

## Live Server

When you're happy with your random message feature and you're ready to show it
to the world, you can deploy it to your live server!

Last week, you should have added your project ID to your
<walkthrough-editor-open-file
    filePath="software-product-sprint/portfolio/pom.xml">
  pom.xml
</walkthrough-editor-open-file>
file. If so, you can deploy to your live server by executing this command from
the `portfolio` directory:

```bash
mvn package appengine:deploy
```

After the command successfully completes, you can navigate to
`YOUR_PROJECT_ID.appspot.com` to see your portfolio and test your comments
feature.

When you're done, share this link in the chat and with your team!

## Next Steps

<walkthrough-conclusion-trophy></walkthrough-conclusion-trophy>

Congratulations! You now have a working random message feature! This project
focused on sending data from the server to the client. Next, you'll learn how
to send data from the client to the server!

To learn more about `POST` requests, run the next project:

```bash
teachme ~/software-product-sprint/walkthroughs/week-2-server/project-2-walkthrough.md
```
