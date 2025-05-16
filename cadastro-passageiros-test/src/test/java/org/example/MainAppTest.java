package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class MainAppTest {
    // CPFs válidos gerados anteriormente para teste
    private static final String VALID_CPF_1 = "53064171321";
    private static final String VALID_CPF_2 = "18987384322";
    private static final String VALID_CPF_3 = "03394014866";
    private static final String VALID_CPF_4 = "03012661581"; // Outro CPF válido

    @BeforeEach
    public void setUp() {
        MainApp.limparPassageirosParaTeste();
    }

    @Test
    public void testAdicionarPassageiroComSucesso() {
        Passageiro p1 = new Passageiro(1, "Joao Silva", VALID_CPF_1, "joao.silva@example.com");
        Assertions.assertTrue(MainApp.adicionarPassageiroParaTeste(p1), "Deveria adicionar passageiro com dados válidos.");
        List<Passageiro> passageiros = MainApp.getPassageiros();
        Assertions.assertEquals(1, passageiros.size());
        Assertions.assertEquals("Joao Silva", passageiros.get(0).getNome());
        Assertions.assertEquals(VALID_CPF_1, passageiros.get(0).getCpf());
    }

    @Test
    public void testNaoAdicionarPassageiroComCpfInvalido() {
        Passageiro pInvalido = new Passageiro(2, "Maria CPF Invalido", "12345", "maria.invalida@example.com");
        // O método adicionarPassageiroParaTeste deve usar Passageiro.validarCpf internamente
        Assertions.assertFalse(MainApp.adicionarPassageiroParaTeste(pInvalido), "Não deveria adicionar passageiro com CPF inválido.");
        Assertions.assertTrue(MainApp.getPassageiros().isEmpty(), "Lista deveria estar vazia após tentativa com CPF inválido.");
    }

    @Test
    public void testNaoAdicionarPassageiroComEmailInvalido() {
        Passageiro pInvalido = new Passageiro(3, "Pedro Email Invalido", VALID_CPF_2, "pedro.invalido");
        // O método adicionarPassageiroParaTeste deve usar Passageiro.validarEmail internamente
        Assertions.assertFalse(MainApp.adicionarPassageiroParaTeste(pInvalido), "Não deveria adicionar passageiro com e-mail inválido.");
        Assertions.assertTrue(MainApp.getPassageiros().isEmpty(), "Lista deveria estar vazia após tentativa com e-mail inválido.");
    }

    @Test
    public void testNaoAdicionarPassageiroComCpfDuplicado() {
        Passageiro p1 = new Passageiro(1, "Carlos Primeiro", VALID_CPF_3, "carlos.primeiro@example.com");
        Assertions.assertTrue(MainApp.adicionarPassageiroParaTeste(p1));

        Passageiro p2Duplicado = new Passageiro(2, "Carlos Duplicado", VALID_CPF_3, "carlos.duplicado@example.com");
        Assertions.assertFalse(MainApp.adicionarPassageiroParaTeste(p2Duplicado), "Não deveria adicionar passageiro com CPF duplicado.");
        Assertions.assertEquals(1, MainApp.getPassageiros().size(), "Lista deveria conter apenas 1 passageiro após tentativa de duplicidade.");
    }

    @Test
    public void testNaoAdicionarPassageiroComCpfDuplicadoFormatacaoDiferente() {
        Passageiro p1 = new Passageiro(1, "Ana Original", VALID_CPF_4, "ana.original@example.com"); // CPF sem formatação
        Assertions.assertTrue(MainApp.adicionarPassageiroParaTeste(p1));

        // Mesmo CPF, mas com formatação
        String cpfFormatado = VALID_CPF_4.substring(0,3) + "." + VALID_CPF_4.substring(3,6) + "." + VALID_CPF_4.substring(6,9) + "-" + VALID_CPF_4.substring(9,11);
        Passageiro p2Duplicado = new Passageiro(2, "Ana Duplicada", cpfFormatado, "ana.duplicada@example.com");
        Assertions.assertFalse(MainApp.adicionarPassageiroParaTeste(p2Duplicado), "Não deveria adicionar passageiro com CPF duplicado (formatação diferente).");
        Assertions.assertEquals(1, MainApp.getPassageiros().size(), "Lista deveria conter apenas 1 passageiro.");
    }

    @Test
    public void testListarPassageirosVazia() {
        List<Passageiro> passageiros = MainApp.getPassageiros();
        Assertions.assertTrue(passageiros.isEmpty(), "Lista de passageiros deveria estar vazia inicialmente.");
    }

    @Test
    public void testListarPassageirosComAlgunsCadastrados() {
        Passageiro p1 = new Passageiro(1, "Passageiro A", VALID_CPF_1, "pa@example.com");
        Passageiro p2 = new Passageiro(2, "Passageiro B", VALID_CPF_2, "pb@example.com");
        MainApp.adicionarPassageiroParaTeste(p1);
        MainApp.adicionarPassageiroParaTeste(p2);

        List<Passageiro> passageiros = MainApp.getPassageiros();
        Assertions.assertEquals(2, passageiros.size());
        Assertions.assertTrue(passageiros.contains(p1));
        Assertions.assertTrue(passageiros.contains(p2));
    }

    @Test
    public void testLimparListaDePassageiros() {
        Passageiro p1 = new Passageiro(1, "Passageiro Temp", VALID_CPF_3, "temp@example.com");
        MainApp.adicionarPassageiroParaTeste(p1);
        Assertions.assertFalse(MainApp.getPassageiros().isEmpty());

        MainApp.limparPassageirosParaTeste();
        Assertions.assertTrue(MainApp.getPassageiros().isEmpty(), "Lista deveria estar vazia após limpeza.");
    }
}

