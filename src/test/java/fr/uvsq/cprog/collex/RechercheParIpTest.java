package fr.uvsq.cprog.collex;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.Test;


/**
 * Classe de tests unitaires pour la commande RechercheParIP.
 */
public class RechercheParIpTest {

  @Test
  public void test_verification() throws IOException {
    File tempFile = File.createTempFile("Test_temp", ".txt");
    tempFile.deleteOnExit();
    String path = tempFile.getAbsolutePath();

    Dns dns = new Dns(path);
    AdresseIp ip = new AdresseIp("10.0.0.1");
    NomMachine nm = new NomMachine("www", "laposte.net");
    

    dns.addItem("10.0.0.1", "www", "laposte.net");

    RechercheParIp r = new RechercheParIp(ip, dns);

    ByteArrayOutputStream o = new ByteArrayOutputStream();
    System.setOut(new PrintStream(o));

    r.execute();
    assertEquals("www.laposte.net" + System.lineSeparator(), o.toString());
  }

  @Test
  public void test_addresseip_existante() throws IOException{
    File tempFile = File.createTempFile("Test_temp", ".txt");
    tempFile.deleteOnExit();
    String path = tempFile.getAbsolutePath();

    Dns dns = new Dns(path);
    AdresseIp ip = new AdresseIp("10.0.0.1");
    AdresseIp inconnue = new AdresseIp("10.0.0.2");
    NomMachine nm = new NomMachine("www", "laposte.net");
    

    dns.addItem("10.0.0.1", "www", "laposte.net");

    RechercheParIp r = new RechercheParIp(inconnue, dns);

    ByteArrayOutputStream o = new ByteArrayOutputStream();
    System.setOut(new PrintStream(o));

    r.execute();
    assertEquals("IP inconnue : 10.0.0.2" + System.lineSeparator(), o.toString());

  }
}