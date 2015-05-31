package jp.ergo.nexttrainrunning.infrastructure.connector;

import com.google.android.gms.wearable.MessageEvent;

/**
 * Created by wataru_ohkawara on 15/05/27.
 */
public class ReceivedMessageConverter {

    public static ReceivedMessage convertReceivedMessage(MessageEvent messageEvent) {
        return new ReceivedMessage(messageEvent.getRequestId(), messageEvent.getSourceNodeId(), messageEvent.getPath(), new String(messageEvent.getData()));
    }

}
