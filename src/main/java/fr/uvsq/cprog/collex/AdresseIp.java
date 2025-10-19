package fr.uvsq.cprog.collex;

/**
 * Classe représentant une adresse IP valide.
 */

public class AdresseIp {
  private final String ip;

  /**
   * Constructeur de la classe Adresse IP.
   * Initialise une adresse IP après vérification.
   */
  public AdresseIp(String ip) {
    if (ip == null) {
      throw new IllegalArgumentException("Adresse IP ne peut pas être nulle");
    }

    if (ip.isEmpty() || !ip.matches(
        "^((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)\\.){3}"
        + "(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)$")) {
      throw new IllegalArgumentException("Adresse IP invalide : " + ip);
    }

    this.ip = ip;
  }

  public String getIp() {
    return ip;
  }

  @Override
  public boolean equals(Object obj) { 
    if (this == obj) {
      return true;
    }

    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    AdresseIp other = (AdresseIp) obj;
    return ip.equals(other.ip);
  }

  @Override
  public int hashCode() {
    return ip.hashCode();
  }

  public String toString() {
    return ip;
  }
}
