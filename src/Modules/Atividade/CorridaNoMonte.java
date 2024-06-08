package Modules.Atividade;

import java.util.Objects;

import Modules.Utilizador.Utilizador;

public class CorridaNoMonte extends Atividade {
    private double distancia;
    private double altimetria;

    public CorridaNoMonte(double tempo, double frequenciaCardiacaMedia, double distancia, double altimetria) {
        super(tempo, frequenciaCardiacaMedia);
        this.setHard(true); // dar override ao isHard
        this.distancia = distancia;
        this.altimetria = altimetria;
    }

    public CorridaNoMonte(CorridaNoMonte atividade) {
        super(atividade);
        this.distancia = atividade.getDistancia();
        this.altimetria = atividade.getAltimetria();
    }

    public CorridaNoMonte clone() {
        return new CorridaNoMonte(this);
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public double getAltimetria() {
        return altimetria;
    }

    public void setAltimetria(double altimetria) {
        this.altimetria = altimetria;
    }

    @Override
    public String toString() {
        return "\nCorrida No Monte \n" +
                "Distância : " + distancia + "\n" +
                "Altimetria = " + altimetria + "\n" +
                "Codigo da Atividade = " + this.hashCode() + "\n" +
                "------------------------------------------\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        CorridaNoMonte atividade = (CorridaNoMonte) o;
        return super.equals(atividade) && this.distancia == atividade.getDistancia()
                && this.altimetria == atividade.getAltimetria();
    }

    // override do metodo hashCode
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.distancia, this.altimetria, "CorridaNoMonte");
    }

    public double calcularCalorias(Utilizador utilizador, int frequenciaCardiacaMedia) {
        double tMinuto = this.getTempo(); // minuto
        double tHora = this.getTempo() / 60; // hora
        double alt = this.getAltimetria(); // metro
        double d = this.getDistancia(); // km
        double v = d * tHora; // km/hora
        double fm = utilizador.calcularFatorMultiplicativo(frequenciaCardiacaMedia);
        double fatorAjuste = 1 + alt / 1000;
        double calorias = 0;

        if (this.isHard() == true) {
            calorias = 1.5 * fm * v * 0.0175 * tMinuto * fatorAjuste;
        } else {
            calorias = fm * v * 0.0175 * tMinuto * fatorAjuste;
        }
        return calorias;
    }

}
