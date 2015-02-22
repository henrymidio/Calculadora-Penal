package br.com.henrique.calculadorapenal;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends Activity {

	private int anos = 0;
	private int meses = 0;
	private int dias = 0;
	private boolean primario = true;
	private String data;
	private int fracao = 1;

	private EditText editAnos;
	private EditText editMeses;
	private EditText editDias;
	private DatePicker dp;

	private Button umSexto;
	private Button doisQuintos;
	private Button tresQuintos;
	private RadioButton rb3;
	private RadioButton rb2;
	private DatePicker dp2;
	private int inicioDias;
	private int inicioMeses;
	private int inicioAnos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		editAnos = (EditText) findViewById(R.id.anos);
		editMeses = (EditText) findViewById(R.id.meses);
		editDias = (EditText) findViewById(R.id.dias);
		dp = (DatePicker) findViewById(R.id.inicioPena);

		umSexto = (Button) findViewById(R.id.umSexto);
		umSexto.setBackgroundColor(Color.GREEN);
		doisQuintos = (Button) findViewById(R.id.doisQuintos);
		doisQuintos.setBackgroundColor(Color.GRAY);
		tresQuintos = (Button) findViewById(R.id.tresQuintos);
		tresQuintos.setBackgroundColor(Color.GRAY);

		rb3 = (RadioButton) findViewById(R.id.reincidente);
		rb2 = (RadioButton) findViewById(R.id.primario);

		dp2 = (DatePicker) findViewById(R.id.inicioPena);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void fracaoPena(View view) {
		// Seta background do botão clicado
		Button botao = (Button) findViewById(view.getId());
		botao.setBackgroundColor(Color.GREEN);

		if (umSexto.getId() != botao.getId()) {
			umSexto.setBackgroundColor(Color.GRAY);
		}
		if (doisQuintos.getId() != botao.getId()) {
			doisQuintos.setBackgroundColor(Color.GRAY);
		}
		if (tresQuintos.getId() != botao.getId()) {
			tresQuintos.setBackgroundColor(Color.GRAY);

		}

		// SETAS AS VARIÁVEIS E OS RADIO BUTTONS DINAMICAMENTE
		switch (botao.getId()) {
		case R.id.umSexto:
			fracao = 1;
			rb2.setEnabled(true);
			rb3.setEnabled(true);
			rb2.setChecked(true);
			primario = true;
			break;
		case R.id.doisQuintos:
			fracao = 2;
			rb2.setEnabled(true);
			rb3.setEnabled(false);
			rb2.setChecked(true);
			primario = true;
			break;
		case R.id.tresQuintos:
			fracao = 3;
			rb3.setEnabled(true);
			rb2.setEnabled(false);
			rb3.setChecked(true);
			primario = false;
			break;
		}

	}

	public void onRadioButtonClicked(View view) {
		boolean checked = ((RadioButton) view).isChecked();

		switch (view.getId()) {
		case R.id.primario:
			if (checked)

				primario = true;

			break;
		case R.id.reincidente:
			if (checked)

				primario = false;

			break;
		}
	}

	public void calcularPena(View view) {

		// Recupera dados de entrada do usuário
		anos = Integer.parseInt(editAnos.getText().toString());
		meses = Integer.parseInt(editMeses.getText().toString());
		dias = Integer.parseInt(editDias.getText().toString());

		inicioDias = dp.getDayOfMonth();
		inicioMeses = dp.getMonth();
		inicioAnos = dp.getYear();

	}
}
