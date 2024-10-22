package Examinationsuppgift.BestGymEver.Test;

import Examinationsuppgift.BestGymEver.FileManager;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {

    FileManager fm = new FileManager();

    // Testar om medlemsfilen finns och inte är null
    @Test
    public void getMemberFileTest() {
        File memberFile = fm.getMemberFile();
        assertNotNull(memberFile);
        assertTrue(memberFile.exists());
    }

    // Testar om loggfilen för besök finns eller kan skapas korrekt
    @Test
    public void testGetVisitLogFile() {
        File visitLogFile = fm.getVisitLogFile();
        assertNotNull(visitLogFile);
        assertTrue(visitLogFile.exists() || visitLogFile.isFile());
    }
}