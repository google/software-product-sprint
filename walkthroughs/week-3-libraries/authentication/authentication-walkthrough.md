# Authentication

## Getting Started

This walkthrough introduces the Google Users API, which lets users login to your
webapp.

You can return to this walkthrough anytime by running this command:

```bash
teachme ~/software-product-sprint/walkthroughs/week-3-libraries/authentication/authentication-walkthrough.md
```

Click the **Start** button to begin!

## The Goal

The goal of this project is to modify the comments feature you added last week,
so users must login before adding a comment.

This walkthrough introduces a lot of concepts, but you don't have to use all of
them. Decide what makes sense for your goal.

Don't be afraid to get creative, but focus on finishing a
[minimum viable product](https://en.wikipedia.org/wiki/Minimum_viable_product)
that proves you have all of the pieces connected, and then come back later and
add extra features if you have time left over.

## Authentication

At this point you can use App Engine to deploy servlets that respond to `GET`
and `POST` requests, and you can store that data in Datastore.

That works for simple cases where you only want to store anonymous data, but if
you want to associate data with a specific user, then you need to implement
**authentication**. In other words, you need a way for users to login.

There are many ways to implement authentication, but because you're using App
Engine, you can allow users to login to your site using their Google accounts.

## Users API

The [Users API](https://cloud.google.com/appengine/docs/standard/java/users/) is
included with App Engine and allows you to get login and logout links, as well
as the current user's email address. You can then use this email address to
associate data with a specific user.

The Users API handles registration and login, so you don't have to. You give the
user a link to Google's login page, and you can then ask Google's login page to
redirect back to your page after the user logs in.

When you deploy to a local dev server, the Users API will use a dummy sign-in
page that allows you to pretend to be any user. This is handy for testing your
code out locally before deploying it to your live server.

When you deploy to your live server, the Users API will use the real Google
sign-in page.

The next few steps walk through a few examples.

## Maven Dependency

The Users API comes with the App Engine environment, so to use it, first add the
App Engine dependency to your
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
Engine SDK, which includes the Users API.

## UserService

The `hello-world` directory contains an example project that uses the Users API
to output HTML based on the user's login status.

Read through the
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-3-libraries/authentication/examples/hello-world/src/main/java/com/google/sps/servlets/HomeServlet.java">
  HomeServlet.java
</walkthrough-editor-open-file>
file to see the Users API in action.

First, the code gets a reference to `UserService`:

```java
UserService userService = UserServiceFactory.getUserService();
```

It then checks whether the user is logged in:

```java
if (userService.isUserLoggedIn()) {
```

If so, the code gets the user's email address and a logout URL, and renders them
as HTML:

```java
String userEmail = userService.getCurrentUser().getEmail();
String urlToRedirectToAfterUserLogsOut = "/";
String logoutUrl = userService.createLogoutURL(urlToRedirectToAfterUserLogsOut);

response.getWriter().println("<p>Hello " + userEmail + "!</p>");
response.getWriter().println("<p>Logout <a href=\"" + logoutUrl + "\">here</a>.</p>");
```

If not, the code gets a login URL and renders it as HTML:

```java
String urlToRedirectToAfterUserLogsIn = "/";
String loginUrl = userService.createLoginURL(urlToRedirectToAfterUserLogsIn);

response.getWriter().println("<p>Login <a href=\"" + loginUrl + "\">here</a>.</p>");
```

Run a dev server in the `hello-world` directory to see how this works.

## Associating Data

To associate a set of data (for example a message sent by the user, or a file
uploaded by the user), you'd store the user ID alongside that data, for example
in a `HashMap` or in Datastore. Then to fetch the data, you'd query for the data
that contains the user ID.

The `shoutbox-v3` directory contains an example that takes this approach. The
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-3-libraries/authentication/examples/shoutbox-v3/src/main/java/com/google/sps/servlets/ShoutboxServlet.java">
  ShoutboxServlet.java
</walkthrough-editor-open-file>
file gets the user's email and adds it to the stored message:

```java
String email = userService.getCurrentUser().getEmail();
messageEntity.setProperty("email", email);
```

`cd` into the `shoutbox-v3` directory and run a dev server to see this in
action.

## Nicknames

You probably shouldn't display user email addresses in your site. Instead, you
might want to allow users to specify display names.

The `user-nicknames` directory contains an example that supports user
display names. The
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-3-libraries/authentication/examples/user-nicknames/src/main/java/com/google/sps/servlets/HomeServlet.java">
  HomeServlet.java
</walkthrough-editor-open-file>
file shows an example that prompts the user for a nickname before continuing.

The
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-3-libraries/authentication/examples/user-nicknames/src/main/java/com/google/sps/servlets/NicknameServlet.java">
  NicknameServlet.java
</walkthrough-editor-open-file>
file renders a form that allows logged-in users to specify a nickname and
handles the resulting POST request.

`cd` into the `user-nicknames` directory and run a dev server to see this in
action.

## Other Approaches

This walkthrough introduced the Users API, mostly because it's the easiest way
to implement authentication if you're using App Engine. But there are many other
ways to handle registration and login.

[OAuth](https://developers.google.com/identity/protocols/OAuth2) and
[OpenID](https://developers.google.com/identity/protocols/OpenIDConnect) both
allow users to login to your site using their accounts from other sites. For
example a user could use their Twitter account to identify themselves on
your site. This is pretty similar to what you saw above, but is not confined to
Google accounts.

## Your Turn

Your goal this week is to use the Users API to require users to login before
they can post a comment.

Try to break this goal down into smaller steps, and then take each step on
individually.

-   Add the App Engine dependency to your
    <walkthrough-editor-open-file
        filePath="software-product-sprint/portfolio/pom.xml">
      pom.xml
    </walkthrough-editor-open-file>
    file.
-   Add a servlet that returns the login status of the user.
    -   Test that this works by running a dev server and navigating to the
        servlet's URL.
    -   When you get this step working, create a pull request and send it to
        your advisor for review!
-   Hide the comments form by default. Modify your
    <walkthrough-editor-open-file
        filePath="software-product-sprint/walkthroughs/week-2-server/examples/random-quotes/src/main/webapp/script.js">
      script.js
    </walkthrough-editor-open-file>
    file to fetch the login status from the servlet. If the user is logged in,
    unhide the form. If the user is not logged in, display a login link.
    -   Test that this works by running a dev server and making sure your page
        shows the right content.
    -   When you get this step working, create a pull request and send it to
        your advisor for review!
-   Modify your
    <walkthrough-editor-open-file
        filePath="software-product-sprint/portfolio/src/main/java/com/google/sps/servlets/DataServlet.java">
      DataServlet.java
    </walkthrough-editor-open-file>
    file to get the current user's email address, and store it alongside the
    text of the comment.
-   Add the user's email address to the JSON returned by your comments servlet.
    -   Test that this works by running a dev server and navigating to the
        comments servlet's URL. You should see the JSON in the browser.
    -   When you get this step working, create a pull request and send it to
        your advisor for review!
-   Modify your JavaScript to show the email address of the user who posted each
    comment.
    -   When you get this step working, create a pull request and send it to
        your advisor for review!
-   (Optional) Add support for users specifying display names, so you no longer
    need to show email addresses.

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

Congratulations! You should now have a working login feature! Make sure your
[MVP](https://en.wikipedia.org/wiki/Minimum_viable_product) works and is
submitted to your GitHub repo before the end of the week.

When you're done, share your live URL in the chat!

To return to the libraries walkthrough, run this command:

```bash
teachme ~/software-product-sprint/walkthroughs/week-3-libraries/libraries-walkthrough.md
```
