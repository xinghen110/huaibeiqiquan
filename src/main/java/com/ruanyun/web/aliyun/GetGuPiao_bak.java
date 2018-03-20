package com.ruanyun.web.aliyun;

import com.ruanyun.web.util.AliKey;
import com.ruanyun.web.util.HttpUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetGuPiao_bak {

    /**
     * 功能描述：获取市场列表
     * @return
     */
    public static List getMarketList() {
        String host = "https://stock.api51.cn";
        String path = "/market/list";
        String method = "GET";
        String appcode = AliKey.GUPIAO_APP_CODE;
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //请求参数
        Map<String, String> querys = new HashMap<String, String>();

        //返回结果
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();

        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            //获取response的body
            String body = EntityUtils.toString(response.getEntity());
            JSONArray jsonArray = JSONObject.fromObject(body).getJSONArray("data");
            for (int i = 0; i < jsonArray.size(); i++) {
                try {
                    Map<String, String> map = new HashMap<String, String>();
                    JSONObject jsonObject = JSONObject.fromObject(jsonArray.get(i));
                    map.put(jsonObject.getString("finance_name"), jsonObject.getString("finance_mic"));
                    result.add(map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 功能描述：获取走势图数据
     * @param code
     * @return
     */
    public static List getZSData(String code) {
        String host = "https://stock.api51.cn";
        String path = "/stock_trend/";
        String method = "GET";
        String appcode = AliKey.GUPIAO_APP_CODE;
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);

        //请求参数
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("prod_code", code);

        List<Map<String, String>> result = new ArrayList<Map<String, String>>();

        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            //获取response的body
            String body = EntityUtils.toString(response.getEntity());
            System.out.println(body);
            //获取data数据
            JSONObject data = JSONObject.fromObject(body).getJSONObject("data");
            //获取最新闭盘价
            String preClosePx = data.getJSONObject("real").getString("pre_close_px");
            Map<String, String> linShiMap = new HashMap<String, String>();
            linShiMap.put("pre_close_px", preClosePx);
            result.add(linShiMap);
            //获取对应走势图数据
            JSONArray jsonArray = data.getJSONObject("trend").getJSONArray(code);
            //获取每个值对应的意思
            List<String> fields = (List<String>) data.getJSONObject("trend").get("fields");
            for (int i = 0; i < jsonArray.size(); i++) {
                Map<String, String> map = new HashMap<String, String>();
                JSONArray linShiData = jsonArray.getJSONArray(i);
                for (int j = 0; j < fields.size(); j++) {
                    map.put(fields.get(j), linShiData.get(j).toString());
                }
                result.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 功能描述：获取K线图数据
     * @param code
     * @param KlinType
     * @return
     */
    public static List getKlineData(String code, String KlinType) {
        String host = "https://stock.api51.cn";
        String path = "/kline";
        String method = "GET";
        String appcode = AliKey.GUPIAO_APP_CODE;
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);

        //请求参数
        Map<String, String> querys = new HashMap<String, String>();
        //K线模式	0：原始K线 1：前复权K线 2：后复权K线
        querys.put("candle_mode", "0");
        //K线周期	取值可以是数字1-9，表示含义如下： 1：1分钟K线 2：5分钟K线 3：15分钟K线 4：30分钟K线 5：60分钟K线 6：日K线 7：周K线 8：月K线 9：年K线
        querys.put("candle_period", KlinType);
        //数据个数	需要取得的 K 线的根数，如果该字段不存在，取值范围[1, 1000]，默认为 10 个。 仅在 get_type=offset 时有效。
        querys.put("data_count", "100");
        //日期	不输入默认为当前日期；请求日K线时，如果输入日期，不返回该日期的K线 get_type=offset时有效
        //querys.put("date", "date");
        //开始日期	1、 start_date 和 end_date 均不填， 返回距离当前日期的1000 根 K 线； 2、 仅填 start_date， 当 start_date和最新日期之间的数据不足1000 根，返回 start_date 和最新日期之间的数据；如果数据超过 1000 根 K 线， 则返回距离当前日期的 1000 根 K线； 3、 仅填 end_date ， 返 回end_date 之前存在的的最多1000 根 K 线； 4、 start_date 和 end_date 均填充，返回该日期区间（闭区间）的数据，最多 1000 根。 仅在 get_type=range 时有效。
        //querys.put("start_date", "start_date");
        //截止日期	默认为当前日期； 1、 start_date 和 end_date 均不填， 返回距离当前日期的1000 根 K 线； 2、 仅填 start_date， 当 start_date和最新日期之间的数据不足1000 根，返回 start_date 和最新日期之间的数据；如果数据超过 1000 根 K 线， 则返回距离当前日期的 1000 根 K线； 3、 仅填 end_date ， 返 回end_date 之前存在的的最多1000 根 K 线； 4、 start_date 和 end_date 均填充，返回该日期区间（闭区间）的数据，最多 1000 根。 仅在 get_type=range 时有效。
        //querys.put("end_date", "end_date");
        //允许的字段： 开盘价：open_px 最高价：high_px 最低价：low_px 收盘价：close_px 成交量：business_amount 成交额：business_balance 如果没有指定任何有效的字段，则返回所有字段
        //querys.put("fields", "fields");
        //offset 按偏移查找；range 按日期区间查找；必须输入其中一个值
        querys.put("get_type", "offset");
        //分时分钟时间(HHMM)	分钟 K 线的时间 HHMM,对于 短 周期 K 线 类型 使用(1min,5min 等)，不填写表示最新的市场时间，若填写必须同时填写 date 字段。请求分钟K线时，如果输入该字段，不返回输入分钟的K线 仅在 get_type=offset 且candle_period=1~5（分钟 K线）时有效。
        //querys.put("min_time", "min_time");
        //有且仅能有 1 个代码；证券代码包含交易所代码做后缀，作为该代码的唯一标识。如：000001.SS
        querys.put("prod_code", code);
        //搜索方向	1 表示向前查找（默认值） ，2 表示向后查找。 仅在 get_type=offset 时有效。
        querys.put("search_direction", "1");

        List<Map<String, String>> result = new ArrayList<Map<String, String>>();

        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            //获取response的body
            String body = EntityUtils.toString(response.getEntity());
            System.out.println(body);

            //获取data数据
            JSONObject data = JSONObject.fromObject(body).getJSONObject("data");
            //获取每个值对应的意思
            List<String> fields = (List<String>) data.getJSONObject("candle").get("fields");
            //获取对应K线图数据
            JSONArray jsonArray = data.getJSONObject("candle").getJSONArray(code);
            for (int i = 0; i < jsonArray.size(); i++) {
                Map<String, String> map = new HashMap<String, String>();
                JSONArray linShiData = jsonArray.getJSONArray(i);
                for (int j = 0; j < fields.size(); j++) {
                    map.put(fields.get(j), linShiData.get(j).toString());
                }
                result.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 阿里获取实时数据
     * @param code
     * @return
     */
    public static Map<String, String> getRealTimeData(String code) {
        String host = "https://stock.api51.cn";
        String path = "/stock/";
        String method = "GET";
        String appcode = AliKey.GUPIAO_APP_CODE;
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //请求参数
        Map<String, String> querys = new HashMap<String, String>();
        //股票代码，多个以英文逗号分隔。如000001.SZ,000002.SZ,
        querys.put("en_prod_code", code);

        Map<String, String> result = new HashMap<String, String>();

        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            System.out.println(response.toString());
            //获取response的body
            String body = EntityUtils.toString(response.getEntity());
            System.out.println(body);

            //获取data数据
            JSONObject data = JSONObject.fromObject(body).getJSONObject("data");
            //获取每个值对应的意思
            List<String> fields = (List<String>) data.getJSONObject("snapshot").get("fields");
            //获取对应实时数据
            List<String> array = data.getJSONObject("snapshot").getJSONArray(code);
            for (int i = 0; i < array.size(); i++) {
                result.put(fields.get(i), array.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
      //  getRealTimeData("600333.SH");
        getZSData("000006.SZ");
       //getKlineData("600333.SH",String.valueOf(6));
    }

}
