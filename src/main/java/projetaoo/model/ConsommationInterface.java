package projetaoo.model;

public interface ConsommationInterface {
    void initierConsommation(Object period);
    <K, V> void ajouterConsommation(K cle, V valeur);
}


