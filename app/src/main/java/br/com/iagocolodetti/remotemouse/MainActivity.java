package br.com.iagocolodetti.remotemouse;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final int MIN_PORT = 0;
    private final int MAX_PORT = 65535;
    private final int MIN_PIXELS = 1;
    private final int MAX_PIXELS = 1000;
    private final int MIN_DELAY = 1;
    private final int MAX_DELAY = 1000;

    private Thread threadMouseCursor;
    private ThreadMouseCursor mouseCursor;
    private Thread threadMouseScroll;
    private ThreadMouseScroll mouseScroll;

    private RemoteMouse rm;
    private boolean block;

    private LinearLayout llIP;
    private LinearLayout llPorta;
    private LinearLayout llPixels;
    private LinearLayout llDelay;
    private ConstraintLayout clBotoes;

    private Button btConectar;

    private TextView tvConectado;

    private EditText etIP;
    private EditText etPorta;
    private EditText etPixels;
    private EditText etDelay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        block = false;

        llIP = findViewById(R.id.llIP);
        llPorta = findViewById(R.id.llPorta);
        llPixels = findViewById(R.id.llPixels);
        llDelay = findViewById(R.id.llDelay);
        clBotoes = findViewById(R.id.clBotoes);

        etIP = findViewById(R.id.etIP);
        etPorta = findViewById(R.id.etPorta);
        etPixels = findViewById(R.id.etPixels);
        etDelay = findViewById(R.id.etDelay);

        btConectar = findViewById(R.id.btConectar);

        tvConectado = findViewById(R.id.tvConectado);

        final Button[] btClick = new Button[4];
        btClick[0] = findViewById(R.id.btClickE);
        btClick[1] = findViewById(R.id.btClickM);
        btClick[2] = findViewById(R.id.btClickD);
        btClick[3] = findViewById(R.id.btClick2E);

        final Button[] btMover = new Button[8];
        btMover[0] = findViewById(R.id.btMoverC);
        btMover[1] = findViewById(R.id.btMoverB);
        btMover[2] = findViewById(R.id.btMoverE);
        btMover[3] = findViewById(R.id.btMoverD);
        btMover[4] = findViewById(R.id.btMoverEC);
        btMover[5] = findViewById(R.id.btMoverDC);
        btMover[6] = findViewById(R.id.btMoverEB);
        btMover[7] = findViewById(R.id.btMoverDB);

        final Button[] btScroll = new Button[2];
        btScroll[0] = findViewById(R.id.btScrollC);
        btScroll[1] = findViewById(R.id.btScrollB);

        etIP.setFilters(IPFilter());

        btConectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!block) {
                    if (btConectar.getText().equals("CONECTAR")) {
                        int porta = Integer.parseInt(etPorta.getText().toString());
                        if (porta >= MIN_PORT && porta <= MAX_PORT) {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    new Connect().execute();
                                }
                            });
                        } else {
                            Toast.makeText(MainActivity.this, "A porta deve ser no mínimo " + MIN_PORT + " e no máximo " + MAX_PORT + ".", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        disconnect();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Tentando realizar uma conexão, aguarde...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btClick[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mouseClick("1");
            }
        });

        btClick[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mouseClick("2");
            }
        });

        btClick[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mouseClick("3");
            }
        });

        btClick[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mouseClick("4");
            }
        });

        btMover[0].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, final MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startMouseCursor(v, "1");
                        break;
                    case MotionEvent.ACTION_UP:
                        stopMouseCursor(v);
                        break;
                }
                return false;
            }
        });

        btMover[1].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, final MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startMouseCursor(v, "2");
                        break;
                    case MotionEvent.ACTION_UP:
                        stopMouseCursor(v);
                        break;
                }
                return false;
            }
        });

        btMover[2].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, final MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startMouseCursor(v, "3");
                        break;
                    case MotionEvent.ACTION_UP:
                        stopMouseCursor(v);
                        break;
                }
                return false;
            }
        });

        btMover[3].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, final MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startMouseCursor(v, "4");
                        break;
                    case MotionEvent.ACTION_UP:
                        stopMouseCursor(v);
                        break;
                }
                return false;
            }
        });

        btMover[4].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, final MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startMouseCursor(v, "5");
                        break;
                    case MotionEvent.ACTION_UP:
                        stopMouseCursor(v);
                        break;
                }
                return false;
            }
        });

        btMover[5].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, final MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startMouseCursor(v, "6");
                        break;
                    case MotionEvent.ACTION_UP:
                        stopMouseCursor(v);
                        break;
                }
                return false;
            }
        });

        btMover[6].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, final MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startMouseCursor(v, "7");
                        break;
                    case MotionEvent.ACTION_UP:
                        stopMouseCursor(v);
                        break;
                }
                return false;
            }
        });

        btMover[7].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, final MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startMouseCursor(v, "8");
                        break;
                    case MotionEvent.ACTION_UP:
                        stopMouseCursor(v);
                        break;
                }
                return false;
            }
        });

        btScroll[0].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, final MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startMouseScroll(v, "1");
                        break;
                    case MotionEvent.ACTION_UP:
                        stopMouseScroll(v);
                        break;
                }
                return false;
            }
        });

        btScroll[1].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, final MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startMouseScroll(v, "2");
                        break;
                    case MotionEvent.ACTION_UP:
                        stopMouseScroll(v);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        restorePreferences();
    }

    @Override
    protected void onStop() {
        super.onStop();
        savePreferences();
    }

    private class Connect extends AsyncTask<Void, Void, String> {

        String ip;
        int porta;

        @Override
        protected void onPreExecute() {
            block = true;
            tvConectado.setText("Conectando...");
            ip = etIP.getText().toString();
            porta = Integer.parseInt(etPorta.getText().toString());
            etIP.setEnabled(false);
            etPorta.setEnabled(false);
        }

        @Override
        protected String doInBackground(Void... nothing) {
            try {
                rm = new RemoteMouse(ip, porta);
                return "";
            } catch (Exception e) {
                return e.getMessage();
            }
        }

        protected void onProgressUpdate(Void... nothing) {

        }

        @Override
        protected void onPostExecute(String msg) {
            if (msg.isEmpty()) {
                llIP.setVisibility(View.GONE);
                llPorta.setVisibility(View.GONE);
                llPixels.setVisibility(View.VISIBLE);
                llDelay.setVisibility(View.VISIBLE);
                clBotoes.setVisibility(View.VISIBLE);
                btConectar.setText("DESCONECTAR");
                tvConectado.setText("Conectado: " + ip + ":" + porta);
            } else {
                rm = null;
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                tvConectado.setText("Desconectado");
            }
            etIP.setEnabled(true);
            etPorta.setEnabled(true);
            block = false;
        }
    }

    private void disconnect() {
        if (rm != null && !rm.isClosed()) {
            rm.close();
        }
        rm = null;
        llIP.setVisibility(View.VISIBLE);
        llPorta.setVisibility(View.VISIBLE);
        llPixels.setVisibility(View.GONE);
        llDelay.setVisibility(View.GONE);
        clBotoes.setVisibility(View.GONE);
        btConectar.setText("CONECTAR");
        tvConectado.setText("Desconectado");
    }

    private InputFilter[] IPFilter() {
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (end > start) {
                    String destTxt = dest.toString();
                    String resultingTxt = destTxt.substring(0, dstart) +
                            source.subSequence(start, end) +
                            destTxt.substring(dend);
                    if (!resultingTxt.matches("^\\d{1,3}(\\.(\\d{1,3}(\\.(\\d{1,3}(\\.(\\d{1,3})?)?)?)?)?)?")) {
                        return "";
                    } else {
                        String[] splits = resultingTxt.split("\\.");
                        for (int i = 0; i < splits.length; i++) {
                            if (Integer.valueOf(splits[i]) > 255) {
                                return "";
                            }
                        }
                    }
                }
                return null;
            }
        };
        return filters;
    }

    private void mouseClick(String button) {
        if (!rm.isClosed()) {
            Thread threadMouseClick = new Thread(
                    new ThreadMouseClick(rm, button));
            threadMouseClick.start();
        } else {
            Toast.makeText(MainActivity.this, "Conexão encerrada de maneira inesperada, verifique se o servidor está aberto ou se a rede está ativa.", Toast.LENGTH_LONG).show();
            disconnect();
        }
    }

    private void startMouseCursor(View v, String direction) {
        if (!rm.isClosed()) {
            int pixels = (etPixels.getText().toString().isEmpty() ? MIN_PIXELS-1 : Integer.parseInt(etPixels.getText().toString()));
            int delay = (etDelay.getText().toString().isEmpty() ? MIN_PIXELS-1 : Integer.parseInt(etDelay.getText().toString()));
            if (pixels >= MIN_PIXELS && pixels <= MAX_PIXELS) {
                if (delay >= MIN_DELAY && delay <= MAX_DELAY) {
                    if (threadMouseCursor == null) {
                        v.setBackgroundResource(R.drawable.btacende2);
                        mouseCursor = new ThreadMouseCursor(rm, String.valueOf(pixels), direction, delay);
                        threadMouseCursor = new Thread(mouseCursor);
                        threadMouseCursor.start();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "O valor do delay (em ms) deve ser no mínimo " + MIN_DELAY + " e no máximo " + MAX_DELAY + ".", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "O valor de pixels deve ser no mínimo " + MIN_PIXELS + " e no máximo " + MAX_PIXELS + ".", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "Conexão encerrada de maneira inesperada, verifique se o servidor está aberto ou se a rede está ativa.", Toast.LENGTH_LONG).show();
            disconnect();
        }
    }

    private void stopMouseCursor(View v) {
        if (threadMouseCursor != null) {
            v.setBackgroundResource(R.drawable.btacende1);
            if (threadMouseCursor.isAlive()) {
                if (mouseCursor != null) {
                    mouseCursor.setPressed(false);
                }
                mouseCursor = null;
            }
            threadMouseCursor = null;
        }
    }

    private void startMouseScroll(View v, String direction) {
        if (!rm.isClosed()) {
            if (threadMouseScroll == null) {
                v.setBackgroundResource(R.drawable.btacende2);
                mouseScroll = new ThreadMouseScroll(rm, direction);
                threadMouseScroll = new Thread(mouseScroll);
                threadMouseScroll.start();
            }
        } else {
            Toast.makeText(MainActivity.this, "Conexão encerrada de maneira inesperada,\nverifique se o servidor está aberto ou se a rede está ativa.", Toast.LENGTH_LONG).show();
            disconnect();
        }
    }

    private void stopMouseScroll(View v) {
        if (threadMouseScroll != null) {
            v.setBackgroundResource(R.drawable.btacende1);
            if (threadMouseScroll.isAlive()) {
                if (mouseScroll != null) {
                    mouseScroll.setPressed(false);
                }
                mouseScroll = null;
            }
            threadMouseScroll = null;
        }
    }

    private void savePreferences() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("etIP", etIP.getText().toString());
        editor.putString("etPorta", etPorta.getText().toString());
        editor.putString("etPixels", etPixels.getText().toString());
        editor.putString("etDelay",etDelay.getText().toString());
        editor.apply();
    }

    private void restorePreferences() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        etIP.setText(preferences.getString("etIP", ""));
        etPorta.setText(preferences.getString("etPorta", "1099"));
        etPixels.setText(preferences.getString("etPixels", "5"));
        etDelay.setText(preferences.getString("etDelay", "10"));
    }
}
