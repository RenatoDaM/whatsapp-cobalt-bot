package org.example.whatsapp.register.student;

import org.example.whatsapp.enumerations.RegisterPhaseEnum;

public class UserData {
    private String name;
    private String email;

    private RegisterPhaseEnum registerPhase = RegisterPhaseEnum.NOME;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RegisterPhaseEnum getRegisterPhase() {
        return registerPhase;
    }

    public void setRegisterPhase(RegisterPhaseEnum registerPhase) {
        this.registerPhase = registerPhase;
    }
}
