package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class AviaoTest {
    private Aviao aviao;
    
    @BeforeEach
    public void setUp() {
        aviao = new Aviao(1, "Boeing 737", 150, "Boeing");
    }
    
    @Test
    public void testCriacaoAviaoComValoresValidos() {
        Aviao a = new Aviao(2, "Airbus A320", 180, "Airbus");
        Assertions.assertEquals(2, a.getId());
        Assertions.assertEquals("Airbus A320", a.getModelo());
        Assertions.assertEquals(180, a.getCapacidade());
        Assertions.assertEquals("Airbus", a.getFabricante());
    }
    
    @Test
    public void testGetters() {
        Assertions.assertEquals(1, aviao.getId());
        Assertions.assertEquals("Boeing 737", aviao.getModelo());
        Assertions.assertEquals(150, aviao.getCapacidade());
        Assertions.assertEquals("Boeing", aviao.getFabricante());
    }
    
    @Test
    public void testSetters() {
        aviao.setId(3);
        aviao.setModelo("Embraer E190");
        aviao.setCapacidade(114);
        aviao.setFabricante("Embraer");
        
        Assertions.assertEquals(3, aviao.getId());
        Assertions.assertEquals("Embraer E190", aviao.getModelo());
        Assertions.assertEquals(114, aviao.getCapacidade());
        Assertions.assertEquals("Embraer", aviao.getFabricante());
    }
    
    @Test
    public void testValidarCapacidadePositiva() {
        Assertions.assertTrue(Aviao.validarCapacidade(1));
        Assertions.assertTrue(Aviao.validarCapacidade(100));
        Assertions.assertTrue(Aviao.validarCapacidade(500));
    }
    
    @Test
    public void testValidarCapacidadeZero() {
        Assertions.assertFalse(Aviao.validarCapacidade(0));
    }
    
    @Test
    public void testValidarCapacidadeNegativa() {
        Assertions.assertFalse(Aviao.validarCapacidade(-1));
        Assertions.assertFalse(Aviao.validarCapacidade(-100));
    }
    
    @Test
    public void testToString() {
        String expected = "Aviao{id=1, modelo='Boeing 737', capacidade=150, fabricante='Boeing'}";
        Assertions.assertEquals(expected, aviao.toString());
    }
}
