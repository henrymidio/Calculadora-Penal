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

public class BDCore extends SQLiteOpenHelper {

	private final static String BD_NOME = "CALCULADORA_PENAL";
	private final static String TABELA_01 = "ACUSADO";
	private final static String TABELA_02 = "PENA";
	private final static int VERSAO = 1;
	private SQLiteDatabase wd;

	public BDCore(Context context) {
		super(context, BD_NOME, null, VERSAO);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL("CREATE TABLE " + TABELA_02
				+ " (ID_PENA integer primary key autoincrement, "
				+ "PROGRESSAO varchar(10) not null, "
				+ "CONDICIONAL varchar(10) not null);");

		db.execSQL("CREATE TABLE " + TABELA_01
				+ " (_ID integer primary key autoincrement, "
				+ "NOME varchar(45) not null, "
				+ "PROCESSO varchar(20) not null, " + "PENA integer, "

				+ "FOREIGN KEY (PENA) REFERENCES PENA(ID_PENA));");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE ACUSADO, PENA;");
	}

	public boolean addAcusado(String nome, String processo) {

		ContentValues cv = new ContentValues();
		cv.put("NOME", nome);
		cv.put("PROCESSO", processo);

		wd = getWritableDatabase();
		long res = wd.insert(TABELA_01, null, cv);

		if (res < 0) {
			throw new SQLException();

		} else

			return true;
	}

	public boolean addPena(String progressao, String condicional) {

		ContentValues cv = new ContentValues();
		cv.put("PROGRESSAO", progressao);
		cv.put("CONDICIONAL", condicional);

		long res = wd.insert(TABELA_02, null, cv);

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

}
