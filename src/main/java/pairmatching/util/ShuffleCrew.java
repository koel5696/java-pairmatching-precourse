package pairmatching.util;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class ShuffleCrew {

    private ShuffleCrew() {
    }

    public static List<String> function(List<String> crews) {
        return Randoms.shuffle(crews);
    }
}
