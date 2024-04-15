package models;

public record CepRecord(String cep,
                        String logradouro,
                        String bairro,
                        String localidade,
                        String uf,
                        String ddd,
                        String erro) {
}
