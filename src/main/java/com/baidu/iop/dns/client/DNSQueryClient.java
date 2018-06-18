package com.baidu.iop.dns.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.baidu.iop.dns.QueryResult;
import com.baidu.iop.dns.TestQuery;

public class DNSQueryClient {
	private static String hostName = "www.baidu.com";
	
	public static void main(String[] args) {
		if (args.length >= 1) {
			hostName = args[0];
		}
		TTransport transport = getTransport();
		TProtocol protocol = new TBinaryProtocol(transport);
		TestQuery.Client client = new TestQuery.Client(protocol);
		
		QueryResult result = new QueryResult();
		try {
			result = client.query(hostName);
		} catch (TException e) {
			//不做处理
		}
		System.out.println(result.queryTime+","+ result.results);
	}

	private static TTransport getTransport() {
		TTransport transport = getTransport("127.0.0.1", 9001, 5000);
		try {
			if (! transport.isOpen()) {
				transport.open();
			}
		}catch (TTransportException exception) {
			System.exit(1);
		}
		return transport;
	} 
	
	private static TTransport getTransport(String hostName, int port, int timeout) {
		final TSocket socket = new TSocket(hostName, port, timeout);
		TTransport transport = new TFramedTransport(socket);
		return transport;
	}
}
