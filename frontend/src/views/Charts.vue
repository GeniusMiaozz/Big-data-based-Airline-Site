<template>
    <b-container style="margin-top: 2em">
        <b-row>
            <b-col style="text-align: center">
                <div id="chart" style="width: 600px;height:400px;"></div>
            </b-col>
        </b-row>
    </b-container>
</template>

<script>
    export default {
        name: "Charts",
        methods: {
            drawChart() {
                var myChart = this.$echarts.init(document.getElementById("chart"));
                var option = {
                    title: {
                        text: '顾客价值分类',
                        x: 'center'
                    },
                    tooltip: {},
                    legend: {
                        data: ['人数'],
                        y: 'bottom'
                    },
                    xAxis: {
                        data: ["低价值顾客", "一般价值顾客", "建议发展顾客", "重要发展顾客", "重要保持顾客"]
                    },
                    yAxis: {},
                    series: [{
                        name: '人数',
                        type: 'bar',
                        data: [5000, 20000, 36000, 10000, 10000]
                    }],
                };
                myChart.setOption(option);
            }
        },
        mounted() {
            this.drawChart()
        }
    }
</script>

<style scoped>

</style>
