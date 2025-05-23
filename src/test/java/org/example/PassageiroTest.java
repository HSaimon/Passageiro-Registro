package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PassageiroTest {
    Passageiro passageiro;

    // CPFs válidos gerados anteriormente para teste
    private static final String VALID_CPF_1 = "53064171321";
    private static final String VALID_CPF_2 = "18987384322";
    private static final String VALID_CPF_3 = "03394014866";
    private static final String VALID_CPF_FORMATTED = "030.126.615-81"; // Corresponds to 03012661581

    @BeforeEach
    public void setUp() {
        // No exemplo, o setUp é geralmente para instanciar o objeto, 
        // mas como muitos testes criam o objeto com parâmetros específicos, 
        // vamos instanciar dentro de cada teste conforme necessário ou usar um default aqui.
        passageiro = new Passageiro(1, "Nome Padrão", VALID_CPF_1, "padrao@example.com");
    }

    @Test
    public void criacaoPassageiroComValoresValidos(){
        Passageiro p = new Passageiro(10, "Ana Silva", VALID_CPF_2, "ana.silva@example.com");
        Assertions.assertEquals(10, p.getId());
        Assertions.assertEquals("Ana Silva", p.getNome());
        Assertions.assertEquals(VALID_CPF_2, p.getCpf());
        Assertions.assertEquals("ana.silva@example.com", p.getEmail());
    }

    @Test
    public void criacaoPassageiroComCpfEmailPotencialmenteInvalidosNoConstrutor(){
        // Conforme o padrão do exemplo, o construtor não valida, apenas atribui.
        // A validação é feita por métodos estáticos ou na lógica de negócio.
        Passageiro p = new Passageiro(20, "Carlos Ruim", "123", "emailinvalido");
        Assertions.assertEquals("123", p.getCpf(), "Construtor deve atribuir CPF mesmo que formato seja inválido.");
        Assertions.assertEquals("emailinvalido", p.getEmail(), "Construtor deve atribuir email mesmo que formato seja inválido.");
    }

    @Test
    public void testGetters() {
        Assertions.assertEquals(1, passageiro.getId());
        Assertions.assertEquals("Nome Padrão", passageiro.getNome());
        Assertions.assertEquals(VALID_CPF_1, passageiro.getCpf());
        Assertions.assertEquals("padrao@example.com", passageiro.getEmail());
    }

    @Test
    public void testSetters() {
        passageiro.setId(2);
        passageiro.setNome("Novo Nome");
        passageiro.setCpf(VALID_CPF_2); // Setter também não valida, apenas atribui
        passageiro.setEmail("novo@example.com");

        Assertions.assertEquals(2, passageiro.getId());
        Assertions.assertEquals("Novo Nome", passageiro.getNome());
        Assertions.assertEquals(VALID_CPF_2, passageiro.getCpf());
        Assertions.assertEquals("novo@example.com", passageiro.getEmail());
    }

    // Testes para validarCpf
    @ParameterizedTest
    @ValueSource(strings = {VALID_CPF_1, VALID_CPF_2, VALID_CPF_3, VALID_CPF_FORMATTED, "870.631.322-10"}) // 87063132210
    public void testValidarCpf_Valido(String cpf) {
        Assertions.assertTrue(Passageiro.validarCpf(cpf), "CPF " + cpf + " deveria ser válido.");
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "12345678900", // Dígito verificador incorreto
        "11111111111", // Todos os números iguais
        "00000000000",
        "12345",       // Menos de 11 dígitos
        "1234567890123",// Mais de 11 dígitos
        "abcdefghijk", // Não numérico
        "",            // Vazio
        "   "          // Apenas espaços (deve ser tratado por trim().isEmpty())
    })
    public void testValidarCpf_Invalido(String cpf) {
        Assertions.assertFalse(Passageiro.validarCpf(cpf), "CPF '" + cpf + "' deveria ser inválido.");
    }

    @Test
    public void testValidarCpf_Nulo() {
        Assertions.assertFalse(Passageiro.validarCpf(null), "CPF nulo deveria ser inválido.");
    }

    // Testes para validarEmail
    @ParameterizedTest
    @ValueSource(strings = {"teste@exemplo.com", "nome.sobrenome@dominio.co.uk", "aluno123@universidade.edu.br"})
    public void testValidarEmail_Valido(String email) {
        Assertions.assertTrue(Passageiro.validarEmail(email), "Email " + email + " deveria ser válido.");
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "testeexemplo.com",  // Sem @
        "teste@exemplo",     // Sem .com/.net etc.
        "@exemplo.com",      // Sem nome local
        "teste@.com",        // Dominio inválido
        "teste@exemplo.",    // TLD inválido
        "email com espaco@exemplo.com",
        "",                 // Vazio
        "   "              // Apenas espaços
    })
    public void testValidarEmail_Invalido(String email) {
        Assertions.assertFalse(Passageiro.validarEmail(email), "Email '" + email + "' deveria ser inválido.");
    }

    @Test
    public void testValidarEmail_Nulo() {
        Assertions.assertFalse(Passageiro.validarEmail(null), "Email nulo deveria ser inválido.");
    }

    @Test
    public void testToString() {
        String expected = "Passageiro{id=1, nome='Nome Padrão', cpf='" + VALID_CPF_1 + "', email='padrao@example.com'}";
        Assertions.assertEquals(expected, passageiro.toString());
    }
}

