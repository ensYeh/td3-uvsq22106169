package fr.uvsq.cprog.collex;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.Test;


/**
 * Classe de tests unitaires pour la commande RechercheParNom.
 */
public class RechercheParNomTest {

  @Test
  public void test_verification() throws IOException {
    File tempFile = File.createTempFile("Test_temp", ".txt");
    tempFile.deleteOnExit();
    String path = tempFile.getAbsolutePath();

    Dns dns = new Dns(path);

    AdresseIp ip = new AdresseIp("10.0.0.1");
    NomMachine nm = new NomMachine("www", "laposte.net");
    

    dns.addItem("10.0.0.1", "www", "laposte.net");

    RechercheParNom r = new RechercheParNom(nm, dns);

    ByteArrayOutputStream o = new ByteArrayOutputStream();
    System.setOut(new PrintStream(o));

    r.execute();
    assertEquals("10.0.0.1" + System.lineSeparator(), o.toString());
  }

  @Test
  public void test_machine_existante() throws IOException {
    File tempFile = File.createTempFile("Test_temp", ".txt");
    tempFile.deleteOnExit();
    String path = tempFile.getAbsolutePath();

    Dns dns = new Dns(path);
    AdresseIp ip = new AdresseIp("10.0.0.1");
    
    NomMachine nm = new NomMachine("www", "laposte.net");
    NomMachine inconnue = new NomMachine("www", "gouv.com");
    

    dns.addItem("10.0.0.1", "www", "laposte.net");

    RechercheParNom r = new RechercheParNom(inconnue, dns);

    ByteArrayOutputStream o = new ByteArrayOutputStream();
    System.setOut(new PrintStream(o));

    r.execute();
    assertEquals("Machine inconnue : www.gouv.com" + System.lineSeparator(), o.toString());

  }
}