package fr.uvsq.cprog.collex;

/**
 * Commande permettant d'ajouter un élément dans le DNS.
 */
public class AjouterItem implements Commande {

  private final Dns dns;
  private final String domaine;
  private final String ip;
  private final String nom;

  /**
   * Constructeur d'AjouterItem.
   * Initialise un DNS, une adresse IP, un nom et le domaine de la machine.
   */
  public AjouterItem(Dns dns, String ip, String nom, String domaine) {
    this.dns = dns;
    this.ip = ip;
    this.nom = nom;
    this.domaine = domaine;
  }

  @Override
  public void execute() {
    try {
      dns.addItem(ip, nom, domaine);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }
}