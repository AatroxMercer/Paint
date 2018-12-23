package gq.aatrox.paint.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import gq.aatrox.paint.R;

public class HelpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        TextView textView = findViewById(R.id.help_content);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        textView.setText(Html.fromHtml(help()));
    }


    private String help() {
        return "<h1 style='text-align:center;'>" + getString(R.string.text_title) + "</h1>" +
                "<p>" + getString(R.string.text_1) + "</p>" +
                "<p>" + getString(R.string.text_2) + "</p>" +
                "<ul>" +
                "<li>" + getString(R.string.text_list_1_1) + "</li>" +
                "<li>" + getString(R.string.text_list_1_2) + "</li>" +
                "<li>" + getString(R.string.text_list_1_3) + "</li>" +
                "<li>" + getString(R.string.text_list_1_4) + "</li>" +
                "</ul>" +
                "<h2>" + getString(R.string.text_title_1) + "</h2>" +
                "<p>" + getString(R.string.text_3) + "</p>" +
                "<h2>" + getString(R.string.text_title_2) + "</h2>" +
                "<p>" + getString(R.string.text_4) + "</p>" +
                "<h3>" + getString(R.string.text_title_2_1) + "</h3>" +
                "<p>" + getString(R.string.text_5) + "</p>" +
                "<h3>" + getString(R.string.text_title_2_2) + "</h3>" +
                "<p>" + getString(R.string.text_6) +  "</p>" +
                "<h3>" + getString(R.string.text_title_2_3) + "</h3>" +
                "<p>" + getString(R.string.text_7) + "</p>" +
                "<p>" + getString(R.string.text_8) + "</p>" +
                "<ul>" +
                "<li>" + getString(R.string.text_list_2_1) + "</li>" +
                "<li>" + getString(R.string.text_list_2_2) + "</li>" +
                "<li>" + getString(R.string.text_list_2_3) + "</li>" +
                "<li>" + getString(R.string.text_list_2_4) + "</li>" +
                "</ul>" +
                "<h3>" + getString(R.string.text_title_2_4) + "</h3>" +
                "<p>" + getString(R.string.text_9) + "</p>" +
                "<h3>" + getString(R.string.text_title_2_5) + "</h3>" +
                "<p>" + getString(R.string.text_10) + "</p>" +
                "<h3>" + getString(R.string.text_title_2_6) + "</h3>" +
                "<p>" + getString(R.string.text_11) + "</p>" +
                "<p>" + getString(R.string.text_12) + "</p>" +
                "<p>" + getString(R.string.text_13) + "</p>" +
                "<h2>" + getString(R.string.text_title_3) + "</h2>" +
                "<p>" + getString(R.string.text_14) + "</p>" +
                "<h2>" + getString(R.string.text_title_4) + "</h2>" +
                "<p>" + getString(R.string.text_15) + "</p>";
    }

    public void start(View view) {
        startActivity(new Intent(this, PaintActivity.class));
        finish();
    }

    public void menu(View view) {
        startActivity(new Intent(this, MenuActivity.class));
        finish();
    }

    public void exit(View view) {
        finish();
    }
}