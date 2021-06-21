package com.google.sps.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Handles requests sent to the /hello URL. Try running a server and navigating to /hello! */
@WebServlet("/myself")
public class MeServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html;");
    ArrayList<String> myself = new ArrayList<String>();
    myself.add("I am 20 years old");
    myself.add("I go to Clemson University");
    myself.add("I love music.");
    
    response.getWriter().println(myself);
    
   
  }
}
