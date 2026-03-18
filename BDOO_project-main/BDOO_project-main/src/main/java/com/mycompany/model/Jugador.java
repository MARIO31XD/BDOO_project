package com.mycompany.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nick;
    private int nivell;

    @ManyToMany
    private List<Carta> coleccio = new ArrayList<>();

    public Jugador() {}
    public Jugador(String nick, int nivell) {
        this.nick = nick;
        this.nivell = nivell;
    }

    public void addCartaAColeccio(Carta c) { this.coleccio.add(c); }
    public String getNick() { return nick; }
}