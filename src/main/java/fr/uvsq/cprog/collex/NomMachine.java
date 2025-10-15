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

  public String getNommachine() {
    return nommachine;
  }

  public String getDomaine() {
    return domaine;
  }

  public String getNomQualifie() {
    return nommachine + "." + domaine;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    NomMachine nm = (NomMachine) obj;
    return getNomQualifie().equals(nm.getNomQualifie());
  }

  @Override
  public int hashCode() {
    return getNomQualifie().hashCode();
  }

  public String toString() {
    return getNomQualifie();
  }
  
}
