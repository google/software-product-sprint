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
import java.util.Arrays;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that processes text. */
@WebServlet("/text")
public final class TextProcessorServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Get the input from the form.
    String text = request.getParameter("text-input");
    boolean upperCase = Boolean.valueOf(request.getParameter("upper-case"));
    boolean sort = Boolean.valueOf(request.getParameter("sort"));

    // Convert the text to upper case.
    if (upperCase) {
      text = text.toUpperCase();
    }

    // Break the text into individual words.
    String[] words = text.split("\\s*,\\s*");

    // Sort the words.
    if (sort) {
      Arrays.sort(words);
    }

    // Respond with the result.
    response.setContentType("text/html;");
    response.getWriter().println(Arrays.toString(words));
  }
}
