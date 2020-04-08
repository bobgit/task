package github.com.bobgit.study.task;


import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.nio.client.util.HttpAsyncClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.*;
import java.util.Map.Entry;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;


/**
 * Created by admin on 2015/6/30.
 */
public class HttpUtils {

	private static final String DEFAULT_CHARSET = "UTF-8";

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);

	private static final int TIME_OUT = 20 * 1000;

	/**
	 * 发送Get请求
	 *
	 * @param url
	 * @return
	 */
	public static String get(String url) {
		LOGGER.info(">> url = {}", url);
//		StringBuilder bufferRes = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			String response = restTemplate.getForObject(url, String.class);
			LOGGER.info("<< response = {}", response);
			return response;
//			URL urlGet = new URL(url);
//			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
//			// 连接超时
//			http.setConnectTimeout(25000);
//			// 读取超时 --服务器响应比较慢，增大时间
//			http.setReadTimeout(25000);
//			http.setRequestMethod("GET");
//			http.setRequestProperty("Content-Type", "application/json");
//			http.setDoOutput(true);
//			http.setDoInput(true);
//			http.connect();
//
//			InputStream in = http.getInputStream();
//			BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
//			String valueString;
//			bufferRes = new StringBuilder();
//			while ((valueString = read.readLine()) != null) {
//				bufferRes.append(valueString);
//			}
//			in.close();
//
//			// 关闭连接
//			http.disconnect();
//			LOGGER.info("<<");
//
//			return bufferRes.toString();
		} catch (Exception e) {

			LOGGER.error("<< url = {}", url, e);
			return null;
		}
	}

	/**
	 * 发送Get请求
	 *
	 * @param url
	 * @param params
	 * @return
	 */
	public static String get(String url, Map<String, String> params) {
		String res = get(initParams(url, params));
		return res;
	}

	/**
	 * 同步请求发送form格式的post请求
	 * @param url
	 * @param params
	 * @return
	 */
	public static String syncFormPost(String url, Map<String, String> params) {
		LOGGER.info(">> url = {}, params = {}", url, params);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		params.forEach((k,v) -> {
			map.add(k, v);
		});

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
		String res = response.getBody();
		LOGGER.info("<< res = {}", res);
		return res;
	}
	
	public static String syncXmlPost(String url, String xml) {
		LOGGER.info(">> url = {}, xml = {}", url, xml);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType(MediaType.APPLICATION_XML, Charset.forName("utf-8")));
//		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
//		headers.setContentType(type);
//		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		HttpEntity<String> request = new HttpEntity<>(xml, headers);


//		RestTemplate restTemplate = new RestTemplate();

		//SpringBoot中，RestTemplate中文乱码解决方案 StringHttpMessageConverter类，默认是的编码是ISO-8859-1：
		SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
		httpRequestFactory.setReadTimeout(35000);
		httpRequestFactory.setConnectTimeout(5000);
//		RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		messageConverters.add(new ByteArrayHttpMessageConverter());
		/** 解决乱码的converter */
		StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName
				("UTF-8"));
		messageConverters.add(stringHttpMessageConverter);
		messageConverters.add(new ResourceHttpMessageConverter());
		messageConverters.add(new SourceHttpMessageConverter());
		messageConverters.add(new AllEncompassingFormHttpMessageConverter());
		RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
		restTemplate.setMessageConverters(messageConverters);




		ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
//		LOGGER.info("请求响应结果:{}",response);
		String res = response.getBody();
		LOGGER.info("<<xml请求的返回结果:{}", res);
		return res;
	}



	/**
	 * @param url
	 * @param params
	 * @return
	 */
	public static String initParams(String url, Map<String, String> params) {
		if (null == params || params.isEmpty()) {
			return url;
		}
		StringBuilder sb = new StringBuilder(url);
		if (url.indexOf("?") == -1) {
			sb.append("?");
		} else {
			sb.append("&");
		}
		boolean first = true;
		for (Entry<String, String> entry : params.entrySet()) {
			if (first) {
				first = false;
			} else {
				sb.append("&");
			}
			String key = entry.getKey();
			String value = entry.getValue();
			sb.append(key).append("=");
			if (StringUtils.isNotEmpty(value)) {
				try {
					sb.append(URLEncoder.encode(value, DEFAULT_CHARSET));
				} catch (UnsupportedEncodingException e) {

					LOGGER.error("url = {}, error = {}", url, e.getMessage(), e);
				}
			}
		}
		return sb.toString();
	}


	/**
	 * 异步Post请求
	 * @param strUrl
	 * @param jsonBody
	 * @param callback
	 */
	public static void asyncPostJson(String strUrl, String jsonBody, FutureCallback<String> callback) {
		LOGGER.debug(">> url = " + strUrl);
		LOGGER.debug("jsonBody = " + jsonBody);
		HttpUtils.asyncPostJson(strUrl, null, jsonBody, callback);
		LOGGER.debug("<<");
	}

	public static void asyncPostJson(String strUrl, Map<String, String> headers, String jsonBody,
			FutureCallback<String> callback) {
		LOGGER.debug(">> url = " + strUrl);
		LOGGER.debug("jsonBody = " + jsonBody);
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault(); // NOSONAR
		try {

			// Start the client
			httpclient.start();

			// Execute request
			HttpPost httpPost = new HttpPost(strUrl);
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(TIME_OUT)
					.setConnectTimeout(TIME_OUT).setSocketTimeout(TIME_OUT).build();
			httpPost.setConfig(requestConfig);

			if (headers != null) {
				Iterator<Entry<String, String>> iter = headers.entrySet().iterator();
				while (iter.hasNext()) {
					Entry<String, String> kv = iter.next();
					httpPost.setHeader(kv.getKey(), kv.getValue());
				}
			}
			if (StringUtils.isNotEmpty(jsonBody)) {
				StringEntity input = new StringEntity(jsonBody, Charset.forName("UTF-8"));
				input.setContentType("application/json;charset=utf-8");
				httpPost.setEntity(input);
			}

			httpclient.execute(httpPost, new FutureCallback<HttpResponse>() {
				@Override
				public void cancelled() {
					callback.cancelled();
					HttpAsyncClientUtils.closeQuietly(httpclient);
				}

				@Override
				public void completed(HttpResponse httpRes) {
					String msg = HttpUtils.getHttpResponseContent(httpRes);
					callback.completed(msg);
					HttpAsyncClientUtils.closeQuietly(httpclient);

				}

				@Override
				public void failed(Exception e) {
					callback.failed(e);
					HttpAsyncClientUtils.closeQuietly(httpclient);
				}

			});

		} catch (Exception e) {
			LOGGER.warn("failed to send post request.", e);
		}
		LOGGER.debug("<<");
	}
	
//	public static Flux<String> asyncGet02(String url,Map<String, String> headers, Map<String, String> params ) {
//		LOGGER.info("url = {}", url);
//		LOGGER.info("headers = {}", headers);
//		LOGGER.info("params = {}", params);
//		WebClient.Builder builder = WebClient.builder().baseUrl(url);
//		
//		headers.forEach((k,v) -> {
//			builder.defaultHeader(k, v);
//		});
//		
//		WebClient webClient = builder.build();
//		MultiValueMap<String, String> paramsX = new LinkedMultiValueMap<>();
//		params.forEach((k, v) -> paramsX.add(k, v));
//		return webClient.get().uri(uriBuilder -> uriBuilder.queryParams(paramsX).build()).retrieve().bodyToFlux(String.class);
//	}

	/**
	 * 异步Get请求
	 * @param strUrl
	 * @param headers
	 * @param params
	 * @param callback
	 */
	public static void asyncGet(String strUrl, Map<String, String> headers, Map<String, String> params,
			FutureCallback<String> callback) {
		LOGGER.debug(">> url = {}, params = {}", strUrl, JsonUtils.toJsonString(params));
		
		
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault(); // NOSONAR

		try {
			// Start the client
			httpclient.start();

			// Execute request
			HttpGet httpGet = new HttpGet(initParams(strUrl, params));
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(TIME_OUT)
					.setConnectTimeout(TIME_OUT).setSocketTimeout(TIME_OUT).build();
			httpGet.setConfig(requestConfig);

			if (headers != null) {
				Iterator<Entry<String, String>> iter = headers.entrySet().iterator();
				while (iter.hasNext()) {
					Entry<String, String> kv = iter.next();
					httpGet.setHeader(kv.getKey(), kv.getValue());
				}
			}

			httpclient.execute(httpGet, new FutureCallback<HttpResponse>() {
				@Override
				public void cancelled() {
					callback.cancelled();
					HttpAsyncClientUtils.closeQuietly(httpclient);
				}

				@Override
				public void completed(HttpResponse httpRes) {
					String msg = HttpUtils.getHttpResponseContent(httpRes);
					callback.completed(msg);
					HttpAsyncClientUtils.closeQuietly(httpclient);
				}

				@Override
				public void failed(Exception e) {
					callback.failed(e);
					HttpAsyncClientUtils.closeQuietly(httpclient);
				}

			});

		} catch (Exception e) {
			LOGGER.warn("failed to send post request.", e);
		} finally {
			// try {
			// httpclient.close();
			// } catch (IOException e) {
			// LOGGER.error(e.getMessage(), e);
			// }
		}
		LOGGER.debug("<<");
	}

	public static String syncJsonPost(String strUrl, String jsonBody) {
		return HttpUtils.syncJsonPost(strUrl, new HashMap<String, String>(), jsonBody);
//		LOGGER.debug(">> url = " + strUrl);
//		LOGGER.debug("request body = " + jsonBody);
//		String msg = "";
//		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
//			CloseableHttpResponse response;
//			HttpPost httpPost = new HttpPost(strUrl);
//			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(TIME_OUT)
//					.setConnectTimeout(TIME_OUT).setSocketTimeout(TIME_OUT).build();
//			httpPost.setConfig(requestConfig);
//			if (StringUtils.isNotEmpty(jsonBody)) {
//				StringEntity input = new StringEntity(jsonBody, Charset.forName("UTF-8"));
//				input.setContentType("application/json;charset=utf-8");
//				httpPost.setEntity(input);
//			}
//			response = httpclient.execute(httpPost);
//			msg = HttpUtils.getHttpResponseContent(response);
//			response.close();
//		} catch (Exception e) {
//			LOGGER.error("<< error", e);
//		}
//		LOGGER.debug("<<");
//		return msg;
	}
	//MultipartFile 是 前端 <input type="file" ....> 按钮发送的固定类型， 所以后端 接收时 只能用这个类型接收

	/**
	 * 调用接口显示
	 * @param strUrl
	 * @param headers
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String syncFilePost(String strUrl, Map<String, String> headers, MultipartFile file)throws IOException {
		LOGGER.debug("url = " + strUrl);
		LOGGER.debug("request headers = " + headers);
		//设置请求头
		HttpHeaders headersX = new HttpHeaders();
		headersX.setContentType(MediaType.MULTIPART_FORM_DATA);
		headers.forEach((k, v) -> {
			headersX.add(k, v);
		});
		//设置请求体，注意是LinkedMultiValueMap
		MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();

		//但是  用 restTemplate  发送 post 请求时，需要用Map 来封装 请求体（http基础不好，我这么叫它） 如上面的  formEntity ，并且请求体里面的 “file” 属性 要是 FileSystemResource 类型的 ，而不能是 MultipartFile 类型
		//multipartFile 对象 没有路径属性，（也就是说从前端选择文件后，点击发送按钮之后，这个文件就只有内容了，没有了他的地址了） 那怎么办？ 这个时候就是知识点了—— java 是有临时文件路径的 （windows 一般是在c/user/xxx/AppData 里面 linux 是/tmp 文件夹）  获取方法：System.getProperty("java.io.tmpdir")        返回的是临时目录的路径
		String fileName = file.getOriginalFilename();//name
		String tempFilePath = System.getProperty("java.io.tmpdir") + file.getOriginalFilename();
		LOGGER.info("目前路径:{}",tempFilePath);
		File tempFile = new File(tempFilePath);
		file.transferTo(tempFile);//生成临时文件  我们可以在这个临时目录里生成一个临时文件

		//MultipartFile 直接转 fileSystemResource 是不行的
		FileSystemResource resource = new FileSystemResource(tempFilePath);//把临时文件变成filesystemresource
		form.add("file", resource);

//		form.add("file", file);
//		form.add("filename",fileName);

		//用HttpEntity封装整个请求报文
		HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(form, headersX);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.postForEntity(strUrl, request, String.class);
		String res = response.getBody();

//		ResponseEntity<JSONObject> response1 = restTemplate.postForEntity(strUrl, request, JSONObject.class);
//		JSONObject res1 = response1.getBody();
		tempFile.delete();//删除临时文件文件
		LOGGER.info("<< res = {}", res);
		return res;
	}
	/**
	 * 同步Post请求
	 * @param strUrl
	 * @param headers
	 * @param jsonBody
	 * @return
	 */
	public static String syncJsonPost(String strUrl, Map<String, String> headers, String jsonBody) {
		LOGGER.debug("url = " + strUrl);
		LOGGER.debug("request body = " + jsonBody);
		HttpHeaders headersX = new HttpHeaders();
		headersX.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.forEach((k,v) -> {
			headersX.add(k, v);
		});

		HttpEntity<String> request = new HttpEntity<>(jsonBody, headersX);




/*
        响应头部 :Content-Type application/json;charset=UTF-8 不用此等方法,如果是
        Content-Type 没有指定charset=UTF-8,则是ISO-8859-1,就会有这个问题
        //SpringBoot中，RestTemplate中文乱码解决方案 StringHttpMessageConverter类，默认是的编码是ISO-8859-1：
		SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
		httpRequestFactory.setReadTimeout(35000);
		httpRequestFactory.setConnectTimeout(5000);
//		RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		messageConverters.add(new ByteArrayHttpMessageConverter());
		*//** 解决乱码的converter *//*
		StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName
				("UTF-8"));
		messageConverters.add(stringHttpMessageConverter);
		messageConverters.add(new ResourceHttpMessageConverter());
		messageConverters.add(new SourceHttpMessageConverter());
		messageConverters.add(new AllEncompassingFormHttpMessageConverter());
		RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
		restTemplate.setMessageConverters(messageConverters);*/
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.postForEntity(strUrl, request, String.class);

		String res = response.getBody();
		LOGGER.info("<< res = {}", res);
		return res;
		
//		String msg = "";
//		try {
//			URL url = new URL(strUrl);
//			if ("HTTPS".equalsIgnoreCase(url.getProtocol())) {
//				return syncJsonPostHttps(strUrl, headers, jsonBody);
//			}
//		} catch (MalformedURLException e1) {
//			LOGGER.error(e1.getMessage(), e1);
//			return msg;
//		}
//
//		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
//
//			CloseableHttpResponse response;
//
//			HttpPost httpPost = new HttpPost(strUrl);
//			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(TIME_OUT)
//					.setConnectTimeout(TIME_OUT).setSocketTimeout(TIME_OUT).build();
//			httpPost.setConfig(requestConfig);
//			Iterator<Entry<String, String>> iter = headers.entrySet().iterator();
//			while (iter.hasNext()) {
//				Entry<String, String> kv = iter.next();
//				httpPost.setHeader(kv.getKey(), kv.getValue());
//			}
//
//			if (StringUtils.isNotEmpty(jsonBody)) {
//				StringEntity input = new StringEntity(jsonBody, Charset.forName("UTF-8"));
//				input.setContentType("application/json;charset=utf-8");
//				httpPost.setEntity(input);
//			}
//			response = httpclient.execute(httpPost);
//			msg = HttpUtils.getHttpResponseContent(response);
//			response.close();
//			httpclient.close();
//		} catch (Exception e) {
//			LOGGER.error("error", e);
//		}
//		return msg;
	}

//	public static String syncJsonPostHttps(String strUrl, Map<String, String> headers, String jsonBody) {
//		LOGGER.debug("url = " + strUrl);
//		LOGGER.debug("request body = " + jsonBody);
//		String msg = "";
//
//		try (CloseableHttpClient httpclient = HttpClients.custom().build()) {
//			CloseableHttpResponse response;
//			HttpPost httpPost = new HttpPost(strUrl);
//			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(TIME_OUT)
//					.setConnectTimeout(TIME_OUT).setSocketTimeout(TIME_OUT).build();
//			httpPost.setConfig(requestConfig);
//			Iterator<Entry<String, String>> iter = headers.entrySet().iterator();
//			while (iter.hasNext()) {
//				Entry<String, String> kv = iter.next();
//				httpPost.setHeader(kv.getKey(), kv.getValue());
//			}
//
//			if (StringUtils.isNotEmpty(jsonBody)) {
//				StringEntity input = new StringEntity(jsonBody, Charset.forName("UTF-8"));
//				input.setContentType("application/json;charset=utf-8");
//				httpPost.setEntity(input);
//			}
//			response = httpclient.execute(httpPost);
//			msg = HttpUtils.getHttpResponseContent(response);
//			response.close();
//			httpclient.close();
//		} catch (Exception e) {
//			LOGGER.error("error", e);
//		}
//		return msg;
//	}

	public static String getHttpResponseContent(HttpResponse response) {
		String msg = "";
		org.apache.http.HttpEntity entity = response.getEntity();
		if (entity != null) {
			InputStream instream = null;
			try {
				instream = entity.getContent();
				BufferedReader bfr = new BufferedReader(new InputStreamReader(instream));
				StringBuffer buffer = new StringBuffer();
				String line = "";
				while ((line = bfr.readLine()) != null){
				    buffer.append(line);
                }
				msg = buffer.toString();
				LOGGER.debug("msg = " + msg);
			} catch (IllegalStateException e) {
				LOGGER.error("Exception", e);
			} catch (IOException e) {
				LOGGER.error("Exception", e);
			} finally {
				try {
					if (instream != null) {
						instream.close();
					}
				} catch (Exception e) {
					LOGGER.error(e.getMessage(), e);
				}
			}
		}
		return msg;
	}

}
