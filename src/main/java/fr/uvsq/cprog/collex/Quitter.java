package fr.uvsq.cprog.collex;

/**
 * Classe qui permet de quitter l'application.
 */
public class Quitter implements Commande {

  @Override
  public void execute() {
    System.exit(0);
  }
}