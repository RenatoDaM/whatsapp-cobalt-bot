package org.example.whatsapp.bot;

import it.auties.whatsapp.api.Whatsapp;
import org.example.whatsapp.command.CommandManager;
import org.example.whatsapp.command.HelloCommand;
import org.example.whatsapp.command.RegisterStudentCommand;

import java.util.Optional;

// This is the main class of our bot
public class WhatsappBot {
    public static void main(String... args) {
            // Initialize the command manager
            CommandManager.instance()
                    .addCommands(new HelloCommand(), new RegisterStudentCommand());

            // Create a new instance of WhatsappAPI
            var whats = Whatsapp.webBuilder()
                    .lastConnection()
                    .build();

            whats.addLoggedInListener(() -> System.out.println("Connected!"))
                    .addListener(new WhatsappBotListener(whats))
                    .connect()
                    .join();

        }
}