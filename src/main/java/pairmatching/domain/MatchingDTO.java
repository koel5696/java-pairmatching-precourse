package pairmatching.domain;

import java.util.List;
import pairmatching.domain.crew.Crew;

public record MatchingDTO(
        List<List<Crew>> matchingResult
) {
    public StringBuilder matchingBuilder() {
        StringBuilder sb = new StringBuilder();

        for (List<Crew> crews : matchingResult) {
            for (int i = 0; i < crews.size(); i++) {
                sb.append(crews.get(i).getName());
                if (i != crews.size() - 1) {
                    sb.append(" : ");
                }
            }
            sb.append("\n");
        }
        return sb;
    }
}
