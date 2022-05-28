package com.google.sps.servlets;

import java.io.IOException;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;
import org.jsoup.Jsoup;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.safety.Whitelist;

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Get the value entered in the form.
    String textValue = Jsoup.clean(request.getParameter("text-input"), Whitelist.none());
    //generates timestamp
    long timestamp = System.currentTimeMillis();

    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("Message");
    FullEntity taskEntity =
        Entity.newBuilder(keyFactory.newKey())
            .set("text-value", textValue)
            .set("timestamp", timestamp)
            .build();
    datastore.put(taskEntity);

    response.sendRedirect("/form-handler");

    // Print the value so you can see it in the server logs.
    System.out.println("You submitted: " + textValue);

    // Write the value to the response so the user can see it.
    response.getWriter().println("You submitted: " + textValue);
    
  }
}