# Datastore

## Getting Started

This walkthrough introduces Datastore, which lets you store data permanently.

You can return to this walkthrough anytime by running this command:

```bash
teachme ~/software-product-sprint/walkthroughs/week-2-server/step-5-datastore-walkthrough.md
```

Click the **Start** button to begin!

## The Goal

The goal of this project is to use Datastore to permanently store the messages
you implemented in last week's "contact me" project. You'll store the messages
in Datastore, and then create a page that displays them. This will be a lot
more convenient than searching through the logs!

This walkthrough introduces a lot of concepts, but you don't have to use all of
them. Decide what makes sense for your goal.

Don't be afraid to get creative, but focus on finishing a
[minimum viable product](https://en.wikipedia.org/wiki/Minimum_viable_product)
that proves you have all of the pieces connected, and then come back later and
add extra features if you have time left over.

## Persistent Storage

You might try to use a data structure like an `ArrayList` to store data in a
servlet. But what happens if you have more data than fits in memory? What
happens when your server restarts, or when App Engine automatically scales your
server up or down?

The answer is that your data will be erased. This won't work for data that you
want to store permanently.

The solution to this problem is to use **persistent storage** to store the data
somewhere safer. You might use a database, or file storage.

[Datastore](https://cloud.google.com/appengine/docs/standard/java11/using-cloud-datastore)
is a [NoSQL](https://en.wikipedia.org/wiki/NoSQL) database that lets you store
and load data using Java code.

The next few steps introduce Datastore through an example that stores tasks in a
todo list.

## Example

The `todo-list` directory contains an example project that uses Datastore to
store tasks posted by the user. The next few steps will walk you through this
example project.

### Running Datastore Locally

By default, Datastore will try to connect to the database for your live site.
This is probably not what you want if you're testing your code locally. Instead,
you should run a local version of Datastore and tell your local code to connect
to that instead.

First, `cd` into the `todo-list` directory, and then start by running this
command:

```bash
gcloud beta emulators datastore start
```

In the output of that command, Find the line that looks like this:

```
Storage: /tmp/tmp.YOUR_PATH_HERE/emulators/datastore/WEB-INF/appengine-generated/local_db.bin
```

The `YOUR_PATH_HERE` part will be a random string of characters.

Copy the `/tmp/tmp.YOUR_PATH_HERE/emulators/datastore` path, not including the
`WEB-INF` part or anything after it.

In a **new console**, run this command, pasting in the path you copied from the
first console:

```bash
$(gcloud beta emulators datastore env-init --data-dir=/tmp/tmp.YOUR_PATH_HERE/emulators/datastore)
```

Then in that second console window, run your local server:

```bash
mvn package exec:java
```

**Note:** If you get a stack trace about authentication, you likely need to run
the above commands again!

## Maven Dependency

To use Datastore, first add the Datastore dependency to your
<walkthrough-editor-open-file
    filePath="software-product-sprint/portfolio/pom.xml">
  pom.xml
</walkthrough-editor-open-file>
file:

```xml
<dependency>
  <groupId>com.google.cloud</groupId>
  <artifactId>google-cloud-datastore</artifactId>
  <version>1.104.0</version>
</dependency>
```

This dependency lets you reference the classes that come with the Google Cloud
Datastore library.

## Datastore

Open the
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-2-server/examples/todo-list/src/main/java/com/google/sps/servlets/NewTaskServlet.java">
  NewTaskServlet.java
</walkthrough-editor-open-file>
file in the `todo-list` directory to see how it uses Datastore to save a task.

To use Datastore, you first need an instance of the `Datastore` class.
You can call the `DatastoreOptions.getDefaultInstance().getService()` function:

```java
Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
```

Now you can use this `datastore` variable to interact with Datastore.

You can read more about the `Datastore` class
[here](https://googleapis.dev/java/google-cloud-datastore/1.105.3/com/google/cloud/datastore/Datastore.html).

## Entities

Data in Datastore is represented by **entities**. You can think of an entity
similar to how you think about an instance of a class in Java. Each entity has
an associated key.

A key has a **kind**, which is similar to a class name.

Look at the `doPost()` function in the
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-2-server/examples/todo-list/src/main/java/com/google/sps/servlets/NewTaskServlet.java">
  NewTaskServlet.java
</walkthrough-editor-open-file>
file to see how it creates an key by giving it a *kind* of `Task`:

```java
KeyFactory keyFactory = datastore.newKeyFactory().setKind("Task");
```

This line of code creates an `KeyFactory` with a *kind* of `Task` and stores it in a
`keyFactory` variable.

An entity also has **properties**, similar to how a class can have variables.
Each property is a **key** and a **value**.

To create an entity, you give it  key and then set its properties by calling
the `set()` function:

```java
FullEntity taskEntity =
    Entity.newBuilder(keyFactory.newKey())
        .set("title", title)
        .set("timestamp", timestamp)
        .build();
```

This code adds two properties to the `taskEntity` entity: `title` and
`timestamp`. Property values can be strings, numbers, or timestamps. See
[this guide](https://cloud.google.com/datastore/docs/concepts/entities#datastore-datastore-upsert-java)
to learn more about the types of values you can store in an entity.

### Storing Entities

Now that you have a `datastore` variable and a `taskEntity` variable, you can
store the entity by passing it into the `datastore.put()` function:

```java
datastore.put(taskEntity);
```

This function stores `taskEntity` in Datastore so that you can load it next time
you need it.

## Your Turn

Modify your servlet file to take the data entered by users in your "contact me"
form and store it as an entity in Datastore. Get this working before
you worry about loading the data.

### Admin Page

Datastore includes an admin page that gives you access to its data. This is very
useful for debugging and managing your data.

To view the admin page for your live server, go to
[https://console.cloud.google.com/datastore](https://console.cloud.google.com/datastore).

Use the admin page to confirm that your data is stored correctly. After you get
this step working, create a pull request and send it to your advisor for
review!

## Loading Entities

Now that you have data stored in Datastore, you can load it whenever a user
requests it.

The `todo-list` example contains a
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-2-server/examples/todo-list/src/main/java/com/google/sps/servlets/ListTasksServlet.java">
  ListTasksServlet.java
</walkthrough-editor-open-file>
file that loads entities from Datastore.

First create a `Query` instance with the kind of entity you want to load, then
pass that `Query` into the `datastore.run()` function, which gives you a
`QueryResults` instance that contains all of the entities in Datastore with
that kind. To loop iterate those entities, call the `hasNext()` and `next()` functions.

```java
Query<Entity> query =
        Query.newEntityQueryBuilder().setKind("Task").setOrderBy(OrderBy.desc("timestamp")).build();
QueryResults<Entity> results = datastore.run(query);

while (results.hasNext()) {
  Entity entity = results.next();
...
```

Then inside this loop, you can use the `entity.get*()` functions to get
the properties that were set on each entity when it was stored in Datastore.

By storing entities when the user creates them and loading them when you need to
use them again, you can use Datastore as persistent storage even when your web
app is shut down or restarted.

## Your Turn

Next, add some code to your servlet class that loads the "contact me" data from
Datastore. You can use Gson to format the data as JSON, and then you can use
JavaScript to fetch that JSON, and parse it to build the HTML of a page that
lists all of the messages you've received.

Test that your code works by running a local dev server, adding a few messages,
and then restarting your server. Your messages should remain when you refresh
the page, and even when you restart your server!

### Sanitizing User Input

If you're taking user input and showing it as HTML, you need to make sure you're
properly handling cases like users entering HTML tags or JavaScript in their
input. Not handling this can lead to security issues like cross-site scripting
(XSS).

The todo list example uses a library called [jSoup](https://jsoup.org/) to
sanitize user input in the `NewTaskServlet.java` file:

```java
String title = Jsoup.clean(request.getParameter("title"), Whitelist.none());
```

This line of code takes the data entered by the user and removes any HTML or
JavaScript content from it. Now it's safe to show in the content of an HTML
page.

You should also consider hiding personal data like email addresses.

## Pull Requests

<walkthrough-conclusion-trophy></walkthrough-conclusion-trophy>

Congratulations! Your portfolio page should now support messages that are
permanently stored in Datastore. Make sure your
[MVP](https://en.wikipedia.org/wiki/Minimum_viable_product) works and is
submitted to your GitHub repo by the end of the week.

When you're done, share your live URL in the chat!

To return to the libraries walkthrough, run this command:

```bash
teachme ~/software-product-sprint/walkthroughs/week-3-libraries/libraries-walkthrough.md
```

### Learn More

This tutorial covered the fundamentals of how to use Datastore, but Datastore
also provides more advanced functionality. See these resources for more info:

-   [Datastore documentation](https://cloud.google.com/datastore/docs/)
-   [Entities documentation](https://cloud.google.com/datastore/docs/concepts/entities)
-   [Datastore API](https://googleapis.dev/java/google-cloud-datastore/1.105.3/index.html?com/google/cloud/datastore/Datastore.html)
