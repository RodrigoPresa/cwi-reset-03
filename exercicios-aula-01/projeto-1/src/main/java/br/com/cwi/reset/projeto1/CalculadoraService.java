package br.com.cwi.reset.projeto1;

public class CalculadoraService {

    public Double multiplicar(Double primeiroNumero, Double segundoNumero) {
        return primeiroNumero * segundoNumero;
    }

    public Double somar(Double primeiroNumero, Double segundoNumero) {
        return primeiroNumero + segundoNumero;
    }

    public Double subtrair(Double primeiroNumero, Double segundoNumero) {
        return primeiroNumero - segundoNumero;
    }

    public Double dividir(Double primeiroNumero, Double segundoNumero) {
        if (segundoNumero == 0) {
            throw new ArithmeticException();
        }
        return primeiroNumero/segundoNumero;
    }
}
