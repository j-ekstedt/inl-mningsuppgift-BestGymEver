package Examinationsuppgift.BestGymEver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MembershipManager {

    private List<Member> members = new ArrayList<>();

    // Konstruktor som laddar in medlemmar från en fil vid skapandet av objektet
    public MembershipManager(String filePath) throws IOException {
        this.members = loadCustomers(filePath); // Laddar in medlemmar från fil
    }

    // Laddar medlemmar från en fil och returnerar en lista med Member-objekt
    public List<Member> loadCustomers(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/Examinationsuppgift/member_data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(", "); // Splittar filraden på ", " för att extrahera personnummer och namn
                String socialSecurityNumber = details[0];
                String name = details[1];
                LocalDate lastPaid = LocalDate.parse(reader.readLine());
                members.add(new Member(name, socialSecurityNumber, lastPaid));
            }
        }
        return members;
    }

    // Kontrollerar medlemsstatus och returnerar nuvarande medlem eller fd medlem
    public String checkMembershipStatus(Member members) {
        if (members.isCurrentMember()) {
            return "Nuvarande medlem";
        } else if (members.isFormerMember()) {
            return "Före detta medlem";
        } else {
            return "Obehörig";
        }
    }

    // Söker efter en medlem med ett  personnummer
    public Member findMemberBySocialSecurityNumber(String socialSecurityNumber) {
        for (Member member : members) {
            if (member.getSocialSecurityNumber().equals(socialSecurityNumber)) {
                return member;
            }
        }
        return null;
    }

    // Söker efter en medlem med ett  namn
    public Member findMemberByName(String name) {
        for (Member member : members) {
            if (member.getName().equalsIgnoreCase(name)) {
                return member;
            }
        }
        return null;
    }


    // Söker efter medlem antingen med namn eller personnummer
    public Member findMemberByNameOrNumber(String input) {
        Member member = findMemberByName(input);
        if (member == null) {
            member = findMemberBySocialSecurityNumber(input);
        }
        return member;
    }

}
