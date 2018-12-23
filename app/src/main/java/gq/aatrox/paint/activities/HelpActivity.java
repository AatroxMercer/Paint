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

    private String tagParagraph(String context) {
        return "<p>&emsp;&emsp;" + context + "</p>";
    }

    private String tagHeading(int level, String context) {
        return "<h" + level + (level == 1 ? " style='text-align:center;'" : "") + ">" + context + "</h" + level + ">";
    }

    private String tagListItem(String context) {
        return "<li>" + context + "</li>";
    }

    private String tagUnorderedList(String[] listItems) {
        String ans = "<ul>";
        for (String listItem : listItems) {
            ans += tagListItem(listItem);
        }
        ans += "</ul>";
        return ans;
    }

    private String help() {
        return tagHeading(1, getString(R.string.text_title)) +
                tagParagraph(getString(R.string.text_1)) +
                tagParagraph(getString(R.string.text_2)) +
                tagUnorderedList(
                        new String[]{
                                getString(R.string.text_list_1_1),
                                getString(R.string.text_list_1_2),
                                getString(R.string.text_list_1_3),
                                getString(R.string.text_list_1_4)
                        }
                ) +
                tagHeading(2, getString(R.string.text_title_1)) +
                tagParagraph(getString(R.string.text_3)) +
                tagHeading(2, getString(R.string.text_title_2)) +
                tagParagraph(getString(R.string.text_4)) +
                tagHeading(3, getString(R.string.text_title_2_1)) +
                tagParagraph(getString(R.string.text_5)) +
                tagHeading(3, getString(R.string.text_title_2_2)) +
                tagParagraph(getString(R.string.text_6)) +
                tagHeading(3, getString(R.string.text_title_2_3)) +
                tagParagraph(getString(R.string.text_7)) +
                tagParagraph(getString(R.string.text_8)) +
                tagUnorderedList(
                        new String[]{
                                getString(R.string.text_list_2_1),
                                getString(R.string.text_list_2_2),
                                getString(R.string.text_list_2_3),
                                getString(R.string.text_list_2_4)
                        }
                ) +
                tagHeading(3, getString(R.string.text_title_2_4)) +
                tagParagraph(getString(R.string.text_9)) +
                tagHeading(3, getString(R.string.text_title_2_5)) +
                tagParagraph(getString(R.string.text_10)) +
                tagHeading(3, getString(R.string.text_title_2_6)) +
                tagParagraph(getString(R.string.text_11)) +
                tagParagraph(getString(R.string.text_12)) +
                tagParagraph(getString(R.string.text_13)) +
                tagHeading(2, getString(R.string.text_title_3)) +
                tagParagraph(getString(R.string.text_14)) +
                tagHeading(2, getString(R.string.text_title_4)) +
                tagParagraph(getString(R.string.text_15));
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