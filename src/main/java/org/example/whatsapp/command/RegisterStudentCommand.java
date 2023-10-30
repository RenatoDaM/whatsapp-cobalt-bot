package org.example.whatsapp.command;

import it.auties.whatsapp.api.Whatsapp;
import it.auties.whatsapp.model.info.MessageInfo;
import org.example.whatsapp.bot.WhatsappBotListener;

import java.util.Set;

public class RegisterStudentCommand implements Command {

    @Override
    public void onCommand(Whatsapp api, MessageInfo message) {
        api.sendMessage(message.chatJid(), "Ok! Você quer se cadastrar, certo? Primeiro, escreva seu nome inteiro");
    }

    @Override
    public String command() {
        return "/registrar";
    }

    @Override
    public Set<String> alias() {
        return Set.of("/register", "/matricular");
    }
}
