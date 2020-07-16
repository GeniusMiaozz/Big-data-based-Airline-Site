<template>
    <b-container style="margin-top: 2em">
        <b-row>
            <b-col style="text-align: center">
                <div ref="chart" style="width: 600px;height:400px;"></div>
            </b-col>
        </b-row>
        <b-row>
            <b-col style="text-align: center">
                <div ref="chart1" style="width: 600px;height:400px;"></div>
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
                var myChart = this.$echarts.init(this.$refs.chart);
                var myChart1 = this.$echarts.init(this.$refs.chart1)
                this.$axios.get(url).then(
                    function (res) {
                        var age_info = res.data.age_info;
                        var data_x = res.data.data;
                        var sex_info = res.data.sex_info;
                        console.log(age_info);
                        console.log(data_x);
                        console.log(sex_info);

                        myChart.setOption(
                            {
                                series: [
                                    {
                                        name: '性别分布',
                                        type: 'pie',
                                        radius: '55%',
                                        data: sex_info
                                    }
                                ]
                            }
                        );
                        myChart.on('updateAxisPointer', function (event) {
                            let xAxisInfo = event.axesInfo[0];
                            if (xAxisInfo) {
                                let dimension = xAxisInfo.value + 1;
                            }
                        })
                        myChart1.setOption(
                            {
                                series: [
                                    {
                                        name: '年龄分布',
                                        type: 'pie',
                                        radius: '55%',
                                        data: age_info
                                    }
                                ]
                            }
                        )

                    }
                )
                this.$axios.get("https://lab.isaaclin.cn/nCoV/api/area?latest=1").then(
                    function (res) {
                        console.log(res)
                    }
                )
            }
        },
        mounted() {
            this.drawChart()
        }
    }
</script>

<style scoped>

</style>
