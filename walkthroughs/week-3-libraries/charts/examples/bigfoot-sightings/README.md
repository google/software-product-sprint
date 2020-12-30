This project displays a chart that shows bigfoot sightings over time.

The data is stored in the `bigfoot-sightings-by-year.csv` file, but could also
come from Datastore or some other database. The data is loaded and formatted
into JSON in `BigfootDataServlet`, and `script.js` contains JavaScript that
fetches this data and adds it to a chart using the Google Charts API.

The bigfoot data came from
[here](https://datasetsearch.research.google.com/search?query=Bigfoot%20Sightings&docid=OPxC8uG4YXtz%2F68nAAAAAA%3D%3D)
which I found by searching on
[Google Dataset Search](https://toolbox.google.com/datasetsearch).

You can run this locally by executing this command:

```
mvn package exec:java
```
