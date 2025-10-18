package fr.uvsq.cprog.collex;

/**
 * Commande permettant d'effectuer une recherche de nom de machine à partir d'une adresse ip.
 */
public class RechercheParIP implements Commande {

  private final AdresseIP ip;
  private final Dns dns;

  public RechercheParIP(AdresseIP ip, Dns dns) {
    this.ip = ip;
    this.dns = dns;
  }

  @Override
  public void execute() {
    NomMachine machine = dns.getItem(ip);
    if (machine != null) {
      System.out.println(machine);
    } else {
      System.out.println("IP inconnue : " + ip);
    }
  }
}