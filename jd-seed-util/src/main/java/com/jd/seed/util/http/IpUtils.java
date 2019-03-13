package com.jd.seed.util.http;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * IP工具类
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月12日 上午11:28:56
 */
public class IpUtils {
	private static Logger IP_UTILS_LOGGER = LoggerFactory.getLogger(IpUtils.class);

	/**
	 * <pre>
	 * 获取请服务客户端IP
	 * 
	 * </pre>
	 * 
	 * @param request
	 *            HttpServletRequest request
	 * @return String
	 */
	public static String getClientIp(HttpServletRequest request) {
		// XFF
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多层反向代理，取第一个
			int idx = ip.indexOf(",");
			if (-1 != idx) {
				return ip.substring(0, idx);
			} else {
				return ip;
			}
		}
		// 用apache http做代理加Proxy-Client-IP请求头
		// weblogic插件加上的WL-Proxy-Client-IP请求头
		// HTTP_CLIENT_IP
		// HTTP_X_FORWARDED_FOR
		// XRI
		ip = request.getHeader("X-Real-IP");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}

	public static String getLocalIp() {
		StringBuilder ip = new StringBuilder();
		try {
			// 获取所有本地网络接口
			Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
			while (nis.hasMoreElements()) {
				NetworkInterface ni = nis.nextElement();
				if (ni.isLoopback() || !ni.isUp() || ni.isVirtual()) {
					continue;
				}
				Enumeration<InetAddress> ias = ni.getInetAddresses();
				while (ias.hasMoreElements()) {
					InetAddress ia = ias.nextElement();
					if (!ia.isLoopbackAddress() && !ia.isLinkLocalAddress() && ia instanceof Inet4Address
							&& ia.isSiteLocalAddress()) {
						ip.append(ia.getHostAddress());
						return ip.toString();
					}
				}
			}
		} catch (SocketException ex) {
			IP_UTILS_LOGGER.error("getLocalIp exception:", ex);
		}
		return ip.toString();
	}
}
