
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestPersona {
    @Test
    void nombreTest(){

        Personamain sarah =new Personamain("Sarah","Alfonsin","sarahalfonsin@hotmail.com",19);
        Personamain matias = new Personamain("matias","fernandez","matifer@gmail.com",17);

        String mayor = sarah.mostrarNombre();
        boolean mayor2 = matias.mayor();


        //nombre completo correcto
        Assertions.assertEquals("Sarah, Alfonsin",mayor);
        //mayor 18
        Assertions.assertFalse(mayor2);

    }
}
