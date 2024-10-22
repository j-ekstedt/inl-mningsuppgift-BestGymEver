package Examinationsuppgift.BestGymEver.Test;

import Examinationsuppgift.BestGymEver.Member;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class MemberTest {

    // Testar om en medlem som just har registrerats är en nuvarande medlem
    @Test
    void isCurrentMemberTest() {
        Member member = new Member("Jane Doe", "0308120001", LocalDate.now());
        assertTrue(member.isCurrentMember());
        assertFalse(member.isFormerMember());
    }

    // Testar om en medlem med utgånget medlemskap klassas som en tidigare medlem
    @Test
    void isFormerMemberTest() {
        Member member = new Member("John Doe", "9702250010", LocalDate.now().minusYears(1).minusDays(1));
        assertFalse(member.isCurrentMember());
        assertTrue(member.isFormerMember());
    }

    // Testar om en medlem är nuvaradne medlem när medlemskapet precis gått ut
    @Test
    void dueDateTest() {
        Member member = new Member("Test Testsson", "8412220200", LocalDate.now().minusYears(1));
        assertTrue(member.isFormerMember());
        assertFalse(member.isCurrentMember());
    }
}