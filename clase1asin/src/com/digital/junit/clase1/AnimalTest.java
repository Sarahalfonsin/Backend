package com.digital.junit.clase1;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AnimalTest {
    @Test
    public void siElAnimalEsPesado(){
        Animal caballo = new Animal("caballo","grande",100);
        Animal perro= new Animal("perro","mediano",28);

        boolean esPesado = caballo.esPesado();
        boolean esPesado1 = perro.esPesado();

        Assertions.assertEquals(true, caballo.esPesado());
        Assertions.assertFalse(esPesado1);

    }
}