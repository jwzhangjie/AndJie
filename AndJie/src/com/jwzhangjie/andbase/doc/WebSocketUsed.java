package com.jwzhangjie.andbase.doc;

import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.drafts.Draft_17;

import com.jwzhangjie.andbase.net.JieWebSocket;

/**
 * title: WebSocketUsed.java
 * @author jwzhangjie
 * Date: 2014-12-6 下午3:37:12
 * version 1.0
 * {@link http://blog.csdn.net/jwzhangjie}
 * Description: JieWebSocket的简单使用，主要还是在JieWebSocket内容处理相关内容
 */
public class WebSocketUsed {

	private JieWebSocket mJieWebSocket;

	public WebSocketUsed() {
		try {
			mJieWebSocket = new JieWebSocket(new URI("url"), new Draft_17());
			mJieWebSocket.connect();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

}
