package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Imports the Google Cloud client library
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;

@WebServlet("/get-majors")
public class GetMajorsServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html;");

    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    Query<Entity> majorsQuery = Query.newEntityQueryBuilder()
                            .setKind("Major")
                            .setOrderBy(OrderBy.asc("Name"))
                            .build();
    QueryResults<Entity> majorsResult = datastore.run(majorsQuery);
    response.getWriter().print("<option selected value=\"\">Select your current major</option>");
    while(majorsResult.hasNext()){
        String name = majorsResult.next().getString("Name");
        if(name.compareTo("General") != 0){
            String optionTag = "<option value=\"" + name + "\">" + name + "</option>";
            response.getWriter().print(optionTag);
        }
    }
  }
}
