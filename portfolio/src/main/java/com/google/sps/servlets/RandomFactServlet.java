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

// package com.google.sps.servlets;

// import java.io.IOException;
// import javax.servlet.annotation.WebServlet;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

// /** Servlet that returns some example content. TODO: modify this file to handle comments data */
// @WebServlet("/data")
// public class DataServlet extends HttpServlet {

//   @Override
//   public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//     response.setContentType("text/html;");
//     response.getWriter().println("<h1>Hello Eve!</h1>");
//   }
// }
package com.google.sps.servlets;

import java.io.IOException;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns a random fact about me. */
@WebServlet("/random-fact")
public final class RandomFactServlet extends HttpServlet {

  private List<String> facts;
  private List<String> images;

  @Override
  public void init() {
    facts = new ArrayList<>();
    facts.add("She loves icecreams!!");
    facts.add("She lives in Suzhou.");
    facts.add("She loves photography.");
    facts.add("She loves cooking and baking!");
    images = new ArrayList<>();
    images.add("images/icecream.jpg");
    images.add("images/shantang.jpg");
    images.add("images/camera.jpg");
    images.add("images/cookie.jpg");

  }

//   @Override
//   public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//     int num = (int) (Math.random() * facts.size());
//     String fact = facts.get(num);
//     String image = images.get(num);

//     response.setContentType("text/html;");
//     response.getWriter().println(fact);
//     response.getWriter().println(image);
//   }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int num = (int) (Math.random() * facts.size());
        List<String> arr = new ArrayList<>();
        arr.add(facts.get(num));
        arr.add(images.get(num));
        String json = convertToJson(arr);

        response.setContentType("text/html;");
        response.getWriter().println(json);
    }

  /**
   * Converts an array into a JSON string using the Gson library. Note: We first added
   * the Gson library dependency to pom.xml.
   */
  private String convertToJson(List<String> arr) {
    Gson gson = new Gson();
    String json = gson.toJson(arr);
    return json;
  }
}
