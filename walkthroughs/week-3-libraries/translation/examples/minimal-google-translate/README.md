This project uses the Translation API in a servlet to translate text submitted
by a user. It uses the `fetch()` function from JavaScript to request
translations, creating a minimalistic version of Google Translate.

![Minimal Google Translate](screenshot.png)

To run this example, first make sure your `GOOGLE_APPLICATION_CREDENTIALS`
environment variable is set and that you've enabled the
[Translation API](https://console.cloud.google.com/apis/library/translate.googleapis.com),
and then execute this command:

```bash
mvn package exec:java
```
