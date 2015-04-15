package com.barbon.minhaloteria.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.barbon.minhaloteria.modelo.Loteria;

/**
 * Created by Barbon on 03/03/2015.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "loterias";
    private static final int VERSAO_BANCO = 1;

    public static final String TABELA_LOTERIA = "loteria";
    public static final String TABELA_PREMIO = "premio";
    public static final String TABELA_JOGO = "jogo";
    public static final String TABELA_NUMEROJOGADO = "numerojogado";
    public static final String TABELA_CONCURSO = "concurso";
    public static final String TABELA_SORTEIO = "sorteio";
    public static final String TABELA_NUMEROSORTEADO = "numerosorteado";
    public static final String TABELA_JOGOCONSURSO = "jogoconcurso";
    public static final String TABELA_JOGOCONCURSOSORTEIO = "jogoconcursosorteio";

    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_DESCRICAO = "descricao";
    public static final String COLUNA_QTDE_MIN_MARCACAO = "qtdeminmarcacao";
    public static final String COLUNA_QTDE_MAX_MARCACAO = "qtdemaxmarcacao";
    public static final String COLUNA_QTDE_ACERTO = "qtdeacerto";
    public static final String COLUNA_NIVEL = "nivel";
    public static final String COLUNA_ID_LOTERIA = "id_loteria";
    public static final String COLUNA_JOGO_PERMANENTE = "jogopermanente";
    public static final String COLUNA_NUMERO = "numero";
    public static final String COLUNA_ID_JOGO = "id_jogo";
    public static final String COLUNA_DATA = "data";
    public static final String COLUNA_NUMERO_SORTEIO = "numerosorteio";
    public static final String COLUNA_ID_CONCURSO = "id_concurso";
    public static final String COLUNA_ID_SORTEIO = "id_sorteio";
    public static final String COLUNA_ID_PREMIO = "id_premio";
    public static final String COLUNA_ID_JOGOCONCURSO = "id_jogoconcurso";

    private static final String CRIA_TABELA_LOTERIA = "CREATE TABLE " + TABELA_LOTERIA + "(" +
                                                        "" + COLUNA_ID + " INTEGER PRIMARY KEY," +
                                                        "" + COLUNA_DESCRICAO + " TEXT," +
                                                        "" + COLUNA_QTDE_MIN_MARCACAO + " INTEGER," +
                                                        "" + COLUNA_QTDE_MAX_MARCACAO + " INTEGER)";

    private static final String CRIA_TABELA_PREMIO = "CREATE TABLE " + TABELA_PREMIO + "(" +
                                                        "" + COLUNA_ID + " INTEGER PRIMARY KEY," +
                                                        "" + COLUNA_QTDE_ACERTO + " INTEGER," +
                                                        "" + COLUNA_NIVEL + " INTEGER," +
                                                        "" + COLUNA_ID_LOTERIA + " INTEGER," +
                                                        "FOREIGN KEY(" + COLUNA_ID_LOTERIA + ") REFERENCES " + TABELA_LOTERIA + "(" + COLUNA_ID + "))";

    private static final String CRIA_TABELA_JOGO = "CREATE TABLE " + TABELA_JOGO + "(" +
                                                        "" + COLUNA_ID + " INTEGER PRIMARY KEY," +
                                                        "" + COLUNA_DESCRICAO + " TEXT," +
                                                        "" + COLUNA_JOGO_PERMANENTE + " INTEGER," +
                                                        "" + COLUNA_ID_LOTERIA + " INTEGER," +
                                                        "FOREIGN KEY(" + COLUNA_ID_LOTERIA + ") REFERENCES " + TABELA_LOTERIA + "(" + COLUNA_ID + "))";

    private static final String CRIA_TABELA_NUMEROJOGADO = "CREATE TABLE " + TABELA_NUMEROJOGADO + "(" +
                                                            "" + COLUNA_ID + " INTEGER PRIMARY KEY," +
                                                            "" + COLUNA_NUMERO + " INTEGER," +
                                                            "" + COLUNA_ID_JOGO + " INTEGER," +
                                                            "FOREIGN KEY (" + COLUNA_ID_JOGO + ") REFERENCES " + TABELA_JOGO + "(" + COLUNA_ID + "))";

    private static final String CRIA_TABELA_CONSURSO = "CREATE TABLE " + TABELA_CONCURSO + "(" +
                                                        "" + COLUNA_ID + " INTEGER PRIMARY KEY," +
                                                        "" + COLUNA_NUMERO + " INTEGER," +
                                                        "" + COLUNA_DATA + " INTEGER," +
                                                        "" + COLUNA_ID_LOTERIA + " INTEGER," +
                                                        "FOREIGN KEY (" + COLUNA_ID_LOTERIA + ") REFERENCES " + TABELA_LOTERIA + "(" + COLUNA_ID + "))";

    private static final String CRIA_TABELA_SORTEIO = "CREATE TABLE " + TABELA_SORTEIO + "(" +
                                                        "" + COLUNA_ID + " INTEGER PRIMARY KEY," +
                                                        "" + COLUNA_NUMERO_SORTEIO + " INTEGER," +
                                                        "" + COLUNA_ID_CONCURSO + " INTEGER," +
                                                        "FOREIGN KEY (" + COLUNA_ID_CONCURSO + ") REFERENCES " + TABELA_CONCURSO + "(" + COLUNA_ID + "))";

    private static final String CRIA_TABELA_NUMEROSORTEADO = "CREATE TABLE " + TABELA_NUMEROSORTEADO + "(" +
                                                                "" + COLUNA_ID + " INTEGER PRIMARY KEY," +
                                                                "" + COLUNA_NUMERO + " INTEGER," +
                                                                "" + COLUNA_ID_SORTEIO + " INTEGER," +
                                                                "FOREIGN KEY (" + COLUNA_ID_SORTEIO + ") REFERENCES " + TABELA_SORTEIO + "(" + COLUNA_ID + "))";

    private static final String CRIA_TABELA_JOGOCONCURSO= "CREATE TABLE " + TABELA_JOGOCONSURSO + "(" +
                                                                "" + COLUNA_ID + " INTEGER PRIMARY KEY," +
                                                                "" + COLUNA_ID_JOGO + " INTEGER," +
                                                                "" + COLUNA_ID_CONCURSO + " INTEGER," +
                                                                "FOREIGN KEY (" + COLUNA_ID_JOGO + ") REFERENCES " + TABELA_JOGO + "(" + COLUNA_ID + ")," +
                                                                "FOREIGN KEY (" + COLUNA_ID_CONCURSO + ") REFERENCES " + TABELA_CONCURSO + "(" + COLUNA_ID + "))";

    private static final String CRIA_TABELA_JOGOCONCURSOSORTEIO = "CREATE TABLE " + TABELA_JOGOCONCURSOSORTEIO + "(" +
                                                            "" + COLUNA_ID_JOGOCONCURSO + " INTEGER," +
                                                            "" + COLUNA_ID_SORTEIO + " INTEGER," +
                                                            "" + COLUNA_ID_PREMIO + " INTEGER," +
                                                            "" + COLUNA_QTDE_ACERTO + " INTEGER," +
                                                            "PRIMARY KEY (" + COLUNA_ID_JOGOCONCURSO + ", " + COLUNA_ID_SORTEIO + "), " +
                                                            "FOREIGN KEY (" + COLUNA_ID_JOGOCONCURSO + ") REFERENCES " + TABELA_JOGOCONSURSO + "(" + COLUNA_ID + ")," +
                                                            "FOREIGN KEY (" + COLUNA_ID_SORTEIO + ") REFERENCES " + TABELA_SORTEIO + "(" + COLUNA_ID + ")," +
                                                            "FOREIGN KEY (" + COLUNA_ID_PREMIO + ") REFERENCES " + TABELA_PREMIO + "(" + COLUNA_ID + "))";

    private static DataBaseHelper instance;

    public static synchronized DataBaseHelper getHelper(Context context){

        if (instance == null)
            instance = new DataBaseHelper(context);

        return instance;
    }

    private DataBaseHelper(Context context){
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
        if (!db.isReadOnly()){
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CRIA_TABELA_LOTERIA);
        db.execSQL(CRIA_TABELA_PREMIO);
        db.execSQL(CRIA_TABELA_JOGO);
        db.execSQL(CRIA_TABELA_NUMEROJOGADO);
        db.execSQL(CRIA_TABELA_CONSURSO);
        db.execSQL(CRIA_TABELA_SORTEIO);
        db.execSQL(CRIA_TABELA_NUMEROSORTEADO);
        db.execSQL(CRIA_TABELA_JOGOCONCURSO);
        db.execSQL(CRIA_TABELA_JOGOCONCURSOSORTEIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
