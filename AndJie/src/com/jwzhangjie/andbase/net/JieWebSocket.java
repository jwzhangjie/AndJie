package com.jwzhangjie.andbase.net;

import java.net.URI;
import java.util.Map;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

/**
 * title: JieWebSocket.java
 * @author jwzhangjie
 * Date: 2014-12-6 下午3:34:22
 * version 1.0
 * {@link http://blog.csdn.net/jwzhangjie}
 * Description:与后台通过WebSocket通信的封装
 */
public class JieWebSocket extends WebSocketClient {

	public JieWebSocket(URI serverUri, Draft draft) {
		super(serverUri, draft);
	}

	public JieWebSocket(URI serverUri, Draft protocolDraft,
			Map<String, String> httpHeaders, int connectTimeout) {
		super(serverUri, protocolDraft, httpHeaders, connectTimeout);
	}

	public JieWebSocket(URI serverURI) {
		super(serverURI);
	}

	@Override
	public void onClose(int arg0, String arg1, boolean o) {

	}

	@Override
	public void onError(Exception exception) {
		exception.printStackTrace();
	}

	/**
	 * 接收后台发送的信息，格式自己定义
	 */
	@Override
	public void onMessage(String msg) {
		
	}

	@Override
	public void onOpen(ServerHandshake server) {

	}

}
