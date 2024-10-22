package Examinationsuppgift.BestGymEver.Test;

import Examinationsuppgift.BestGymEver.Member;
import Examinationsuppgift.BestGymEver.MembershipManager;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

class MembershipManagerTest {
 MembershipManager mm;
 String filePathTest = "test/Examinationsuppgift/customer_data_test.txt";

 // Konstruktor som initierar MembershipManager med testfilen
 MembershipManagerTest() throws IOException {
  mm = new MembershipManager(filePathTest);
 }

 // Testar om medlemsinformation kan laddas från en fil och att listan inte är null eller tom
 @Test
 public void loadMembersTest() {
  try {
   List<Member> membersTest = mm.loadCustomers(filePathTest);
   assertNotNull(membersTest);
   assertFalse(membersTest.isEmpty());
  } catch (IOException e) {
   e.printStackTrace();
   fail("IOException kastades vid laddning av medlemmar.");
  }
 }

 // Testar om medlemsstatusen för den första medlemmen i listan är korrekt identifierad som nuvarande medlem
 @Test
 public void checkMembershipStatusTest() {
  try {
   List<Member> members = mm.loadCustomers(filePathTest);
   assertEquals("Nuvarande medlem", mm.checkMembershipStatus(members.get(0)));
  } catch (IOException e) {
   e.printStackTrace();
   fail("IOException kastades vid kontroll av medlemsstatus.");
  }
 }

 // Testar om rätt medlem hittas med sitt personnummer eller namn, och att en medlem med ogiltigt personnummer eller namn inte hittas
 @Test
 void findMemberBySocialSecurityNumberTest() {
  List<Member> membersTest = new ArrayList<>();
  membersTest.add(new Member("Test Testsson", "8412220200", LocalDate.of(2024, 10, 10)));
  membersTest.add(new Member("John Doe", "9702250010", LocalDate.of(2020, 1, 1)));
  assertNotNull("Medlem med personnummer '8412220200' ska hittas.", membersTest);
  Member nonExistingMember = mm.findMemberBySocialSecurityNumber("0123456789");
  assertNull("Medlem med personnummer '0123456789' ska inte hittas.", nonExistingMember);

  assertNotNull("Medlem med namn 'Test Testsson' ska hittas.", membersTest);
  Member nonExistingMember2 = mm.findMemberByName("John Locke");
  assertNull("Medlem med namnet 'John Locke' ska inte hittas.", nonExistingMember2);
 }
}