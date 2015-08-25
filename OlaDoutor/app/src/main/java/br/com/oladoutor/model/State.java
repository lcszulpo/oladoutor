package br.com.oladoutor.model;

/**
 * Classe utilizada para representar um estado.
 *
 * @author Lucas Zulpo Pasa
 * @since 16/08/2015.
 */
public class State {

    private Integer id;
    private String name;
    private FederativeUnit federativeUnit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FederativeUnit getFederativeUnit() {
        return federativeUnit;
    }

    public void setFederativeUnit(FederativeUnit federativeUnit) {
        this.federativeUnit = federativeUnit;
    }

    public enum FederativeUnit {
        AC, AL, AP, AM, BA, CE, DF, ES, GO, MA, MT, MS, MG, PA, PB, PR, PE, PI, RJ, RN, RS, RO, RR, SC, SP, SE, TO;
    }

}
