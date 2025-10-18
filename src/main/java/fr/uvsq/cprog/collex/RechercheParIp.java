package fr.uvsq.cprog.collex;

/**
 * Commande permettant d'effectuer une recherche de nom de machine à partir d'une adresse ip.
 */
public class RechercheParIp implements Commande {

  private final AdresseIp ip;
  private final Dns dns;

  public RechercheParIp(AdresseIp ip, Dns dns) {
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