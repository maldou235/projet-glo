Fichier 1: ClasseVoyage.java
java
package enums;

public enum ClasseVoyage {
    PREMIERE("1ère Classe", 1.5),
    SECONDE("2e Classe", 1.0),
    WAGON_LIT("Wagon Lit", 2.5);
    
    private String libelle;
    private double coefficientPrix;
    
    ClasseVoyage(String libelle, double coefficientPrix) {
        this.libelle = libelle;
        this.coefficientPrix = coefficientPrix;
    }
    
    public String getLibelle() { return libelle; }
    public double getCoefficientPrix() { return coefficientPrix; }
}
Fichier 2: StatutReservation.java
java
package enums;

public enum StatutReservation {
    EN_ATTENTE,
    CONFIRMEE,
    ANNULEE,
    REMBOURSEE
}
Fichier 3: StatutTicket.java
java
package enums;

public enum StatutTicket {
    VALIDE,
    UTILISE,
    REMBOURSE,
    ANNULE
}
Fichier 4: TypeReclamation.java
java
package enums;

public enum TypeReclamation {
    PLAINTE,
    RECLAMATION,
    SUGGESTION
}
Fichier 5: StatutReclamation.java
java
package enums;

public enum StatutReclamation {
    EN_ATTENTE,
    EN_COURS,
    TRAITEE
}
Fichier 6: Horaires.java
java
package models;

import java.time.LocalDateTime;
import java.time.Duration;

public class Horaires {
    private LocalDateTime heureDepart;
    private LocalDateTime heureArrivee;
    private LocalDateTime date;
    
    public Horaires(LocalDateTime heureDepart, LocalDateTime heureArrivee, LocalDateTime date) {
        this.heureDepart = heureDepart;
        this.heureArrivee = heureArrivee;
        this.date = date;
    }
    
    public long getDuree() {
        return Duration.between(heureDepart, heureArrivee).toMinutes();
    }
    
    public LocalDateTime getHeureDepart() { return heureDepart; }
    public void setHeureDepart(LocalDateTime heureDepart) { this.heureDepart = heureDepart; }
    public LocalDateTime getHeureArrivee() { return heureArrivee; }
    public void setHeureArrivee(LocalDateTime heureArrivee) { this.heureArrivee = heureArrivee; }
    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
    
    @Override
    public String toString() {
        return String.format("Départ: %s, Arrivée: %s, Durée: %d minutes", 
            heureDepart, heureArrivee, getDuree());
    }
}
Fichier 7: Arret.java
java
package models;

public class Arret {
    private String nom;
    private Train train;
    private int positionOrdre;
    private Horaires horaires;
    
    public Arret(String nom, int positionOrdre, Horaires horaires) {
        this.nom = nom;
        this.positionOrdre = positionOrdre;
        this.horaires = horaires;
    }
    
    public Arret(String nom, int positionOrdre) {
        this.nom = nom;
        this.positionOrdre = positionOrdre;
    }
    
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public Train getTrain() { return train; }
    public void setTrain(Train train) { this.train = train; }
    public int getPositionOrdre() { return positionOrdre; }
    public void setPositionOrdre(int positionOrdre) { this.positionOrdre = positionOrdre; }
    public Horaires getHoraires() { return horaires; }
    public void setHoraires(Horaires horaires) { this.horaires = horaires; }
    
    @Override
    public String toString() {
        if (horaires != null) {
            return String.format("Arrêt: %s (Position %d) - %s", nom, positionOrdre, horaires);
        }
        return String.format("Arrêt: %s (Position %d)", nom, positionOrdre);
    }
}
Fichier 8: Train.java
java
package models;

import java.util.ArrayList;
import java.util.List;
import enums.ClasseVoyage;

public class Train {
    private String numeroTrain;
    private String nomTrain;
    private ClasseVoyage categorieClasse;
    private List<Arret> arrets;
    
    public Train(String numeroTrain, String nomTrain, ClasseVoyage categorieClasse) {
        this.numeroTrain = numeroTrain;
        this.nomTrain = nomTrain;
        this.categorieClasse = categorieClasse;
        this.arrets = new ArrayList<>();
    }
    
    public void ajouterArret(Arret arret, int position) {
        arret.setPositionOrdre(position);
        arret.setTrain(this);
        arrets.add(arret);
    }
    
    public List<Arret> getArrets() {
        return arrets;
    }
    
    public String getNumeroTrain() { return numeroTrain; }
    public void setNumeroTrain(String numeroTrain) { this.numeroTrain = numeroTrain; }
    public String getNomTrain() { return nomTrain; }
    public void setNomTrain(String nomTrain) { this.nomTrain = nomTrain; }
    public ClasseVoyage getCategorieClasse() { return categorieClasse; }
    public void setCategorieClasse(ClasseVoyage categorieClasse) { this.categorieClasse = categorieClasse; }
    
    @Override
    public String toString() {
        return String.format("Train %s - %s (%s)", numeroTrain, nomTrain, categorieClasse.getLibelle());
    }
}
Fichier 9: Siege.java
java
package models;

import enums.ClasseVoyage;

public class Siege {
    private int numeroSiege;
    private int numeroWagon;
    private ClasseVoyage classe;
    private boolean estOccupe;
    
    public Siege(int numeroSiege, int numeroWagon, ClasseVoyage classe) {
        this.numeroSiege = numeroSiege;
        this.numeroWagon = numeroWagon;
        this.classe = classe;
        this.estOccupe = false;
    }
    
    public int getNumeroSiege() { return numeroSiege; }
    public void setNumeroSiege(int numeroSiege) { this.numeroSiege = numeroSiege; }
    public int getNumeroWagon() { return numeroWagon; }
    public void setNumeroWagon(int numeroWagon) { this.numeroWagon = numeroWagon; }
    public ClasseVoyage getClasse() { return classe; }
    public void setClasse(ClasseVoyage classe) { this.classe = classe; }
    public boolean isEstOccupe() { return estOccupe; }
    public void setEstOccupe(boolean estOccupe) { this.estOccupe = estOccupe; }
    
    public void reserver() {
        if (!estOccupe) {
            this.estOccupe = true;
        } else {
            throw new IllegalStateException("Ce siège est déjà occupé");
        }
    }
    
    public void liberer() {
        this.estOccupe = false;
    }
    
    @Override
    public String toString() {
        return String.format("Wagon %d, Siège %d (%s) - %s", 
            numeroWagon, numeroSiege, classe.getLibelle(), 
            estOccupe ? "Occupé" : "Libre");
    }
}
Fichier 10: Voyageur.java
java
package models;

import java.util.ArrayList;
import java.util.List;

public class Voyageur {
    private String idVoyageur;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private List<Reservation> reservations;
    
    public Voyageur(String idVoyageur, String nom, String prenom, String email, String telephone) {
        this.idVoyageur = idVoyageur;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.reservations = new ArrayList<>();
    }
    
    public void ajouterReservation(Reservation reservation) {
        reservations.add(reservation);
        reservation.setVoyageur(this);
    }
    
    public List<Reservation> getReservations() {
        return reservations;
    }
    
    public String getIdVoyageur() { return idVoyageur; }
    public void setIdVoyageur(String idVoyageur) { this.idVoyageur = idVoyageur; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    
    @Override
    public String toString() {
        return String.format("Voyageur: %s %s (%s)", prenom, nom, email);
    }
}
Fichier 11: Agence.java
java
package models;

import java.util.ArrayList;
import java.util.List;

public class Agence {
    private String idAgence;
    private String nomAgence;
    private String adresse;
    private String telephone;
    private String ville;
    private List<PointDeVente> pointsDeVente;
    private List<Reclamation> reclamations;
    
    public Agence(String idAgence, String nomAgence, String adresse, String telephone, String ville) {
        this.idAgence = idAgence;
        this.nomAgence = nomAgence;
        this.adresse = adresse;
        this.telephone = telephone;
        this.ville = ville;
        this.pointsDeVente = new ArrayList<>();
        this.reclamations = new ArrayList<>();
    }
    
    public void ajouterPointDeVente(PointDeVente pointDeVente) {
        pointsDeVente.add(pointDeVente);
        pointDeVente.setAgence(this);
    }
    
    public List<PointDeVente> getPointsDeVente() {
        return pointsDeVente;
    }
    
    public void recevoirReclamation(Reclamation reclamation) {
        reclamations.add(reclamation);
    }
    
    public List<Reclamation> getReclamations() {
        return reclamations;
    }
    
    public String getIdAgence() { return idAgence; }
    public void setIdAgence(String idAgence) { this.idAgence = idAgence; }
    public String getNomAgence() { return nomAgence; }
    public void setNomAgence(String nomAgence) { this.nomAgence = nomAgence; }
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }
    
    @Override
    public String toString() {
        return String.format("Agence: %s - %s, %s", nomAgence, ville, telephone);
    }
}
Fichier 12: PointDeVente.java
java
package models;

import java.util.ArrayList;
import java.util.List;

public class PointDeVente {
    private String idPointDeVente;
    private String nomPointVente;
    private String adresse;
    private Agence agence;
    private List<Ticket> ticketsVendus;
    private List<Reclamation> reclamationsRecues;
    
    public PointDeVente(String idPointDeVente, String nomPointVente, String adresse) {
        this.idPointDeVente = idPointDeVente;
        this.nomPointVente = nomPointVente;
        this.adresse = adresse;
        this.ticketsVendus = new ArrayList<>();
        this.reclamationsRecues = new ArrayList<>();
    }
    
    public Ticket vendreTicket(Reservation reservation) {
        Ticket ticket = new Ticket(
            "TKT" + System.currentTimeMillis(),
            reservation.getDateReservation(),
            reservation.getPrixTotal(),
            reservation,
            reservation.getSiege()
        );
        ticketsVendus.add(ticket);
        return ticket;
    }
    
    public void recevoirReclamation(Reclamation reclamation) {
        reclamationsRecues.add(reclamation);
        if (agence != null) {
            agence.recevoirReclamation(reclamation);
        }
    }
    
    public void traiterReclamation(Reclamation reclamation) {
        reclamation.traiter();
    }
    
    public String getIdPointDeVente() { return idPointDeVente; }
    public void setIdPointDeVente(String idPointDeVente) { this.idPointDeVente = idPointDeVente; }
    public String getNomPointVente() { return nomPointVente; }
    public void setNomPointVente(String nomPointVente) { this.nomPointVente = nomPointVente; }
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public Agence getAgence() { return agence; }
    public void setAgence(Agence agence) { this.agence = agence; }
    public List<Ticket> getTicketsVendus() { return ticketsVendus; }
    public List<Reclamation> getReclamationsRecues() { return reclamationsRecues; }
    
    @Override
    public String toString() {
        return String.format("Point de Vente: %s - %s", nomPointVente, adresse);
    }
}
Fichier 13: Ticket.java
java
package models;

import java.time.LocalDateTime;
import enums.StatutTicket;

public class Ticket {
    private String numeroTicket;
    private LocalDateTime dateVoyage;
    private double prix;
    private StatutTicket statutTicket;
    private Reservation reservation;
    private Siege siege;
    
    public Ticket(String numeroTicket, LocalDateTime dateVoyage, double prix, 
                  Reservation reservation, Siege siege) {
        this.numeroTicket = numeroTicket;
        this.dateVoyage = dateVoyage;
        this.prix = prix;
        this.statutTicket = StatutTicket.VALIDE;
        this.reservation = reservation;
        this.siege = siege;
    }
    
    public void valider() {
        if (statutTicket == StatutTicket.VALIDE) {
            this.statutTicket = StatutTicket.UTILISE;
        }
    }
    
    public void rembourser() {
        if (statutTicket == StatutTicket.VALIDE) {
            this.statutTicket = StatutTicket.REMBOURSE;
        } else {
            throw new IllegalStateException("Ce ticket ne peut pas être remboursé");
        }
    }
    
    public String getNumeroTicket() { return numeroTicket; }
    public void setNumeroTicket(String numeroTicket) { this.numeroTicket = numeroTicket; }
    public LocalDateTime getDateVoyage() { return dateVoyage; }
    public void setDateVoyage(LocalDateTime dateVoyage) { this.dateVoyage = dateVoyage; }
    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }
    public StatutTicket getStatutTicket() { return statutTicket; }
    public void setStatutTicket(StatutTicket statutTicket) { this.statutTicket = statutTicket; }
    public Reservation getReservation() { return reservation; }
    public void setReservation(Reservation reservation) { this.reservation = reservation; }
    public Siege getSiege() { return siege; }
    public void setSiege(Siege siege) { this.siege = siege; }
    
    @Override
    public String toString() {
        return String.format("Ticket %s - Prix: %.2f FCFA - Statut: %s - %s", 
            numeroTicket, prix, statutTicket, siege);
    }
}
Fichier 14: Reclamation.java
java
package models;

import java.time.LocalDateTime;
import enums.StatutReclamation;
import enums.TypeReclamation;

public class Reclamation {
    private String idReclamation;
    private LocalDateTime dateReclamation;
    private String description;
    private StatutReclamation statut;
    private TypeReclamation type;
    private Voyageur voyageur;
    private PointDeVente pointDeVente;
    
    public Reclamation(String idReclamation, String description, TypeReclamation type, 
                       Voyageur voyageur, PointDeVente pointDeVente) {
        this.idReclamation = idReclamation;
        this.dateReclamation = LocalDateTime.now();
        this.description = description;
        this.statut = StatutReclamation.EN_ATTENTE;
        this.type = type;
        this.voyageur = voyageur;
        this.pointDeVente = pointDeVente;
    }
    
    public void traiter() {
        this.statut = StatutReclamation.TRAITEE;
    }
    
    public String getIdReclamation() { return idReclamation; }
    public void setIdReclamation(String idReclamation) { this.idReclamation = idReclamation; }
    public LocalDateTime getDateReclamation() { return dateReclamation; }
    public void setDateReclamation(LocalDateTime dateReclamation) { this.dateReclamation = dateReclamation; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public StatutReclamation getStatut() { return statut; }
    public void setStatut(StatutReclamation statut) { this.statut = statut; }
    public TypeReclamation getType() { return type; }
    public void setType(TypeReclamation type) { this.type = type; }
    public Voyageur getVoyageur() { return voyageur; }
    public void setVoyageur(Voyageur voyageur) { this.voyageur = voyageur; }
    public PointDeVente getPointDeVente() { return pointDeVente; }
    public void setPointDeVente(PointDeVente pointDeVente) { this.pointDeVente = pointDeVente; }
    
    @Override
    public String toString() {
        String desc = description.length() > 50 ? description.substring(0, 47) + "..." : description;
        return String.format("Réclamation %s - %s - Statut: %s", 
            idReclamation, desc, statut);
    }
}
Fichier 15: Reservation.java
java
package models;

import java.time.LocalDateTime;
import enums.StatutReservation;
import enums.ClasseVoyage;

public class Reservation {
    private String idReservation;
    private LocalDateTime dateReservation;
    private StatutReservation statut;
    private double prixTotal;
    private Voyageur voyageur;
    private Train train;
    private Arret arretDepart;
    private Arret arretArrivee;
    private Siege siege;
    private ClasseVoyage classeChoisie;
    
    public Reservation(String idReservation, Voyageur voyageur, Train train, 
                       Arret arretDepart, Arret arretArrivee, Siege siege, 
                       ClasseVoyage classeChoisie) {
        this.idReservation = idReservation;
        this.dateReservation = LocalDateTime.now();
        this.statut = StatutReservation.EN_ATTENTE;
        this.voyageur = voyageur;
        this.train = train;
        this.arretDepart = arretDepart;
        this.arretArrivee = arretArrivee;
        this.siege = siege;
        this.classeChoisie = classeChoisie;
        this.prixTotal = calculerPrix();
    }
    
    private double calculerPrix() {
        double prixBase = 5000.0;
        int nbArrets = Math.abs(arretArrivee.getPositionOrdre() - arretDepart.getPositionOrdre());
        return prixBase * nbArrets * classeChoisie.getCoefficientPrix();
    }
    
    public void annuler() {
        if (statut == StatutReservation.CONFIRMEE || statut == StatutReservation.EN_ATTENTE) {
            this.statut = StatutReservation.ANNULEE;
            if (siege != null) {
                siege.liberer();
            }
        } else {
            throw new IllegalStateException("Cette réservation ne peut pas être annulée");
        }
    }
    
    public void confirmer() {
        if (statut == StatutReservation.EN_ATTENTE) {
            this.statut = StatutReservation.CONFIRMEE;
            if (siege != null) {
                siege.reserver();
            }
        }
    }
    
    public String getIdReservation() { return idReservation; }
    public void setIdReservation(String idReservation) { this.idReservation = idReservation; }
    public LocalDateTime getDateReservation() { return dateReservation; }
    public void setDateReservation(LocalDateTime dateReservation) { this.dateReservation = dateReservation; }
    public StatutReservation getStatut() { return statut; }
    public void setStatut(StatutReservation statut) { this.statut = statut; }
    public double getPrixTotal() { return prixTotal; }
    public void setPrixTotal(double prixTotal) { this.prixTotal = prixTotal; }
    public Voyageur getVoyageur() { return voyageur; }
    public void setVoyageur(Voyageur voyageur) { this.voyageur = voyageur; }
    public Train getTrain() { return train; }
    public void setTrain(Train train) { this.train = train; }
    public Arret getArretDepart() { return arretDepart; }
    public void setArretDepart(Arret arretDepart) { this.arretDepart = arretDepart; }
    public Arret getArretArrivee() { return arretArrivee; }
    public void setArretArrivee(Arret arretArrivee) { this.arretArrivee = arretArrivee; }
    public Siege getSiege() { return siege; }
    public void setSiege(Siege siege) { this.siege = siege; }
    public ClasseVoyage getClasseChoisie() { return classeChoisie; }
    public void setClasseChoisie(ClasseVoyage classeChoisie) { this.classeChoisie = classeChoisie; }
    
    @Override
    public String toString() {
        return String.format("Réservation %s - %s → %s - Prix: %.2f FCFA - Statut: %s", 
            idReservation, arretDepart.getNom(), arretArrivee.getNom(), prixTotal, statut);
    }
}
Fichier 16: Main.java (Programme principal exécutable)
java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import models.*;
import enums.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║     SYSTÈME DE RÉSERVATION DE TRAIN N'DJAMÉNA - NGAOUNDÉRÉ     ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝\n");
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        
        try {
            // 1. CRÉATION DU TRAIN
            System.out.println("【1】 CRÉATION DU TRAIN");
            System.out.println("----------------------------------------");
            Train trainExpress = new Train("TN001", "Express Tchad-Cameroun", ClasseVoyage.PREMIERE);
            
            // 2. CRÉATION DES ARRÊTS
            LocalDateTime maintenant = LocalDateTime.now();
            
            Arret arretNgaoundere = new Arret("Ngaoundéré (Départ)", 0, 
                new Horaires(maintenant, maintenant.plusMinutes(30), maintenant));
            Arret arretMbe = new Arret("Mbe", 1, 
                new Horaires(maintenant.plusHours(1), maintenant.plusHours(1).plusMinutes(15), maintenant));
            Arret arretGouna = new Arret("Gouna", 2, 
                new Horaires(maintenant.plusHours(2), maintenant.plusHours(2).plusMinutes(15), maintenant));
            Arret arretRabinga = new Arret("Rabinga", 3, 
                new Horaires(maintenant.plusHours(3), maintenant.plusHours(3).plusMinutes(15), maintenant));
            Arret arretGaroua = new Arret("Garoua", 4, 
                new Horaires(maintenant.plusHours(4), maintenant.plusHours(4).plusMinutes(30), maintenant));
            Arret arretGuider = new Arret("Guider Moutourwa", 5, 
                new Horaires(maintenant.plusHours(5), maintenant.plusHours(5).plusMinutes(15), maintenant));
            Arret arretMaroua = new Arret("Maroua", 6, 
                new Horaires(maintenant.plusHours(6), maintenant.plusHours(6).plusMinutes(15), maintenant));
            Arret arretKousserie = new Arret("Koussérie", 7, 
                new Horaires(maintenant.plusHours(7), maintenant.plusHours(7).plusMinutes(30), maintenant));
            Arret arretNdjamenna = new Arret("N'Djaména (Arrivée)", 8, 
                new Horaires(maintenant.plusHours(8), maintenant.plusHours(8).plusMinutes(30), maintenant));
            
            trainExpress.ajouterArret(arretNgaoundere, 0);
            trainExpress.ajouterArret(arretMbe, 1);
            trainExpress.ajouterArret(arretGouna, 2);
            trainExpress.ajouterArret(arretRabinga, 3);
            trainExpress.ajouterArret(arretGaroua, 4);
            trainExpress.ajouterArret(arretGuider, 5);
            trainExpress.ajouterArret(arretMaroua, 6);
            trainExpress.ajouterArret(arretKousserie, 7);
            trainExpress.ajouterArret(arretNdjamenna, 8);
            
            System.out.println(trainExpress);
            System.out.println("\nListe des arrêts du trajet:");
            System.out.println("┌─────┬─────────────────────┬────────────────────────────┐");
            System.out.println("│ N°  │ Arrêt                │ Horaire                    │");
            System.out.println("├─────┼─────────────────────┼────────────────────────────┤");
            for (Arret arret : trainExpress.getArrets()) {
                String horaire = "Départ: " + arret.getHoraires().getHeureDepart().format(formatter);
                System.out.printf("│ %-3d │ %-19s │ %-26s │\n", 
                    arret.getPositionOrdre(), arret.getNom(), horaire);
            }
            System.out.println("└─────┴─────────────────────┴────────────────────────────┘\n");
            
            // 3. CRÉATION DES AGENCES ET POINTS DE VENTE
            System.out.println("【2】 CRÉATION DES AGENCES ET POINTS DE VENTE");
            System.out.println("----------------------------------------");
            
            Agence agenceNgaoundere = new Agence("AG001", "Agence Centrale Ngaoundéré", 
                "Avenue de la Gare, Ngaoundéré", "222 11 22 33", "Ngaoundéré");
            Agence agenceGaroua = new Agence("AG002", "Agence Régionale Garoua", 
                "Boulevard de l'Indépendance, Garoua", "222 44 55 66", "Garoua");
            Agence agenceMaroua = new Agence("AG003", "Agence Nord Extrême", 
                "Rue des Voyageurs, Maroua", "222 77 88 99", "Maroua");
            
            PointDeVente pointVenteGare = new PointDeVente("PV001", "Point de Vente Gare SNCF", 
                "Gare Centrale, Ngaoundéré");
            PointDeVente pointVenteCentre = new PointDeVente("PV002", "Point de Vente Centre Ville", 
                "Rue Principal, Ngaoundéré");
            PointDeVente pointVenteMarche = new PointDeVente("PV003", "Point de Vente Grand Marché", 
                "Marché Central, Garoua");
            
            agenceNgaoundere.ajouterPointDeVente(pointVenteGare);
            agenceNgaoundere.ajouterPointDeVente(pointVenteCentre);
            agenceGaroua.ajouterPointDeVente(pointVenteMarche);
            
            System.out.println(agenceNgaoundere);
            System.out.println("  → Points de vente:");
            for (PointDeVente pv : agenceNgaoundere.getPointsDeVente()) {
                System.out.println("     • " + pv);
            }
            System.out.println(agenceGaroua);
            System.out.println("  → Points de vente:");
            for (PointDeVente pv : agenceGaroua.getPointsDeVente()) {
                System.out.println("     • " + pv);
            }
            System.out.println();
            
            // 4. CRÉATION DES VOYAGEURS
            System.out.println("【3】 ENREGISTREMENT DES VOYAGEURS");
            System.out.println("----------------------------------------");
            
            Voyageur voyageur1 = new Voyageur("V001", "Djibril", "Mohamed", 
                "mohamed.djibril@email.com", "+237 698 12 34 56");
            Voyageur voyageur2 = new Voyageur("V002", "Ali", "Fatima", 
                "fatima.ali@email.com", "+237 699 98 76 54");
            Voyageur voyageur3 = new Voyageur("V003", "Bello", "Hamadou", 
                "hamadou.bello@email.com", "+235 601 23 45 67");
            
            System.out.println("✓ " + voyageur1);
            System.out.println("✓ " + voyageur2);
            System.out.println("✓ " + voyageur3);
            System.out.println();
            
            // 5. CRÉATION DES SIÈGES
            System.out.println("【4】 CONFIGURATION DES SIÈGES");
            System.out.println("----------------------------------------");
            
            Siege siege1 = new Siege(15, 1, ClasseVoyage.PREMIERE);
            Siege siege2 = new Siege(42, 2, ClasseVoyage.SECONDE);
            Siege siege3 = new Siege(8, 3, ClasseVoyage.WAGON_LIT);
            Siege siege4 = new Siege(25, 1, ClasseVoyage.PREMIERE);
            Siege siege5 = new Siege(67, 2, ClasseVoyage.SECONDE);
            
            System.out.println("✓ " + siege1);
            System.out.println("✓ " + siege2);
            System.out.println("✓ " + siege3);
            System.out.println("✓ " + siege4);
            System.out.println("✓ " + siege5);
            System.out.println();
            
            // 6. RÉSERVATIONS
            System.out.println("【5】 PROCESSUS DE RÉSERVATION");
            System.out.println("----------------------------------------");
            
            // Réservation 1: Ngaoundéré → Garoua en 1ère classe
            Reservation reservation1 = new Reservation("RES001", voyageur1, trainExpress, 
                arretNgaoundere, arretGaroua, siege1, ClasseVoyage.PREMIERE);
            voyageur1.ajouterReservation(reservation1);
            reservation1.confirmer();
            System.out.println("✓ Réservation créée: " + reservation1);
            
            // Réservation 2: Garoua → Maroua en Wagon Lit
            Reservation reservation2 = new Reservation("RES002", voyageur2, trainExpress, 
                arretGaroua, arretMaroua, siege3, ClasseVoyage.WAGON_LIT);
            voyageur2.ajouterReservation(reservation2);
            reservation2.confirmer();
            System.out.println("✓ Réservation créée: " + reservation2);
            
            // Réservation 3: Mbe → Koussérie en 2ème classe
            Reservation reservation3 = new Reservation("RES003", voyageur3, trainExpress, 
                arretMbe, arretKousserie, siege2, ClasseVoyage.SECONDE);
            voyageur3.ajouterReservation(reservation3);
            reservation3.confirmer();
            System.out.println("✓ Réservation créée: " + reservation3);
            System.out.println();
            
            // 7. VENTE DES TICKETS
            System.out.println("【6】 ÉMISSION DES TICKETS");
            System.out.println("----------------------------------------");
            
            Ticket ticket1 = pointVenteGare.vendreTicket(reservation1);
            Ticket ticket2 = pointVenteMarche.vendreTicket(reservation2);
            Ticket ticket3 = pointVenteCentre.vendreTicket(reservation3);
            
            System.out.println("✓ Ticket émis au " + pointVenteGare.getNomPointVente() + ":");
            System.out.println("     " + ticket1);
            System.out.println("✓ Ticket émis au " + pointVenteMarche.getNomPointVente() + ":");
            System.out.println("     " + ticket2);
            System.out.println("✓ Ticket émis au " + pointVenteCentre.getNomPointVente() + ":");
            System.out.println("     " + ticket3);
            System.out.println();
            
            // 8. DÉPÔT DE RÉCLAMATIONS
            System.out.println("【7】 DÉPÔT DES RÉCLAMATIONS");
            System.out.println("----------------------------------------");
            


