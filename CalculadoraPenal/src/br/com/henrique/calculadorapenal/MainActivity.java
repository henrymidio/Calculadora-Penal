package br.com.henrique.calculadorapenal;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends Activity {

	private int condenacaoAnos = 0;
	private int condenacaoMeses = 0;
	private int condenacaoDias = 0;
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
	private int[] condenacao;

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
		if (id == R.id.itemArquivo) {
			Intent intent = new Intent(this, Lista.class);
			startActivity(intent);
			return true;
		}
		if (id == R.id.itemLEP) {
			Intent intent = new Intent(this, Lep.class);
			startActivity(intent);
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
		condenacao = new int[3];
		condenacao[0] = Integer.parseInt(editAnos.getText().toString());
		condenacao[1] = Integer.parseInt(editMeses.getText().toString());
		condenacao[2] = Integer.parseInt(editDias.getText().toString());

		inicioDias = dp.getDayOfMonth();
		inicioMeses = dp.getMonth();
		inicioAnos = dp.getYear();
		Calendar dataInicio = new GregorianCalendar(inicioAnos, inicioMeses,
				inicioDias);

		Pena pena = new Pena(condenacao, fracao, primario, dataInicio);

		Intent intent = new Intent(this, Resultado.class);
		intent.putExtra("Pena", pena);
		startActivity(intent);

	}
}
