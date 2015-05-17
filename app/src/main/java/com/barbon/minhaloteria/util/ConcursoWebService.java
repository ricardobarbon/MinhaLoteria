package com.barbon.minhaloteria.util;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Barbon on 14/05/2015.
 */
public class ConcursoWebService {
    @SerializedName("Concurso")
    public int concurso;
    @SerializedName("Data")
    public String data;
    @SerializedName("Dezenas")
    public String dezenas;
}
