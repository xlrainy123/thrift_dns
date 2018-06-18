package com.baidu.iop.dns.server;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

import com.baidu.iop.dns.QueryImp;
import com.baidu.iop.dns.TestQuery;

public class DNSQueryServer {
	private static final int port = 9001;
	private static TServer SERVER = null;
	public static void main(String[] args) throws TTransportException {
		TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(port);
		TestQuery.Processor<QueryImp> processor = new TestQuery.Processor<QueryImp>(new QueryImp());
		
		TNonblockingServer.Args params = new TNonblockingServer.Args(serverSocket);
		params.protocolFactory(new TBinaryProtocol.Factory());
		params.transportFactory(new TFramedTransport.Factory());
		params.processorFactory(new TProcessorFactory(processor));
		
		SERVER = new TNonblockingServer(params);
		SERVER.serve();
	}

}
