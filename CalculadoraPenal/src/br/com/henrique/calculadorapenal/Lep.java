package br.com.henrique.calculadorapenal;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class Lep extends Activity {

	private WebView wv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.layout_lep);

		ActionBar ab = getActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setDisplayShowTitleEnabled(false);
		ab.setIcon(R.drawable.ic_action_back);
		
		setProgressBarIndeterminateVisibility(true);

		wv = (WebView) findViewById(R.id.webView1);

		wv.loadUrl("http://www.planalto.gov.br/ccivil_03/leis/l7210.htm");
		wv.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {

				/** This prevents the loading of pages in system browser */
				return false;
			}

			/** Callback method, executed when the page is completely loaded */
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);

				/** Hiding Indeterminate Progress Bar in the title bar */
				setProgressBarIndeterminateVisibility(false);

			}

		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == android.R.id.home) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
