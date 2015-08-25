package br.com.oladoutor.model;

/**
 * Classe utilizada para representar uma cidade.
 *
 * @author Lucas Zulpo Pasa
 * @since 16/08/2015.
 */
public class City {

    private Integer id;
    private String name;
    private State state;

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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

}
