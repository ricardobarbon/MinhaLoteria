package com.barbon.minhaloteria.modelo;

/**
 * Created by Barbon on 22/02/2015.
 */
public abstract class Numero {
    private long id;
    private byte numero;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte getNumero() {
        return numero;
    }

    public void setNumero(byte numero) {
        this.numero = numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Numero numero1 = (Numero) o;

        return numero == numero1.numero;
    }

    @Override
    public int hashCode() {
        return (int) numero;
    }
}
