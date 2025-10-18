package fr.uvsq.cprog.collex;

import java.util.Scanner;

public class DnsTui {

  private final Scanner scanner = new Scanner(System.in);
  private final Dns dns;

  public DnsTui(Dns dns) {
    this.dns = dns;
  }

  public Commande nextCommande() {
    System.out.print("> ");
    String ligne = scanner.nextLine().trim();
        

    if (ligne.isEmpty()) {
      return null;
    }

    String[] parts = ligne.split("\\s+");
       

    try {
      if (parts[0].equalsIgnoreCase("ls")) {
        boolean trieParAdresse = parts.length == 3 && parts[1].equals("-a");
        String domaine;
        if (trieParAdresse) {
          domaine = parts[2];
        } else {
          domaine = parts[1];
        }

        return new RechercheParDomaine(dns, domaine, trieParAdresse);
      } else if (parts[0].equalsIgnoreCase("add") && parts.length == 3) {
        String ip = parts[1];
        String nomqualifie = parts[2];

        int indexPoint = nomqualifie.indexOf('.');
        if (indexPoint == -1) {
          affiche("Nom qualifié invalide : " + nomqualifie);
          return null;
        }

        String nom = nomqualifie.substring(0, indexPoint);
        String domaine = nomqualifie.substring(indexPoint + 1);

        return new AjouterItem(dns, ip, nom, domaine);

      } else if (ligne.matches("^\\d+\\.\\d+\\.\\d+\\.\\d+$")) {
        return new RechercheParIp(new AdresseIP(ligne), dns);

      } else if (ligne.contains(".")) {
        int indexPoint = ligne.indexOf('.');
        String nom = ligne.substring(0, indexPoint);
        String domaine = ligne.substring(indexPoint + 1);

        return new RechercheParNom(new NomMachine(nom, domaine), dns);

      } else if (parts[0].equalsIgnoreCase("quit")) {
        return new Quitter();
      } else {
        affiche("Commande inconnue : " + ligne);
        return null;
      }

    } catch (IllegalArgumentException e) {
      affiche("Erreur : " + e.getMessage());
      return null;
    }
  }

  public void affiche(String message) {
    System.out.println(message);
  }
}