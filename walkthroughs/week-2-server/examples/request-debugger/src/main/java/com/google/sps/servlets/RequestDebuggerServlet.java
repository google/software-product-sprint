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
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet that prints out debug info about requests. Explore the API here:
 * https://docs.oracle.com/javaee/7/api/javax/servlet/http/HttpServletRequest.html
 */
@WebServlet("/my-data-url")
public final class RequestDebuggerServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html;");
    printRequest(response.getWriter(), request);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html;");
    printRequest(response.getWriter(), request);
  }

  private void printRequest(PrintWriter out, HttpServletRequest request) {
    out.println("request URL: " + request.getRequestURL());
    out.println("<br/>");

    out.println("request URI: " + request.getRequestURI());
    out.println("<br/>");

    out.println("content length: " + request.getContentLength());
    out.println("<br/>");

    out.println("content type: " + request.getContentType());
    out.println("<br/>");

    out.println("protocol: " + request.getProtocol());
    out.println("<br/>");

    out.println("client IP: " + request.getRemoteAddr());
    out.println("<br/>");

    out.println("server name: " + request.getServerName());
    out.println("<br/>");

    out.println("character encoding: " + request.getCharacterEncoding());
    out.println("<br/>");

    out.println("headers:");
    out.println("<ul>");
    Enumeration<String> headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String headerName = headerNames.nextElement();
      out.print("<li>" + headerName + ": " + request.getHeader(headerName) + "</li>");
    }
    out.println("</ul>");

    out.println("parameters:");
    out.println("<ul>");
    Enumeration<String> parameterNames = request.getParameterNames();
    while (parameterNames.hasMoreElements()) {
      String parameterName = parameterNames.nextElement();
      out.print("<li>" + parameterName + ": " + request.getParameter(parameterName) + "</li>");
    }
    out.println("</ul>");
  }
}
