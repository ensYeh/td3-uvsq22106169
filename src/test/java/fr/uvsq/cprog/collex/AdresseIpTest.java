package fr.uvsq.cprog.collex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Classe de tests unitaires pour la classe AdresseIP.
 */
public class AdresseIpTest {

  @Test(expected = IllegalArgumentException.class)
  public void test_adresse_vide() {
    new AdresseIp("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void test_adresse_nulle() {
    new AdresseIp(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void test_adresse_non_valide_lettre() {
    new AdresseIp("193.Ab.12.7");
  }

  @Test(expected = IllegalArgumentException.class)
  public void test_adresse_non_valide_nombre() {
    new AdresseIp("256.100.60.12");
  }


  @Test
  public void test_adresse_valide() {
    AdresseIp ip = new AdresseIP("193.51.25.12");
    assertEquals("193.51.25.12", ip.getIp());
  }

  @Test
  public void test_equals() {
    AdresseIp ip1 = new AdresseIp("192.51.25.12");
    AdresseIp ip2 = new AdresseIp("192.51.25.12");
    AdresseIp ip3 = new AdresseIp("10.0.0.1");

    assertTrue(ip1.equals(ip2));
    assertFalse(ip1.equals(ip3));
        
  }

  @Test
  public void test_hashCode() {
    AdresseIp ip1 = new AdresseIp("192.168.0.1");
    AdresseIp ip2 = new AdresseIp("192.168.0.1");
    AdresseIp ip3 = new AdresseIp("192.168.1.0");
    assertEquals(ip1.hashCode(), ip2.hashCode());
    assertNotEquals(ip1.hashCode(), ip3.hashCode());
  }

  @Test
  public void test_toString() {
    AdresseIp ip = new AdresseIp("10.0.0.1");
    assertEquals("10.0.0.1", ip.toString());
  }
}