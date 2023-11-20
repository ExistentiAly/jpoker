package dev.ambs.poker;

import dev.ambs.poker.exception.NoSeatAvailableException;
import dev.ambs.poker.game.Game;
import dev.ambs.poker.player.ConsolePlayer;
import dev.ambs.poker.player.MrStupid;
import dev.ambs.poker.table.ChipStack;
import dev.ambs.poker.table.Table;
import dev.ambs.poker.table.TableBuilder;

import java.io.IOException;
import java.io.InputStreamReader;

public class Poker {

    public static void main(String... args) throws NoSeatAvailableException, IOException {

        Table table = TableBuilder.createTableBuilder(9)
                .withName("Console Poker Tournament")
                .withGame(Game.noLimitTexasHoldem())
                .build();

        table.seatPlayer(MrStupid.create("Vicks"), ChipStack.of(1500L));
        table.seatPlayer(MrStupid.create("Wedge"), ChipStack.of(1500L));
        table.seatPlayer(MrStupid.create("Chewbacca"), ChipStack.of(1500L));
        table.seatPlayer(MrStupid.create("Han"), ChipStack.of(1500L));
        table.seatPlayer(MrStupid.create("C3PO"), ChipStack.of(1500L));
        table.seatPlayer(MrStupid.create("Luke"), ChipStack.of(1500L));
        table.seatPlayer(MrStupid.create("R2D2"), ChipStack.of(1500L));
        table.seatPlayer(MrStupid.create("Leia"), ChipStack.of(1500L));

        try (InputStreamReader inputStreamReader = new InputStreamReader(System.in)) {
            table.seatPlayer(ConsolePlayer.create(inputStreamReader, "Bork"), ChipStack.of(1500L));
            table.run();
        }
    }
}
