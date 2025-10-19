package fr.uvsq.cprog.collex;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe représentant une base de données DNS.
 */
public class Dns {

  private final List<DnsItem> enregistrements = new ArrayList<>();
  private final String path;
    
  /**
   * Constructeur de la classe DNS.
   */
  public Dns(String path) {
    this.path = path;

    try {

      File f = new File(path);
      if (!f.exists()) {
        f.createNewFile();
      }

      try (FileInputStream file = new FileInputStream(f);
           Scanner scanner = new Scanner(file)) {
                
        while (scanner.hasNextLine()) {
          String ligne = scanner.nextLine().trim(); 
          if (ligne.isEmpty()) {
            continue;
          }
          String[] parts = ligne.split("\\s+");
          if (parts.length != 2) {
            System.err.println("Format invalide");
            continue;
          }

          String ip = parts[0];
          String nomQualifie = parts[1];

          int indexPoint = nomQualifie.indexOf('.');
          if (indexPoint == -1) {
            System.err.println("Nom qualifié invalide : " + nomQualifie);
            continue;   
          }

          String nom = nomQualifie.substring(0, indexPoint);
          String domaine = nomQualifie.substring(indexPoint + 1);

          try {
            AdresseIp adresse = new AdresseIp(ip);
            NomMachine machine = new NomMachine(nom, domaine);
            DnsItem item = new DnsItem(machine, adresse);
            enregistrements.add(item);

          } catch (IllegalArgumentException e) {
            System.err.println("Erreur dans la ligne : " + ligne + " → " + e.getMessage());
          }
                    
        }
        
      }
    } catch (IOException e) {
      throw new RuntimeException("erreur lors de la lecture" + path, e);

    }

  }

  /**
   * Retourne la liste complète des machines DNS.
   */
  public List<DnsItem> getEnregistrements() {
    return enregistrements;
  }

  /**
   * Recherche un nom de machine correspondant à une adresse IP donnée.
   */
  public NomMachine getItem(AdresseIp ip) {
    for (DnsItem item : enregistrements) {
      if (item.getAdresseIpobj().equals(ip)) {
        return item.getNomMachineobj();
      }
    }
    return null;
  }
    
  /**
   * Recherche une adresse IP correspondant à un nom de machine donné.
   */

  public AdresseIp getItem(NomMachine machine) {
    for (DnsItem item : enregistrements) {
      if (item.getNomMachineobj().equals(machine)) {
        return item.getAdresseIpobj();
      }
    }
    return null;
  }

  /**
   * Recherche de toutes les machines appartenant à un domaine donné.
   */
  public List<DnsItem> getItems(String domaine) {
    List<DnsItem> result = new ArrayList<>();
    for (DnsItem item : enregistrements) {
      if (item.getNomMachineobj().getDomaine().equals(domaine)) {
        result.add(item);
      }
    }
    return result;
  }

  /**
  * Ajoute une nouvelle machine avec son nom et son adresse IP.
  */
  public void addItem(String ipstr, String nom, String domaine) {
    AdresseIp ip = new AdresseIp(ipstr);
    NomMachine machine = new NomMachine(nom, domaine);
    for (DnsItem item : enregistrements) {
      if (item.getAdresseIpobj().equals(ip)) {
        throw new IllegalArgumentException("Erreur : l'adresse IP " + ip + " existe déjà.");
      }

      if (item.getNomMachineobj().equals(machine)) {
        throw new IllegalArgumentException("Erreur : la machine " + machine + " existe déjà.");
      }
            
    }
    DnsItem item = new DnsItem(machine, ip);
    enregistrements.add(item);
        
    try (BufferedWriter w = new BufferedWriter(new FileWriter(this.path, true))) {
      w.write(ipstr + " " + nom + "." + domaine + System.lineSeparator());
    } catch (IOException e) {
      System.err.println("Erreur lors de l'écriture dans le fichier : " + e.getMessage());
    }
  }
    
}