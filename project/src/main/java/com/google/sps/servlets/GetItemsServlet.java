package com.google.sps.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Stack;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;
import com.google.cloud.datastore.KeyValue;
import com.google.cloud.datastore.Key;

import org.json.JSONObject;
import org.json.JSONArray;

@WebServlet("/get-items")
public class GetItemsServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");

    //TODO: Probably there's something faster than iterating through all cookies:
    String major = null;
    Cookie[] cookies = request.getCookies();
    if(cookies != null){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("major")){
                major = cookie.getValue().replace("-", " ");
            }
        }
    }

    if(major == null){
        //TODO: We probably should do something about it
        return;
    }

    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    JSONObject jsonResponse = new JSONObject();
    jsonResponse.put("major", major);

    //We put the Item arrays inside the JSON
    jsonResponse.put("generalItemNames", getItemArray("General", datastore));
    jsonResponse.put("majorItemNames", getItemArray(major, datastore));

    //We put the JSON inside the response
    response.getWriter().print(jsonResponse.toString());
  }



  //Could be modified to include more item properties besides their names
  JSONArray getItemArray(String major, Datastore datastore){
    JSONArray responseArray = new JSONArray();
     //We get all the items (relevant to the major) from the Datastore
    Query<Entity> query = Query.newEntityQueryBuilder()
        .setKind("Major")
        .setFilter(PropertyFilter.eq("Name", major))
        .build();
    QueryResults<Entity> queryResult = datastore.run(query);
    List<KeyValue> itemsKV = queryResult.next().getList("Items");
    Stack<Entity> items = new Stack<Entity>();
    for(int i = 0; i < itemsKV.size(); i++){
        Key key = itemsKV.get(i).get();
        items.add(datastore.get(key));
    }

    //We get the properties we want from the major items:
    while(!items.empty()){
        responseArray.put(items.pop().getString("Name"));
    }

    return responseArray;
  }
}
