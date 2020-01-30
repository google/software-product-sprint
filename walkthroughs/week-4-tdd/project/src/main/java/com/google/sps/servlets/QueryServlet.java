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

import com.google.sps.Events;
import com.google.sps.FindMeetingQuery;
import com.google.sps.MeetingRequest;
import com.google.sps.TimeRange;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/query")
public class QueryServlet extends HttpServlet {
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Gson gson = new Gson();

    // Convert the JSON to an instance of MeetingRequest.
    MeetingRequest meetingRequest = gson.fromJson(request.getReader(), MeetingRequest.class);

    // Find the possible meeting times.
    FindMeetingQuery findMeetingQuery = new FindMeetingQuery();
    Collection<TimeRange> answer =
        findMeetingQuery.query(Arrays.asList(Events.events), meetingRequest);

    // Convert the times to JSON
    String jsonResponse = gson.toJson(answer);

    // Send the JSON back as the response
    response.setContentType("application/json");
    response.getWriter().println(jsonResponse);
  }
}
