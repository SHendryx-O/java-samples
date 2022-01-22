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

//[START classroom_create_course]
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.classroom.Classroom;
import com.google.api.services.classroom.model.Course;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class CreateCourse {
    /**
     * Create Course
     *
     * @return the created course
     * @throws IOException
     * @throws GeneralSecurityException
     */
    public static Course createCourse() throws GeneralSecurityException, IOException {
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

        // Define and build course
        Course course = new Course()
                .setName("10th Grade Biology")
                .setSection("Period 2")
                .setDescriptionHeading("Welcome to 10th Grade Biology")
                .setDescription("We'll be learning about about the structure of living creatures "
                        + "from a combination of textbooks, guest lectures, and lab work. Expect "
                        + "to be excited!")
                .setRoom("301")
                .setOwnerId("me")
                .setCourseState("PROVISIONED");
        try {
            course = classroom.courses().create(course).execute();
        } catch (IOException e){
            // TODO (developer) Do something sensible with this exception.
            System.err.println("Course not created: " + e.getMessage());
            throw e;
        }
        System.out.printf("Course created: %s (%s)\n", course.getName(), course.getId());
        return course;
    }
    // [END classroom_create_course]
}