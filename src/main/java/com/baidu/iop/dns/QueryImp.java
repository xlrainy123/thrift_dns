package com.baidu.iop.dns;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.thrift.TException;

public class QueryImp implements TestQuery.Iface{

	public QueryResult query(String hostName) throws TException {
		QueryResult queryResult = new QueryResult();
		InetAddress[] addresses = null;
		try {
			addresses = InetAddress.getAllByName(hostName);
		} catch (UnknownHostException e) {
			addresses = new InetAddress[0];
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		queryResult.queryTime = format.format(new Date()); 
		for (InetAddress address : addresses) {
			queryResult.results.add(address.getHostAddress());
		}
		return queryResult;
	}
	
//	public static void main(String[] args) throws TException {
//		QueryImp imp = new QueryImp();
//		QueryResult result = imp.query("www.baidu.com");
//		System.out.println(result.queryTime);
//	}
	
}
