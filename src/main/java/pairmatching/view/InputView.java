package pairmatching.view;

import static pairmatching.view.GuideMessage.SELECT_ALL;
import static pairmatching.view.GuideMessage.SELECT_UTIL;
import static pairmatching.view.GuideMessage.SPLIT;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;

public class InputView {

    public String selectUtility() {
        System.out.println(SELECT_UTIL.getGuideMessage());
        return Console.readLine();
    }

    public String alreadyMatchingError(String message) {
        System.out.println(message);
        System.out.println("네 | 아니오");
        return Console.readLine();
    }

    public String inputCourse_level_mission() {
        System.out.println();
        System.out.println(SPLIT.getGuideMessage());
        System.out.println("과정: 백엔드 | 프론트엔드");
        System.out.println("미션: ");
        for (Level level : Level.values()) {
            List<String> missionsName = new ArrayList<>();
            for (Mission mission : Mission.values()) {
                if (mission.getLevel() == level) {
                    missionsName.add(mission.name());
                }
            }
            System.out.println("    -" + level.getName() + ": " + String.join(" | ", missionsName));
        }
        System.out.println(SPLIT.getGuideMessage());
        System.out.println(SELECT_ALL.getGuideMessage());
        return Console.readLine();
    }
}
