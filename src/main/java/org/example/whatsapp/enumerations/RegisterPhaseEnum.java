package org.example.whatsapp.enumerations;

public enum RegisterPhaseEnum {
    NOME(1),
    EMAIL(2),
    VERIFICACAO(3);

    int numVal;

    RegisterPhaseEnum(int i) {
            this.numVal = i;
        }

        public int value() {
            return numVal;
        }
}
