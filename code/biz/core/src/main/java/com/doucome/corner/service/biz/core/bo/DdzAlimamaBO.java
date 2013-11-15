package com.doucome.corner.service.biz.core.bo;

import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.DisposableBean;

import com.doucome.corner.service.biz.core.constant.DecimalConstant;
import com.doucome.corner.service.biz.core.enums.DdzTbItemSourceEnum;
import com.doucome.corner.service.biz.core.model.AlimamaHttpClient;
import com.doucome.corner.service.biz.core.model.DdzTbItemModel;
import com.doucome.corner.service.biz.core.utils.DecimalUtils;


/**
 * 
 * @author ze2200
 *
 */
public class DdzAlimamaBO implements DisposableBean {
	
	private static final Log alimamaLogger = LogFactory.getLog("alimama-log");
	
	private String REFRESH_URL = "http://u.alimama.com/union/myunion/myOverview.htm";
	
	private static final LinkedBlockingQueue<AlimamaHttpClient> alimamaClients;
	
	private final static ThreadSafeClientConnManager connManager;
	
	private final static String[][] loginInfos = new String[][]{{"ze2200", "Ddch198528", "zh-CN", "1440*900", "macos|10.9", "firefox|24", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:25.0) Gecko/20100101 Firefox/25.0"},
										{"dashabengta", "1qaz2wsx", "zh-CN", "1440*900", "windows|6.1", "chrome|30.0159969", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36"},
										{"weiboyn", "1qaz2wsx", "zh-CN", "1440*900", "windows|6.1", "chrome|30.0159969", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36"},
										{"changshifenbie", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "firefox|24", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:25.0) Gecko/20100101 Firefox/25.0"},
										{"dashabengta", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "firefox|24", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:25.0) Gecko/20100101 Firefox/25.0"},
										{"changshifenbie", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "chrome|30.0159969", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36"},
										{"weiboyn", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "chrome|30.0159969", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36"},
										{"dashabengta", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "chrome|30.0159969", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36"},
										{"weiboyn", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "firefox|24", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:25.0) Gecko/20100101 Firefox/25.0"},
										{"dashabengta", "1qaz2wsx", "zh-CN", "1440*900", "windows|6.1", "chrome|30.0159969", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36"},
										{"weiboyn", "1qaz2wsx", "zh-CN", "1440*900", "windows|6.1", "chrome|30.0159969", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36"},
										{"dashabengta", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "firefox|24", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:25.0) Gecko/20100101 Firefox/25.0"},
										{"changshifenbie", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "chrome|30.0159969", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36"},
										{"weiboyn", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "chrome|30.0159969", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36"},
										{"dashabengta", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "chrome|30.0159969", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36"},
										{"changshifenbie", "1qaz2wsx", "zh-CN", "1440*900", "windows|6.1", "chrome|30.0159969", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36"},//
										{"weiboyn", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "chrome|30.0159969", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36"},
										{"dashabengta", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "chrome|30.0159969", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36"},
										{"weiboyn", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "firefox|24", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:25.0) Gecko/20100101 Firefox/25.0"},
										{"dashabengta", "1qaz2wsx", "zh-CN", "1440*900", "windows|6.1", "chrome|30.0159969", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36"},
										{"weiboyn", "1qaz2wsx", "zh-CN", "1440*900", "windows|6.1", "chrome|30.0159969", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36"},
										{"dashabengta", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "firefox|24", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:25.0) Gecko/20100101 Firefox/25.0"},
										{"changshifenbie", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "chrome|30.0159969", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36"},
										{"weiboyn", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "chrome|30.0159969", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36"},
										{"dashabengta", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "chrome|30.0159969", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36"}};
									
	static {
		try {
			alimamaClients = new LinkedBlockingQueue<AlimamaHttpClient>(100);
			SSLContext ctx = SSLContext.getInstance("SSL");
			X509TrustManager sslManager = new X509TrustManager() {
				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return new X509Certificate[]{};
				}
				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType)
						throws CertificateException {
				}
				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType)
						throws CertificateException {
				}
			};
			ctx.init(null, new TrustManager[] {sslManager}, null);
			SSLSocketFactory sckFactory = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("https", 443, sckFactory));
			registry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
			connManager = new ThreadSafeClientConnManager(registry);
			connManager.setDefaultMaxPerRoute(60);
			connManager.setMaxTotal(100);
			
			for (int i = 0; i < loginInfos.length; i++) {
				AlimamaHttpClient loginClient = loginAlimama(connManager, i);
				if (loginClient == null) {
					loginClient = loginAlimama(connManager, i);
				}
				if (loginClient == null) {
					throw new Exception("---commissionRate can't login alimama");
				}
				alimamaClients.put(loginClient);
				alimamaLogger.error("login succ " + i);
			}
		} catch (Exception e) {
			alimamaLogger.error("---init alimama login fail", e);
			throw new  RuntimeException(e);
		}
	}
	
	/**
	 * 
	 * @param httpClient
	 * @param numIid
	 * @param title
	 * @return
	 */
	public BigDecimal getItemCommissionRate(String numIid, String title) {
		alimamaLogger.error("---" + title + "/" + numIid);
		if (StringUtils.isEmpty(title)  || !StringUtils.isNumeric(numIid)) {
			return null;
		}
		long time0 = System.currentTimeMillis();
		AlimamaHttpClient loginInstance = pollQueue(4000);
		if (loginInstance == null) {
			alimamaLogger.error("---fail, can't get httpClient from blocking queue: " + numIid);
			return null;
		}
		long time1 = System.currentTimeMillis();
		try {
			String html = requestCommForm(loginInstance, title);
			if (StringUtils.isEmpty(html)) {
				//异步刷新链接.
				new Thread() {
					@Override
					public void run() {
						refreshLoginStatus();
					}
				}.start();
				//尝试重新登录后获取数据.
				int idx = new Random(System.currentTimeMillis()).nextInt(loginInfos.length);
				loginInstance = loginAlimama(connManager, idx);
				if (loginInstance == null) {
					alimamaLogger.error("fail, re request comm form when instance new login fail");
					return null;
				}
				html = requestCommForm(loginInstance, title);
			}
			if (StringUtils.isEmpty(html)) {
				alimamaLogger.error("---fail, can't read html from commission form response");
				return null;
			}
			alimamaLogger.error("---request commission form total time: " + (System.currentTimeMillis() - time1));
			time1 = System.currentTimeMillis();
			BigDecimal commRate = parseCommissionRateFromTable(numIid, html);
			alimamaLogger.error("---commissionRate total time: " + (System.currentTimeMillis() - time0));
			return commRate;
		} catch (Exception e) {
			alimamaLogger.error("---get commission rate from alimama exception", e);
		} finally {
			if (loginInstance != null) {
				putQueue(loginInstance);
			}
		}
		return null;
	}
	
	/**
	 * 
	 *
	 */
	public DdzTbItemModel getTbItemInfo(String numIid, DdzTbItemSourceEnum sourceEnum) {
		if (!StringUtils.isNumeric(numIid)) {
			return null;
		}
		long time1 = System.currentTimeMillis();
		String itemUrl = sourceEnum.getItemUrl(numIid);
		String html = getResponseHtml(itemUrl, sourceEnum.getPageCharset());
		if (StringUtils.isEmpty(html)) {
			alimamaLogger.error("---getTbItemInfo fail, can't get page html: " + numIid);
			return null;
		}
		DdzTbItemModel itemModel = sourceEnum.parseFromHtml(numIid, html);
		if (itemModel == null) {
			alimamaLogger.error("---getTbItemIfno fail, can't parse from html: " + numIid);
		}
		alimamaLogger.error("---getTbItemInfon: consumeTime: " + (System.currentTimeMillis() - time1));
		return itemModel;
	}
	
	/**
	 * 刷新，保持登录状态.
	 * 
	 * 
	 */
	public void refreshLoginStatus() {
		long time1 = System.currentTimeMillis();
		AlimamaHttpClient alimamaClient = null;
		int size = alimamaClients.size();
		for (int i = 0; i < size; i++) {
			try {
				alimamaClient = pollQueue(2000);
				//获取不到，表明并发较多，新初始化部分client.
				if (alimamaClient == null && alimamaClients.size() < 40) {
					int idx = new Random(System.currentTimeMillis()).nextInt(loginInfos.length);
					alimamaClient = loginAlimama(connManager, idx);
					if (alimamaClient != null) {
						continue;
					}
				}
				if (alimamaClient == null) {
					alimamaLogger.error("---refresh fail, can't get client from queue");
					continue;
				}
//				if (!alimamaClient.needRefresh()) {
//					alimamaLogger.error("---refresh none, client is active");
//					continue;
//				}
				int statusCode = 0;
				HttpResponse response = null;
				try {
					HttpGet getReq = new HttpGet(REFRESH_URL);
					getReq.addHeader("Accept-Encoding", "gzip,deflate,sdch");
					getReq.addHeader("Referer", "http://u.alimama.com/union/spread/common/guide.htm");
					getReq.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36");
					response = alimamaClient.getHttpClient().execute(getReq);
					statusCode = response.getStatusLine().getStatusCode();
				} catch (Exception e) {
					alimamaLogger.error("---refresh exception, request url fail", e);
				} finally {
					consumeResponse(response);
				}
				if (statusCode != 200) {
					alimamaClient = null;
					int idx = new Random(System.currentTimeMillis()).nextInt(loginInfos.length);
					alimamaClient = loginAlimama(connManager, idx);
					if (alimamaClient == null) {
						alimamaLogger.error("---refresh fail, inactive httpclient relogin fail");	
					} else {
						alimamaLogger.error("---refresh succ with relogin");	
					}
				} else {
					alimamaLogger.error("---refresh succ");	
				}
			} finally {
				putQueue(alimamaClient);
			}
		}
		alimamaLogger.error("---refresh consume: " + (System.currentTimeMillis() - time1));
	}
	
	/**
	 * 
	 * 
	 */
	private String requestCommForm(AlimamaHttpClient alimamaClient, String title) {
		HttpResponse response = null;
		try {
			int loginIdx = alimamaClient.getLoginInfoIdx();
			String userAgent = loginInfos[loginIdx][6];
			
			HttpClient httpClient = alimamaClient.getHttpClient();
			HttpGet getReq = new HttpGet(getCommissionReqUrl(title));
			getReq.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			getReq.addHeader("Accept-Encoding", "gzip,deflate");
			getReq.addHeader("Connection", "keep-alive");
			getReq.addHeader("Referer", "http://u.alimama.com/union/spread/selfservice/taokeSearch.htm");
			getReq.addHeader("User-Agent", userAgent);
			response = httpClient.execute(getReq);
			int statusCode= response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				alimamaLogger.error("---fail, request commission form response status: " + statusCode);
				return null;
			}
			String contentEncode = response.getFirstHeader("Content-Encoding").getValue();
			if (contentEncode.toLowerCase().indexOf("gzip") != -1) {
				return getHtmlFromGzip(response.getEntity(), "gbk");
			} else {
				return EntityUtils.toString(response.getEntity(), "GBK");
			}
		} catch (Exception e) {
			alimamaLogger.error(e);
		} finally {
			consumeResponse(response);
		}
		return null;
	}
	
	private AlimamaHttpClient pollQueue(long millTime) {
		try {
			return alimamaClients.poll(millTime, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			alimamaLogger.error("---refresh fail, interupt when poll", e);
		}
		return null;
	}
	
	private boolean putQueue(AlimamaHttpClient alimamaClient) {
		if (alimamaClient != null) {
			try {
				alimamaClient.refreshTimestamp();
				alimamaClients.put(alimamaClient);
				return true;
			} catch (InterruptedException e) {
				alimamaLogger.error("---refresh fail, can't put login alimama to queue");
			}
		} else {
			alimamaLogger.error("---refresh fail, can't login alimama. alimama login instance dec 1.");
		}
		return false;
	}
	
	private String getCommissionReqUrl(String title) {
		try {
			title = URLEncoder.encode(title, "gbk");
			return "http://u.alimama.com/union/spread/selfservice/merchandisePromotion.htm?cat=&discountId=&pidvid=&_fmu.a._0.t=&_fmu.a._0.pe=&_fmu.a._0.l=&_fmu.a._0.so=&c=&rewrite=&cat=&mid=&searchType=&_fmu.a._0.u=&_fmu.a._0.s=&_fmu.a._0.sta=&_fmu.a._0.end=&_fmu.a._0.st=&_fmu.a._0.en=&_fmu.a._0.star=&loc=&q=" + title;
		} catch (Exception e) {
			return null;
		}
	}
	
	private String getHtmlFromGzip(HttpEntity entity, String charset) {
		Reader reader = null;
		try {
			GZIPInputStream zipInStream = new GZIPInputStream(entity.getContent());
			int conLen = (int)entity.getContentLength();
			conLen = conLen < 0? 4096: conLen;
	        reader = new InputStreamReader(zipInStream, charset);
	        CharArrayBuffer buffer = new CharArrayBuffer(conLen);
	        char[] tmp = new char[1024];
	        int l;
	        while((l = reader.read(tmp)) != -1) {
	            buffer.append(tmp, 0, l);
	        }
	        return buffer.toString();
		} catch (Exception e) {
			alimamaLogger.error("---exception, commssionRate can't read inputstream from gzip", e);
		} finally {
			try {
				reader.close();
			} catch (Exception e1) {
			}
		}
		return null;
	}
	
	private BigDecimal parseCommissionRateFromTable(String numIid, String html) {
		String commTableReg = "<table[\\s\\S]+?id\\s*=\\s*[\"\']J_listMainTable[\"\'][\\s\\S]*?>([\\s\\S]*?)</table>";
		Matcher matcher = Pattern.compile(commTableReg).matcher(html);
		for (int i = 0; i < 3 && matcher.find(); i++) {
			String trsHtml = matcher.group(1);
			String[] trs = trsHtml.split("</tr>");
			for (String trHtml: trs) {
				if (trHtml.indexOf(numIid) != -1) {
					BigDecimal commRate = parseCommissionRateFromTr(trHtml);
					if (commRate != null) {
						return commRate;
					}
					alimamaLogger.error("---fail, can't parse commission rate from dest tr: " + numIid);
					return null;
				}
			}
			alimamaLogger.error("---fail, can't find commission tr: " + numIid);
		}
		alimamaLogger.error("---fail, can't find commission table: " + numIid);
		return null;
	}
	
	private BigDecimal parseCommissionRateFromTr(String trHtml) {
		String commReg = "<td[\\s\\S]*?>[\\s\\S]*?(\\d+(.\\d*)?)\\s*%\\s*?</td>";
		Matcher matcher = Pattern.compile(commReg).matcher(trHtml);
		for (int i = 0; i < 3 && matcher.find(); i++) {
			String rateStr = matcher.group(1);
			try {
				BigDecimal commRate = new BigDecimal(rateStr);
				if (commRate.compareTo(DecimalConstant.ZERO) > 0 && commRate.compareTo(DecimalConstant.SIXTYFIVE) < 0) {
					return DecimalUtils.divide(commRate, DecimalConstant.HUNDRED);
				}
			} catch (Exception e) {
				alimamaLogger.error("---exception, parse commission rate succ, convert to bigdecimal fail: " + rateStr, e);
			}
		}
		return null;
	}
	
	private String getResponseHtml(String getReqUrl, String charset) {
		HttpResponse response = null;
		HttpClient httpClient = newHttpClient(true);
		int statusCode = 0, cnt = 0;
		do {
			try {
				HttpGet getReq = new HttpGet(getReqUrl);
				getReq.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
				getReq.addHeader("Accept-Encoding", "gzip,deflate");
				getReq.addHeader("Connection", "keep-alive");
				getReq.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.69 Safari/537.36");
				getReq.getParams().setParameter("http.protocol.allow-circular-redirects", true);
				response = httpClient.execute(getReq);
				statusCode = response.getStatusLine().getStatusCode();
				if (statusCode == 301 || statusCode == 302) {
					getReqUrl = response.getFirstHeader("Location").getValue();
				} else {
					String contentEncode = response.getFirstHeader("Content-Encoding").getValue();
					if (contentEncode.toLowerCase().indexOf("gzip") != -1) {
						return getHtmlFromGzip(response.getEntity(), charset);
					} else {
						return EntityUtils.toString(response.getEntity(), charset);
					}
				}
			} catch (Exception e) {
				alimamaLogger.error("---request item exception: " + getReqUrl, e);
			} finally {
				consumeResponse(response);
			}
		} while ((statusCode == 301 || statusCode == 302) && cnt < 5);
		alimamaLogger.error("---get item page html fail: " + getReqUrl);
		return null;
	}
	
	/**.
	 * @param connManager
	 * @param userName
	 * @param password
	 * @param oslanguage
	 * @param sr
	 * @param osVer
	 * @param naviVer
	 * @return
	 */
	private static AlimamaHttpClient loginAlimama(ThreadSafeClientConnManager connManager, int idx) {
		HttpClient httpClient = newHttpClient(false);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		String html = null;
		HttpResponse response = null;
		try {
			//alimama.com cookie
			String itemUrl = "https://www.alimama.com/membersvc/index.htm";
			HttpGet getReq = null;
			for (int i = 0; i < 2; i++) {
				getReq = new HttpGet(itemUrl);
				response = httpClient.execute(getReq);
				if (response.getStatusLine().getStatusCode() == 200) {
					consumeResponse(response);
					break;
				} else if (response.getStatusLine().getStatusCode() == 302) {
					itemUrl = response.getFirstHeader("Location").getValue();
				} else {
					alimamaLogger.error("request alimama index failed, status code: " + response.getStatusLine().getStatusCode());
					alimamaLogger.error("response text: " + EntityUtils.toString(response.getEntity(), "GBK"));
					try {
						Thread.currentThread().sleep(500);
					} catch (InterruptedException e) {
					}
				}
				consumeResponse(response);
			}
			
			//taobao login, get the form data
			getReq = new HttpGet("https://login.taobao.com/member/login.jhtml?style=minisimple&from=alimama&redirectURL=http%3A%2F%2Flogin.taobao.com%2Fmember%2Ftaobaoke%2Flogin.htm%3Fis_login%3d1&full_redirect=true&disableQuickLogin=true");
			getReq.addHeader("Referer", "http://www.alimama.com/member/login.htm");
			getReq.addHeader("User-Agent", loginInfos[idx][6]);
			response = httpClient.execute(getReq);
			if (response.getStatusLine().getStatusCode() != 200) {
				alimamaLogger.error("request taobao login index failed, status code: " + response.getStatusLine().getStatusCode());
				alimamaLogger.error("response text: " + EntityUtils.toString(response.getEntity(), "GBK"));
				return null;
			}
			html = EntityUtils.toString(response.getEntity(), "GBK");
			
			getReq = new HttpGet("https://log.mmstat.com/member.2.1.2?ok=1");
			getReq.addHeader("Referer", "https://login.taobao.com/member/login.jhtml?style=minisimple&from=alimama&redirectURL=http%3A%2F%2Flogin.taobao.com%2Fmember%2Ftaobaoke%2Flogin.htm%3Fis_login%3d1&full_redirect=true&disableQuickLogin=true");
			getReq.addHeader("User-Agent", loginInfos[idx][6]);
			response = httpClient.execute(getReq);
			consumeResponse(response);
			getReq = new HttpGet("https://log.mmstat.com/member.2.1.4");
			getReq.addHeader("Referer", "https://login.taobao.com/member/login.jhtml?style=minisimple&from=alimama&redirectURL=http%3A%2F%2Flogin.taobao.com%2Fmember%2Ftaobaoke%2Flogin.htm%3Fis_login%3d1&full_redirect=true&disableQuickLogin=true");
			getReq.addHeader("User-Agent", loginInfos[idx][6]);
			response = httpClient.execute(getReq);
			consumeResponse(response);
			
			//params.add(new BasicNameValuePair("ua", parseFieldValue(html, "ua")));
			params.add(new BasicNameValuePair("umto", parseFieldValue(html, "umto")));
			params.add(new BasicNameValuePair("gvfdcre", parseFieldValue(html, "gvfdcre")));
			params.add(new BasicNameValuePair("TPL_username", loginInfos[idx][0]));
			params.add(new BasicNameValuePair("TPL_password", loginInfos[idx][1]));
			params.add(new BasicNameValuePair("TPL_checkcode", ""));
			params.add(new BasicNameValuePair("need_check_code", ""));
			params.add(new BasicNameValuePair("loginsite", "0"));
			params.add(new BasicNameValuePair("newlogin", "0"));
			params.add(new BasicNameValuePair("TPL_redirect_url", "http://login.taobao.com/member/taobaoke/login.htm?is_login=1"));
			params.add(new BasicNameValuePair("from", "alimama"));
			params.add(new BasicNameValuePair("fc", "default"));
			params.add(new BasicNameValuePair("style", "minisimple"));
			params.add(new BasicNameValuePair("css_style", ""));
			params.add(new BasicNameValuePair("tid", ""));
			params.add(new BasicNameValuePair("support", "000001"));
			params.add(new BasicNameValuePair("CtrlVersion", "1,0,0,7"));
			params.add(new BasicNameValuePair("loginType", "3"));
			params.add(new BasicNameValuePair("minititle", ""));
			params.add(new BasicNameValuePair("minipara", ""));
			params.add(new BasicNameValuePair("pstrong", "2"));
			params.add(new BasicNameValuePair("llnick", ""));
			params.add(new BasicNameValuePair("sign", ""));
			params.add(new BasicNameValuePair("need_sign", ""));
			params.add(new BasicNameValuePair("isIgnore", ""));
			params.add(new BasicNameValuePair("full_redirect", "true"));
			params.add(new BasicNameValuePair("popid", ""));
			params.add(new BasicNameValuePair("callback", ""));
			params.add(new BasicNameValuePair("guf", ""));
			params.add(new BasicNameValuePair("not_duplite_str", ""));
			params.add(new BasicNameValuePair("need_user_id", ""));
			params.add(new BasicNameValuePair("poy", ""));
			params.add(new BasicNameValuePair("gvfdcname", "10"));
			params.add(new BasicNameValuePair("from_encoding", ""));
			params.add(new BasicNameValuePair("sub", ""));
			params.add(new BasicNameValuePair("oslanguage", loginInfos[idx][2]));
			params.add(new BasicNameValuePair("sr", loginInfos[idx][3]));
			params.add(new BasicNameValuePair("osVer", loginInfos[idx][4]));
			params.add(new BasicNameValuePair("naviVer", loginInfos[idx][5]));
			HttpPost postReq = new HttpPost("https://login.taobao.com/member/login.jhtml");
			postReq.addHeader("Origin", "https://login.taobao.com");
			postReq.addHeader("Referer", "https://login.taobao.com/member/login.jhtml?style=minisimple&from=alimama&redirectURL=http%3A%2F%2Flogin.taobao.com%2Fmember%2Ftaobaoke%2Flogin.htm%3Fis_login%3d1&full_redirect=true&disableQuickLogin=true");
			postReq.addHeader("User-Agent", loginInfos[idx][6]);
			postReq.setEntity(new UrlEncodedFormEntity(params));
			response = httpClient.execute(postReq);
			if (response.getStatusLine().getStatusCode() != 200) {
				alimamaLogger.error("request taobao login failed, status code: " + response.getStatusLine().getStatusCode());
				alimamaLogger.error("response text: " + EntityUtils.toString(response.getEntity(), "GBK"));
				return null;
			}
			html = EntityUtils.toString(response.getEntity(), "GBK");
			Pattern pattern = Pattern.compile("var\\s+sThisURL\\s*=\\s[\"\']\\s*(http[\\s\\S]+?)\\s*[\'\"]\\s*;");
			Matcher matcher = pattern.matcher(html);
			String reqUrl = null;
			if (matcher.find()) {
				reqUrl = matcher.group(1);
			} else {
				alimamaLogger.error("parse token error, response text: " + html);
				return null;
			}
			getReq = new HttpGet(reqUrl);
			getReq.addHeader("Referer", "	https://login.taobao.com/member/login.jhtml");
			getReq.addHeader("User-Agent", loginInfos[idx][6]);
			response = httpClient.execute(getReq);
			int statusCode = response.getStatusLine().getStatusCode();
			consumeResponse(response);
			if (statusCode == 302) {
				while(statusCode == 302) {
					reqUrl = response.getFirstHeader("Location").getValue();
					getReq = new HttpGet(reqUrl);
					getReq.addHeader("User-Agent", loginInfos[idx][6]);
					response = httpClient.execute(getReq);
					statusCode = response.getStatusLine().getStatusCode();
					consumeResponse(response);
				}
			} else {
				reqUrl = "http://www.alimama.com/index.htm";
				getReq = new HttpGet(reqUrl);
				getReq.addHeader("User-Agent", loginInfos[idx][6]);
				response = httpClient.execute(getReq);
				consumeResponse(response);
			}
			return new AlimamaHttpClient(httpClient, idx);
		} catch (Exception e) {
			alimamaLogger.error("alimama taobao login exception", e);
		} finally {
			consumeResponse(response);
		}
		return null;
	}
	
	private static HttpClient newHttpClient(boolean autoRedirect) {
		HttpClient httpClient = new DefaultHttpClient(connManager);
		httpClient.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
		httpClient.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, autoRedirect);
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
		return httpClient;
	}
	
	private static String parseFieldValue(String html, String fieldName) {
		Pattern pattern = Pattern.compile("<input +.*?name *= *[\"\']" + fieldName + "[\"\'][^>]+?value *= *[\"\']([^>]+?)[\"\'].*?>");
		Matcher matcher = pattern.matcher(html);
		if (matcher.find()) {
			return matcher.group(1);
		}
		return "";
	}
	
	private boolean logoutAlimama(AlimamaHttpClient alimamaClient) {
		HttpGet getReq = new HttpGet("http://www.alimama.com/member/logout.htm?forward=http://u.alimama.com");
		getReq.addHeader("Referer", "http://u.alimama.com/union/myunion/myOverview.htm");
		getReq.addHeader("User-Agent", loginInfos[alimamaClient.getLoginInfoIdx()][6]);
		HttpResponse response = null;
		try {
			response = alimamaClient.getHttpClient().execute(getReq);
		} catch (Exception e) {
			alimamaLogger.error("logout alimama error", e);
		} finally {
			consumeResponse(response);
		}
		return true;
	}
	
	private static void consumeResponse(HttpResponse response) {
		if (response == null) {
			return ;
		}
		try {
			EntityUtils.consume(response.getEntity());
		} catch (Exception e) {
			
		}
	}
	
	@Override
	public void destroy() throws Exception {
		try {
			AlimamaHttpClient alimamaClient = alimamaClients.poll();
			while(alimamaClient != null) {
				logoutAlimama(alimamaClient);
				alimamaClient = alimamaClients.poll();
			}
		} finally {
			try {
				if (connManager != null) {
					connManager.shutdown();
				}
			} catch (Exception e) {
			}
		}
	}
	
	public static void main(String[] args) {
//		final String[] numIids = new String[] {"35258234134", "19753582926", "21550011680", "35303350378", "12676757591",
//				"19668897067", "26990196908", "16354643412", "27619368894", "22612323893"};
//		final String[] titles = new String[] {"2013秋同步款 HIM汉崇正品 流行时尚男士小脚牛仔裤 70539202BL",
//				"強菲斯二代 德國進口原絲 50米台釣競技魚線子線主線線 釣線批發",
//				"专利 卸力8字环 八字环 连接环连接器转环 垂钓小配件缓冲器",
//				"【11-1】速寫男裝croquis 時尚大氣搖滾羽絨服 90878A3",
//				"安的古尼2013秋裝新款韓版短款修身機車女士皮衣PU大碼外套",
//				"【天猫超市】Saky/舒客 白牙素牙膏超强脱渍 120g 美白牙齿去烟渍",
//				"無漆環保創意辦公簡易書架桌面桌上小書架宜家置物架收納架可水洗",
//				"【天貓超市】立白洗衣液全效護理洗衣液洗護合一2kg不傷手不傷衣",
//				"秋款格子襯衫女長袖韓版複古文藝棉麻學院風寬松打底襯衣森女日系",
//				"嫣然之姿2013秋冬通勤複古撞色新款包臀修身高檔針織羊毛連衣裙女"};
		DdzAlimamaBO ddzLoginBO = new DdzAlimamaBO();
		BigDecimal commRate = ddzLoginBO.getItemCommissionRate("35020288824", "太平鳥2013冬裝外套 男士短款亮色羽絨服/90%含絨量80812486811");
		System.out.println(commRate);
//		DdzTbItemModel item = ddzLoginBO.getTbItemInfo("35303350378", DdzTbItemSourceEnum.TMALL);
//		if (item != null) {
//			//379.00
//			System.out.println(item.getTitle() + "/" + item.getPrice() + "/" + item.getPicUrl());
//		} else {
//			System.out.println("fail");
//		}
//		item = ddzLoginBO.getTbItemInfo("35769810773", DdzTbItemSourceEnum.TAOBAO);
//		if (item != null) {
//			//399.00
//			System.out.println(item.getTitle() + "/" + item.getPrice() + "/" + item.getPicUrl());
//		} else {
//			System.out.println("fail");
//		}
//		item = ddzLoginBO.getTbItemInfo("35446311826", DdzTbItemSourceEnum.JUHUASUAN);
//		if (item != null) {
//			//669.00
//			System.out.println(item.getTitle() + "/" + item.getPrice() + "/" + item.getPicUrl());
//		} else {
//			System.out.println("fail");
//		}
//		item = ddzLoginBO.getTbItemInfo("19926186602", DdzTbItemSourceEnum.CHAOSHI);
//		if (item != null) {
//			//119.00
//			System.out.println(item.getTitle() + "/" + item.getPrice() + "/" + item.getPicUrl());
//		} else {
//			System.out.println("fail");
//		}
	}
}
