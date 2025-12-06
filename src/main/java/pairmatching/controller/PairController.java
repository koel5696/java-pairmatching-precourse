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
            String inputCommand = oneStep();
            if (checkExit(inputCommand)) {
                return;
            }
            int commandNumber = Integer.parseInt(inputCommand);

            if (Command.validThreeSelect(commandNumber)) {
                functionSelectionService.resetService(pairStore);
                outputView.completeReset();
            } else {
                outputView.printMatchingResult(twoStep(commandNumber));
            }
        }
    }

    private String oneStep() {
        while (true) {
            try {
                String select = inputView.selectUtility();
                functionSelectionService.validSelectService(select);
                return select;
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
                MatchingDTO matchingDTO = alreadyMatchingResponse(e);
                if (matchingDTO != null) {
                    return matchingDTO;
                }
            }
        }
    }

    private MatchingDTO alreadyMatchingResponse(AlreadyExistException e) {
        while (true) {
            try {
                String reMatchingRequest = inputView.alreadyMatchingError(e.getMessage());
                return functionSelectionService.reMatching(pairStore, reMatchingRequest,
                        e.getMissionKey());
            } catch (IllegalArgumentException ee) {
                outputView.printErrorMessage(ee.getMessage());
            }
        }
    }

    private boolean checkExit(String inputCommand) {
        return Command.commandCompare(inputCommand);
    }
}
