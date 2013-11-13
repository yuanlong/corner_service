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
import com.doucome.corner.service.biz.core.model.AlimamaLoginHttpClient;
import com.doucome.corner.service.biz.core.model.DdzTbItemModel;
import com.doucome.corner.service.biz.core.utils.DecimalUtils;


/**
 * 
 * @author ze2200
 *
 */
public class DdzAlimamaBO implements DisposableBean {
	
	private static final Log alimamaLogger = LogFactory.getLog("alimama-log");
	
	private static final LinkedBlockingQueue<AlimamaLoginHttpClient> alimamaClients;
	
	private final static ThreadSafeClientConnManager connManager;
	
	private final static String[][] loginInfos = new String[][]{{"weiboyn", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "firefox|24"},
										{"dashabengta", "1qaz2wsx", "zh-CN", "1440*900", "windows|6.1", "chrome|30.0159969"},
										{"weiboyn", "1qaz2wsx", "zh-CN", "1440*900", "windows|6.1", "chrome|30.0159969"},
										{"changshifenbie", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "firefox|24"},
										{"dashabengta", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "firefox|24"},
										{"changshifenbie", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "chrome|30.0159969"},
										{"weiboyn", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "chrome|30.0159969"},
										{"dashabengta", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "chrome|30.0159969"},
										{"weiboyn", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "firefox|24"},
										{"dashabengta", "1qaz2wsx", "zh-CN", "1440*900", "windows|6.1", "chrome|30.0159969"},
										{"weiboyn", "1qaz2wsx", "zh-CN", "1440*900", "windows|6.1", "chrome|30.0159969"},
										{"dashabengta", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "firefox|24"},
										{"changshifenbie", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "chrome|30.0159969"},
										{"weiboyn", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "chrome|30.0159969"},
										{"dashabengta", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "chrome|30.0159969"},
										{"changshifenbie", "1qaz2wsx", "zh-CN", "1440*900", "windows|6.1", "chrome|30.0159969"},//
										{"weiboyn", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "chrome|30.0159969"},
										{"dashabengta", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "chrome|30.0159969"},
										{"weiboyn", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "firefox|24"},
										{"dashabengta", "1qaz2wsx", "zh-CN", "1440*900", "windows|6.1", "chrome|30.0159969"},
										{"weiboyn", "1qaz2wsx", "zh-CN", "1440*900", "windows|6.1", "chrome|30.0159969"},
										{"dashabengta", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "firefox|24"},
										{"changshifenbie", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "chrome|30.0159969"},
										{"weiboyn", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "chrome|30.0159969"},
										{"dashabengta", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "chrome|30.0159969"},
										{"changshifenbie", "1qaz2wsx", "zh-CN", "1440*900", "windows|6.1", "chrome|30.0159969"},
										{"weiboyn", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "firefox|24"},
										{"dashabengta", "1qaz2wsx", "zh-CN", "1440*900", "windows|6.1", "chrome|30.0159969"},
										{"weiboyn", "1qaz2wsx", "zh-CN", "1440*900", "windows|6.1", "chrome|30.0159969"},
										{"changshifenbie", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "firefox|24"},
										{"dashabengta", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "firefox|24"},
										{"changshifenbie", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "chrome|30.0159969"},
										{"weiboyn", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "chrome|30.0159969"},
										{"dashabengta", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "chrome|30.0159969"},
										{"weiboyn", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "firefox|24"},
										{"dashabengta", "1qaz2wsx", "zh-CN", "1440*900", "windows|6.1", "chrome|30.0159969"},
										{"weiboyn", "1qaz2wsx", "zh-CN", "1440*900", "windows|6.1", "chrome|30.0159969"},
										{"dashabengta", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "firefox|24"},
										{"changshifenbie", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "chrome|30.0159969"},
										{"weiboyn", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "chrome|30.0159969"},
										{"dashabengta", "1qaz2wsx", "zh-CN", "1440*900", "macos|10.9", "chrome|30.0159969"}};
									
	static {
		try {
			alimamaClients = new LinkedBlockingQueue<AlimamaLoginHttpClient>(100);
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
				AlimamaLoginHttpClient loginClient = loginAlimama(connManager, loginInfos[i][0], loginInfos[i][1], loginInfos[i][2], loginInfos[i][3], loginInfos[i][4], loginInfos[i][5]);
				if (loginClient == null) {
					loginClient = loginAlimama(connManager, loginInfos[i][0], loginInfos[i][1], loginInfos[i][2], loginInfos[i][3], loginInfos[i][4], loginInfos[i][5]);
				}
				if (loginClient == null) {
					throw new Exception("---commissionRate can't login alimama");
				}
				alimamaClients.put(loginClient);
			}
		} catch (Exception e) {
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
		if (StringUtils.isEmpty(title)  || !StringUtils.isNumeric(numIid)) {
			return null;
		}
		long time0 = System.currentTimeMillis();
		AlimamaLoginHttpClient loginInstance = null;
		try {
			loginInstance = alimamaClients.poll(4000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			alimamaLogger.error("---exception, take httpClient from blocking queue: " + numIid, e);
		}
		alimamaLogger.error("----take httpclient time: " + (System.currentTimeMillis() - time0));
		if (loginInstance == null) {
			alimamaLogger.error("---fail, can't get httpClient from blocking queue: " + numIid);
			return null;
		}
		long time1 = System.currentTimeMillis();
		HttpResponse response = null;
		try {
			String html = null;
			try {
				HttpClient httpClient = loginInstance.getHttpClient();
				HttpGet getReq = new HttpGet(getCommissionReqUrl(title));
				getReq.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
				getReq.addHeader("Accept-Encoding", "gzip,deflate");
				getReq.addHeader("Connection", "keep-alive");
				getReq.addHeader("Referer", "http://u.alimama.com/union/spread/selfservice/taokeSearch.htm");
				getReq.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:24.0) Gecko/20100101 Firefox/24.0");
				response = httpClient.execute(getReq);
				int statusCode = response.getStatusLine().getStatusCode();
				if (statusCode != 200) {
					alimamaLogger.error("---fail, request commission form response status: " + statusCode + "/" + numIid);
					//��������ʧ�ܣ��첽���µ�¼��������
					logoutAlimama(httpClient);
					//����Ϊ�գ����������
					loginInstance = null;
					new Thread() {
						@Override
						public void run() {
							int idx = new Random(System.currentTimeMillis()).nextInt(loginInfos.length);
							AlimamaLoginHttpClient loginClient = loginAlimama(connManager, loginInfos[idx][0], loginInfos[idx][1], loginInfos[idx][2], loginInfos[idx][3], loginInfos[idx][4], loginInfos[idx][5]);
							if (loginClient != null) {
								try {
									alimamaClients.put(loginClient);
								} catch (Exception e) {
									alimamaLogger.error("---exception, async put relogin htttClient to blockingqueue", e);
								}
							} else {
								alimamaLogger.error("---fail, async relogin alimama fail");
							}
						}
					}.start();
					return null;
				}
				String contentEncode = response.getFirstHeader("Content-Encoding").getValue();
				if (contentEncode.toLowerCase().indexOf("gzip") != -1) {
					html = getHtmlFromGzip(response.getEntity(), "gbk");
				} else {
					html = EntityUtils.toString(response.getEntity(), "GBK");
				}
				if (StringUtils.isEmpty(html)) {
					alimamaLogger.error("---fail, can't read html from commission form response: " + numIid);
					return null;
				}
			} catch (Exception e) {
				throw e;
			} finally {
				if (loginInstance != null) {
					try {
						loginInstance.refreshTimestamp();
						alimamaClients.put(loginInstance);
					} catch (InterruptedException e) {
						alimamaLogger.error("------exception, put httpClient to blockingqueue exception: " + numIid, e);
					}
				}
				consumeResponse(response);
			}
			alimamaLogger.error("---request commission form total time: " + (System.currentTimeMillis() - time1));
			time1 = System.currentTimeMillis();
			BigDecimal commRate = parseCommissionRateFromTable(numIid, html);
			alimamaLogger.error("---commissionRate total time: " + (System.currentTimeMillis() - time0));
			return commRate;
		} catch (Exception e) {
			alimamaLogger.error("---get commission rate from alimama exception", e);
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
	
	
	public void refreshLoginStatus(String destUrl) {
		long time1 = System.currentTimeMillis();
		AlimamaLoginHttpClient alimamaClient = null;
		for (int i = 0; i < 80; i++) {
			try {
				alimamaClient = pollQueue(2000);
				if (alimamaClient == null && alimamaClients.size() < 80) {
					int idx = new Random(System.currentTimeMillis()).nextInt(loginInfos.length);
					alimamaClient = loginAlimama(connManager, loginInfos[idx][0], loginInfos[idx][1], loginInfos[idx][2],
					                            loginInfos[idx][3], loginInfos[idx][4], loginInfos[idx][5]);
					if (alimamaClient != null) {
						continue;
					}
				}
				if (!alimamaClient.needRefresh()) {
					break;
				}
				int statusCode = 0;
				HttpResponse response = null;
				try {
					HttpGet getReq = new HttpGet(destUrl);
					response = alimamaClient.getHttpClient().execute(getReq);
					statusCode = response.getStatusLine().getStatusCode();
				} catch (Exception e) {
					alimamaLogger.error("---refresh exception", e);
				} finally {
					consumeResponse(response);
				}
				if (statusCode != 200) {
					try {
						//��ǰclient��ʧЧ�����³�ʼ��һ���������.
						int idx = new Random(System.currentTimeMillis()).nextInt(loginInfos.length);
						alimamaClient = loginAlimama(connManager, loginInfos[idx][0], loginInfos[idx][1], loginInfos[idx][2],
						                            loginInfos[idx][3], loginInfos[idx][4], loginInfos[idx][5]);
						if (alimamaClient != null) {
							HttpGet getReq = new HttpGet(destUrl);
							response = alimamaClient.getHttpClient().execute(getReq);
							if (statusCode != 200) {
								alimamaClient = null;
								alimamaLogger.error("---refresh fail, dest page response code: " + response.getStatusLine().getStatusCode());
							}
						} else {
							alimamaLogger.error("---refresh fail, with relogin fail");
						}
					} catch (Exception e) {
						alimamaLogger.error("---refresh exception", e);
					} finally {
						consumeResponse(response);
					}
				}
			} finally {
				putQueue(alimamaClient);
			}
		}
		alimamaLogger.error("---refresh consume: " + (System.currentTimeMillis() - time1));
	}
	
	public AlimamaLoginHttpClient pollQueue(long millTime) {
		try {
			return alimamaClients.poll(millTime, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			alimamaLogger.error("---refresh fail, interupt when poll", e);
		}
		return null;
	}
	
	public boolean putQueue(AlimamaLoginHttpClient alimamaClient) {
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
		String commReg = "<td[\\s\\S]*?>[\\s\\S]*?(\\d+(.\\d*)?)\\s*%[\\s\\S]*?</td>";
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
	
	/**
	 * ��ȡһ����½����������httpclient.
	 * @param connManager
	 * @param userName
	 * @param password
	 * @param oslanguage
	 * @param sr
	 * @param osVer
	 * @param naviVer
	 * @return
	 */
	private static AlimamaLoginHttpClient loginAlimama(ThreadSafeClientConnManager connManager, String userName, String password,
			 String oslanguage, String sr, String osVer, String naviVer) {
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
			getReq.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:24.0) Gecko/20100101 Firefox/24.0");
			response = httpClient.execute(getReq);
			if (response.getStatusLine().getStatusCode() != 200) {
				alimamaLogger.error("request taobao login index failed, status code: " + response.getStatusLine().getStatusCode());
				alimamaLogger.error("response text: " + EntityUtils.toString(response.getEntity(), "GBK"));
				return null;
			}
			html = EntityUtils.toString(response.getEntity(), "GBK");
			
			getReq = new HttpGet("https://log.mmstat.com/member.2.1.2?ok=1");
			getReq.addHeader("Referer", "https://login.taobao.com/member/login.jhtml?style=minisimple&from=alimama&redirectURL=http%3A%2F%2Flogin.taobao.com%2Fmember%2Ftaobaoke%2Flogin.htm%3Fis_login%3d1&full_redirect=true&disableQuickLogin=true");
			getReq.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:24.0) Gecko/20100101 Firefox/24.0");
			response = httpClient.execute(getReq);
			consumeResponse(response);
			getReq = new HttpGet("https://log.mmstat.com/member.2.1.4");
			getReq.addHeader("Referer", "https://login.taobao.com/member/login.jhtml?style=minisimple&from=alimama&redirectURL=http%3A%2F%2Flogin.taobao.com%2Fmember%2Ftaobaoke%2Flogin.htm%3Fis_login%3d1&full_redirect=true&disableQuickLogin=true");
			getReq.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:24.0) Gecko/20100101 Firefox/24.0");
			response = httpClient.execute(getReq);
			consumeResponse(response);
			
			//params.add(new BasicNameValuePair("ua", parseFieldValue(html, "ua")));
			params.add(new BasicNameValuePair("umto", parseFieldValue(html, "umto")));
			params.add(new BasicNameValuePair("gvfdcre", parseFieldValue(html, "gvfdcre")));
			params.add(new BasicNameValuePair("TPL_username", userName));
			params.add(new BasicNameValuePair("TPL_password", password));
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
			params.add(new BasicNameValuePair("oslanguage", oslanguage));
			params.add(new BasicNameValuePair("sr", sr));
			params.add(new BasicNameValuePair("osVer", osVer));
			params.add(new BasicNameValuePair("naviVer", naviVer));
			HttpPost postReq = new HttpPost("https://login.taobao.com/member/login.jhtml");
			postReq.addHeader("Origin", "https://login.taobao.com");
			postReq.addHeader("Referer", "https://login.taobao.com/member/login.jhtml?style=minisimple&from=alimama&redirectURL=http%3A%2F%2Flogin.taobao.com%2Fmember%2Ftaobaoke%2Flogin.htm%3Fis_login%3d1&full_redirect=true&disableQuickLogin=true");
			postReq.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:24.0) Gecko/20100101 Firefox/24.0");
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
			getReq.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:24.0) Gecko/20100101 Firefox/24.0");
			response = httpClient.execute(getReq);
			int statusCode = response.getStatusLine().getStatusCode();
			consumeResponse(response);
			if (statusCode == 302) {
				while(statusCode == 302) {
					reqUrl = response.getFirstHeader("Location").getValue();
					getReq = new HttpGet(reqUrl);
					getReq.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:24.0) Gecko/20100101 Firefox/24.0");
					response = httpClient.execute(getReq);
					statusCode = response.getStatusLine().getStatusCode();
					consumeResponse(response);
				}
			} else {
				reqUrl = "http://www.alimama.com/index.htm";
				getReq = new HttpGet(reqUrl);
				getReq.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:24.0) Gecko/20100101 Firefox/24.0");
				response = httpClient.execute(getReq);
				consumeResponse(response);
			}
			return new AlimamaLoginHttpClient(httpClient);
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
	
	private boolean logoutAlimama(HttpClient httpClient) {
		HttpGet getRequest = new HttpGet("http://www.alimama.com/union/logout.htm?spm=0.0.0.0.X9uNrD");
		HttpResponse response = null;
		try {
			response = httpClient.execute(getRequest);
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
			AlimamaLoginHttpClient alimamaClient = alimamaClients.poll();
			while(alimamaClient != null) {
				logoutAlimama(alimamaClient.getHttpClient());
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
		DdzAlimamaBO ddzLoginBO = new DdzAlimamaBO();
//		BigDecimal commRate = ddzLoginBO.getItemCommissionRate("19753582926", "����˹���� ���M��ԭ�z 50��̨ី����~���Ӿ������� ើ���l", 0);
//		System.out.println(commRate);
		DdzTbItemModel item = ddzLoginBO.getTbItemInfo("35303350378", DdzTbItemSourceEnum.TMALL);
		if (item != null) {
			//379.00
			System.out.println(item.getTitle() + "/" + item.getPrice() + "/" + item.getPicUrl());
		} else {
			System.out.println("fail");
		}
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
