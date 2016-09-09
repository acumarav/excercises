package org.alext.lambdas;

import java.util.function.Consumer;

/**
 * Created by tsumaraa on 09/09/2016.
 */
public class FluentMailer {

    private FluentMailer() {}

    private StringBuilder builder=new StringBuilder();

    public FluentMailer from(final String address) { builder.append(address+"\n"); return this; }

    public FluentMailer to(final String to)   { builder.append(to+"\n"); return this; }

    public FluentMailer subject(final String line) { builder.append(line+"\n"); return this; }

    public FluentMailer body(final String message) { builder.append(message+"\n"); return this; }


    public static void send(final Consumer<FluentMailer> block) {

        final FluentMailer mailer = new FluentMailer();

        block.accept(mailer);

        System.out.println("sending..." +mailer.builder.toString());

    }
    //...
}
