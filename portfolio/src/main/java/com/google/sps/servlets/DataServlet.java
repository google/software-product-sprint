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
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.andrewrs.sps.data.ListRecord;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {
  private DatastoreService datastore;
  public void init()
  {
    datastore = DatastoreServiceFactory.getDatastoreService();
  }
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException 
  {
    ArrayList<ListRecord> records = new ArrayList<ListRecord>(); 
    response.setContentType("text/json;");
    Query query = new Query("message_log").addSort("timeStamp", SortDirection.DESCENDING);
    PreparedQuery results = datastore.prepare(query);
    for (Entity entity : results.asIterable()) 
    {
        long time = -1;
        try{
            time = Long.parseLong((String)entity.getProperty("timeStamp") );
        }catch(Exception e)
        {
            e.printStackTrace();
        }
          records.add(new ListRecord(
            time,
                (String)entity.getProperty("message") ));
    }

    Gson gson = new Gson();
    response.getWriter().println(gson.toJson(records));
  }
    @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException 
  {
    String text = getParameter(request, "message", "");
    String messages[] = text.split(",");
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
 
    for(String message: messages)
    {
      Entity entity = new Entity("message_log");
      entity.setProperty("timeStamp", System.currentTimeMillis());
      entity.setProperty("message", escapeQuotesInParameter(message));
      datastore.put(entity);
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
  private String escapeQuotesInParameter(String param)
  {
      StringBuilder data = new StringBuilder();
      int lastIndex = 0;
      for(int i = 0;i < param.length();i++)
      {
          if(param.charAt(lastIndex) == '\\' || param.charAt(i) != '\'' || param.charAt(i) != '\"')
            data.append(param.charAt(i));
          else
          {
              data.append('\\');
              data.append(param.charAt(i));
          }
          lastIndex = i;
      }
      return data.toString();
  }
}
