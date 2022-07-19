package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;


@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Get the value entered in the form.
    String address = request.getParameter("address");
    String address2 = request.getParameter("address2");
    String city = request.getParameter("city");
    String state = request.getParameter("state");
    String country = request.getParameter("country");
    String zip = request.getParameter("zip");
    String major = request.getParameter("major");
    String allergies = request.getParameter("allergies");
    String interests = request.getParameter("interests");
    long timestamp = System.currentTimeMillis();

    System.out.println("You submitted: address: " + address);
    System.out.println("You submitted: address2: " + address2);
    System.out.println("You submitted: city: " + city);
    System.out.println("You submitted: state: " + state);
    System.out.println("You submitted: country: " + country);
    System.out.println("You submitted: zip: " + zip);
    System.out.println("You submitted: major: " + major);
    System.out.println("You submitted: allergies: " + allergies);
    System.out.println("You submitted: interests: " + interests);
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("Form");
    FullEntity formEntity =
        Entity.newBuilder(keyFactory.newKey())
            .set("address", address)
            .set("address2", address2)
            .set("city", city)
            .set("state", state)
            .set("country", country)
            .set("zip", zip)
            .set("major", major)
            .set("allergies", allergies)
            .set("interests", interests)
            .set("timestamp", timestamp)
            .build();
    datastore.put(formEntity);

    //We store the info obtained in cookies, so that GetItemsServlet can load the right items:
    response.addCookie(new Cookie("major", major.replace(" ", "-")));
    response.sendRedirect("/results.html");



    // Print the value so you can see it in the server logs.

    // Write the value to the response so the user can see it.
    // response.getWriter().println("You submitted: " + formEntity);
  }
}