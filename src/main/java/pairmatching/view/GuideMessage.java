package pairmatching.view;

public enum GuideMessage {
    SELECT_UTIL("기능을 선택하세요.\n"
            + "1. 페어 매칭\n"
            + "2. 페어 조회\n"
            + "3. 페어 초기화\n"
            + "Q. 종료"),
    SPLIT("#############################################"),
    SELECT_ALL("과정, 레벨, 미션을 선택하세요.\n"
            + "ex) 백엔드, 레벨1, 자동차경주"),
    MATCHING_RESULT_GUIDE("페어 매칭 결과입니다.");


    private final String guideMessage;

    GuideMessage(String guideMessage) {
        this.guideMessage = guideMessage;
    }

    public String getGuideMessage() {
        return guideMessage;
    }
}
