# Intro to JSON

## Getting Started

This walkthrough introduces JSON, a format that lets you send more complex data
from the server to the client.

You can return to this walkthrough anytime by running this command:

```bash
teachme ~/software-product-sprint/walkthroughs/week-2-server/step-3-json-walkthrough.md
```

Click the **Start** button to begin!

## Content

So far, you've seen an example that returns the current time from the server,
and you've added a servlet that responds with a hard-coded string to your
portfolio. Returning a single string from the server is okay for small pieces of
data, but you'll normally want to return more than one value in the response
from a server. For example, your servlet will eventually return a list of
random messages, rather than a single string.

To return multiple values, you can use any format you want: XML and key-value
pairs are popular choices.

Another common format is [JavaScript Object Notation](http://www.json.org/).
JSON is handy because JavaScript can parse JSON without requiring any external
libraries.

The next few steps walk you through some examples. You can also read more about
JSON at [W3Schools](https://www.w3schools.com/js/js_json_intro.asp) or
[MDN](https://developer.mozilla.org/en-US/docs/Learn/JavaScript/Objects/JSON),
or by Googling for JSON tutorials.

## JSON Syntax

The JSON format specifies a few rules:

-   Objects are enclosed in `{ }` curly brackets.
-   Objects contain `key: value` pairs. Pairs are separated by commas.
-   Field names and string values should be enclosed in `" "` double quotes.
-   Arrays are enclosed in `[ ]` square brackets.

For example, here is an object with a `name` and a `birthYear` field:

```json

{"name": "Ada", "birthYear": 1815}
```

In JavaScript, you would be able to access this object's `.name` and
`.birthYear` fields.

Here is an array that contains three objects:

```json
[
  {"name": "Ada", "birthYear": 1815},
  {"name": "Grace", "birthYear": 1906},
  {"name": "Alan", "birthYear": 1912}
]
```

In JavaScript, parsing this would give you an array, which you could access by
index. Each element in the array would contain `.name` and `.birthYear` fields.

Note that JSON can contain newlines and indentation to make it more
human-readable, but the parser ignores whitespace.

JSON objects can also be nested, with objects containing other objects, or
arrays, or arrays of objects. Here's an object that contains an array of other
objects:

```json
{
  "people": [
    {"name": "Ada", "birthYear": 1815},
    {"name": "Grace", "birthYear": 1906},
    {"name": "Alan", "birthYear": 1912}
  ]
}
```

In JavaScript, parsing this would give you an object with a `.people` field that
referenced an array of objects. Each object has a `.name` and a `.birthYear` field.

[MDN](https://developer.mozilla.org/en-US/docs/Learn/JavaScript/Objects/JSON) has more JSON examples.

## Creating JSON

Generally, you'll create a JSON string on the server, in Java. One way to do
that is by manually building the string yourself.

The `server-stats` directory contains an example that uses JSON. Open the
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-2-server/examples/server-stats/src/main/java/com/google/sps/servlets/ServerStatsServlet.java">
  ServerStatsServlet.java
</walkthrough-editor-open-file>
file. This class creates an instance of a `ServerStats` class, and its
`convertToJson()` function converts that instance to a JSON-formatted string
by manually building a string. The servlet then sends this JSON string to the
client as the response.

## Gson

Formatting data as JSON can be tedious and error-prone. Instead of doing it
yourself, you can use a library that does it for you!

[Gson](https://github.com/google/gson) is an open-source Java library that
formats Java objects as JSON strings. The
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-2-server/examples/server-stats/src/main/java/com/google/sps/servlets/ServerStatsServlet.java">
  ServerStatsServlet.java
</walkthrough-editor-open-file>
file contains an example `convertToJsonUsingGson()` function that converts a
`ServerStats` instance to JSON using the Gson library.

The output of the `convertToJson()` function and the `convertToJsonUsingGson()`
function is the same JSON string. You can use whichever approach you prefer.

**Note:** To use Gson, first add this dependency to your
<walkthrough-editor-open-file
    filePath="software-product-sprint/portfolio/pom.xml">
  pom.xml
</walkthrough-editor-open-file>
file:

```xml
<dependency>
  <groupId>com.google.code.gson</groupId>
  <artifactId>gson</artifactId>
  <version>2.8.6</version>
</dependency>
```

This tells Maven to add the Gson library to your project. Now you can use it in
your Java code.

## Parsing JSON

Now you know how to create JSON on the server. Next, you need to parse the JSON
in JavaScript to convert it into an object.

Since you're using the `fetch()` function to retrieve JSON from the server,
you can use the built-in `json()` function to parse the response into objects
you can use in JavaScript.

Here's an example:

```javascript
// Send a request to /my-data-url.
const responseFromServer = await fetch('/my-data-url');

// Parse the response as JSON.
const myObject = await responseFromServer.json();

// Now we can reference the fields in myObject!
console.log(myObject.x);
console.log(myObject.y);
console.log(myObject.z);
```

To see a more complete example, look at the example
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-2-server/examples/server-stats/src/main/webapp/script.js">
  script.js
</walkthrough-editor-open-file>
file in the `server-stats` directory. The `getServerStats()` function calls
`fetch()`, parses the response as JSON, and then uses the object to build HTML
content.

## Your Turn

Now that you've learned more about JSON, you goal is to modify your servlet to
return a list of messages. The messages are up to you! They could be facts about
yourself, or your favorite movie quotes.

Then, modify your JavaScript to parse the response it gets from the server as
JSON. Then write JavaScript that takes the list it received from the server,
chooses a random message from that list, and then adds it to the page.

Remember that your goal this week is to create a servlet that stores comments,
and to write JavaScript that builds a UI from that data.

To break it down into smaller steps:

1.  Modify your servlet to contain an `ArrayList<String>` or array variable
    containing a few hard-coded messages. Keep it simple! Three hard-coded
    values is good enough for a start.
2.  Modify your servlet so its `doGet()` function returns the `ArrayList` or
    array as a JSON string. Confirm this works by deploying and
    navigating to your servlet's URL in your browser to view the response
    directly.
3.  Modify your JavaScript file to fetch the JSON list from the server. Confirm
    this works by printing it using the `console.log()` function.
4.  Modify your JavaScript to choose a random message and add it to the page.

Don't spend too much time making the page look pretty. You can always come back
after you have everything working.

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

## Pull Request

<walkthrough-conclusion-trophy></walkthrough-conclusion-trophy>

Congratulations!

At this point, your server should be returning a hard-coded list of messages,
and your JavaScript should be fetching this data and using it to add a random
message to the page.

Create a pull request from what you have so far, and send it to your advisor for
review.

Then continue to step 4:

```bash
teachme ~/software-product-sprint/walkthroughs/week-2-server/step-4-client-to-server-walkthrough.md
```
