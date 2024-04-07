package projetaoo.model;

import java.time.Month;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import projetaoo.model.MoyenTransport.NomMoyenTransport;

@Entity
@Table(name = "utilisateur")

public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "informations_personnelles_id", referencedColumnName = "id")
    private InformationsPersonnelles informationsPersonnelles;

    @ElementCollection
    @CollectionTable(name = "nombre_pas", joinColumns = @JoinColumn(name = "utilisateur_id"))
    @MapKeyColumn(name = "date")
    @Column(name = "nombre_pas")
    private Map<LocalDate, Integer> nombrePas = new HashMap<>();

    @Column(name = "type_alimentation")
    private String typeAlimentation;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ConsommationEnergie consoEnergie;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ConsommationEau consoEau;

    @ElementCollection
    private Map<NomMoyenTransport, Integer> minutesMoyenTransport = new HashMap<>(); // par mois, il
                                                                                     // faut qu'on
    // sauvegarde les données dès le
    // début
    @ElementCollection
    private Map<Month, Float> quantiteDechets = new HashMap<>(); // en kilos, par mois, le compteur va se
                                                                 // reinitialiser au début de
    // chaque mois

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "habitude_achat_id", referencedColumnName = "id")
    private HabitudeAchat habitudeAchat;
    // HabitudeAchat nécéssaire pour la méthode calculerEmpreinteAchats de la classe
    // CalculateurEmpreinte

    public Utilisateur() {
        this.nombrePas = new HashMap<>();
        this.minutesMoyenTransport = new HashMap<>();
        this.quantiteDechets = new HashMap<>();
    }

    public HabitudeAchat getHabitudeAchat() {
        return habitudeAchat;
    }

    public void setHabitudeAchat(HabitudeAchat habitudeAchat) {
        this.habitudeAchat = habitudeAchat;
    }

    public Map<LocalDate, Integer> getNombrePas() {
        return nombrePas;
    }

    public void setNombrePas(HashMap<LocalDate, Integer> nombrePas) {
        this.nombrePas = nombrePas;
    }

    public String getTypeAlimentation() {
        return typeAlimentation;
    }

    public void setTypeAlimentation(String typeAlimentation) {
        this.typeAlimentation = typeAlimentation;
    }


    public Map<NomMoyenTransport, Integer> getMinutesMoyenTransport() {
        return minutesMoyenTransport;
    }

    public void initMinutesTransport(NomMoyenTransport moyen) {
        this.minutesMoyenTransport.put(moyen, Integer.valueOf(0));
    }

    public void addMinutesTransport(NomMoyenTransport moyen, Integer nbMinutes) {
        if (this.minutesMoyenTransport.containsKey(moyen)) {
            this.minutesMoyenTransport.put(moyen, this.minutesMoyenTransport.get(moyen) + nbMinutes);
        } else {
            this.minutesMoyenTransport.put(moyen, nbMinutes);
        }
    }

    public Map<Month, Float> getQuantiteDechets() {
        return quantiteDechets;
    }

    public void initQuantiteDechets(Month mois) {
        this.quantiteDechets.put(mois, Float.valueOf(0));
    }

    public void addQuantiteDechets(Month mois, Float quantite) {
        if (this.quantiteDechets.containsKey(mois)) {
            this.quantiteDechets.put(mois, this.quantiteDechets.get(mois) + quantite);
        } else {
            this.quantiteDechets.put(mois, quantite);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConsommationEnergie getConsoEnergie() {
        return consoEnergie;
    }

    public void setConsoEnergie(ConsommationEnergie consoEnergie) {
        this.consoEnergie = consoEnergie;
    }

    public ConsommationEau getConsoEau() {
        return consoEau;
    }

    public void setConsoEau(ConsommationEau consoEau) {
        this.consoEau = consoEau;
    }

    

}
