package org.example.whatsapp.register.student;

import it.auties.whatsapp.api.Whatsapp;
import it.auties.whatsapp.model.info.MessageInfo;

import static org.example.whatsapp.enumerations.RegisterPhaseEnum.EMAIL;
import static org.example.whatsapp.enumerations.RegisterPhaseEnum.NOME;

public class RegistrationManager {
    public boolean isRegistering = false;
    private UserData userData = new UserData();
    String text;

    public void processMessage(Whatsapp whatsapp, MessageInfo info) {
        if (isRegistering) {
            switch (userData.getRegisterPhase()) {
                case NOME:
                    if (info.chat().newestMessage().get().message().textWithNoContextMessage().isPresent() && !info.chat().newestMessage().get().fromMe()) {
                        text = info.chat().newestMessage().get().message().textWithNoContextMessage().get();
                        userData.setName(text);
                        userData.setRegisterPhase(EMAIL);
                        whatsapp.sendMessage(info.chatJid(), "Agora digite o seu e-mail.");
                    }
                    break;

                case EMAIL:
                    if (!info.chat().newestMessage().get().fromMe()) {
                        text = info.chat().newestMessage().get().message().textWithNoContextMessage().get();
                        userData.setEmail(text);
                        checkInformations(whatsapp, info);
                    }
                    break;
            }
        }
    }

    private void checkInformations(Whatsapp whatsapp, MessageInfo info) {
        whatsapp.sendMessage(info.chatJid(), "Vamos confirmar os dados agora, seu nome é: " + userData.getName() + " e seu e-mail é: " + userData.getEmail());
        userData.setRegisterPhase(NOME);
        isRegistering = false;
    }
}
