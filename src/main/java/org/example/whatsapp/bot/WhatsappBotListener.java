package org.example.whatsapp.bot;

import it.auties.whatsapp.api.Whatsapp;
import it.auties.whatsapp.listener.Listener;
import it.auties.whatsapp.listener.RegisterListener;
import it.auties.whatsapp.model.info.MessageInfo;
import it.auties.whatsapp.model.message.standard.TextMessage;
import org.example.whatsapp.command.CommandManager;
import org.example.whatsapp.enumerations.RegisterPhaseEnum;
import org.example.whatsapp.register.student.RegistrationManager;

@RegisterListener
public record WhatsappBotListener(Whatsapp whatsapp) implements Listener {
    static RegistrationManager registrationManager = new RegistrationManager();
    static boolean isRegistering = false;
    static String name;
    static String email;
    static RegisterPhaseEnum registerPhase = RegisterPhaseEnum.NOME;

    @Override
    public void onNewMessage(MessageInfo info) {
        if (!(info.message()
                .content() instanceof TextMessage textMessage)) {
            return;
        }

        registrationManager.processMessage(whatsapp, info);

        CommandManager.instance()
                .findCommand(textMessage.text())
                .ifPresent(command -> {
                    command.onCommand(whatsapp, info);
                    if (command.command().equals("/registrar")) registrationManager.isRegistering = true;
                });
    }

    void checkInformations(MessageInfo info) {
        whatsapp.sendMessage(info.chatJid(), "Vamos confirmar os dados agora, seu nome é: " + name + " e seu e-mail é: " + email);
        registerPhase = RegisterPhaseEnum.NOME;
        isRegistering = false;
    }
}
