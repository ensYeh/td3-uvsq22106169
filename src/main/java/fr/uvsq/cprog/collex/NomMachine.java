package fr.uvsq.cprog.collex;

public class NomMachine {
  private final String nommachine;
  private final String domaine;

  public NomMachine(String nommachine, String domaine) {
    if (domaine == null || nommachine == null || nommachine.isEmpty() || domaine.isEmpty() ) {
      throw new IllegalArgumentException("Le domaine et le nom ne peuvent être vide ou null");
    }
    
    this.nommachine = nommachine;
    this.domaine = domaine;

  }
}
