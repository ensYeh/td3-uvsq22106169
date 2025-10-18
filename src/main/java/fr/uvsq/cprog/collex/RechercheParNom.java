package fr.uvsq.cprog.collex;

/**
 * Commande permettant d'effectuer une recherche d'adresse IP à partir d'un nom de machine.
 */
public class RechercheParNom implements Commande {

  private final NomMachine nommachine;
  private final Dns dns;

  public RechercheParNom(NomMachine nommachine, Dns dns) {
    this.nommachine = nommachine;
    this.dns = dns;
  }

  @Override
  public void execute() {
    AdresseIp ip = dns.getItem(nommachine);
    if (ip != null) {
      System.out.println(ip);
    } else {
      System.out.println("Machine inconnue : " + nommachine);
    }
  }
}