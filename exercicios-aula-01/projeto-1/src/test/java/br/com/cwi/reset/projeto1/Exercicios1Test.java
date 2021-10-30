package br.com.cwi.reset.projeto1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class Exercicios1Test {

    //------ TESTE DE VALIDACAO DE SOMA ---------//

    @Test
    public void testSomarListaNumerosInteirosPositivos(){
        //Arrange
        Exercicios1 exercicios = new Exercicios1();
        List<Integer> numList = Arrays.asList(5, 10, 2, 20, 6);
        Integer expected = 43;

        //Action
        Integer result = exercicios.somarLista(numList);

        //Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testSomarListaNumerosInteirosEUmNegativo(){
        //Arrange
        Exercicios1 exercicios = new Exercicios1();
        List<Integer> numList = Arrays.asList(5, 10, -2, 20, 6);
        Integer expected = 39;

        //Action
        Integer result = exercicios.somarLista(numList);

        //Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testSomarListaNumerosInteirosNegativos(){
        //Arrange
        Exercicios1 exercicios = new Exercicios1();
        List<Integer> numList = Arrays.asList(-5, -10, -5);
        Integer expected = -20;

        //Action
        Integer result = exercicios.somarLista(numList);

        //Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testSomarListaComNumerosZero(){
        //Arrange
        Exercicios1 exercicios = new Exercicios1();
        List<Integer> numList = Arrays.asList(0, 0, 0);
        Integer expected = 0;

        //Action
        Integer result = exercicios.somarLista(numList);

        //Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testSomarListaVazia(){
        //Arrange
        Exercicios1 exercicios = new Exercicios1();
        List<Integer> numList = Arrays.asList();
        Integer expected = 0;

        //Action
        Integer result = exercicios.somarLista(numList);

        //Assert
        Assertions.assertEquals(expected, result);
    }

    //------ TESTE DE VALIDACAO DE CALCULO DE MEDIAS ---------//

    @Test
    public void testCalcularMediaListaNumerosInteirosPositivos(){
        //Arrange
        Exercicios1 exercicios = new Exercicios1();
        List<Integer> numList = Arrays.asList(10, 10, 4, 20, 6);
        Double expected = 10.0;

        //Action
        Double result = exercicios.calcularMedia(numList);

        //Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testCalcularMediaListaNumerosInteirosEUmNegativo(){
        //Arrange
        Exercicios1 exercicios = new Exercicios1();
        List<Integer> numList = Arrays.asList(-10, 10, 4, 20, 6);
        Double expected = 6.0;

        //Action
        Double result = exercicios.calcularMedia(numList);

        //Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testCalcularMediaListaNumerosInteirosNegativos(){
        //Arrange
        Exercicios1 exercicios = new Exercicios1();
        List<Integer> numList = Arrays.asList(-20, -10, -30);
        Double expected = -20.0;

        //Action
        Double result = exercicios.calcularMedia(numList);

        //Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testCalcularMediaListaComNumerosZero(){
        //Arrange
        Exercicios1 exercicios = new Exercicios1();
        List<Integer> numList = Arrays.asList(0, 0, 0);
        Double expected = 0.0;

        //Action
        Double result = exercicios.calcularMedia(numList);

        //Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testCalcularMediaListaVazia(){
        //Arrange
        Exercicios1 exercicios = new Exercicios1();
        List<Integer> numList = Arrays.asList();
        Double expected = 0.0;

        //Action
        Double result = exercicios.calcularMedia(numList);

        //Assert
        Assertions.assertEquals(expected, result);
    }

    //------ TESTE DE VALIDACAO DE INVERSAO DE STRINGS ---------//

    @Test
    public void testIverterPalavraAbacate(){
        //Arrange
        Exercicios1 exercicios = new Exercicios1();
        String palavra = "Abacate";
        String expected = "etacabA";

        //Action
        String result = exercicios.obterPalavraInvertida(palavra);

        //Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testIverterPalavraBanana(){
        //Arrange
        Exercicios1 exercicios = new Exercicios1();
        String palavra = "Banana";
        String expected = "ananaB";

        //Action
        String result = exercicios.obterPalavraInvertida(palavra);

        //Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testIverterPalavraPessego(){
        //Arrange
        Exercicios1 exercicios = new Exercicios1();
        String palavra = "Pessego";
        String expected = "ogesseP";

        //Action
        String result = exercicios.obterPalavraInvertida(palavra);

        //Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testIverterPalavraMorango(){
        //Arrange
        Exercicios1 exercicios = new Exercicios1();
        String palavra = "Morango";
        String expected = "ognaroM";

        //Action
        String result = exercicios.obterPalavraInvertida(palavra);

        //Assert
        Assertions.assertEquals(expected, result);
    }
}
