package br.com.atividade.modelos;

public record Filtro(String cep, String logradouro, String complemento, String bairro, String localidade, String uf, boolean erro) {
}
