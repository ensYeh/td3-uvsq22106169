package fr.uvsq.cprog.collex;

public class AdresseIP {
  private final String ip;

  public AdresseIP(String ip) {
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
  
  
  

}
