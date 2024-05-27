package br.com.atividade.modelos;

public class Cep implements Comparable<Cep>{
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

    public Cep(Filtro dado) {
        this.cep = formataCep(dado.cep());
        this.logradouro = dado.logradouro();
        this.complemento = dado.complemento();
        this.bairro = dado.bairro();
        this.localidade = dado.localidade();
        this.uf = dado.uf();
    }

    public String getCep() {
        return cep;
    }

    @Override
    public String toString() {
        return "\ncep: " + this.cep +
                "\nlogradouro: " + this.logradouro +
                "\ncomplemento: " + this.complemento +
                "\nbairro: " + this.bairro +
                "\nlocalidade: " + this.localidade +
                "\nuf: " + this.uf + "\n";
    }

    @Override
    public int compareTo(Cep o) {
        return Integer.compare(Integer.parseInt(this.getCep()), Integer.parseInt(o.getCep()));
    }

    public String formataCep(String cep){
            return cep.substring(0, 5) +  cep.substring(6, 9);
    }
}
