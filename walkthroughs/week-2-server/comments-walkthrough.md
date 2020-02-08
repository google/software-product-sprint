# Server Programming - Comments

## Getting Started

Last week you created a portfolio page and deployed a live server, allowing web
browsers to view your portfolio. This week you'll extend it to support user
comments.

You can return to this walkthrough anytime by running this command:

```bash
teachme ~/software-product-sprint/walkthroughs/week-2-server/comments-walkthrough.md
```

Click the **Start** button to begin!

## The Goal

The goal of this project is to add comments to your portfolio page. The page
should contain a comment form, and it should show all of the user comments that
have already been left.

For now, think about how you want your comments to work. What features do you
find most useful?

Focus on finishing a
[minimum viable product](https://en.wikipedia.org/wiki/Minimum_viable_product)
that proves you have all of the pieces connected, and then come back later and
add extra features if you have time left over.

To accomplish this goal, follow the steps in this walkthrough. Each step has its
own walkthrough that introduces concepts and shows you examples.

Each step is designed to take about an hour. Make sure you leave time for code
reviews!

## Step 1: Servlets

Servlets allow you to write Java code that runs on a server.

To learn about servlets, follow the `step-1-servlets-walkthrough.md`
walkthrough:

```bash
teachme ~/software-product-sprint/walkthroughs/week-2-server/step-1-servlets-walkthrough.md
```

Work through the servlets walkthrough and then return here.

### Pull Request

After the servlets walkthrough, your portfolio directory should contain a
`DataServlet.java` file that contains a `doGet()` function that responds with a
hard-coded `"Hello [your name]"` message. Make sure you run a development server
to make sure this works.

This is the first step towards implementing a full server that supports
comments. Create a pull request from what you have so far, and send that to your
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
okay if the message is still a hard-coded `"Hello [your name]"` string.

It might not feel like you're implementing a comments feature yet, but engineers
often work in small steps like this to confirm that each piece works and that
everything is connected. Make sure you run a development server to make sure
everything works.

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
request comments data, and your server should respond with some hard-coded JSON
data. Your JavaScript should add these comments to the page when it loads.

After running a dev server and confirming that everything works, create a pull
request from what you have so far, and send that to your advisor for a code
review!

## Step 4: HTML Forms and POST Requests

You can use HTML forms to send data as POST requests to your server.

To learn about HTML forms and POST requests, follow the
`step-4-post-walkthrough.md` walkthrough:

```bash
teachme ~/software-product-sprint/walkthroughs/week-2-server/step-4-post-walkthrough.md
```

Work through the POST walkthrough and then return here.

### Pull Request

At this point, you should have a fully-functional comments feature. You may have
noticed that the comments are deleted whenever you restart your server. You'll
fix that next, but for now run a dev server to confirm that what you have so far
works. Then create a pull request and send it to your advisor for a code review.

## Step 5: Datastore

You can use Datastore to permanently store your comments.

To learn about Datastore, follow the `step-5-datastore-walkthrough.md`
walkthrough:

```bash
teachme ~/software-product-sprint/walkthroughs/week-2-server/step-5-datastore-walkthrough.md
```

Work through the Datastore walkthrough and then return here.

### Pull Request

At this point, you should have a fully-functional comments feature, including
saving and loading! Run a dev server to confirm that everything works, and then
create a pull request and send it to your advisor for a code review.

## Live Server

When you're happy with your comments feature and you're ready to show it to the
world, you can deploy it to your live server!

Last week, you should have added your project ID to your
<walkthrough-editor-open-file
    filePath="software-product-sprint/portfolio/src/main/webapp/WEB-INF/appengine-web.xml">
  appengine-web.xml
</walkthrough-editor-open-file>
file. If so, you can deploy to your live server by executing this command from
the `portfolio` directory:

```bash
mvn appengine:update
```

-   The first time you run this command, the console will give you a link. Open
    that link, and login to that page using your `@sps-program` account.
-   After you login, you'll see a long string of characters. Copy those
    characters.
-   Paste the characters into the console and press enter.

After the command successfully completes, you can navigate to
`YOUR_PROJECT_ID.appspot.com` to see your portfolio and test your comments
feature.

When you're done, share this link in the chat and with your team!

## Next Steps

<walkthrough-conclusion-trophy></walkthrough-conclusion-trophy>

Congratulations! You now have a working comment feature! Make sure your
[MVP](https://en.wikipedia.org/wiki/Minimum_viable_product) works and is
submitted to your GitHub repo by the end of the week.

If you have some time left over and you want to learn more, here are a few ideas
for what to do next:

-   Add a text field that allows users to specify their name as well as a
    comment.
-   Add a dropdown box that allows users to add a mood (like happy or sad) to
    their comments.
-   Allow users to change the order that comments display: newest first, oldest
    first, longest first, etc.
-   What happens when you have 1000 comments? Should they all show?
-   Improve error handling and security. What if a user enters HTML content or
    JavaScript code?

To start the next set of projects, run the libraries walkthrough:

```bash
teachme ~/software-product-sprint/walkthroughs/week-3-libraries/libraries-walkthrough.md
```