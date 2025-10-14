package fr.uvsq.cprog.collex;


import java.io.*;
import java.util.*;


public class Dns{

    private final List<DnsItem> enregistrements = new ArrayList<>();
    
    public Dns(String path) {
        try {
            FileInputStream file = new FileInputStream(path);
            Scanner scanner = new Scanner(file);
            
            while(scanner.hasNextLine()) {
                String ligne = scanner.nextLine().trim(); 
                if (ligne.isEmpty()) {
                    continue;
                }
                String[] parts = ligne.split("\\s+");
                if (parts.length != 2) {
                    System.err.println("Format invalide");
                    continue;
                }

                String ip = parts[0];
                String nomQualifie = parts[1];

                int indexPoint = nomQualifie.indexOf('.');
                if (indexPoint == -1) {
                    System.err.println("Nom qualifié invalide : " + nomQualifie);
                    continue;   
                }

                String nom = nomQualifie.substring(0, indexPoint);
                String domaine = nomQualifie.substring(indexPoint + 1);

                try {
                    AdresseIP adresse = new AdresseIP(ip);
                    NomMachine machine = new NomMachine(nom,domaine);
                    DnsItem item = new DnsItem(machine,adresse);
                    enregistrements.add(item);

                } catch (IllegalArgumentException e) {
                    System.err.println("Erreur dans la ligne : " + ligne + " → " + e.getMessage());
                }
                
            }
            
        } catch (IOException e) {
            throw new RuntimeException("erreur lors de la lecture" + path,e);

        }

    }

    public List<DnsItem> getEnregistrements() {
        return enregistrements;
    }

    public DnsItem getItem(AdresseIP ip) {
        for (DnsItem item : enregistrements) {
            if (item.getIp().equals(ip)) {
                return item;
            }
        }
        return null;
    }
    
    public DnsItem getItem(NomMachine machine) {
        for (DnsItem item : enregistrements) {
            if (item.getNommachine().equals(machine)) {
                return item;
            }
        }
        return null;
    }

    public List<DnsItem> getItems(String domaine) {
        List<DnsItem> result = new ArrayList<>();
        for (DnsItem item : enregistrements) {
            if (item.getNommachine().getDomaine().equals(domaine)) {
                result.add(item);
            }
        }
        return result;

    }

    public void addItem(String ipstr, String nom, String domaine ) {
        AdresseIP ip = new AdresseIP(ipstr);
        NomMachine machine = new NomMachine(nom, domaine);
        for (DnsItem item : enregistrements) {
            if (item.getIp().equals(ip)) {
                throw new IllegalArgumentException("Erreur : l'adresse IP " + ip + " existe déjà.");
            }

            if (item.getNommachine().equals(machine)) {
                throw new IllegalArgumentException("Erreur : la machine " + machine + " existe déjà dans la base.");
            }
            
        }
        DnsItem item = new DnsItem(machine,ip);
        enregistrements.add(item);
        System.out.println("Ajout réussi : " + item);

    }
    
}