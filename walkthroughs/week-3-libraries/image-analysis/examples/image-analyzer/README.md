This project uses the Google Cloud Vision API in a servlet to display a list of
labels for an image uploaded by the user.

![image upload form](screenshot-1.png)

![image labels webpage](screenshot-2.png)

To run this example, first make sure your `GOOGLE_APPLICATION_CREDENTIALS`
environment variable is set and that you've enabled the
[Vision API](https://console.cloud.google.com/apis/library/vision.googleapis.com),
and then execute this command:

```bash
mvn package appengine:run
```

Then open a web browser to `http://localhost:8080/index.jsp`.
