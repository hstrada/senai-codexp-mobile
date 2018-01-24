package sp.senai.br.android_rv_notify.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import sp.senai.br.android_rv_notify.model.Jogo;

/**
 * Created by adminLocal on 10/01/2018.
 */
public class JogoDaoSqlite {

    private SQLiteDatabase db;
    private JogoDbHelper dbo;

    public JogoDaoSqlite(Context context) {
        dbo = new JogoDbHelper(context);
    }

    public void salvar(Jogo jogo) {

        SQLiteDatabase db = dbo.getWritableDatabase();

        /* long resultado;
        ContentValues values = new ContentValues();
        values.put(JogoDbHelper.NOME, jogo.getNome());
        values.put(JogoDbHelper.FABRICANTE, jogo.getFabricante());
        resultado = db.insert(JogoDbHelper.TABELA,
                null,
                values);
        if (resultado == -1) {
            Log.d("Erro ao inserir", "Erro");
            return "Erro ao inserir registro";
        } else {
            Log.d("Sucesso ao inserir", "Sucesso");
            return "Registro inserido com sucesso";
        } */

        String inserir = "insert into "
                + JogoDbHelper.TABELA
                + " (nome, fabricante) values (?, ?)";
        db.execSQL(inserir, new Object[]{jogo.getNome(), jogo.getFabricante()});
        db.close();

    }

    public List<Jogo> getLista() {
        List<Jogo> jogos = new LinkedList<>();
        String rawQuery = "SELECT _id, nome, fabricante FROM " +
                JogoDbHelper.TABELA;
        SQLiteDatabase db = dbo.getReadableDatabase();
        Cursor cursor = db.rawQuery(rawQuery, null);
        Jogo jogo = null;
        if (cursor.moveToFirst()) {
            do {
                jogo = new Jogo();
                jogo.setId(cursor.getLong(0));
                jogo.setNome(cursor.getString(1));
                jogo.setFabricante(cursor.getString(2));
                jogos.add(jogo);
            } while (cursor.moveToNext());
        }
        return jogos;
    }

    public void atualizar(Jogo jogo) {

        SQLiteDatabase db = dbo.getWritableDatabase();

        /* long resultado;
        ContentValues values = new ContentValues();
        values.put(JogoDbHelper.NOME, jogo.getNome());
        values.put(JogoDbHelper.FABRICANTE, jogo.getFabricante());
        resultado = db.update(JogoDbHelper.TABELA, values, JogoDbHelper.ID + "=" + jogo.getId(), null);

        if (resultado == -1) {
            Log.d("Erro ao inserir", "Erro");
            return "Erro ao inserir registro";
        } else {
            Log.d("Sucesso ao inserir", "Sucesso");
            return "Registro inserido com sucesso";
        } */

        String update = "update " + JogoDbHelper.TABELA + " set nome = ?, fabricante = ? where _id = ?";
        db.execSQL(update, new Object[]{jogo.getNome(), jogo.getFabricante(), jogo.getId()});
        Log.d("sql: ", update);
        db.close();
    }

    public Jogo localizar(Long id) {
        SQLiteDatabase db = dbo.getWritableDatabase();
        String query = "SELECT _id, nome, fabricante FROM " + JogoDbHelper.TABELA + " WHERE _id = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});
        cursor.moveToFirst();
        Jogo jogoA = new Jogo();
        jogoA.setId(cursor.getLong(0));
        jogoA.setNome(cursor.getString(1));
        jogoA.setFabricante(cursor.getString(2));
        db.close();
        return jogoA;
    }

    public void remover(Jogo jogo) {
        SQLiteDatabase db = dbo.getWritableDatabase();

        /* String where = JogoDbHelper.ID + "=" + jogo.getId();
        db.delete(JogoDbHelper.TABELA, where, null); */
        String deletar = "delete from " + JogoDbHelper.TABELA + " where _id = ?";
        db.execSQL(deletar, new Object[]{jogo.getId()});
        db.close();

    }

}

