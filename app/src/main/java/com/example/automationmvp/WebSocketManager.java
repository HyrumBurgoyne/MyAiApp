package com.example.automationmvp;

import android.os.Handler;
import android.os.Looper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class WebSocketManager {
    private WebSocket webSocket;
    private OkHttpClient client;
    private WSListener listener;

    // Define a callback interface for WebSocket events.
    public interface WSListener {
        void onAck();
        void onCompleted();
        void onError(String error);
    }

    public WebSocketManager(WSListener listener) {
        this.listener = listener;
        client = new OkHttpClient();
    }

    // Connect to the provided WebSocket URL.
    public void connect(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        webSocket = client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                // Optionally send an initial message after connection, if needed.
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                // Switch to the main thread for UI updates.
                new Handler(Looper.getMainLooper()).post(() -> {
                    if ("ack".equalsIgnoreCase(text)) {
                        listener.onAck();
                    } else if ("completed".equalsIgnoreCase(text)) {
                        listener.onCompleted();
                    }
                });
            }

            @Override
            public void onMessage(WebSocket webSocket, ByteString bytes) {
                // Not used in this example.
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                new Handler(Looper.getMainLooper()).post(() -> {
                    listener.onError(t.getMessage());
                });
            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                webSocket.close(1000, null);
            }
        });
    }

    // Send a message through the WebSocket.
    public void sendMessage(String message) {
        if (webSocket != null) {
            webSocket.send(message);
        }
    }

    // Close the WebSocket connection.
    public void close() {
        if (webSocket != null) {
            webSocket.close(1000, "Closing");
        }
    }
}
