<template>
    <b-container style="margin-top: 2em">
        <b-row>
            <b-col style="text-align: center">
                <div id="chart0" style="width: 600px;height:400px;"></div>
            </b-col>
        </b-row>
        <b-row>
            <b-col style="text-align: center">
                <div id="chart1" style="width: 600px;height:400px;"></div>
            </b-col>
        </b-row>
        <b-row>
            <b-col style="text-align: center">
                <div id="chart2" style="width: 600px;height:400px;"></div>
            </b-col>
        </b-row>
        <b-row>
            <b-col style="text-align: center">
                <div id="chart3" style="width: 600px;height:400px;"></div>
            </b-col>
        </b-row>
        <b-row>
            <b-col style="text-align: center">
                <div id="chart4" style="width: 600px;height:400px;"></div>
            </b-col>
        </b-row>
        <b-row>
            <b-col style="text-align: center">
                <div id="chart5" style="width: 600px;height:400px;"></div>
            </b-col>
        </b-row>
    </b-container>
</template>

<script>
export default {
    name: "Charts",
    methods: {
        drawChart() {
            const url = "json\\test_echarts_data.json";
            var myChart0 = this.$echarts.init(
                document.getElementById("chart0")
            );
            var myChart1 = this.$echarts.init(
                document.getElementById("chart1")
            );
            var myChart2 = this.$echarts.init(
                document.getElementById("chart2")
            );
            var myChart3 = this.$echarts.init(
                document.getElementById("chart3")
            );
            var myChart4 = this.$echarts.init(
                document.getElementById("chart4")
            );
            var myChart5 = this.$echarts.init(
                document.getElementById("chart5")
            );

            this.$axios.get(url).then(function(res) {
                var value_info = res.data.value_info;
                var age_info = res.data.age_info;
                var sex_info = res.data.sex_info;
                var ffp_info = res.data.ffp_info;
                var tier_info = res.data.tier_info;
                var flight_info = res.data.company_flight_info;

                myChart0.setOption({
                    title: {
                        text: "用户价值分类信息图",
                        x: "center",
                        y: "top"
                    },
                    legend: {
                        orient: "vertical",
                        left: "left",
                        data: [
                            "低价值客户",
                            "一般价值客户",
                            "重要发展客户",
                            "重要挽留客户",
                            "重要保持客户"
                        ]
                    },
                    series: [
                        {
                            name: "分类信息",
                            type: "pie",
                            radius: "55%",
                            data: value_info,
                            itemStyle: {
                                normal: {
                                    shadowBlur: 200,
                                    shadowColor: "rgba(0, 0, 0, 0.5)"
                                }
                            }
                        }
                    ]
                });
                myChart0.on("click", function(params) {
                    alert("类别:" + params.name);
                });
                myChart1.setOption({
                    title: {
                        text: "用户性别分布信息图",
                        x: "center",
                        y: "top"
                    },
                    legend: {
                        orient: "vertical",
                        left: "left",
                        data: ["男", "女"]
                    },
                    series: [
                        {
                            name: "性别信息",
                            type: "pie",
                            radius: "55%",
                            data: sex_info,
                            itemStyle: {
                                normal: {
                                    shadowBlur: 200,
                                    shadowColor: "rgba(0, 0, 0, 0.5)"
                                }
                            }
                        }
                    ]
                });
                myChart1.on("click", function(params) {
                    alert("性别:" + params.name);
                });
                myChart2.setOption({
                    title: {
                        text: "用户年龄分布信息图",
                        x: "center",
                        y: "top"
                    },
                    legend: {
                        orient: "vertical",
                        left: "left",
                        data: [
                            "26岁",
                            "27岁",
                            "28岁",
                            "29岁",
                            "30岁",
                            "31岁",
                            "32岁"
                        ]
                    },
                    series: [
                        {
                            name: "年龄信息",
                            type: "pie",
                            radius: "55%",
                            data: age_info,
                            itemStyle: {
                                normal: {
                                    shadowBlur: 200,
                                    shadowColor: "rgba(0, 0, 0, 0.5)"
                                }
                            }
                        }
                    ]
                });
                myChart2.on("click", function(params) {
                    alert("年龄:" + params.name);
                });
                myChart3.setOption({
                    title: {
                        text: "会员入会年份分布图",
                        x: "center",
                        y: "top"
                    },
                    legend: {
                        orient: "vertical",
                        left: "left",
                        data: ["年份"]
                    },
                    xAxis: {
                        data: ffp_info.ffp_date
                    },
                    yAxis: {},
                    dataZoom: [
                        {
                            type: "slider", // 这个 dataZoom 组件是 slider 型 dataZoom 组件
                            start: 10, // 左边在 10% 的位置。
                            end: 60 // 右边在 60% 的位置。
                        }
                    ],
                    series: [
                        {
                            name: "年份",
                            type: "bar",
                            data: ffp_info.count,
                            itemStyle: {
                                normal: {
                                    shadowBlur: 200,
                                    shadowColor: "rgba(0, 0, 0, 0.5)"
                                }
                            }
                        }
                    ]
                });
                myChart3.on("click", function(params) {
                    alert("年份:" + params.name);
                });
                myChart4.setOption({
                    title: {
                        text: "会员等级信息分布图",
                        x: "center",
                        y: "top"
                    },
                    legend: {
                        orient: "vertical",
                        left: "left",
                        data: [
                            "等级1",
                            "等级2",
                            "等级3",
                            "等级4",
                            "等级5",
                            "等级6",
                            "等级7"
                        ]
                    },
                    series: [
                        {
                            name: "等级",
                            type: "pie",
                            data: tier_info,
                            itemStyle: {
                                normal: {
                                    shadowBlur: 200,
                                    shadowColor: "rgba(0, 0, 0, 0.5)"
                                }
                            }
                        }
                    ]
                });
                myChart4.on("click", function(params) {
                    alert("等级:" + params.name);
                });
                myChart5.setOption({
                    title: {
                        text: "公司航班数量信息分布图",
                        x: "center",
                        y: "top"
                    },
                    legend: {
                        orient: "vertical",
                        left: "left",
                        data: [flight_info.company]
                    },
                    xAxis: {
                        data: flight_info.company
                    },
                    yAxis: {},
                    dataZoom: [
                        {
                            type: "slider", // 这个 dataZoom 组件是 slider 型 dataZoom 组件
                            start: 10, // 左边在 10% 的位置。
                            end: 60 // 右边在 60% 的位置。
                        }
                    ],
                    series: [
                        {
                            name: "等级",
                            type: "bar",
                            data: flight_info.flight_num,
                            itemStyle: {
                                normal: {
                                    shadowBlur: 200,
                                    shadowColor: "rgba(0, 0, 0, 0.5)"
                                }
                            }
                        }
                    ]
                });
                myChart5.on("click", function(params) {
                    alert("公司:" + params.name);
                });
            });
        }
    },
    mounted() {
        this.drawChart();
    }
};
</script>

<style scoped>
</style>
