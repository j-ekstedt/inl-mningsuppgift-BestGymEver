package Examinationsuppgift.BestGymEver;

import java.io.File;

public class FileManager {

    // Returnerar filen som innehåller medlemsdata
    public File getMemberFile() {
        return new File("src/Examinationsuppgift/member_data.txt");
    }

    // Returnerar filen där kundbesök loggas
    public File getVisitLogFile() {
        return new File("src/Examinationsuppgift/pt_log.txt");
    }
}
