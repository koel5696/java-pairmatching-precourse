package pairmatching.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrewFileLoader {
    public static final String BACKEND_CREW_FILE_PATH = "src/main/resources/backend-crew.md";
    public static final String FRONTEND_CREW_FILE_PATH = "src/main/resources/frontend-crew.md";

    private CrewFileLoader() {
    }

    public static List<String> backendFileLoader() {
        List<String> names = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(BACKEND_CREW_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                names.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return names;
    }

    public static List<String> frontendFileLoader() {
        List<String> names = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FRONTEND_CREW_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                names.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return names;
    }
}
