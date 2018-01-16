package sp.senai.br.android_rv_notify.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by helena.strada on 11/01/18.
 */

public class JogoDbHelper extends SQLiteOpenHelper {

    // propriedades para o nosso banco de dados
    private static final String NOME_BANCO = "dbjogos.db";
    public static final String TABELA = "jogos";
    public static final String ID = "_id";
    public static final String NOME = "nome";
    public static final String FABRICANTE = "fabricante";
    private static final int VERSAO = 1;

    // definimos o nome do nosso banco e qual script queremos executar no início da nossa aplicação
    public JogoDbHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // script para a criação da nossa tabela
        String criarBD = "CREATE TABLE "+TABELA+" ("
                + ID + " integer primary key autoincrement,"
                + NOME + " text,"
                + FABRICANTE + " text)";
        sqLiteDatabase.execSQL(criarBD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // script para atualização
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(sqLiteDatabase);

    }
}
