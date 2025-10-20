package fr.uvsq.cprog.collex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * Classe de tests unitaires pour la classe DnsItem.
 */

public class DnsItemTest {
    
  @Test(expected = IllegalArgumentException.class) 
  public void test_nommachine_null() {
    new DnsItem(null, new AdresseIp("193.51.31.90"));    
  }

  @Test(expected = IllegalArgumentException.class)
  public void test_adresseIp_null() {
    new DnsItem(new NomMachine("www", "uvsq.fr"), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void test_adresseIp_Nommachine_null() {
    new DnsItem(null, null);
  }

  @Test
  public void test_getters() {
    NomMachine nm = new NomMachine("www", "uvsq.fr");
    AdresseIp ip = new AdresseIp("193.51.31.90");
    DnsItem item = new DnsItem(nm, ip);

    assertEquals(nm, item.getNomMachineobj());
    assertEquals(ip, item.getAdresseIpobj());
  }

  @Test
  public void test_toString() {
    NomMachine nm = new NomMachine("www", "uvsq.fr");
    AdresseIp ip = new AdresseIp("193.51.31.90");
    DnsItem item = new DnsItem(nm, ip);

    assertEquals("193.51.31.90 www.uvsq.fr", item.toString());
  }

  @Test
  public void test_equals_hashCode() {
    DnsItem item1 = new DnsItem(new NomMachine("www", "uvsq.fr"), new AdresseIp("193.51.31.90"));
    DnsItem item2 = new DnsItem(new NomMachine("www", "uvsq.fr"), new AdresseIp("193.51.31.90"));
    DnsItem item3 = new DnsItem(new NomMachine("poste", "uvsq.fr"), new AdresseIp("193.51.31.90"));

    assertEquals(item1, item2);
    assertEquals(item1.hashCode(), item2.hashCode());
    assertNotEquals(item1, item3);
  }

}