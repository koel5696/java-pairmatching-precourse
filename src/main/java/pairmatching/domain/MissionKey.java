package pairmatching.domain;

public record MissionKey(Course course, Level level, Mission mission) {

    public MissionKey(String[] input) {
        this(
                Course.validateCourse(input[0]),
                Level.validateLevel(input[1]),
                Mission.validateMission(input[2], Level.validateLevel(input[1]))
        );
    }

}
