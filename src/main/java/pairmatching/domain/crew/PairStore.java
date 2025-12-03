package pairmatching.domain.crew;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pairmatching.domain.MatchingDTO;
import pairmatching.domain.MissionKey;
import pairmatching.exception.AlreadyExistException;

public class PairStore {
    private Map<MissionKey, List<List<Crew>>> store;

    public PairStore() {
        this.store = new HashMap<>();
    }

    public void save(MissionKey missionKey, List<List<Crew>> crews) {
        store.put(missionKey, crews);
    }

    public void remove(MissionKey missionKey) {
        store.remove(missionKey);
    }

    public boolean validDuplicate(MissionKey newKey, List<List<Crew>> newCrews) {
        List<String> existingPairs = store.entrySet().stream()
                .filter(entry -> entry.getKey().level() == newKey.level()) // 동일 레벨만
                .flatMap(entry -> entry.getValue().stream())
                .map(this::toPairKey)
                .toList();

        for (List<Crew> pair : newCrews) {
            String current = toPairKey(pair);

            if (existingPairs.contains(current)) { // 겹치면 실패
                return false;
            }
        }
        return true;
    }

    public MatchingDTO inquiry(MissionKey missionKey) {
        if (!store.containsKey(missionKey)) {
            throw new IllegalArgumentException("[ERROR] 해당 과정은 아직 매칭되지 않았습니다.");
        }
        return new MatchingDTO(store.get(missionKey));

    }

    public void matchingInformation(MissionKey missionKey) {
        if (store.containsKey(missionKey)) {
            throw new AlreadyExistException(missionKey);
        }
    }

    public void reset() {
        store = new HashMap<>();
    }

    private String toPairKey(List<Crew> pair) {
        // crew 이름을 정렬해서 페어를 순서 없이 비교 가능하게 만듦
        return pair.stream()
                .map(Crew::getName)
                .sorted()
                .reduce((a, b) -> a + "|" + b)
                .orElse("");
    }
}
