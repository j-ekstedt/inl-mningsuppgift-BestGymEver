package Examinationsuppgift.BestGymEver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VisitLogger {

        private final FileManager fileManager;

    // Konstruktor som tar emot en FileManager för att hantera loggfiler, loggar besöket i loggfilen
        public VisitLogger(FileManager fileManager) {
            this.fileManager = fileManager;
        }

    // Loggar medlemmens besök genom att skriva information till loggfilen
        public void logVisit(Member member) throws IOException {
            File logFile = fileManager.getVisitLogFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
                String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                writer.write(member.getName() + ", " + member.getSocialSecurityNumber() + " - " + timeStamp);
                writer.newLine();
            }
        }
    }
