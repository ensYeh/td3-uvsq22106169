package fr.uvsq.cprog.collex;

import java.util.Comparator;
import java.util.List;

/**
 * Commande permettant d'effectuer une recherche par domaine.
 */

public class RechercheParDomaine implements Commande {

  private final Dns dns;
  private final String domaine;
  private final boolean trieparAdresse; 

  /**
   * Constructeur de la commande de recherche par domaine.
   */
  public RechercheParDomaine(Dns dns, String domaine, boolean trieparAdresse) {

    this.dns = dns;
    this.domaine = domaine;
    this.trieparAdresse = trieparAdresse;
  }

  /**
   * Exécute la commande de recherche et affiche les résultats.
   */
  @Override
  public void execute() {
    List<DnsItem> items = dns.getItems(domaine);

    if (items.isEmpty()) {
      System.out.println("Aucune machine trouvée pour le domaine " + domaine);
      return;
    }

    if (trieparAdresse) {
      items.sort((a, b) -> {
        String[] ipA = a.getAdresseIpobj().getIp().split("\\.");
        String[] ipB = b.getAdresseIpobj().getIp().split("\\.");
        for (int i = 0; i < 4; i++) {
          int ia = Integer.parseInt(ipA[i]);
          int ib = Integer.parseInt(ipB[i]);
          if (ia != ib) {
            return Integer.compare(ia, ib);
          }
        }
        return 0;
      });
        
    } else {
      items.sort(Comparator.comparing(item -> item.getNomMachineobj().getNommachine()));
    }

    for (DnsItem item : items) {
      System.out.println(item.getAdresseIpobj() + " " +  item.getNomMachineobj());
    }
  }
}