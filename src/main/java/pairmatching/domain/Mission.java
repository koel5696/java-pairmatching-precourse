package pairmatching.domain;

public enum Mission {
    자동차경주(Level.LEVEL1),
    로또(Level.LEVEL1),
    숫자야구게임(Level.LEVEL1),

    장바구니(Level.LEVEL2),
    지하철노선도(Level.LEVEL2),
    결제(Level.LEVEL2),

    성능개선(Level.LEVEL4),
    배포(Level.LEVEL4);

    private final Level level;

    Mission(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    public static Mission validateMission(String inputMission, Level selectedLevel) {
        for (Mission mission : Mission.values()) {
            if (mission.name().equals(inputMission) && mission.level == selectedLevel) {
                return mission;
            }
        }
        throw new IllegalArgumentException("[ERROR] 선택한 레벨에 존재하지 않는 미션입니다.");
    }
}
