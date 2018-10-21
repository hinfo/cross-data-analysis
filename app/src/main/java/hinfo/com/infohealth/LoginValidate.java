package hinfo.com.infohealth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.Toast;

public class LoginValidate extends AppCompatActivity {

    Intent it, viewHome;
    String[] results;
    private ListView list;
    WebView webview;
    String urlBase, url, login, password, month, year, data, errorLogin;

    private UserLoginTask mAuthTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exames);
        webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        urlBase = "http://www.infohealth.com.br:40191/cgi-bin/maps.sh?"; // labivida
        errorLogin = "<html><body style=\"background-color:powderblue;\"><h2 style=\"text-align:center;\">User or Password incorrects!</h2></body></html>";

        it = new Intent(this, LoginValidate.class);
        viewHome = new Intent(this, LoginActivity.class);
        Intent params = getIntent();
//        list = (ListView) findViewById(R.id.lista_exames);
        if (params != null) {
            login = params.getStringExtra("user");
            password = params.getStringExtra("password");
            data = params.getStringExtra("date");
            month = data.split("/")[1];
            year = data.split("/")[2];
            url = urlBase + "login_op=" + login + "&passwd_op=" + password + "&month_ref=" + month + "&year_ref=" + year;
            mAuthTask = new LoginValidate.UserLoginTask(login, password);
            mAuthTask.execute(url);

        } else {
            System.out.println("Result is empty!");
        }


    }


    public class UserLoginTask extends AsyncTask<String, Void, String> {

        ProgressDialog dialog;
        private final String mUsername;
        private final String mPassword;


        UserLoginTask(String username, String password) {
            mUsername = username;
            mPassword = password;
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(LoginValidate.this);
            dialog.show();
        }


        @Override
        protected String doInBackground(String... params) {

            try {
                String filter = params[0];
                System.out.println(filter);
                if (TextUtils.isEmpty(filter)) {
                    return null;
                }
                String content = HTTPUtils.acessar(filter);
                System.out.println(content);


                return content;

//            } catch (JSONException e) {
            } catch (Exception e) {
                System.out.println("No results to show");
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String conteudo) {
            mAuthTask = null;

            if (conteudo.contains("User or Password incorrects!")) {
                Toast.makeText(LoginValidate.this, "User or Password incorrects!", Toast.LENGTH_LONG).show();
                startActivity(viewHome);

            } else {
                webview.loadData(conteudo, "text/html", null);
            }

            dialog.dismiss();
        }


        @Override
        protected void onCancelled() {
            mAuthTask = null;
            dialog.dismiss();
        }

    }
}
