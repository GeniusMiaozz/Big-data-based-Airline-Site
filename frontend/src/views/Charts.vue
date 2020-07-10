<template>
    <b-container style="margin-top: 2em">
        <b-row>
            <b-col style="text-align: center">
                <div id="chart" style="width: 600px;height:400px;"></div>
            </b-col>
        </b-row>
        <b-row>
            <b-col style="text-align: center">
                <div id="chart1" style="width: 600px;height:400px;"></div>
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
                var myChart = this.$echarts.init(document.getElementById("chart"));
                var myChart1 = this.$echarts.init(document.getElementById("chart1"))
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
                //
                // var option = {
                //     series: [
                //         {
                //             name: '访问来源',
                //             type: 'pie',    // 设置图表类型为饼图
                //             radius: '55%',  // 饼图的半径，外半径为可视区尺寸（容器高宽中较小一项）的 55% 长度。
                //             data: res.data_pie
                //         }
                //     ]
                // }
                // var option = {
                //     title: {
                //         text: '顾客价值分类',
                //         x: 'center'
                //     },
                //     tooltip: {},
                //     legend: {
                //         data: ['人数'],
                //         y: 'bottom'
                //     },
                //     xAxis: {
                //         data: ["低价值顾客", "一般价值顾客", "建议发展顾客", "重要发展顾客", "重要保持顾客"]
                //     },
                //     yAxis: {},
                //     series: [{
                //         name: '人数',
                //         type: 'bar',
                //         data: [5000, 20000, 36000, 10000, 10000]
                //     }],
                // };
                // myChart.setOption(option);
            }
        },
        mounted() {
            this.drawChart()
        }
    }
</script>

<style scoped>

</style>
