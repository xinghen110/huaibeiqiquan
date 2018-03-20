<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id='zs_line' style="width: 100%!important;height: 80%;">
</div>

<script type="text/javascript">

    // 数据意义：开盘(open)，收盘(close)，最低(lowest)，最高(highest)
    var options = {
        backgroundColor: '#21202D',
        legend: {
            data: [],
            inactiveColor: '#777',
            textStyle: {
                color: '#fff'
            }
        },
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
        },
        xAxis: [{
            type: 'category',
            data: ['1', '2', '3', '4', '5', '2', '3', '4', '5'],//走势 x轴
            axisLine: {lineStyle: {color: '#8392A5'}},
            axisLabel: {
                formatter: function (param) {
                    return param;
                },
                textStyle: {color: '#525C6A'},
            }
        }, {
            type: 'category',
            gridIndex: 1,
            data: ['1', '2', '3', '4', '5', '2', '3', '4', '5'],//柱状x轴
            scale: false,
            boundaryGap: false,
            splitNumber: 20,
        }
        ],
        yAxis: [{
            scale: true,
            axisLine: {lineStyle: {color: '#525C6A'}},
            splitLine: {
                show: true,
                lineStyle: {
                    color: '#525C6A',//网格线颜色
                    width: 1,//网格线宽度
                    type: 'dashed'//网格线样式

                }

            }
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
            left: '8%',
            right: '1%',
            height: '60%',
            bottom: '30%'
        },
            {
                left: '10%',
                right: '1%',
                top: '76%',
                height: '10%'
            }],
        dataZoom: [{
            type: 'inside',
            xAxisIndex: [0, 1],
            start: 0,
            end: 100
        },
            {
                show: true,
                xAxisIndex: [0, 1],
                type: 'inside',
                start: 0,
                end: 100
            }],
        animation: true,
        series: [

            {
                name: '最新价',
                type: 'line',
                data: ['1', '2', '3', '4', '5', '2', '3', '4', '5'],//走势图data
                smooth: true,
                showSymbol: false,
                itemStyle: {
                    normal: {
                        color: '#389BB7'//'#2D6FEE'
                    }
                },
                areaStyle: {
                    normal: {
                        color: 'RGBA(255,255,255,0.1)'//'#ABD1F3'
                    }
                },
            },
            {
                name: '数量',
                type: 'bar',
                xAxisIndex: 1,
                yAxisIndex: 1,
                data: ['1', '2', '3', '4', '5', '2', '3', '4', '5'],//柱状图data
                itemStyle: {
                    normal: {color: '#389BB7'}
                }
            }
        ]
    };

    //定时加载走势图数据
    function loadZSData(symbol) {

        var url = "mobile/getZSData?productCode=" + symbol;



        $.ajax({
            type: 'post',
            cache: false,
            url: url,
            success: function (data) {

                options.series[0].data = [];
                options.series[1].data = [];
                options.xAxis[0].data = [];
                options.xAxis[1].data = [];

                if (JSON.stringify(data) == '[]') {
                    return;
                }

                var preClosePrice = data[0].pre_close_px;
                var max = Number(preClosePrice);
                var min = Number(preClosePrice);

                for (var i = 1; i < data.length; i++) {

                    $.each(data[i], function (key, value) {
                        if (key == "min_time") {
                            var date = value.substring(8, 10) + ":" + value.substring(10, 12);
                            options.xAxis[0].data.push("" + date + "");
                            options.xAxis[1].data.push("" + date + "");
                        }
                        if (key == "last_px") {
                            if (max < Number(value)) {
                                max = Number(value);
                            }
                            if (min > Number(value)) {
                                min = Number(value);
                            }
                            options.series[0].data.push("" + value + "");
                        }
                        if (key == "avg_px") {}
                        if (key == "business_amount") {
                            options.series[1].data.push("" + value + "");
                        }
                        if (key == "business_balance") {}
                    })


                }
                var cha = (max - min) * 0.5;
                if (cha > 0) {
                    options.yAxis[0].max = (max + cha).toFixed(1);
                    options.yAxis[0].min = (min - cha).toFixed(1);
                } else {
                    options.yAxis[0].max = (max + max * 0.1).toFixed(0);
                    options.yAxis[0].min = (min + min * 0.1).toFixed(0);
                }


                /*基于准备好的dom，初始化echarts实例*/
                var myChart = echarts.init(document.getElementById('zs_line'));

                myChart.setOption(options, true);

                //显示走势图
                // $("#main").parent().hide();
                // $("#zs_line").parent().show();

            },
            error: function () {
                //alert("系统未知错误！") 
                return;
            }
        });

    }


</script>
