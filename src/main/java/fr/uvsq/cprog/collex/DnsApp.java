package fr.uvsq.cprog.collex;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;



public class DnsApp {

  public void run() {
    try {
      Properties p = new Properties();
      FileInputStream in = new FileInputStream("src/main/resources/config.properties");
      p.load(in);
      in.close();
            
      String path = p.getProperty("dnsFile");

      Dns dns = new Dns(path);
      DnsTui tui = new DnsTui(dns);

      while (true) {
        Commande c = tui.nextCommande();
        if (c == null) {
          continue;
        }
        if (c instanceof Quitter) {
          break;
        }
        c.execute();
      }
    } catch (IOException e) {
      System.err.println(
          "Erreur lors de la lecture du fichier de configuration : " + e.getMessage());
    }

  }
    
  public static void main(String[] args) {
    new DnsApp().run();
  }

}
