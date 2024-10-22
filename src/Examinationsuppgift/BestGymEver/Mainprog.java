package Examinationsuppgift.BestGymEver;

import javax.swing.*;
import java.io.IOException;

public class Mainprog {

    private MembershipManager mm;

    // Konstruktor som laddar medlemmar från fil
    public Mainprog() {
        try {
            mm = new MembershipManager("src/Examinationsuppgift/member_data.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Kunde inte läsa in medlemsdata från filen.", "Fel", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Söker efter en medlem genom att låta användaren ange namn eller personnummer
    public void searchMember() {
        boolean continueSearch = true;

        while (continueSearch) {
            String input = JOptionPane.showInputDialog(null, "Ange medlemmens namn eller personnummer:", "Sök medlem", JOptionPane.QUESTION_MESSAGE);

            // Sök medlem baserat på input
            if (input != null && !input.trim().isEmpty()) {
                Member foundMember = mm.findMemberByNameOrNumber(input);

                // Kontrollera om medlemmen är nuvarande eller tidigare medlem
                if (foundMember != null) {
                    String membershipStatus = mm.checkMembershipStatus(foundMember);

                    // Visa medlemmens information
                    JOptionPane.showMessageDialog(null,
                            "Namn: " + foundMember.getName() + "\n" +
                                    "Personnummer: " + foundMember.getSocialSecurityNumber() + "\n" +
                                    "Status: " + membershipStatus, "Medlem funnen", JOptionPane.INFORMATION_MESSAGE);

                    // Logga besök om medlemmen är nuvarande
                    if (foundMember.isCurrentMember()) {
                        try {
                            FileManager fileManager = new FileManager();
                            VisitLogger logger = new VisitLogger(fileManager);
                            logger.logVisit(foundMember);
                            JOptionPane.showMessageDialog(null, "Besöket har loggats.",
                                    "Loggning framgångsrik", JOptionPane.INFORMATION_MESSAGE);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Kunde inte logga besöket.",
                                    "Fel", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                } else {
                    // Medlem hittades inte
                    JOptionPane.showMessageDialog(null,
                            "Ingen medlem hittades med det namnet eller personnumret.",
                            "Medlem ej funnen",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Hantera om användaren klickar avbryt eller anger tom input
                JOptionPane.showMessageDialog(null,
                        "Försök igen.",
                        "Fel",
                        JOptionPane.WARNING_MESSAGE);

            } // Fråga användaren om de vill fortsätta söka
            int val = JOptionPane.showConfirmDialog(null, "Vill du söka efter en annan medlem?", "Fortsätt söka",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            // Om användaren väljer "No" eller stänger dialogrutan, avsluta loopen
            if (val == JOptionPane.NO_OPTION || val == JOptionPane.CLOSED_OPTION) {
                continueSearch = false;
                JOptionPane.showMessageDialog(null, "Sökning avslutad.");
            }
        }
    }

    // Huvudmetod som startar programmet
    public static void main(String[] args) {
        Mainprog mp = new Mainprog();
        if (mp.mm != null) { // Kontrollera att medlemsdata har laddats korrekt
            mp.searchMember(); // Startar medlemsökningen
        }
    }
}
