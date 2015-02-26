package br.com.henrique.calculadorapenal;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.util.Log;
import android.widget.Toast;

public class Pena implements Serializable {

	int[] condenacao;
	int fracao;
	boolean primariedade;
	Calendar inicioPena;
	private int diasPena;

	public Pena(int[] condenacao, int fracao, boolean primariedade,
			Calendar inicioPena) {

		this.condenacao = condenacao;
		this.fracao = fracao;
		this.inicioPena = inicioPena;
		this.primariedade = primariedade;

	}

	private String calculaProgressao() {

		if (fracao == 1) {
			// Converte a pena em dias para realizar a operação
			diasPena = (condenacao[0] * 365) + (condenacao[1] * 30)
					+ condenacao[2];
			
			diasPena = diasPena / 6;
			inicioPena.add(Calendar.DAY_OF_MONTH, diasPena);

			// Formata a data para o retorno
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return sdf.format(inicioPena.getTime());
		} else if (fracao == 2) {

			diasPena = (condenacao[0] * 365) + (condenacao[1] * 30)
					+ condenacao[2];
			diasPena = (diasPena / 5) * 2;
			inicioPena.add(Calendar.DAY_OF_YEAR, diasPena);

			// Formata a data para o retorno
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return sdf.format(inicioPena.getTime());

		} else if (fracao == 3) {

			// Converte a pena em dias para realizar a operação
			diasPena = (condenacao[0] * 365) + (condenacao[1] * 30)
					+ condenacao[2];
			diasPena = (diasPena / 5) * 3;
			
			inicioPena.add(Calendar.DAY_OF_MONTH, diasPena);

			// Formata a data para o retorno
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return sdf.format(inicioPena.getTime());

		} else
			return null;
	}

	private String calculaCondicional() {

		if (fracao == 1 && primariedade == true) {
			
			inicioPena.add(Calendar.DAY_OF_MONTH, -diasPena);
			
			// Converte a pena em dias para realizar a operação
			diasPena = (condenacao[0] * 365) + (condenacao[1] * 30)
					+ condenacao[2];

			
			inicioPena.add(Calendar.DAY_OF_MONTH, diasPena / 3);

			// Formata a data para o retorno
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return sdf.format(inicioPena.getTime());
		} else if (fracao == 1 && primariedade == false) {
			
			inicioPena.add(Calendar.DAY_OF_MONTH, -diasPena);

			// Converte a pena em dias para realizar a operação
			diasPena = (condenacao[0] * 365) + (condenacao[1] * 30)
					+ condenacao[2];
			diasPena = (diasPena / 3) * 2;
			
			inicioPena.add(Calendar.DAY_OF_MONTH, diasPena);

			// Formata a data para o retorno
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return sdf.format(inicioPena.getTime());

		} else {

			inicioPena.add(Calendar.DAY_OF_MONTH, -diasPena);

			// Converte a pena em dias para realizar a operação
			diasPena = (condenacao[0] * 365) + (condenacao[1] * 30)
					+ condenacao[2];
			diasPena = (diasPena / 3) * 2;

			inicioPena.add(Calendar.DAY_OF_MONTH, diasPena);

			// Formata a data para o retorno
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return sdf.format(inicioPena.getTime());

		}

	}

	public String getProgressao() {
		return calculaProgressao();
	}

	public String getCondicional() {
		return calculaCondicional();
	}

}
