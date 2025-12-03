package pairmatching.domain;

import java.util.List;
import pairmatching.config.CrewFileLoader;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private final String name;

    Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Course validateCourse(String inputCourse) {
        for (Course course : Course.values()) {
            if (course.name.equals(inputCourse)) {
                return course;
            }
        }
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 코스입니다: " + inputCourse);
    }

    public List<String> judgeCourse() {
        if (name.equals(BACKEND.name)) {
            return CrewFileLoader.backendFileLoader();
        }

        if (name.equals(FRONTEND.name)) {
            return CrewFileLoader.frontendFileLoader();
        }

        throw new IllegalArgumentException("[ERROR] 너 잘못 입력함. 백엔 프엔만 가능");
    }
}
