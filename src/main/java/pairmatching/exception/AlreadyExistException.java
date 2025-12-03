package pairmatching.exception;

import pairmatching.domain.MissionKey;

public class AlreadyExistException extends RuntimeException {
    private final MissionKey missionKey;

    public AlreadyExistException(MissionKey missionKey) {
        super("매칭 정보가 있습니다. 다시 매칭하시겠습니까?");
        this.missionKey = missionKey;
    }

    public MissionKey getMissionKey() {
        return missionKey;
    }
}
