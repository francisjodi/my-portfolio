package com.google.sps.servlets;

import java.io.IOException;
import com.google.cloud.datastore.KeyFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.FullEntity;

/** Servlet responsible for creating new tasks. */
@WebServlet("/new-task")
public class DataStoreServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
      
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String projectName = request.getParameter("projectName");
    String message = request.getParameter("message");

    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("Task");
        FullEntity Entity;
        FullEntity taskEntity =
                FullEntity.newBuilder(keyFactory.newKey())
            .set("name", name)
            .set("email", email)
            .set("projectName", projectName)
            .set("message", message)
            .build();
    datastore.put(taskEntity);

    response.sendRedirect("/index.html");
  }
}

