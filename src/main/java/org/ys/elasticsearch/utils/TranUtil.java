package org.ys.elasticsearch.utils;

import java.net.InetAddress;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class TranUtil {
	
	public static TransportClient getCilet() throws Exception{
		TransportClient cilent = new PreBuiltTransportClient(Settings.EMPTY);
		cilent.addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
		return cilent;
	}
}
