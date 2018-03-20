<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="main" style="width: 100%!important;height: 80%; border: none;margin-left: 0;">
</div>
<script type="text/javascript">

    function toGetkline(symbol){
        /* 请求一次ajax */
        $.ajax({
            type:'post',
            cache: false,
            url:"mobile/getKlineData?code="+symbol+"&KlineType=6",
            success:function(data){
                try{

//                    var jsonMsg=eval('(' + data + ')');

                    setKlineData(data);
                }catch(e){

                }
                //成功
            },
            error: function(){
                return;
            }
        });
    }


    //获取k 线图数据
    function setKlineData(jsonMsg){
        setKline(jsonMsg)
    }

    Date.prototype.Format = function (fmt) {
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "h+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));

        return fmt;
    }
    function setKline(valueStr){

        var rawData =eval(valueStr);
        var volumeResult=[];
        var dataVolumes = rawData.map(function (item) {
            var array=JSON.stringify(item).split(",");
            var item5=array[5];
            volumeResult.push(""+item5+"");
        });

        var app = echarts.init(document.getElementById('main'));
        function calculateMA(dayCount, data) {
            var result = [];
            for (var i = 0, len = data.length; i < len; i++) {
                if (i < dayCount) {
                    result.push('-');
                    continue;
                }
                var sum = 0;
                for (var j = 0; j < dayCount; j++) {
                    sum += data[i - j][1];
                }
                result.push((sum / dayCount).toFixed(5));
            }
            return result;
        }
        var dates = rawData.map(function (item) {


            var datesvalue=item.min_time;

            return datesvalue;

        });
        var data = rawData.map(function (item) {

            var high_px=item.high_px;
            var open_px=item.open_px;
            var low_px=item.low_px;
            var close_px=item.close_px;
            var business_amount=item.business_amount;

            return [ +open_px, +close_px, +high_px,+low_px,+business_amount];
        });
        var  upColor= '#00A800';
        var  downColor= '#FC4040';
        var dataLength=(60/rawData.length)*100;

        var option = {
            backgroundColor: '#21202D',
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    animation: false,
                    type: 'cross',
                    lineStyle: {
                        color: '#376df4',
                        width: 2,
                        opacity: 1
                    }
                }
            },tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross'
                },
                backgroundColor: 'rgba(245, 245, 245, 0.8)',
                borderWidth: 1,
                borderColor: '#ccc',
                padding: 10,
                textStyle: {
                    color: '#000'
                },
                position: function (pos, params, el, elRect, size) {
                    var obj = {top: 10};
                    obj[['left', 'right'][+(pos[0] < size.viewSize[0] / 2)]] = 30;
                    return obj;
                },
                extraCssText: 'width: 170px'
            },axisPointer: {  //设置可以两个联动
                link: {xAxisIndex: 'all'},
                label: {
                    backgroundColor: '#777'
                }
            },
            toolbox: {
                show:false,
                feature: {
                    dataZoom: {
                        yAxisIndex: false
                    },
                    brush: {
                        type: ['lineX', 'clear']
                    }
                }
            }, brush: {
                xAxisIndex: 'all',
                brushLink: 'all',
                outOfBrush: {
                    colorAlpha: 0.1
                }
            },visualMap: {
                show: false,
                seriesIndex: 5,
                dimension: 2,
                pieces: [{
                    value: 1,
                    color: downColor
                }, {
                    value: -1,
                    color: upColor
                }]
            },
            formatter: function (params, ticket, callback) {
                var valueArray = params[0].value;
                var res = "交易日:" + params[0].name;
                res += "<br/>开盘价:" + valueArray[1];
                res += "<br/>收盘价:" + valueArray[2];
                res += "<br/>最高:" + valueArray[3];
                res += "<br/>最低:" + valueArray[4];
                res += "<br/>交易量:" + valueArray[5];
                res += "<br/>MA5:" + params[1].value;
                res += "<br/>MA10:" + params[2].value;
                res += "<br/>MA30:" + params[3].value;
                res += "<br/>MA60:" + params[4].value;
                return res;
            },
            xAxis:[ {
                type: 'category',
                data: dates,
                axisLine: { lineStyle: { color: '#8392A5' } },
                axisLabel: {
                    formatter: function (param) {
                        return param;
                    },
                    textStyle: {color: '#000'},
                    axisPointer: {
                        z: 100
                    }
                }
            },{
                type: 'category',
                gridIndex: 1,
                data: dates,
                scale: true,
                boundaryGap: false,
                axisLine: {onZero: false},
                axisTick: {show: false},
                splitLine: {show: false},
                axisLabel: {show: false},
                splitNumber: 20,
                axisPointer: {
                    label: {
                        formatter: function (params) {
                            var seriesValue = (params.seriesData[0] || {}).value;
                            return params.value
                                + (seriesValue != null
                                        ? '\n' + echarts.format.addCommas(seriesValue)
                                        : ''
                                );
                        }
                    }
                }
            }
            ],
            yAxis: [{
                scale: true,
                axisLine: { lineStyle: { color: '#8392A5' } },
                splitLine: { show: true }
            },
                {
                    scale: true,
                    gridIndex: 1,
                    splitNumber: 2,
                    axisLabel: {show: false},
                    axisLine: {show: false},
                    axisTick: {show: false},
                    splitLine: {show: false}
                }],
            grid: [{
                left: '10%',
                right: '4%',
                height: '70%'
            },
                {
                    left: '10%',
                    right: '1%',
                    top: '83%',
                    height: '16%'
                }],
            dataZoom: [{
                type: 'inside',
                xAxisIndex: [0, 1],
                start: 100-dataLength,
                end: 100
            },
                {
                    show: true,
                    xAxisIndex: [0, 1],
                    type: 'inside',
                    start: 100-dataLength,
                    end: 100
                }],
            animation: true,
            series: [
                {
                    type: 'candlestick',
                    name: '日K',
                    data: data,
                    itemStyle: {
                        normal: {
                            color: '#FFFFFF',
                            color0: '#00A800',
                            borderColor: '#FC4040',
                            borderColor0: '#00A800'
                        }
                    }
                },
                {
                    name: 'MA5',
                    type: 'line',
                    data: calculateMA(5, data),
                    smooth: true,
                    showSymbol: false,
                    lineStyle: {
                        normal: {
                            width: 1
                        }
                    }
                },
                {
                    name: 'MA10',
                    type: 'line',
                    data: calculateMA(10, data),
                    smooth: true,
                    showSymbol: false,
                    lineStyle: {
                        normal: {
                            width: 1
                        }
                    }
                },
                {
                    name: 'MA30',
                    type: 'line',
                    data: calculateMA(30, data),
                    smooth: true,
                    showSymbol: false,
                    lineStyle: {
                        normal: {
                            width: 1
                        }
                    }
                },
                {
                    name: 'MA60',
                    type: 'line',
                    data: calculateMA(60, data),
                    smooth: true,
                    showSymbol: false,
                    lineStyle: {
                        normal: {
                            width: 1
                        }
                    }
                },
                {
                    name: 'Volume',
                    type: 'bar',
                    xAxisIndex: 1,
                    yAxisIndex: 1,
                    data: volumeResult,
                    itemStyle:{
                        normal:{color:'#ABD1F3'}
                    }
                }
            ]
        };
        app.setOption(option);
    }


</script>
