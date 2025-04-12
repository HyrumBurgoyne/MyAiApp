package com.example.automationmvp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class MainActivity extends ComponentActivity {
    private static final long MAX_LISTEN_MS = 15_0000;

    private Button btnRecord;
    private TextView tvResult;

    private Button deployButton;

    private Button clearButton;
    private SpeechRecognizer speechRecognizer;
    private Intent recognizerIntent;

    private boolean isListening = false;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable stopRunnable = this::stopListening;

    private ActivityResultLauncher<String> permissionLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRecord = findViewById(R.id.voiceButton);
        tvResult = findViewById(R.id.resultTextView);
        deployButton = findViewById(R.id.DeployButton);
        clearButton = findViewById(R.id.ClearButton);

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

        // Set up the RecognitionListener
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {}

            @Override
            public void onBeginningOfSpeech() {}

            @Override
            public void onRmsChanged(float rmsdB) {}

            @Override
            public void onBufferReceived(byte[] buffer) {}

            @Override
            public void onEndOfSpeech() {}

            @Override
            public void onError(int error) {
                Log.e("SpeechRecognizer", "Error code: " + error);
            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                btnRecord.setText("Start Listening");
                if (matches != null && !matches.isEmpty()) {
                    String result = matches.get(0);
                    tvResult.setText(result);
                }
            }

            @Override
            public void onPartialResults(Bundle partialResults)
            {
                ArrayList<String> matches = partialResults.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null && !matches.isEmpty()) {
                    String result = matches.get(0);
                    tvResult.setText(result);
                }
            }

            @Override
            public void onEvent(int eventType, Bundle params) {}
        });

        // Create and initialize the recognizer intent BEFORE checking permissions
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, 5000L);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS, 3000L);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, 1000L);

        // Register the launcher for the permission request
        permissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted) {
                        startListening();
                    } else {
                        tvResult.setText("Permission denied");
                    }
                }
        );

        // Check for permissions and start listening if granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            permissionLauncher.launch(Manifest.permission.RECORD_AUDIO);
        }

        // Button click to toggle listening
        btnRecord.setOnClickListener(v -> {
            if (!isListening) {
                startListening();
            } else {
                stopListening();
            }
        });

        clearButton.setOnClickListener(v -> {
            tvResult.setText(R.string.ResultWait);
        });
    }

    private void startListening(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            // This branch should now rarely be reached.
            permissionLauncher.launch(Manifest.permission.RECORD_AUDIO);
            return;
        }
        tvResult.setText(R.string.ResultWait);
        isListening = true;
        btnRecord.setText("Stop Listening");
        speechRecognizer.startListening(recognizerIntent);
        handler.postDelayed(stopRunnable, MAX_LISTEN_MS);
    }

    private void stopListening(){
        if (!isListening) return;
        isListening = false;
        btnRecord.setText("Start Listening");
        handler.removeCallbacks(stopRunnable);
        speechRecognizer.stopListening();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (speechRecognizer != null) {
            speechRecognizer.destroy();
        }
        handler.removeCallbacks(stopRunnable);
    }
}
