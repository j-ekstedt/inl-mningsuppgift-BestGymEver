package Examinationsuppgift.BestGymEver.Test;

import Examinationsuppgift.BestGymEver.FileManager;
import Examinationsuppgift.BestGymEver.Member;
import Examinationsuppgift.BestGymEver.VisitLogger;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class VisitLoggerTest {

    VisitLogger vl = new VisitLogger(new FileManager());
    private File logPtFile;

    @Test
    // Testar om ett besök kan loggas korrekt i loggfilen och att filen innehåller rätt information
    public void logVisitTest() throws IOException {
        FileManager fm = new FileManager();
        logPtFile = fm.getVisitLogFile();
        if (logPtFile.exists()) {
            logPtFile.delete();
        }

        vl = new VisitLogger(fm);
        Member member = new Member("0308120001", "Jane Doe", LocalDate.now());
        vl.logVisit(member);

        List<String> lines = Files.readAllLines(logPtFile.toPath());
        assertFalse(lines.isEmpty());
        assertTrue(lines.get(0).contains("Jane Doe"));
    }
}


