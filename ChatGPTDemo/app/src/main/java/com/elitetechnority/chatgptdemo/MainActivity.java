package com.elitetechnority.chatgptdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OpenAiService service = new OpenAiService("Your Token");
        TextView tv_bot=(TextView)findViewById(R.id.tv_bot);
        EditText et_bot=(EditText)findViewById(R.id.et_bot);
        et_bot.setText("Tell about ChatGPT");
        Button btn_bot=(Button)findViewById(R.id.btn_bot);
        btn_bot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CompletionRequest completionRequest = CompletionRequest.builder()
                        .prompt(et_bot.getText().toString())
                        .model("text-davinci-003")
                        .topP(1.0)
                        .temperature(0.3)
                        .maxTokens(150)
                        .frequencyPenalty(0.0)
                        .presencePenalty(0.0)
                        .build();
                CompletionResult completionResult=service.createCompletion(completionRequest);
                System.out.println(completionResult);
                tv_bot.setText(completionResult.getChoices().get(0).getText());
                String toSpeak = tv_bot.getText().toString();
                textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

         textToSpeech=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });
    }

    @Override
    protected void onPause() {
        if(textToSpeech !=null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if(textToSpeech !=null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}