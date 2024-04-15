package models;

import exceptions.NonexistentCEP;

public class Cep{
    private String number;
    private String publicPlace;
    private String neighborhood;
    private String city;
    private String state;
    private String ddd;

    public Cep(CepRecord cepRecord) throws NonexistentCEP {
        if ("true".equals(cepRecord.erro())) {
            throw new NonexistentCEP(
                    "CEP is valid but doesn't exist in ViaCEP's database");
        }
        this.number = cepRecord.cep();
        this.publicPlace = cepRecord.logradouro();
        this.neighborhood = cepRecord.bairro();
        this.city = cepRecord.localidade();
        this.state = cepRecord.uf();
        this.ddd = cepRecord.ddd();
    }

    @Override
    public String toString() {
        return "Address{" +
                "number='" + number + '\'' +
                ", publicPlace='" + publicPlace + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", ddd=" + ddd +
                '}';
    }

    // future behaviours...
}
