package Examinationsuppgift.BestGymEver;

import java.time.LocalDate;

public class Member {

    protected String name;
    protected String socialSecurityNumber;
    protected LocalDate lastPaid;

    public Member(String name, String socialSecurityNumber, LocalDate lastPaid) {
        this.name = name;
        this.socialSecurityNumber = socialSecurityNumber;
        this.lastPaid = lastPaid;
    }

    public String getName() {
        return name;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    // Returnerar datumet då medlemsavgiften senast betalades
    public LocalDate getLastPaid() {
        return lastPaid;
    }

    // Kontrollerar om medlemmen är nuvarande medlem
    public boolean isCurrentMember() {
        return lastPaid.isAfter(LocalDate.now().minusYears(1));
    }

    // Kontrollerar om medlemmen är en före detta medlem
    public boolean isFormerMember() {
        return !isCurrentMember();
    }
}
