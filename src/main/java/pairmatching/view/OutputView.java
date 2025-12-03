package pairmatching.view;

import static pairmatching.view.GuideMessage.MATCHING_RESULT_GUIDE;

import pairmatching.domain.MatchingDTO;

public class OutputView {

    public void printErrorMessage(String message) {
        System.out.println(message);
    }


    public void printMatchingResult(MatchingDTO matchingDTO) {
        System.out.println();
        System.out.println(MATCHING_RESULT_GUIDE.getGuideMessage());
        System.out.println(matchingDTO.matchingBuilder());
    }

    public void completeReset() {
        System.out.println();
        System.out.println("초기화 되었습니다.");
        System.out.println();
    }
}
