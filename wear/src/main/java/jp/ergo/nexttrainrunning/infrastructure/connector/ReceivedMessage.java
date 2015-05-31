package jp.ergo.nexttrainrunning.infrastructure.connector;

/**
 * Created by wataru_ohkawara on 15/05/27.
 */
public class ReceivedMessage {

    private final String messageBody;

    private final String path;

    private final int requestId;

    private final String senderId;

    public ReceivedMessage(int requestId, String senderId, String path, String messageBody) {
        this.requestId = requestId;
        this.senderId = senderId;
        this.path = path;
        this.messageBody = messageBody;
    }


    public String getMessageBody() {
        return messageBody;
    }

    public String getPath() {
        return path;
    }

    public int getRequestId() {
        return requestId;
    }

    public String getSenderId() {
        return senderId;
    }

}
