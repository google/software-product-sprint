# Google Maps

## Getting Started

This walkthrough introduces the Google Maps library, which lets you add an
interactive Google Map to your page.

You can return to this walkthrough anytime by running this command:

```bash
teachme ~/software-product-sprint/walkthroughs/week-3-libraries/maps/maps-walkthrough.md
```

Click the **Start** button to begin!

## The Goal

The goal of this project is to add a page that shows a Google Map to your
portfolio. What you do with that map is up to you! You could show a map of your
favorite restaurants, or of bigfoot sightings, or of local homeless shelters.
You could make your map interactive, by letting users specify their hometowns
or share the best places to get avocado toast.

This walkthrough introduces a lot of concepts, but you don't have to use all of
them. Decide what makes sense for your goal.

Focus on finishing a
[minimum viable product](https://en.wikipedia.org/wiki/Minimum_viable_product)
that proves you have all of the pieces connected, and then come back later and
add extra features if you have time left over.

## API Keys

The Google Maps library requires an API key, which is a special string that
tells the library which project is making the request. So before you can start
writing code, you need to obtain an API key.

Follow
[these steps](https://developers.google.com/maps/documentation/javascript/get-api-key)
to get an API key.

### Restricting Keys

In a production environment, you'd prevent random people from using your API key
by restricting it so it only works from your live URL. This means you'd have two
API keys: one for testing locally, and one for your live site.

For SPS, it's fine to only have a single unrestricted API key that you check
into your repo and use both locally and on your live site.

But if you want to explore how you'd use two keys, here is how you could
approach that:

1. Create two keys using the above link. Restrict one of them to your live site,
   and leave the other unrestricted.
2. Create an `env_variables.yaml` file. Do NOT check this into your repo! Add it
   to your `.gitignore` file to make sure it never gets added.
3. In your `env_variables.yaml` file, add this content:

```yaml
env_variables:
  GOOGLE_MAPS_API_KEY: YOUR_RESTRICTED_API_KEY_HERE
```

(Make sure you substitute in your real key!)

4. In your `app.yaml` file, add this content:

```yaml
includes:
  - /path/to/your/env_variables.yaml
```

5. Now your live server will contain an environment variable that points to your
   restricted API key. Next, add a local environment variable that points to
   your unrestricted API key:

```bash
export GOOGLE_MAPS_API_KEY="YOUR_UNRESTRICTED_API_KEY_HERE"
```

6. Create a servlet that reads the `GOOGLE_MAPS_API_KEY` environment variable
   and writes it to the response. On your live server, this will be your
   restricted key, and on your local server, it will be your unrestricted key!
7. Create client-side code that fetches the API key from the server and loads
   the Google Maps JavaScript library using that key.

(You can combine steps 6 and 7 into a single step if you use JSP!)

Again, this is all optional. For now, using a single unrestricted key is fine.

## Hello World

Read through
[this page](https://developers.google.com/maps/documentation/javascript/tutorial)
to learn how to add a Google Map to your page.
[Here](https://developers-dot-devsite-v2-prod.appspot.com/maps/documentation/javascript/examples/map-sync)
is another example.

The `examples` directory contains a `hello-world` project. Modify its
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-3-libraries/maps/examples/hello-world/src/main/webapp/index.html">
  index.html
</walkthrough-editor-open-file>
file to include your API key where it currently contains `YOUR_API_KEY`. Then
`cd` into the `hello-world` directory and run a dev server:

```bash
mvn package exec:java
```

You should see a webpage that shows a Google Map.

Read through the
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-3-libraries/maps/examples/hello-world/src/main/webapp/index.html">
  index.html
</walkthrough-editor-open-file>
and
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-3-libraries/maps/examples/hello-world/src/main/webapp/script.js">
  script.js
</walkthrough-editor-open-file>
files to see how they add a Google Map to the page.

### Your Turn

Modify your portfolio to include a Google Map. Don't worry about adding
features like markers or interactivity yet! Focus on getting the map to show
before moving on to more advanced functionality.

### Pull Request

When you have a Google Map showing in your portfolio, create a pull request and
send it to your advisor for a code review. Try to keep code reviews as short as
possible!

## Customizing Your Map

You can change what your map looks like by specifying its center and zoom level.
You can also modify its styling, or change how it interacts with the user.

To learn about these options, read through this documentation:

-   [MapOptions](https://developers.google.com/maps/documentation/javascript/reference/map#MapOptions)
-   [Map Types](https://developers.google.com/maps/documentation/javascript/maptypes)
-   [Styling Your Map](https://developers.google.com/maps/documentation/javascript/styling)

Customize your map so it looks and behaves the way you want!

## Markers

The Google Maps library supports adding **markers** which are pins that show
specific locations. To learn about markers, read through this documentation:

-   [Adding a Google Map with a Marker to Your Website](https://developers.google.com/maps/documentation/javascript/adding-a-google-map)
-   [Markers](https://developers.google.com/maps/documentation/javascript/markers)
-   [Custom Markers](https://developers.google.com/maps/documentation/javascript/custom-markers)

The `examples` directory contains a `marker` example to help you get started.
The
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-3-libraries/maps/examples/marker/src/main/webapp/script.js">
  script.js
</walkthrough-editor-open-file>
file contains code that adds a marker to a Google Map.

If you want to show specific locations, then consider adding markers to the map
in your portfolio page. For example, you could add markers that show your
favorite places in your hometown.

### Pull Request

If you chose to add markers to your map, then commit what you have so far and
send it as a pull request to your advisor for code review!

## Info Windows

If you want to show more information about a location on a Google Map, you can
use an **info window**. You can show an info window directly on the map, or you
can show an info window when a user clicks a marker.

Read
[this documentation](https://developers.google.com/maps/documentation/javascript/infowindows)
to learn about info windows.

The `examples` directory contains an `info-window` project to help you get
started, and a `google-tour` project that uses markers and info windows.

If you want to show more information on your map, then consider using info
windows. For example, if your map shows markers on local dog parks, clicking a
marker could show an info window that contains an image of your dog at that
park.

You could also use a
[marker click listener](https://developers.google.com/maps/documentation/javascript/events#MarkerEvents)
to detect user interaction, and then use DOM manipulation (like you learned
about in week 1) to show more information in the page next to the map. It's up
to you!

### Pull Request

If you chose to add info windows to your map, then commit what you have so far
and send it as a pull request to your advisor for code review!

## Server Code

So far, all of the examples have been strictly client-side, requiring only HTML
and JavaScript, but no server-side Java code. If you want to show a hard-coded
list of markers on the map, that's okay!

However, you can use the concepts you learned last week to add server-side
processing that interacts with your map.

For example, the `ufos` example project uses server-side Java code to convert a
CSV file into JSON, which the client then fetches and uses to build a map that
visualizes UFO sightings.

The `marker-storage` example uses POST requests to allow users to add markers to
a map.

If you want to visualize data from a CSV, or if you want your map to be
interactive, consider adding server code that supports this functionality.

### Pull Requests

If you choose to add server code to your map, then commit what you have and send
it as a pull request to your advisor for code review! You might also consider
breaking the server code into smaller commits.

For example, you might first create a servlet that returns some hard-coded JSON
data, send that out for code review, and then build on that to read from a CSV
file. Remember: try to keep each pull request as small as possible!

## Data Visualization

In addition to markers and info windows, Google Maps supports different kinds of
visualizations. Read through this documentation to learn more:

-   [Visualizing Data](https://developers.google.com/maps/documentation/javascript/earthquakes)
-   [Importing Data into Google Maps](https://developers.google.com/maps/documentation/javascript/importing_data)
-   [Combining Data](https://developers.google.com/maps/documentation/javascript/combining-data)
-   [Shapes](https://developers.google.com/maps/documentation/javascript/shapes)
-   [Displaying data](https://developers.google.com/maps/documentation/javascript/layers)

You could also use
[Google Dataset Search](https://toolbox.google.com/datasetsearch) to find
interesting data sets to visualize. For example, you could create a
visualization from
[bigfoot sightings data](https://toolbox.google.com/datasetsearch/search?query=Bigfoot%20Sightings).

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

Congratulations! You should now have a working map feature! Make sure your
[MVP](https://en.wikipedia.org/wiki/Minimum_viable_product) works and is
submitted to your GitHub repo by the end of the week.

When you're done, share your live URL in the chat!

To return to the libraries walkthrough, run this command:

```bash
teachme ~/software-product-sprint/walkthroughs/week-3-libraries/libraries-walkthrough.md
```
