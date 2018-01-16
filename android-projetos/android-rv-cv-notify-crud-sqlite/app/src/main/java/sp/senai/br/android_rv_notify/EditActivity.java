package sp.senai.br.android_rv_notify;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import sp.senai.br.android_rv_notify.dao.JogoDao;
import sp.senai.br.android_rv_notify.model.Jogo;

public class EditActivity extends AppCompatActivity {

    private TextInputLayout tilNome;
    private TextInputLayout tilFabricante;
    private EditText etNome;
    private EditText etFabricante;
    public final static int CODE_JOGO = 1002;

    private JogoDao dao = new JogoDao(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        tilNome = (TextInputLayout) findViewById(R.id.tilNome);
        tilFabricante = (TextInputLayout) findViewById(R.id.tilFabricante);

        etNome = (EditText) findViewById(R.id.etNome);
        etFabricante = (EditText) findViewById(R.id.etFabricante);

        // para verificar se o jogo já existe
        carregarJogo();

    }

    public void cadastrar(View v) {
        final Bundle extras = getIntent().getExtras();
        Long jogoId = (extras != null) ? extras.getLong("jogoId") : null;
        if (jogoId == null) {
            Jogo jogo = new Jogo();
            jogo.setNome(tilNome.getEditText().getText().toString());
            jogo.setFabricante(tilFabricante.getEditText().getText().toString());
            dao.salvar(jogo);
        } else {
            Jogo jogo = new Jogo();
            jogo.setId(jogoId);
            jogo.setNome(tilNome.getEditText().getText().toString());
            jogo.setFabricante(tilFabricante.getEditText().getText().toString());
            dao.atualizar(jogo);
        }
        retornaParaTelaAnterior();
    }

    public void retornaParaTelaAnterior() {
        Intent intentMessage = new Intent();
        setResult(CODE_JOGO, intentMessage);
        finish();
    }

    public void carregarJogo() {
        final Bundle extras = getIntent().getExtras();
        Long jogoId = (extras != null) ? extras.getLong("jogoId") : null;

        if (jogoId == null) {
            Jogo jogo = new Jogo();
        } else {
            Jogo jogo = new Jogo();
            jogo = dao.localizar(jogoId);
            etNome.setText(jogo.getNome().toString());
            etFabricante.setText(jogo.getFabricante().toString());

        }
    }
}
