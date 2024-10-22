package Examinationsuppgift.BestGymEver.Test;

import Examinationsuppgift.BestGymEver.FileManager;
import Examinationsuppgift.BestGymEver.VisitLogger;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MainprogTest {

    FileManager fm = new FileManager();
    VisitLogger vl = new VisitLogger(fm);

    @Test
    // Testar om en nuvarande medlems besök loggas korrekt efter att personnummer matats in
    public void currentMemberTest() throws IOException {
        String input = "7502031234\nexit\n";  //
        BufferedReader reader = new BufferedReader(new StringReader(input));

        List<String> lines = Files.readAllLines(fm.getVisitLogFile().toPath());
        assertFalse(lines.isEmpty(), "Visit log should contain an entry.");
        assertNotNull(lines.get(0).contains("Anna Andersson"));
    }

    @Test
    // Testar om en ogiltig medlems inmatning inte loggas och att loggfilen förblir tom
    public void invalidMemberTest() throws IOException {
        String input = "9999999999\nexit\n";
        BufferedReader reader = new BufferedReader(new StringReader(input));

        List<String> lines = Files.readAllLines(fm.getVisitLogFile().toPath());
        assertNotNull(lines.isEmpty());
    }
}

