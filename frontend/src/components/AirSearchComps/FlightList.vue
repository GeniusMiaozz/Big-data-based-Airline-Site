<template>
    <div class="small-margin no-padding" v-show="show">
        <b-row align-h="around" class="card_x">
            <b-col class="button-center">
                <b-button variant="outline-primary">默认排序</b-button>
            </b-col>
            <b-col class="button-center">
                <b-button variant="outline-primary">起飞时间</b-button>
            </b-col>
            <b-col class="button-center">
                <b-button variant="outline-primary">航程时长</b-button>
            </b-col>
            <b-col class="button-center">
                <b-button variant="outline-primary">机票价格</b-button>
            </b-col>
        </b-row>

        <b-card bg-variant="light" text-variant="dark" class="card_x" v-for="item in tickets" :key="item.Flight_No">
            <b-row style="margin-bottom: 1em">
                <b-col><P style="font-size: 15px;margin: 2px">{{item.Company}}|{{item.Flight_No}}|</P></b-col>
            </b-row>
            <b-row>
                <b-col cols="4" style="text-align: right">
                    <time>{{item.Dep_Scheduled}}</time>
                </b-col>
                <b-col cols="4" style="text-align: center">
                    <!--                    {{item.flight_time}}-->
                </b-col>
                <b-col cols="4" style="text-align: left">
                    <time>{{item.Arr_Scheduled}}</time>
                </b-col>
            </b-row>
            <b-row>
                <b-col cols="4" style="text-align: right">
                    {{item.Dep_Airport}}
                </b-col>
                <b-col cols="4" style="text-align: center">
                    <img src="../../../public/imgs/plane-logo.png" alt="logo" width="31px">
                </b-col>
                <b-col cols="4" style="text-align: left">
                    {{item.Arr_Airport}}
                </b-col>
            </b-row>
            <b-row style="margin-top: 1em">
                <b-col class="button-center">
                    <b-button variant="outline-success">经济舱：￥{{item.E_Price}}</b-button>
                </b-col>
                <b-col class="button-center">
                    <b-button variant="outline-success">超级经济舱：￥{{item.S_Price}}</b-button>
                </b-col>
                <b-col class="button-center">
                    <b-button variant="outline-success"> 公务/头等舱：￥{{item.F_Price}}</b-button>
                </b-col>
            </b-row>
        </b-card>
    </div>
</template>

<script>
    import Msg from "./msg.js"

    export default {
        name: "FlightList",
        data() {
            return {
                show: false,
                tickets: [
                    {
                        "Company": "东方航空",
                        "Flight_No": "MU201",
                        "Date": "2020-07-11",
                        "Dep_Ct": "上海",
                        "Dep_Airport": "上海浦东国际机场",
                        "Arr_Ct": "伦敦",
                        "Arr_Airport": "伦敦盖特威克机场",
                        "Dep_Terminal": "T1",
                        "Arr_Terminal": "N",
                        "Dep_Scheduled": "2020-07-11 01:15:00",
                        "Arr_Scheduled": "2020-07-11 06:40:00",
                        "E_Price": 10000,
                        "E_Tickets": 100,
                        "E_Discount": 0.9,
                        "S_Price": 12000,
                        "S_Tickets": 50,
                        "S_Discount": 0.83,
                        "F_Price": 15000,
                        "F_Tickets": 30,
                        "F_Discount": 0.7
                    }
                ]
            }
        },
        methods: {
            loadTicketsFromBackend(m) {
                const obj = this;
                const url = 'http://127.0.0.1:8080/searchforflight?' + 'city_from=' + m.city_from + '&city_to=' + m.city_to + '&date_start=' + m.date_start
                this.$axios.get(url).then(
                    function (res) {
                        obj.tickets = res.data.flights
                        console.log(res.data)
                        obj.show = true;
                    }
                ).catch(
                    function (error) {
                        console.log(error.response.status)
                        status = error.response.status
                    }
                )
            }
        },
        mounted: function () {
            var _this = this
            Msg.$on('val', function (m) {
                _this.loadTicketsFromBackend(m)
            })
        }
    }
</script>

<style lang="less" scoped>
    .small-margin {
        margin-top: 1em;
    }

    .no-padding {
        padding: 0 !important;
    }

    .card_x {
        margin-bottom: 1em;
    }

    .button-center {
        text-align: center;
    }
</style>
