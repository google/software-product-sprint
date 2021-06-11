// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import com.google.sps.data.MarcoStats;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Date;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Handles requests sent to the /hello URL. Try running a server and navigating to /hello! */
@WebServlet("/hello")
public class HelloWorldServlet extends HttpServlet {

  private final Date startTime = new Date();

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Calculate server stats
    Date currentTime = new Date();

    // Convert data to JSON
    MarcoStats marcoStats = new MarcoStats(20, "Marco");
    String json = convertToJsonUsingGson(marcoStats);

    // Send the JSON as the response
    response.setContentType("application/json;");
    response.getWriter().println(json);

    //response.setContentType("text/html;");
    //response.getWriter().println("<h1>Hello world!</h1>");
    //response.getWriter().println("The server's current date is " + new Date());
  }

   private String convertToJson(MarcoStats marcoStats) {
    String json = "{";
    json += "\"Age\": ";
    json += "\"" + marcoStats.getAge() + "\"";
    json += ", ";
    json += "\"Name\": ";
    json += "\"" + marcoStats.getName() + "\"";
    json += "}";
    return json;
  }

  private String convertToJsonUsingGson(MarcoStats marcoStats) {
    Gson gson = new Gson();
    String json = gson.toJson(marcoStats);
    return json;
  }
}