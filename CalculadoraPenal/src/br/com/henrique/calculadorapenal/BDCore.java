package br.com.henrique.calculadorapenal;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BDCore extends SQLiteOpenHelper {

	private final static String BD_NOME = "CALCULADORA_PENAL";
	private final static String TABELA_01 = "ACUSADO";

	private final static int VERSAO = 3;
	private SQLiteDatabase wd = getWritableDatabase();

	public BDCore(Context context) {
		super(context, BD_NOME, null, VERSAO);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL("CREATE TABLE IF NOT EXISTS " + TABELA_01
				+ " (_ID integer primary key autoincrement, "
				+ "NOME varchar(45) not null, " + "PROCESSO varchar(20) not null, "
				+ "PROGRESSAO varchar(18) not null, "
				+ "CONDICIONAL varchar(18) not null);");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS ACUSADO;");
		onCreate(db);
	}

	public boolean addAcusado(String nome, String processo, String progressao,
			String condicional) {

		ContentValues cv = new ContentValues();
		cv.put("NOME", nome);
		cv.put("PROCESSO", processo);
		cv.put("PROGRESSAO", progressao);
		cv.put("CONDICIONAL", condicional);

		long res = wd.insert(TABELA_01, null, cv);

		if (res < 0) {
			throw new SQLException();

		} else

			return true;
	}

	public List<String> selecionaNomesAcusado() {

		List<String> nomes = new ArrayList<String>();
		String[] coluna = new String[] { "NOME" };

		Cursor cursor = wd.query(TABELA_01, coluna, null, null, null, null,
				"NOME ASC");

		if (cursor.getCount() > 0) {

			cursor.moveToFirst();

			do {

				nomes.add(cursor.getString(0));

			} while (cursor.moveToNext());

		}

		return nomes;
	}

	public List<String> selecionaDadosAcusado(String nome) {

		List<String> dados = new ArrayList<String>();

		Cursor mCursor = wd.rawQuery("SELECT NOME, PROCESSO, PROGRESSAO, CONDICIONAL FROM ACUSADO "
				+ "WHERE ACUSADO.NOME = '" + nome + "'", null);

		if (mCursor.getCount() > 0) {

			mCursor.moveToFirst();

			do {

				dados.add(mCursor.getString(0));
				dados.add(mCursor.getString(1));
				dados.add(mCursor.getString(2));
				dados.add(mCursor.getString(3));

				Log.d("dados", dados.get(0));
				Log.d("dados", dados.get(1));
				Log.d("dados", dados.get(2));
				Log.d("dados", dados.get(3));

			} while (mCursor.moveToNext());

		}

		return dados;
	}
}
