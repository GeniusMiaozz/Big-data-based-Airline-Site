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
                document.getElementById("chart0"),
                "light"
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
                myChart0.setOption({
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
                myChart1.setOption({
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
                myChart2.setOption({
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
