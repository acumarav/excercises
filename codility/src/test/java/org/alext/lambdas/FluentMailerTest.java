package org.alext.lambdas;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by tsumaraa on 09/09/2016.
 */
public class FluentMailerTest {

    @Test
    public void testBuilder() {

        Consumer<FluentMailer> fluentMailerConsumer = mailer ->
                mailer.from("alex")
                        .to("nata")
                        .subject("hello")
                        .body("much better");
        FluentMailer.send(fluentMailerConsumer);
    }

    @Test
    public void testMyTries() {

        Consumer<FluentMailer> nv2= a->  System.out.println("A is: "+a);


        Consumer<FluentMailer> fluentMailerConsumer = mailer ->
                mailer.from("alex")
                        .to("nata")
                        .subject("hello")
                        .body("much better");
        FluentMailer.send(nv2);
    }


}