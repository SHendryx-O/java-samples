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

// [START classroom_getCourse]

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.classroom.Classroom;
import com.google.api.services.classroom.model.Course;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class GetCourse {
  /**
   * Get Course
   *
   * @return the course requested (null if the course doesn't exist)
   * @throws IOException
   * @throws GeneralSecurityException
   */
  public static Course getCourse() throws IOException, GeneralSecurityException {
    // Load pre-authorized user credentials from the environment.
    // TODO(developer) - See https://developers.google.com/identity for
    // guides on implementing OAuth2 for your application.
    GoogleCredentials defaultCredentials = GoogleCredentials.getApplicationDefault();
    NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    Classroom classroom = new Classroom.Builder(HTTP_TRANSPORT,
            new GsonFactory(),
            new HttpCredentialsAdapter(defaultCredentials))
            .setApplicationName("Classroom Get Course Sample")
            .build();

    // TODO (developer) Please change the course ID below to a course ID in your classroom
    String courseId = "1234567";
    Course course = null;
    try {
      course = classroom.courses().get(courseId).execute();
      System.out.printf("Course '%s' found.\n", course.getName());
    } catch (GoogleJsonResponseException e) {
      GoogleJsonError error = e.getDetails();
      if (error.getCode() == 404) {
        System.out.printf("Course with ID '%s' not found.\n", courseId);
      } else {
        throw e;
      }
    }
    return course;
  }
  // [END classroom_getCourse]
}
