This project uses the Natural Language API in a servlet to perform sentiment
analysis on text submitted by a user.

![sentiment analysis form](screenshot-1.png)

![sentiment analysis result](screenshot-2.png)

To run this example, first make sure your `GOOGLE_APPLICATION_CREDENTIALS`
environment variable is set and that you've enabled the
[Natural Language API](https://console.developers.google.com/apis/library/language.googleapis.com),
and then execute this command:

```bash
mvn package exec:java
```
