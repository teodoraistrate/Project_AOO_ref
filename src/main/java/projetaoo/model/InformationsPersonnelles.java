package projetaoo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "informations_personnelles")
public class InformationsPersonnelles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Ajout d'un champ ID
    
    private String nom;
    private int age;
    private String paysResidence;

    @OneToOne(mappedBy = "informationsPersonnelles", cascade = CascadeType.ALL)
    private Utilisateur utilisateur;

    // Constructeur, getters et setters
    public InformationsPersonnelles(String nom, int age, String paysResidence) {
        this.nom = nom;
        this.age = age;
        this.paysResidence = paysResidence;
    }

    // Getters et Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPaysResidence() {
        return paysResidence;
    }

    public void setPaysResidence(String paysResidence) {
        this.paysResidence = paysResidence;
    }
}
