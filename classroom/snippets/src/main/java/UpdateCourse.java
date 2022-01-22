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

//[START classroom_update_course]

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.classroom.Classroom;
import com.google.api.services.classroom.model.Course;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class UpdateCourse {
  /**
   * Update Course
   *
   * @return the course that was updated
   * @throws IOException
   * @throws GeneralSecurityException
   */
  public static Course updateCourse() throws IOException, GeneralSecurityException {
    // Load pre-authorized user credentials from the environment.
    // TODO(developer) - See https://developers.google.com/identity for
    // guides on implementing OAuth2 for your application.
    GoogleCredentials defaultCredentials = GoogleCredentials.getApplicationDefault();
    NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    Classroom classroom = new Classroom.Builder(HTTP_TRANSPORT,
            new GsonFactory(),
            new HttpCredentialsAdapter(defaultCredentials))
            .setApplicationName("Classroom Update Course Sample")
            .build();

    // TODO (developer) Please change the course ID below to a course ID in your classroom
    String courseId = "1234567";
    Course course = classroom.courses().get(courseId).execute();
    course.setSection("Period 3");
    course.setRoom("302");
    course = classroom.courses().update(courseId, course).execute();
    System.out.printf("Course '%s' updated.\n", course.getName());
    return course;
  }
  // [END classroom_update_course]
}
