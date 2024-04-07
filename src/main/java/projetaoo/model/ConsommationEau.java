package projetaoo.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "consommation_eau")
public class ConsommationEau implements ConsommationInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
    
    @ElementCollection
    @CollectionTable(name = "consommation_eau_details", joinColumns = @JoinColumn(name = "consommation_eau_id"))
    @MapKeyColumn(name = "date")
    @Column(name = "valeur")
    private Map<LocalDate, Integer> consommations = new HashMap<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void initierConsommation(Object date) {
        if (!(date instanceof LocalDate)) {
            throw new IllegalArgumentException("L'objet passé à initierConsommation doit être de type LocalDate");
        }
        consommations.put((LocalDate) date, 0);
    }

    @Override
    public <K, V> void ajouterConsommation(K cle, V valeur) {
        if (cle instanceof LocalDate && valeur instanceof Integer) {
            consommations.put((LocalDate) cle, (Integer) valeur);
        } else {
            throw new IllegalArgumentException("Types de clé ou de valeur incorrects pour ConsommationEau.");
        }
    }

    public Map<LocalDate, Integer> getConsommations() {
        return consommations;
    }

    
}
