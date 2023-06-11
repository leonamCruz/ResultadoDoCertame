package tech.leonam.resultadodocertame;

import org.junit.Assert;
import org.junit.Test;
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        int j = 2;
        var letra = (char)('a' + j);

        String nomeDoArquivo = letra + ".png";
        Assert.assertEquals("c.png",nomeDoArquivo);
    }
}