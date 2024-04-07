package projetaoo.model;

import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.MapKeyEnumerated;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "consommation_energie")
public class ConsommationEnergie implements ConsommationInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "utilisateur_id") // clé étrangère dans consommation_energie table
    private Utilisateur utilisateur;

    @ElementCollection
    @CollectionTable(name = "consommation_details", joinColumns = @JoinColumn(name = "consommation_energie_id"))
    @MapKeyColumn(name = "mois")
    @MapKeyEnumerated(EnumType.STRING) // Pour stocker l'énumération Month comme une chaîne
    @Column(name = "valeur")
    private Map<Object, Object> consommations = new HashMap<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void initierConsommation(Object m) {
        if (!(m instanceof Month)) {
            throw new IllegalArgumentException("L'objet passé à initierConsommation doit être de type Month");
        }
        consommations.put(m, Float.valueOf(0));
    }

    @Override
    public <K, V> void ajouterConsommation(K cle, V valeur) {
        if (!(cle instanceof Month)) {
            throw new IllegalArgumentException("La clé passée à ajouterConsommation doit être de type Month");
        }
        consommations.put(cle, valeur);
    }

    public Map<Object, Object> getConsommations() {
        return consommations;
    }

    

}


