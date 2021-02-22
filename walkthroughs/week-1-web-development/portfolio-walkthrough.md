# Portfolio Page

## Getting Started

This week, you'll use HTML to create a portfolio webpage, and you'll use App
Engine to deploy that webpage to a public URL.

You can return to this walkthrough anytime by running this command:

```bash
teachme ~/software-product-sprint/walkthroughs/week-1-web-development/portfolio-walkthrough.md
```

Click the **Start** button to begin!

## Directory Structure

You'll be working from the `portfolio` directory. Take a minute to look at the
files under that directory.

-   <walkthrough-editor-open-file
        filePath="software-product-sprint/portfolio/pom.xml">
      pom.xml
    </walkthrough-editor-open-file>
    is a Maven configuration file. This file sets up the libraries the project
    uses.
-   <walkthrough-editor-open-file
        filePath="software-product-sprint/portfolio/src/main/webapp/index.html">
      index.html
    </walkthrough-editor-open-file>
    is an HTML file that will be rendered by the browser.
-   <walkthrough-editor-open-file
        filePath="software-product-sprint/portfolio/src/main/webapp/script.js">
      script.js
    </walkthrough-editor-open-file>
    contains JavaScript that makes the page interactive.
-   <walkthrough-editor-open-file
        filePath="software-product-sprint/portfolio/src/main/webapp/style.css">
      style.css
    </walkthrough-editor-open-file>
    contains CSS that styles the page.
-   <walkthrough-editor-open-file
        filePath="software-product-sprint/portfolio/src/main/appengine/app.yaml">
      app.yaml
    </walkthrough-editor-open-file>
    is App Engine's configuration file. This file tells Google Cloud how to
    deploy your code, e.g. by using Java 11.
-   <walkthrough-editor-open-file
        filePath="software-product-sprint/portfolio/src/main/java/com/google/sps/ServerMain.java">
      ServerMain.java
    </walkthrough-editor-open-file>
    is a Java class that contains a `main()` method that runs a web server. The
    web server handles *requests* to specific URLs by *responding* with content
    or a file. You'll most likely never have to change the `ServerMain` file,
    but if you're curious about how the server runs, start by looking here!

Try navigating to the various files in this project and viewing their contents
in the Google Cloud Shell editor.

You'll learn more about these files in the following steps.

## Run a Development Server

This project is a web application that contains HTML files. To make that HTML
available to your web browser, you need to run a server that hosts the files.

To run a development server, first navigate to the `portfolio` directory:

```bash
cd ~/software-product-sprint/portfolio
```

Then execute this command:

```bash
mvn package exec:java
```

This command tells Maven to run the `main()` method in the `ServerMain` class,
which runs a local copy of the server. This is useful for testing changes before
deploying your site publicly. You'll run this command a lot over the next few
weeks!

The first time you run this command, Maven will automatically download all of
the libraries and files required to run a server, so it might take a few
minutes. When the command completes, you'll see this in the console:

**Server started!**

Now that you have a server running, your browser can send requests to it. Click
the <walkthrough-web-preview-icon></walkthrough-web-preview-icon> icon and then
select **Preview on port 8080** to open a tab that displays the `index.html`
file.

Your new tab should show an example portfolio. You'll start customizing this in
the next step!

## index.html

Open the
<walkthrough-editor-open-file
    filePath="software-product-sprint/portfolio/src/main/webapp/index.html">
  index.html
</walkthrough-editor-open-file>
file in the Google Cloud Shell editor.

This file contains **HTML tags** that tell a browser how to render text. For
example, the `<h1>` tag renders as a heading, and the `<p>` tag renders as a
paragraph.

Modify the text in this file and save your changes. Try changing the heading
to include your name.

### Restarting Your Development Server

**Tip:** Pressing `ctrl + c` in the console will interrupt any process running
in the foreground, which will usually cause the program to abort.

Whenever you change your code, you need to restart your server to see your
changes. Press `ctrl + c` in the console to shut down your server, and then run
this command again:

```bash
mvn package exec:java
```

**Tip:** You can press the up arrow key to cycle through previous commands
instead of retyping them.

After your server is running again, click the
<walkthrough-web-preview-icon></walkthrough-web-preview-icon> icon and then
select `Preview on port 8080` to see your changes. Get in the habit of rerunning
your dev server to test your changes often!

**Note:** If you don't see your changes after you refresh, your browser might be
caching an old version. Follow the instructions
[here](https://en.wikipedia.org/wiki/Wikipedia:Bypass_your_cache) to execute a
cache-clearing refresh.

## Example

The `examples/stanley` directory contains an example webpage. It contains
several files and directories:

-   <walkthrough-editor-open-file
        filePath="software-product-sprint/walkthroughs/week-1-web-development/examples/stanley/pom.xml">
      pom.xml
    </walkthrough-editor-open-file>
    is Maven's configuration file.
-   <walkthrough-editor-open-file
        filePath="software-product-sprint/walkthroughs/week-1-web-development/examples/stanley/src/main/webapp/index.html">
      index.html
    </walkthrough-editor-open-file>
    uses HTML tags to create a homepage that links to other pages.
-   <walkthrough-editor-open-file
        filePath="software-product-sprint/walkthroughs/week-1-web-development/examples/stanley/src/main/webapp/images.html">
      images.html
    </walkthrough-editor-open-file>
    uses HTML tags to create an image gallery.
-   <walkthrough-editor-open-file
        filePath="software-product-sprint/walkthroughs/week-1-web-development/examples/stanley/src/main/webapp/random.html">
      random.html
    </walkthrough-editor-open-file>
    loads JavaScript that shows a random image from the gallery.
-   <walkthrough-editor-open-file
        filePath="software-product-sprint/walkthroughs/week-1-web-development/examples/stanley/src/main/webapp/script.js">
      script.js
    </walkthrough-editor-open-file>
    is JavaScript that defines a function that shows a random image.
-   `images/` contains the images used in the above files.
-   <walkthrough-editor-open-file
        filePath="software-product-sprint/walkthroughs/week-1-web-development/examples/stanley/src/main/webapp/style.css">
      style.css
    </walkthrough-editor-open-file>
    is a CSS file that defines the styling for the HTML content.
-   `/WEB-INF` contains files that visitors to the site can't navigate to.
-   <walkthrough-editor-open-file
        filePath="software-product-sprint/walkthroughs/week-1-web-development/examples/stanley/src/main/java/com/google/sps/ServerMain.java">
      ServerMain.java
    </walkthrough-editor-open-file>
    is the main Java class that that runs a web server.

Deploy the example webpage by `cd`-ing into the
`~/software-product-sprint/walkthroughs/week-1-web-development/examples/stanley`
directory and then running a dev server:

```bash
mvn package exec:java
```

Then look through the files in the `stanley` project to see an example of HTML,
JavaScript, and CSS in action.

**Remember:** If you don't see changes after you restart your server, you might
need a
[cache-clearing refresh](https://en.wikipedia.org/wiki/Wikipedia:Bypass_your_cache).

When you're done looking at the example, `cd` back into the `portfolio`
directory.

## HTML tags

If you're new to HTML, that's okay! Here are a few place to learn more:

-   [W3Schools](https://www.w3schools.com/html/default.asp)
-   [MDN](https://developer.mozilla.org/en-US/docs/Learn/Getting_started_with_the_web/HTML_basics)
-   [Google's HTML Style Guide](https://google.github.io/styleguide/htmlcssguide.html)

Don't forget that Google is your friend! For example, try searching "html list
tag" for a ton of useful results about adding lists to HTML.

For now, edit your
<walkthrough-editor-open-file
    filePath="software-product-sprint/portfolio/src/main/webapp/index.html">
  index.html
</walkthrough-editor-open-file>
file to contain a `<p>` element that introduces you. Then run a dev server and
confirm that you can see your change.

## Uploading Images

Static files like images are stored in the project's `webapp` directory. If you
want to add images to your portfolio, create a new `images` directory inside the
`webapp` directory. To create a new directory, right click on the `webapp`
directory. Then select "New Folder".

Once you've created the `images` directory, right-click it and select
`Upload Files...` to select an image from your computer.

To reference an image in your `images` directory in HTML, use a URL that starts
with a `/` forward slash, like this:

```html
<img src="/images/cat.jpg" />
```

This URL will work on a development server and when you deploy to a live URL.

Modify your
<walkthrough-editor-open-file
    filePath="software-product-sprint/portfolio/src/main/webapp/index.html">
  index.html
</walkthrough-editor-open-file>
file to include an image. It can be a selfie, or an avatar, or a picture of
your pet. This is your portfolio, so make it your own!

## CSS

You can use Cascading Style Sheets, or CSS, to style your HTML with formatting
and colors. The
<walkthrough-editor-open-file
    filePath="software-product-sprint/portfolio/src/main/webapp/style.css">
  style.css
</walkthrough-editor-open-file>
file contains some default styling.

Learning CSS is **optional** for SPS. You can skip this step if you want. But
if you're curious, here are a few places to learn more:

-   [W3Schools](https://www.w3schools.com/css/)
-   [MDN](https://developer.mozilla.org/en-US/docs/Web/CSS)

If you're interested in CSS, feel free to explore it further. But make sure you
aren't spending most of your time on styling. You can always make your projects
pretty after you get their core functionality working!

## JavaScript

You can use JavaScript to make your page interactive.

Open the
<walkthrough-editor-open-file
    filePath="software-product-sprint/portfolio/src/main/webapp/script.js">
  script.js
</walkthrough-editor-open-file>
file to see a function that shows a random greeting.

Change this to show a random quote from your favorite TV show, or a random fact
about yourself.

### JavaScript Console

Your browser's JavaScript console will show anything you print out using the
`console.log()` function, as well as any errors. **This should be the first
place you look when you have a problem.** It's a good idea to always have your
JavaScript console open, so you see errors right away.

### Learn More

You can learn more about JavaScript at
[W3Schools](https://www.w3schools.com/js/default.asp) and
[MDN](https://developer.mozilla.org/en-US/docs/Web/JavaScript), or by googling
"JavaScript tutorials".

## Your Turn

Your goal this week is to customize your portfolio page. What that means is up
to you, but here are a few ideas to get you started:

-   Add a couple paragraphs explaining your background and what makes you you.
-   Add a list of projects you've worked on.
-   Add a link to your LinkedIn and GitHub profiles.
-   Add images! Create a gallery of your favorite places, or selfies, or
    pictures of your pets.
-   Add a couple paragraphs explaining what you enjoy photographing.
-   Add some blog posts explaining one of your hidden talents.

This is **your** portfolio page, so make it your own!

Don't be afraid to get creative, but focus on finishing a
[minimum viable product](https://en.wikipedia.org/wiki/Minimum_viable_product)
that proves you have all of the pieces connected, and then come back later and
add extra features if you have time left over.

## Development Workflow

As you make changes, make sure you're testing them using your dev server. You
should test your changes in small increments: it's not uncommon to test your
changes after every few lines of code!

That might seem like overkill, but it's much better to detect bugs right away.
If you wait until the end to test, chances are you'll face many bugs, and it
will be difficult to debug any of them. Get into the habit of testing often now!

Remember, to run a dev server, you `cd` into a directory that contains a
`pom.xml` file, and then you execute this command:

```bash
mvn package exec:java
```

When you see `Dev App Server is now running` in the console, click the
<walkthrough-web-preview-icon></walkthrough-web-preview-icon> icon and then
select **Preview on port 8080** to open a tab that shows your page.

After you've added a working feature to your project, it's a good idea to
commit your changes. It's easier for reviewers to understand many small
commits instead of a few large ones.

A good commit message is a summary of what a change _does_, not what was _done_.
Use the imperative tone:

```
# Good
Add selfie image to main page.
```

```
# Bad
Added selfie image to main page.
```

**Remember:** to commit, you have to stage the changed files, then commit it.

Replace FILEPATH with the path/to/the/changed/file. To stage a file, run:

```
git add FILEPATH
```

You can stage multiple files at once.

To check the staged files, run:

```
git status
```

To commit changes, run:

```
git commit -m "YOUR COMMIT MESSAGE"
```

## App Engine

You're about to deploy your code to your live site, but first you need to
create an App Engine Application so Google Cloud can run your server.

-   Navigate to
    [https://console.cloud.google.com/appengine](https://console.cloud.google.com/appengine).
-   Make sure your project is selected in the dropdown at the top.
-   Click the `Create Application` button.
-   On the next screen, click `Create app`. The default region is fine.
-   On the next screen, select `Java` as a language and `Standard` as an
    environment.
-   When you see `Your App Engine app has successfully been created`, you're
    done!

Now when you navigate to
[https://console.cloud.google.com/appengine](https://console.cloud.google.com/appengine),
you should see `Your App Engine application has been created`.

## Service Account Permissions

Google Cloud uses service accounts to do certain things. To use App Engine,
you need to enable App Engine for your service account. 

-   Navigate to
    [https://console.cloud.google.com/cloud-build/settings/service-account](https://console.cloud.google.com/cloud-build/settings/service-account).
-   Make sure your project is selected in the dropdown at the top.
-   Change the `App Engine` service to `Enabled`.
-   If a dialog opens, click the `Skip` button to close it.

The setting saves automatically, so you can close that tab before continuing.

## Live Server

When you're happy with your portfolio page and you're ready to show it to the
world, you can deploy it to a live server!

To deploy to a live server:

-   Navigate to
    [https://console.cloud.google.com/home/dashboard](https://console.cloud.google.com/home/dashboard).
-   Make sure your project is selected in the dropdown at the top.
-   Find the **Project ID** on that page. (It should be something like lknope-sps-seasonYY)
-   Open the
    <walkthrough-editor-open-file
        filePath="software-product-sprint/portfolio/pom.xml">
      pom.xml
    </walkthrough-editor-open-file>
    file.
-   Change `YOUR_PROJECT_ID_HERE` to your project ID.
-   Execute this command:

```bash
mvn package appengine:deploy
```

If you see an error that says `The current Google Cloud project does
not contain an App Engine application`, then make sure you created
an App Engine application in the previous step!

After the command successfully completes, you can now navigate to
`YOUR_PROJECT_ID.appspot.com` to see your portfolio.

When you're done, share this link in the chat and with your team!

## Pull Requests

Your advisor will be reviewing your code to provide feedback and support. If
you haven't already, create a pull request from your code so your advisor
can review it.

You don't have to wait until you're finished to send your code for review. In
fact, it's better if you send multiple small pull requests instead of one big
one!

Make sure your code is reviewed and merged into your repo before the end of the
week.

## Congratulations!

<walkthrough-conclusion-trophy></walkthrough-conclusion-trophy>

Congratulations, you now have a portfolio page deployed to a live URL! Make sure
you share your URL with your team and in the chat.

If you have extra time left over, consider adding a page that describes your
experiences with SPS so far, or writing JavaScript that makes your page
interactive, or using CSS to make your page more beautiful.

Next week, you'll learn about server-side code and add a "contact me" feature to
your portfolio.

When you're ready, start the next walkthrough by running this command:

```bash
teachme ~/software-product-sprint/walkthroughs/week-2-server/project-1-walkthrough.md
```

