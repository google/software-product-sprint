# Google Charts

## Getting Started

This walkthrough introduces the Google Charts library, which lets you add an
interactive chart to your page.

You can return to this walkthrough anytime by running this command:

```bash
teachme ~/software-product-sprint/walkthroughs/week-3-libraries/charts/charts-walkthrough.md
```

Click the **Start** button to begin!

## The Goal

The goal of this project is to add a page that shows a chart to your portfolio.
What you do with that chart is up to you! You could show a chart of how many
steps you walk each day, or of global temperatures, or of how often your
favorite musician uses certain words. Those are just examples- you should
create something that's meaningful to you.

This walkthrough introduces a lot of concepts, but you don't have to use all of
them. Decide what makes sense for your goal.

Focus on finishing a
[minimum viable product](https://en.wikipedia.org/wiki/Minimum_viable_product)
that proves you have all of the pieces connected, and then come back later and
add extra features if you have time left over.

## Hello World

Read through
[the documentation](https://developers.google.com/chart/interactive/docs) to
understand how the Google Charts library works.

The `examples` directory contains a `hello-world` project. You can `cd` into
the `hello-world` directory and run a dev server:

```bash
mvn package exec:java
```

You should see a webpage that shows a pie chart.

Read through the
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-3-libraries/charts/examples/hello-world/src/main/webapp/index.html">
  index.html
</walkthrough-editor-open-file>
and
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-3-libraries/charts/examples/hello-world/src/main/webapp/script.js">
  script.js
</walkthrough-editor-open-file>
files to see how they add the chart to the page.

### Your Turn

Modify your portfolio to include a chart. Don't worry about making it perfect
yet! Keep it simple for now. Use hard-coded data, and focus on getting the
chart to show before moving on to more advanced functionality.

### Pull Request

When you have a chart showing in your portfolio, create a pull request and send
it to your advisor for a code review. Try to keep code reviews as short as
possible!

## Chart Types

The Google Charts library offers many kinds of charts. The
[chart gallery](https://developers.google.com/chart/interactive/docs/gallery)
page shows some examples. For example,
[this page](https://developers.google.com/chart/interactive/docs/gallery/barchart)
shows how you'd create a bar chart.

Decide what kind of chart makes the most sense for your data, then modify your
HTML and JavaScript to create that type of chart. Then create a pull request
and send it to your advisor for review.

## Server Data

So far, the examples have used hard-coded data in JavaScript. If that's enough
for the data you want to display, that's okay! But you can also use server
code to load more complex data, for example from a CSV file.

The `bigfoot-sightings` directory contains an example that loads yearly bigfoot
sightings from a CSV file and uses it to create a chart. You can `cd` into the
`bigfoot-sightings` directory and then run a dev server:

```bash
mvn package exec:java
```

When you view your dev server in your browser, you should see a line chart that
shows bigfoot sightings over time.

The
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-3-libraries/charts/examples/bigfoot-sightings/src/main/java/com/google/sps/servlets/BigfootDataServlet.java">
  BigfootDataServlet.java
</walkthrough-editor-open-file>
file loads the CSV file and converts it into JSON. Then the
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-3-libraries/charts/examples/bigfoot-sightings/src/main/webapp/script.js">
  script.js
</walkthrough-editor-open-file>
file fetches this JSON and uses it to create a chart.

### Finding Data

You can use [Google Dataset Search](https://datasetsearch.research.google.com/)
to find interesting data to visualize.

You'll probably need to "massage" the data by converting it into a format you
can use. For example, the bigfoot sightings CSV was created from
[this data](https://data.world/timothyrenner/bfro-sightings-data) by loading it
into Google Sheets and converting it to a table of yearly sightings.

### Pull Request

If you choose to load data from your server, then submit your code as a pull
request and send it to your advisor for review!

## Interactive Data

You can also combine what you learned last week with the charts library to
create a visualization from user-submitted data.

The `favorite-colors` directory contains an example that allows users to vote
for their favorite color. The votes are then visualized in a Google Chart.

You can `cd` into the
`favorite-colors` directory and then run a dev server:

```bash
mvn package exec:java
```

When you view your dev server in your browser, you should see a webapp that
shows a chart of color votes.

The
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-3-libraries/charts/examples/favorite-colors/src/main/java/com/google/sps/servlets/ColorDataServlet.java">
  ColorDataServlet.java
</walkthrough-editor-open-file>
file contains a `doGet()` function that returns the color votes as JSON, and a
`doPost()` function that allows users to submit votes.

The
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-3-libraries/charts/examples/favorite-colors/src/main/webapp/script.js">
  script.js
</walkthrough-editor-open-file>
file fetches this JSON and uses it to create a chart.

**Note:** This example uses in-memory data structures, but if you want your data
to persist when your server restarts, you should check out the Datastore
library!

Consider creating a chart from user-submitted data. To get started, you might
create a line chart of how often users contact you over time, or you could let
users vote on what kind of project you work on next.

### Pull Request

If you choose to visualize user data, then submit your code as a pull request
and send it to your advisor for review!

## Live Server

When you're happy with your feature and you're ready to show it to the world,
deploy it to your live server!

Your
<walkthrough-editor-open-file
    filePath="software-product-sprint/portfolio/pom.xml">
  pom.xml
</walkthrough-editor-open-file>
file should already contain your project ID. If so, you can deploy to your live
server by executing this command:

```bash
mvn package appengine:deploy
```

After the command successfully completes, you can navigate to
`YOUR_PROJECT_ID.appspot.com` to see your portfolio and test your feature.

When you're done, share the link in the chat and with your team!

## Pull Requests

<walkthrough-conclusion-trophy></walkthrough-conclusion-trophy>

Congratulations! You should now have a working chart feature! Make sure your
[MVP](https://en.wikipedia.org/wiki/Minimum_viable_product) works and is
submitted to your GitHub repo by the end of the week.

When you're done, share your live URL in the chat!

To return to the libraries walkthrough, run this command:

```bash
teachme ~/software-product-sprint/walkthroughs/week-3-libraries/libraries-walkthrough.md
```
