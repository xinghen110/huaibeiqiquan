package com.pay.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：1.0
 *日期：2016-06-06
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
		//注意，支付宝应用必须要审核过，才能使用

	//合作身份者ID
	public static String partner = "2017091508741113"; //(真实乐享)

	//商户的私钥
    public static String private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQChpreOimPcw78/54nr2sPz0kJsouQH1TMll0PT2zf9cooop88IOw83IzcIV61CQUei6SYK5wkZ/drWNpB0Sm8i3cWQKlcCc2ZC67HDEK46l3vjMhLriH94EghQW1AV1o2i/2maI7x6AZsBMWyG2T1Kd2JEPGBb4ktxjz9h9DWMWluP2Ucys3YE66MllYNHh4UCiA0AWp+V8OaTNFbFVpTfntWLVyo8RT/kHAQUNwV0GkLOExIr9bZz7BNNHjWKZeyjP+tC7k1CpdDbvvFRvFrPpbH6SK6NeIY8g50ECb+eaQEZmfOflvaHIZjaLsxxzNl27HO1E8sBIZ0DPyeIvEETAgMBAAECggEAO0sy0zcR8D1g3mjonzmsXLgII18Qc4sRar10ZLZpcC7eXJFJEITRTPE+03GNnNrjEu+gQ6mBHiaOabqz++P9pfVg7UOKjNyVawCXQKzSq607BRgt9nUoBHvgztDfrFL5uwsiyiZHkR0bpTFi1IfEwpbN1aer1KhJ8gqQPjbcmXFKAL9APsScleOKTkEpsDdMWTyyRWBOlulvKtDKl0WE3LYGc9rLjSdkMu2Ia7yDnOYcZGXfd74cEfuCx/ACwRz691eflyMvfqb/mMpnm+bukHVIuiWzoAmQQThGaMjoskqYywcWh0EdnuBy+uoCjH7/iho+f34fmosyDq4BwP+q4QKBgQDTilqUFzwb7AQ8KcTHja1191h4BSODJ+Ji6/yG6RJ2HBWxcUF6uMFegNnexp5bd3vLIF9lDcjHkukdB0+SSJwz/qc85PtX4dU/tq7JzQWKerrE4Nol77XzSG20dhyUv31DTkXFI6hD8/yYHFixh/NwkjAF1fDoYM8Zk2RJx4t04wKBgQDDoCRqGFY6ibNbVd89BA6C8hW4SzfAGP1FqR0x4xoQfcBrjLusQW0DMjBg/WsBqJk22bZ9NeKwEWrZWto/sd1bm1CFkmOO4TroqdmX603m53nOgoEj+M9h0dIpP6Z2Ew6YiUA1RlaE/t9r5vbbjhaqG+8aZOK4ydioMynehXbqEQKBgBViWg+4XZIxBKiUTFkj4FIZg+72DgX3JTJ8ypVUwFICOssqOMVQIfQMsHmNuHpklLYO+rW7c/2Aw2H02D1ga7Hx3zQPrbzPjoAXVon92L6L849tb4AAQHNscfKZMgBzbxU7t1/bX+omovU5gFnJ9Xj+vi4I14aWKrlaJfwzWMyjAoGAH6TC3yqKoDNwAME2OB903XedTc9U+EzSgzck0tICgFJnEr6UCPwYTyEKNFYpvf3wsq+q1jwmLAbVOs14aj8yKERKVSDl29HQ/pui5/EXC9e7m0z+6LuxiAgxr5xtJ8JTZRcmfLeg/ShhKYoVulQUlHWqSqGxDuUPl72W8zixkJECgYB59j2XIU3T1K5AJ5wBFJEDbYzvXdDbbUvXnNSJ+meBAPgzMb/pRzSbgcbUd5cG+FFJ9TduWJ1yXCqKX/5aoOtEvvai5Fp9dNCNONMYVljWBEMEswSPEFP0WmfYX58we0CsLZQTq5Q4BYeYUVb/0JfGvLFeR998aqd6aMD6V83+Bw==";
	
	//支付宝的公钥 （支付宝后台也需要配置）
    public static String alipay_public_key  = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoaa3jopj3MO/P+eJ69rD89JCbKLkB9UzJZdD09s3/XKKKKfPCDsPNyM3CFetQkFHoukmCucJGf3a1jaQdEpvIt3FkCpXAnNmQuuxwxCuOpd74zIS64h/eBIIUFtQFdaNov9pmiO8egGbATFshtk9SndiRDxgW+JLcY8/YfQ1jFpbj9lHMrN2BOujJZWDR4eFAogNAFqflfDmkzRWxVaU357Vi1cqPEU/5BwEFDcFdBpCzhMSK/W2c+wTTR41imXsoz/rQu5NQqXQ277xUbxaz6Wx+kiujXiGPIOdBAm/nmkBGZnzn5b2hyGY2i7McczZduxztRPLASGdAz8niLxBEwIDAQAB";

	// 签名方式
	public static String sign_type = "RSA";
	
	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	public static String log_path ="C://";
		
	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";

	// 接收通知的接口名
	public static String service = "mobile.securitypay.pay";
	
	//支付宝回调地址(必须外网能访问)
	public static String IP_ADDRESS="http://192.126.123.85/daowei/app/payinfo/aliyunReturn";

//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
}

