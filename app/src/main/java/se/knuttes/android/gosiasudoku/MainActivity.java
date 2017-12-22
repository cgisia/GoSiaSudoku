package se.knuttes.android.gosiasudoku;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    private WsCaller wsCaller;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wsCaller = new WsCaller(this);
        textView = (TextView) findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod());
    }

    public void listSudokus(View view) {
        System.out.println("Knappen klickad!\n");
        wsCaller.getList(this);
    }

    public void addText(String text) {
        textView.append(text);
    }

    public TextView getTextView() {
        return textView;
    }
}
