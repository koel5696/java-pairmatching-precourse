package pairmatching.controller;

import pairmatching.domain.MatchingDTO;
import pairmatching.domain.MissionKey;
import pairmatching.domain.crew.PairStore;
import pairmatching.exception.AlreadyExistException;
import pairmatching.service.FunctionSelectionService;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairController {
    private final InputView inputView;
    private final OutputView outputView;
    private final PairStore pairStore;
    private final FunctionSelectionService functionSelectionService;

    public PairController(InputView inputView, OutputView outputView, PairStore pairStore,
                          FunctionSelectionService functionSelectionService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.pairStore = pairStore;
        this.functionSelectionService = functionSelectionService;
    }

    public void run() {
        while (true) {
            int num = oneStep();
            if (checkExit(num)) {
                return;
            }
            if (num == 3) {
                functionSelectionService.resetService(pairStore);
                outputView.completeReset();
            } else {
                outputView.printMatchingResult(twoStep(num));
            }
        }
    }

    private int oneStep() {
        while (true) {
            try {
                String select = inputView.selectUtility();
                functionSelectionService.validSelectService(select);
                if (select.equals("Q")) {
                    return 100;
                }
                return Integer.parseInt(select);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private MatchingDTO twoStep(int num) {
        while (true) {
            try {
                String[] inputs = functionSelectionService.validCourse_Level_Mission
                        (inputView.inputCourse_level_mission());
                return functionSelectionService.start(new MissionKey(inputs), num, pairStore);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            } catch (AlreadyExistException e) {
                String reMatchingRequest = inputView.alreadyMatchingError(e.getMessage());
                MatchingDTO matchingDTO = functionSelectionService.reMatching(pairStore, reMatchingRequest,
                        e.getMissionKey());
                if (matchingDTO != null) {
                    return matchingDTO;
                }
            }
        }
    }

    private boolean checkExit(int num) {
        if (num == 3) {
            return false; // 초기화 기능 추가.
        }
        return num == 100;
    }
}
