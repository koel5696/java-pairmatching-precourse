package pairmatching.domain.crew;

import java.util.ArrayList;
import java.util.List;
import pairmatching.domain.Course;
import pairmatching.domain.MissionKey;

public class Crews {
    private final List<Crew> crews;

    public Crews(List<String> crews) {
        List<Crew> bufferCrews = new ArrayList<>();
        for (String crew : crews) {
            bufferCrews.add(new Crew(Course.BACKEND, crew));
        }
        this.crews = bufferCrews;
    }

    public List<List<Crew>> pairMatching(PairStore pairStore, MissionKey missionKey) {
        List<List<Crew>> pairResult = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            pairResult = new ArrayList<>();

            for (int j = 0; j < crews.size() / 2; j++) {
                pairResult.add(new ArrayList<>());
            }

            int k = 0;
            for (int j = 0; j < pairResult.size(); j++) {
                pairResult.get(j).add(crews.get(k));
                pairResult.get(j).add(crews.get(k + 1));
                if (k + 2 >= crews.size() - 1 && crews.size() % 2 == 1) {
                    pairResult.get(j).add(crews.get(k + 2));
                }
                k += 2;
            }
            if (pairStore.validDuplicate(missionKey, pairResult)) {
                break;
            }
            if (i == 2) {
                throw new IllegalArgumentException("[ERROR] 3회 이상 매칭 시도했으나 실패");
            }
        }

        return pairResult;
    }

}
