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

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import java.time.LocalDateTime;
import com.andrewrs.sps.data.ListRecord;
import java.util.ArrayList;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {
    private ArrayList<ListRecord> records;
  public void init()
  {
    records = new ArrayList<ListRecord>();    
  }
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException 
  {
    response.setContentType("text/json;");

    Gson gson = new Gson();
    //
    response.getWriter().println(gson.toJson(records));
  }
    @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException 
  {
    String text = getParameter(request, "message", "");
    String messages[] = text.split(",");
    for(String message: messages)
    {
      records.add(new ListRecord(LocalDateTime.now(),message));
    }
    response.sendRedirect("/todo.html");
  }

  private String getParameter(HttpServletRequest request, String name, String defaultValue) 
  {
    String value = request.getParameter(name);
    if (value == null) {
      return defaultValue;
    }
    return value;
  }
}
