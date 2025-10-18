package fr.uvsq.cprog.collex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.junit.Test;


/**
 * Classe de tests unitaires pour la classe Dns.
 */

public class DnsTest {
    
  Dns dns;

  {
    Properties p = new Properties();
    try (FileInputStream in = new FileInputStream("src/main/resources/config.properties")) {
      p.load(in);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    String path = p.getProperty("dnsFile");
    dns = new Dns(path);
  }

  @Test
  public void test_recherche_ip() {
    AdresseIp ipRecherche = new AdresseIp("193.51.25.12");
    NomMachine machineTrouvee = dns.getItem(ipRecherche);
    assertEquals("ecampus.uvsq.fr", machineTrouvee.getNomQualifie());
  }

  @Test
  public void test_recherche_machine() {
    NomMachine machineRecherche = new NomMachine("www", "uvsq.fr");
    AdresseIp ipTrouvee = dns.getItem(machineRecherche);
    assertEquals("193.51.31.90", ipTrouvee.getIp());
  }

  @Test
  public void test_ip_inexistante() {
    AdresseIp ipRecherche = new AdresseIp("192.168.43.241");
    NomMachine machineTrouvee = dns.getItem(ipRecherche);
    assertNull(machineTrouvee);

  }

  @Test
  public void test_machine_inexistante() {
    NomMachine machineRecherche = new NomMachine("mauvaise", "domaine.fr");
    AdresseIp ipTrouvee = dns.getItem(machineRecherche);
    assertNull(ipTrouvee);
  }

  @Test
  public void test_par_domaine() {
    List<DnsItem> items = dns.getItems("uvsq.fr");

    assertFalse(items.isEmpty());

    for (DnsItem item : items) {
      assertEquals("uvsq.fr", item.getNomMachineobj().getDomaine());
    }

  }

  @Test
  public void test_addItem() throws IOException {
    String tempFile = "test_dns.txt";

    Dns dnsTest = new Dns(tempFile);

    dnsTest.addItem("192.168.1.100", "nouvelleMachine", "test.fr");

    AdresseIp ip = new AdresseIp("192.168.1.100");
    NomMachine nm = dnsTest.getItem(ip);
    assertNotNull(nm);
    assertEquals("nouvelleMachine.test.fr", nm.getNomQualifie());

    File f = new File(tempFile);
    if (f.exists()) {
      f.delete();
    }

  }

  @Test(expected = IllegalArgumentException.class)
  public void test_addItem_exception_ip() throws IOException {
    String tempFile = "test_dns_exception_ip.txt";
    File f = new File(tempFile);

    try {
      Dns dnsTest = new Dns(tempFile);
      dnsTest.addItem("192.168.1.101", "machineTest", "test.fr");
      dnsTest.addItem("192.168.1.101", "autreMachine", "test.fr");
    } finally {
      if (f.exists()) {
        f.delete();
      }
    }
       
  }

  @Test(expected = IllegalArgumentException.class)
  public void test_addItem_exception_machine() throws IOException {
    String tempFile = "test_dns_exception_machine.txt";
    File f = new File(tempFile);
    try {
      Dns dnsTest = new Dns(tempFile);
      dnsTest.addItem("192.168.1.100", "machineTest", "test.fr");
      dnsTest.addItem("192.168.1.102", "machineTest", "test.fr");
    } finally {
      if (f.exists()) {
        f.delete();
      }
    }
  }

}