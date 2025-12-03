package pairmatching.service;

import java.util.List;
import pairmatching.domain.Course;
import pairmatching.domain.MatchingDTO;
import pairmatching.domain.MissionKey;
import pairmatching.domain.crew.Crew;
import pairmatching.domain.crew.Crews;
import pairmatching.domain.crew.PairStore;
import pairmatching.util.ShuffleCrew;

public class FunctionSelectionService {
    private static final String regex = "[1-3]|Q";
    private static final String missionRegex = "[가-힣1-9]+[, 가-힣1-9]+";
    private static final String reMatchingRegex = "^(네|아니오)$";

    public void validSelectService(String input) {
        if (!input.matches(regex)) {
            throw new IllegalArgumentException("[ERROR] 1, 2, 3, Q 중에 하나를 입력하세요.");
        }
    }

    public String[] validCourse_Level_Mission(String course_level_mission) {
        if (!course_level_mission.matches(missionRegex)) {
            throw new IllegalArgumentException("[ERROR] 매칭을 위한 정보 입력이 잘못됨.");
        }

        return course_level_mission.split(", ");
    }

    public MatchingDTO start(MissionKey missionKey, int num, PairStore pairStore) {
        if (num == 1) {
            return pairMatchingService(missionKey, pairStore);
        } else {
            return pairInquiry(missionKey, pairStore);
        }
    }

    public void resetService(PairStore pairStore) {
        pairStore.reset();
    }

    public MatchingDTO reMatching(PairStore pairStore, String reMatchingRequest, MissionKey missionKey) {
        if (!reMatchingRequest.matches(reMatchingRegex)) {
            throw new IllegalArgumentException("[ERROR] 네, 아니오만 입력");
        }

        if (reMatchingRequest.equals("네")) {
            pairStore.remove(missionKey);
            return start(missionKey, 1, pairStore);
        }

        return null;
    }

    private MatchingDTO pairMatchingService(MissionKey missionKey, PairStore pairStore) {
        pairStore.matchingInformation(missionKey);

        Course course = missionKey.course(); // 백인지 프인지 코스 확인
        List<String> crewsNames = course.judgeCourse(); // 코스에 따른 크루 리스트 생성
        crewsNames = ShuffleCrew.function(crewsNames); // 크루 랜덤 셔플

        Crews crews = new Crews(crewsNames); // 크루들 객체 생성
        List<List<Crew>> pairResult = crews.pairMatching(pairStore, missionKey); // 크루 페어 매칭

        pairStore.save(missionKey, pairResult);

        return new MatchingDTO(pairResult);
    }

    private MatchingDTO pairInquiry(MissionKey missionKey, PairStore pairStore) {
        return pairStore.inquiry(missionKey);
    }

}
