// Copyright 2021 Google LLC
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

//[START classroom_list_course_aliases]
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.classroom.Classroom;
import com.google.api.services.classroom.model.CourseAlias;
import com.google.api.services.classroom.model.ListCourseAliasesResponse;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class ListCourseAliases {

  public static List<CourseAlias> listCourseAliases(String courseId)
          throws IOException, GeneralSecurityException {
    // Load pre-authorized user credentials from the environment.
    // TODO(developer) - See https://developers.google.com/identity for
    // guides on implementing OAuth2 for your application.
    GoogleCredentials defaultCredentials = GoogleCredentials.getApplicationDefault();
    NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    Classroom classroom = new Classroom.Builder(HTTP_TRANSPORT,
            new GsonFactory(),
            new HttpCredentialsAdapter(defaultCredentials))
            .setApplicationName("Classroom Create Course Sample")
            .build();

    String pageToken = null;
    List<CourseAlias> courseAliases = new ArrayList<CourseAlias>();
    do {
      ListCourseAliasesResponse response = classroom.courses().aliases().list(courseId)
              .setPageSize(100)
              .setPageToken(pageToken)
              .execute();
      courseAliases.addAll(response.getAliases());
      pageToken = response.getNextPageToken();
    } while (pageToken != null);
    if (courseAliases.isEmpty()) {
      System.out.println("No aliases found.");
    } else {
      System.out.println("Aliases:");
      for (CourseAlias courseAlias : courseAliases) {
        System.out.println(courseAlias.getAlias());
      }
    }
    // [END listCourseAliases]
    return courseAliases;
  }
}
