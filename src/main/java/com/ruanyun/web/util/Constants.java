package com.ruanyun.web.util;

import java.math.BigDecimal;


public class Constants {

    //多得宝支付密钥
    public static String  MERCHANT_PRIVATE_KEY= "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAMVq8892HIjbL/Xx\n" +
            "5QGz4nYCPHwvOowGy4CUT6Ow4EsOqElHOLa0k6pSRpEQHBDy/Getd2jCxC8N+AHq\n" +
            "3KwSQyfiS3LiIBgZznQawposD+/+JCPWvcM+olD59cFJ1VRgDR1R2iuw2NJul14g\n" +
            "8um2gH3JzOnr6mKseoQNFkWNfQbHAgMBAAECgYEAgLHnPNBPa7wPuPI3CvQcZjew\n" +
            "/HfBX/UyBqEohZpRrTxh4ltGknyHYtn93KU21qnAd0ny9N07DT269m0H7Dh/g8MH\n" +
            "VGnYOwHQUBYcInCAVoMmQCMMTl0UdPKiG2ia6XWGyWmcUX530ShCMphJeLlsGCRJ\n" +
            "yn0osAPGFalQJj7FFnkCQQD4WNjKvSZbOfhl4UBXc5PNJxwYxy9AY/GpMT6Kegr5\n" +
            "Sg4LE5ORP1OEDsfTjRgjEehRBLztIea5hwoY4MzVZPt7AkEAy4BWDNYpPKbR0oQN\n" +
            "AIvNzGaPqi/IN8ZWsT1rOWnb7efyhJ8o4CdLd7YFH4QSmMEIGSFw9TNjUyQ4K5/1\n" +
            "qR+qJQJBAM2i88/cE3iSZcYmX9qJzyaebbtuoweUOjQ4nGqZZ6DH2xz81sH8R7Ef\n" +
            "dAMN36CaYV1EqRNJcPzKfmffLHy+IyUCQEEQpXKxProPtGKUqfuUt5Y07JZDdMaw\n" +
            "eaNtucy/lfv4/0d8A/u3VJ/P6M9s0O1aOpaeZJlhds5sdDEukcMjRqkCQQC0ZqsA\n" +
            "PdJUWOvfLkdFZiQ3KzoNhRAs6Lb6yTzdHAs/vS7mh+ubSSlp7nCOFe44Bdwa2GdL\n" +
            "EwkRIK/bSQKV+vVx";

    public static String FILE_SEPARATOR = System.getProperty("file.separator");
    /**
     * url 地址配置
     */
    public static String URL_PATH = "/url.properties";

    /**
     * 敏感词
     */
    public static String FILE_DOCUMENT = "file/document/";
    /**
     * email 地址配置
     */
    public static String EMAILCONFIG_PATH = "/emailconfig.properties";

    /**
     * 页面消息配置 路径
     */
    public static String PAGECONFIG_PATH = "/pagemessage.properties";

    /**
     * 短信通知配置文件
     */
    public static final String SMS_PATH = "/sms.properties";
    /**
     * 用户的session key值
     */
    public static String SESSION_KEY_USER = "systemUser";

    /**
     * 存用户实名信息
     */
    public static String SESSION_KEY_USER_INFO = "systemUserInfo";

    /**
     * 用户的session key值
     */
    public static String SESSION_KEY_USERAPP = "appUser";

    /**
     * 微信登录session key值
     */
    public static final String SESSION_KEY_WEIXIN_USERINFO = "systemWeixinUser";

    /**
     * 微信用户的session key值
     */
    public static final String SESSION_KEY_WEIXIN_OPEN_ID = "systemWeixinOpenId";
    public static final String SESSION_KEY_WEIXIN_ACCESS_TOKEN = "systemWeixinAccessToken";

    /**
     * 微信用户的session key值
     */
    public static final String SESSION_KEY_WEIXIN_USER = "systemWeixinUser";

    /**
     * 商品图片
     */
    public static final String FILE_GOODS = "file" + FILE_SEPARATOR + "goods" + FILE_SEPARATOR;

    /**
     * 左边菜单url sessionKEY
     */
    public static String SEESION_KEY_LEFTURLS = "leftUrls";


    /**
     * 新加环信用户默认密码【已加密】B3A23D90AD680CE8126DBAC1D1E8D938
     */
    public static String HUANXIN_USER_DEFULT_PASSWORD = "B3A23D90AD680CE8126DBAC1D1E8D938";


    /**
     * 权限类型URL
     */
    public static String AUTHORITY_TYPE_URL = "1";

    /**
     * 权限类型 权限
     */
    public static String AUTHORITY_TYPE_AUTH = "2";


    /**
     * 新加用户默认密码
     */
    public static String USER_DEFULT_PASSWORD = "123456";

    /**
     * 上传文件最大50
     */
    public static int FILE_MAX_SIZE_FILE = 50 * 1000 * 1000;
    /**
     * 上传文件名长度，最大30
     */
    public static int FILE_NAME_MAX_SIZE = 60;
    /**
     * 没有选择文件
     */
    public static int FILE_ERROR_NOFILE = -5;
    /**
     * 文件名太长
     */
    public static int FILE_ERROR_MOREFILENAME = -2;

    /**
     * 文件格式不正确
     */
    public static int FILE_ERROR_NOTYPE = -3;

    /**
     * 文件太大
     */
    public static int FILE_ERROR_MOREFILESIZE = -4;

    /**
     * 文件上传证成功
     */
    public static int FILE_SUCCESS = 1;

    /**
     * 文件上传失败
     */
    public static int FILE_FAIL = -1;


    /**
     * 全局通用禁用状态
     */
    public static int GLOBAL_ENBLE_STATUS = 2;

    /**
     * 全局通用正常状态
     */
    public static int GLOBAL_STATUS = 1;

    /**
     * 全局通用删除状态
     */
    public static int GLOBAL_DEL = 0;
    /**
     * 手机端返回access_token 判断账户是否异常
     */
    public static String APP_ACCESS_TOKEN = "access_token";
    /**
     * 手机端返回成功状态
     */
    public static int APP_SUCCESS = 1;
    /**
     * 手机端返回失败状态
     */
    public static int APP_ERROR = -1;

    /**
     * 需要的点号
     */
    public static String FILE_BIT = ".";

    /**
     * 需要的逗号
     */
    public static String FILE_COMMA = ",";

    /**
     * 需要的负号
     */
    public static String SUBTRACT = "-";

    /**
     * 兑换类型图片路劲
     */

    public static String FILE_GOODS_TYPE = "file" + FILE_SEPARATOR + "goodstype" + FILE_SEPARATOR;
    /**
     * 广告图片
     */

    public static String FILE_ADVERTISEMENT = "file" + FILE_SEPARATOR + "advertisement" + FILE_SEPARATOR;

    /**
     * 商品管理
     */
    public static String FILE_GOODSINFO = "file" + FILE_SEPARATOR + "goodsinfo" + FILE_SEPARATOR;

    /**
     * 版本更新
     */
    public static String FILE_VERSIONUPDATE = "file" + FILE_SEPARATOR + "versionupdate" + FILE_SEPARATOR;

    /**
     * 默认图片
     */
    public static String DEFAULT_IMG = "img/ewm.png";

    /**
     * 图片---用户头像路劲
     */

    public static String FILE_USER_PHOTO = "file" + FILE_SEPARATOR + "userphoto" + FILE_SEPARATOR;


    /**
     * 附件上传文件夹
     */
    public static final String attachName = "docx";

    /**
     * xhEditor编辑器文件上传路径
     */
    public static String XHEDITOR_UPLOAD_PATH = "file//xheditor//";

    /**
     * 图片压缩比例的宽度compression ratio
     */
    public static int PIC_COMPRESS_RATIO = 350;

    /**
     * 七牛图片默认上传类型
     */
    public static String QINIU_PIC_TYPE = "gif,jpg,jpeg,bmp,png,GIF,JPG,JPEG,BMP,PNG,apk,mp4";

    /**
     * 七牛公共图片上传地址
     */
    public static String QINIU_COMMONT_BUCKET = "userimg";

    /**
     * ueditor上传图片
     */
    public static String QINIU_UEDITOR_BUCKET = "ueditor";


    /**
     * 七牛上传用户图片域名地址
     */
    public static String QINIU_USER_IMGURL = "http://ow72stc9k.bkt.clouddn.com/";

    /**
     * ueditor
     */
    public static String QINIU_UEDITOR_IMGURL = "http://ow72eu7w7.bkt.clouddn.com/";


    //==============================JQYERY UI数据操作返回值=================================================================

    /**
     * JQYERY UI 表单提交 返回值--正常状态 statusCode
     */
    public static String STATUS_SUCCESS_CODE = "200";
    /**
     * JQYERY UI 表单提交 返回值--失败状态 statusCode
     */
    public static String STATUS_FAILD_CODE = "300";
    /**
     * JQYERY UI 表单提交 返回值--会话失效状态 statusCode
     */
    public static String STATUS_FAILSESSION_CODE = "301";
    /**
     * JQYERY UI 提示信息-- 返回 成功提示信息 message
     */
    public static String MESSAGE_SUCCESS = "数据操作成功";
    /**
     * JQYERY UI 提示信息-- 返回 失败提示信息 message
     */
    public static String MESSAGE_FAILED = "数据操作失败";

    /**
     * 百度APIKEY
     */
    public static String APIKEY = "lbRrSxqGAYwk6hWt3vEdxZG7";
    /**
     * 百度SECRETKEY
     */
    public static String SECRETKEY = "p2fV1eTe1BgBwGo7bjSjcVUQWuqohEfs";

    /**
     * 用户兑换总额人工审核限制
     */
    public static int exchangeMoney = 50;

    /**
     * 同一用户每天限制兑换金额的次数
     */
    public static int EXCHANGE_COUNT = 3;


    /**
     * 用户积分上线
     */
    public static int MAX_SCORE = 5000;


    /**
     * 通过微信绑定时,用来存放短信验证码和用户的手机号
     */
    public static String validateContent = "validateContent";

    /**
     * 用户类型
     **/
    public static int USER_TYPE_1 = 1;  //系统用户
    public static int USER_TYPE_2 = 2;  //商家/技师用户
    public static int USER_TYPE_3 = 3;  //会员用户

    public static int USER_TYPE_99 = 99;  //全局


    /**
     * 流水消费类型
     **/
    public static int CONSUM_TYPE_1 = 1;//购物消费
    public static int CONSUM_TYPE_2 = 2;//积分兑换
    public static int CONSUM_TYPE_3 = 3;//充值
    public static int CONSUM_TYPE_4 = 4;//提现
    public static int CONSUM_TYPE_5 = 5;//充值送积分
    public static int CONSUM_TYPE_6 = 6;//会员用户购物返现
    public static int CONSUM_TYPE_7 = 7;//分销提成
    public static int CONSUM_TYPE_8 = 8;//系统赠送会员

    //流水消费类型提示
    public static String CONSUM_TYPE1_title = "订单消费";
    public static String CONSUM_TYPE2_title = "积分兑换";//积分兑换
    public static String CONSUM_TYPE3_title = "账户充值";//充值
    public static String CONSUM_TYPE4_title = "提现";//提现
    public static String CONSUM_TYPE4_1_title = "提现失败";//提现
    public static String CONSUM_TYPE5_title = "充值送积分";//充值送积分
    public static String CONSUM_TYPE7_title = "订单退款";//退款
    public static String CONSUM_TYPE8_title = "交易成功";//买家确认收货
    public static String CONSUM_TYPE9_title = "提现费率";
    public static String CONSUM_TYPE10_title = "购买VIP会员卡";
    public static String CONSUM_TYPE11_title = "+会员返现";
    public static String CONSUM_TYPE12_title = "分销提成";
    public static String CONSUM_TYPE13_title = "系统赠送会员";

    /**
     * 物流状态
     **/
    public static int LOGISTICS_STATUS_1 = 1;//待取货
    public static int LOGISTICS_STATUS_2 = 2;//配送中
    public static int LOGISTICS_STATUS_3 = 3;//已送达
    public static int LOGISTICS_STATUS_4 = 4;//已签收


    /**
     * 提现状态
     **/
    public static int APPLICATION_STATUS_1 = 1;//提现成功
    public static int APPLICATION_STATUS_2 = 2;//已提交
    public static int APPLICATION_STATUS_1_ = -1;//提现失败


    /**
     * 店铺状态
     **/
    public static int SHOP_STATUS_1 = 1;//审核通过
    public static int SHOP_STATUS_3 = 3;//审核中
    public static int SHOP_STATUS_2 = 2;//待审核
    public static int SHOP_STATUS_1_ = -1;//暂存


    /**
     * 支付类型
     **/
    public static int PAY_TYPE_1 = 1;//账号支付
    public static int PAY_TYPE_2 = 2;//支付宝支付
    public static int PAY_TYPE_3 = 3;//微信支付
    public static int PAY_TYPE_4 = 4;//积分账户


    /**
     * 用户有无店铺状态
     **/
    public static int AUDIT_SHOP_STATUS_1 = 1;//有店铺且审核通过
    public static int AUDIT_SHOP_STATUS_2 = 2;//有店铺且待审核


    /**
     * 订单状态
     **/
    public static int ORDER_STATUS_1 = 1;    //未付款
    public static int ORDER_STATUS_2 = 2;    //已付款
    public static int ORDER_STATUS_3 = 3;    //待收货
    public static int ORDER_STATUS_4 = 4;    //待评价
    public static int ORDER_STATUS_5 = 5;    //已评论
    public static int ORDER_STATUS_1_ = -1;  //已取消
    public static int ORDER_STATUS_2_ = -2;  //已删除
    public static int ORDER_STATUS_6 = 6;    //交易成功


    /**
     * 订单类型
     */
    public static int ORDER_TYPE_1 = 1;    //普通订单
    public static int ORDER_TYPE_2 = 2;    //会员订单
    public static int ORDER_TYPE_3 = 3;    //充值订单

    /**
     * 发送验证码次数
     */
    public static int SEND_COUNT = 5;    //短信次数

    /****用户角色***/
    public static int USER_ROLE_SYS = 1;//系统用户
    public static int USER_ROLE_SHOP = 2;//经销商
    public static int USER_ROLE_MEMBER = 3;//会员用户

    public static int USER_TYPE_MOTHER_01 = 1;//母公司
    public static int USER_TYPE_CHILD_02 = 2;//分公司
    public static int USER_TYPE_THIRD_03 = 3;//第三方
    public static int USER_TYPE_SPREAD_04 = 4;//推广人员：组长与组员
    public static int USER_TYPE_CUSTOM_05 = 5;//注册用户

    public static int USER_ROLE_AREA_MANAGER = 5;//区域管理员

    /************************start 附件类型******************************/
    public static String ATTACH_INFO_ATTACHTYPE_1 = "t_goods_info";//1、商品

    public static String ATTACH_INFO_ATTACHTYPE_2 = "t_shop_info";//2、商家信息

    public static String ATTACH_INFO_ATTACHTYPE_5 = "t_local_new"; //5、新闻图片

    public static String ATTACH_INFO_ATTACHTYPE_11 = "t_version_update";//11、版本更新
    /************************ end 附件类型******************************/

    /**
     * 提现费率
     */
    public static BigDecimal RATE = BigDecimal.valueOf(0.006);

    //分销提成比例50%,20%,10%


    /**
     * 短信的类型
     * 0 验证码
     * 1 短信提醒
     */
    public static String SMS_TYPE_0 = "0";
    public static String SMS_TYPE_1 = "1";


    public static Integer V_MID = 16976;

    /**
     *
     */
    public static String V_KEY = "test";

    /**
     * 公钥文件地址
     */
    public static String KEY_PATH = "src/main/resources/Public1024.key";


    /**
     * 方案状态
     */
    public static String STOCK_PLAN_ORDER_STATUS_APPLY = "1";
    public static String STOCK_PLAN_ORDER_STATUS_POSITION = "2";
    public static String STOCK_PLAN_ORDER_STATUS_EXERCISE = "3";
    public static String STOCK_PLAN_ORDER_STATUS_SETTLEMENT = "4";
    public static String STOCK_PLAN_ORDER_STATUS_FAIL = "-1";


    public static final String oneMonth = "1m";
    public static final String twoMonth = "2m";
    public static final String threeMonth = "3m";
    public static final String sixMonth = "6m";
    public static final String nineMonth = "9m";
    public static final String fortityDay = "14d";


    /**
     * 支付部分的常量
     * <p>
     * 商户号：MER1000021
     * 商户名称：镇江满茂商贸有限公司
     * 商户秘钥：58e0131ca0e5525b8cc0372ce5611c76
     */
    public static final String PAYMENT_MCHNO = "MER1000021";
    public static final String PAYMENT_KEY = "58e0131ca0e5525b8cc0372ce5611c76";
    public static final String PAYMENT_FRONT_URL = "http://jnhh666.com/web/index";
    public static final String PAYMENT_NOTIFY_URL = "http://jnhh666.com/transaction/notify";

}
