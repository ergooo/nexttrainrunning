package jp.ergo.nexttrainrunning.infrastructure.connector;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

import java.util.List;

/**
 * Created by wataru_ohkawara on 15/05/26.
 */
public class WearConnector {

    public interface ConnectionStatusChangedListener {

        void onConnected();

        void onConnectionSuspended();

        void onConnectionFailed();

    }

    public interface MessageReceivedListener {

        void onReceived(ReceivedMessage receivedMessage);

    }

    private static final String TAG = WearConnector.class.getSimpleName();

    private GoogleApiClient client;

    private Context context;

    private ConnectionStatusChangedListener connectionStatusChangedListener;

    private MessageReceivedListener messageReceivedListener;

    private MessageApi.MessageListener messageListener = new MessageApi.MessageListener() {

        @Override
        public void onMessageReceived(final MessageEvent messageEvent) {
            handler.post(new Runnable() {

                @Override
                public void run() {
                    if (messageReceivedListener != null) {
                        messageReceivedListener.onReceived(ReceivedMessageConverter.convertReceivedMessage(messageEvent));
                    }
                }

            });
        }

    };

    private Handler handler;

    public WearConnector(Context context) {
        this.context = context;
    }

    /**
     * ActivityあるいはFragmentのライフサイクル"onCreate"の際に呼び出す
     */
    public void onCreate() {
        this.client = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(Bundle bundle) {
                        Log.d(TAG, "onConnected");
                        if (connectionStatusChangedListener != null) {
                            connectionStatusChangedListener.onConnected();
                        }
                    }

                    @Override
                    public void onConnectionSuspended(int i) {
                        Log.d(TAG, "onConnectionSuspended");
                        if (connectionStatusChangedListener != null) {
                            connectionStatusChangedListener.onConnectionSuspended();
                        }
                    }
                })
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult connectionResult) {

                        Log.d(TAG, "onConnectionFailed");
                        Log.d(TAG, "" + connectionResult.getErrorCode());
                        Log.d(TAG, "" + connectionResult.getResolution());
                        Log.d(TAG, "" + connectionResult);

                        if (connectionStatusChangedListener != null) {
                            connectionStatusChangedListener.onConnectionFailed();
                        }

                    }
                })
                .addApi(Wearable.API)
                .build();
        handler = new Handler();
    }

    /**
     * ActivityあるいはFragmentのライフサイクル"onResume"の際に呼び出す
     */
    public void onResume() {
        this.client.connect();
        Wearable.MessageApi.addListener(client, messageListener);
    }

    /**
     * ActivityあるいはFragmentのライフサイクル"onPause"の際に呼び出す
     */
    public void onPause() {
        Wearable.MessageApi.removeListener(client, messageListener);
        if (client != null) {
            client.disconnect();
        }
    }

    /**
     * 接続状態の変化を検知するリスナーをセットする
     *
     * @param listener 接続状態の変化を検知するリスナー
     */
    public void setConnectionStatusChangedListener(ConnectionStatusChangedListener listener) {
        connectionStatusChangedListener = listener;
    }

    /**
     * メッセージの受信を検知するリスナーをセットする
     *
     * @param listener メッセージの受信を検知するリスナー
     */
    public void setMessageReceivedListener(MessageReceivedListener listener) {
        messageReceivedListener = listener;
    }

    // 接続中の全てのノードにメッセージを送信する。非ブロッキング
    public void beginBroadcastMessage(final String path, final String message) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NodeApi.GetConnectedNodesResult nodes = Wearable.NodeApi.getConnectedNodes(client).await();
                broadcastMessage(nodes.getNodes(), path, message);
            }
        }).start();
    }

    // 接続中の全てのノードにメッセージを送信する。ブロッキング
    private void broadcastMessage(List<Node> nodes, String path, String message) {
        for (Node node : nodes) {
            MessageApi.SendMessageResult result = Wearable.MessageApi.sendMessage(
                    client,
                    node.getId(),
                    path,
                    message.getBytes()).await();
            Log.d(TAG, "isSuccess: " + result.getStatus().isSuccess());
        }
    }

}
