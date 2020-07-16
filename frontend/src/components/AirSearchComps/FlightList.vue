<template>
    <div class="small-margin no-padding" v-show="show" style="margin-bottom: 5em">
        <b-card style="margin-bottom: 2em" class="shadow">
            <b-row align-h="around" class="card_x" style="margin: auto">
                <b-col class="button-center">
                    <b-button variant="outline-primary" class="shadow-sm">
                        默认排序
                        <i class="fa fa-sort-amount-asc" aria-hidden="true"/>
                    </b-button>
                </b-col>
                <b-col class="button-center">
                    <b-button variant="outline-primary" class="shadow-sm">
                        起飞时间
                        <i class="fa fa-sort-amount-asc" aria-hidden="true"/>

                    </b-button>
                </b-col>
                <b-col class="button-center">
                    <b-button variant="outline-primary" class="shadow-sm">
                        航程时长
                        <i class="fa fa-sort-amount-asc" aria-hidden="true"/>
                    </b-button>
                </b-col>
                <b-col class="button-center">
                    <b-button variant="outline-primary" class="shadow-sm">
                        机票价格
                        <i class="fa fa-sort-amount-asc" aria-hidden="true"/>
                    </b-button>
                </b-col>
            </b-row>
        </b-card>

        <b-card bg-variant="light" text-variant="dark" class="card_x shadow" v-for="item in tickets" :key="item.id">

            <b-row style="margin-bottom: 1em">
                <b-col><P style="font-size: 18px;margin: 2px;color: cadetblue">{{item.Company}}|{{item.Flight_No}}|</P>
                </b-col>
            </b-row>

            <b-row>
                <b-col cols="4" style="text-align: right;font-size: 1.3em">
                    <time>{{item.Dep_Scheduled}}</time>
                </b-col>
                <b-col cols="4" style="text-align: center;font-size: 1.3em;color: brown">
                    {{item.Flight_time}}
                </b-col>
                <b-col cols="4" style="text-align: left;font-size: 1.3em">
                    <time>{{item.Arr_Scheduled}}</time>
                </b-col>
            </b-row>

            <b-row>
                <b-col cols="4" style="text-align: right;font-size: 1.5em">
                    {{item.Dep_Airport}}{{item.Dep_Terminal}}
                </b-col>
                <b-col cols="4" style="text-align: center;font-size: 1.5em">
                    <i class="fa fa-fighter-jet" aria-hidden="true"></i>
                    <i class="fa fa-fighter-jet" aria-hidden="true"></i>
                    <i class="fa fa-fighter-jet" aria-hidden="true"></i>
                </b-col>
                <b-col cols="4" style="text-align: left;font-size: 1.5em">
                    {{item.Arr_Airport}}{{item.Arr_Terminal}}
                </b-col>
            </b-row>

            <b-row style="margin-top: 1em">
                <b-col class="button-center">
                    <b-button variant="outline-success">
                        <b-row align-h="center">
                            <b-col @click="choose_ticket(0,item)">
                                <label class="price">
                                    经济舱：￥{{item.E_Price}}
                                    <b-badge variant="success" style="margin-left: 5px">{{item.E_Tickets}}</b-badge>
                                </label>
                            </b-col>
                        </b-row>
                    </b-button>
                </b-col>

                <b-col class="button-center">
                    <b-button variant="outline-success">
                        <b-row align-h="center">
                            <b-col @click="choose_ticket(1,item)">
                                <label class="price">
                                    超级经济舱：￥{{item.S_Price}}
                                    <b-badge variant="success" style="margin-left: 5px">{{item.S_Tickets}}</b-badge>
                                </label>
                            </b-col>
                        </b-row>
                    </b-button>
                </b-col>

                <b-col class="button-center">
                    <b-button variant="outline-success">
                        <b-row align-h="center">
                            <b-col @click="choose_ticket(2,item)">
                                <label class="price">
                                    头等舱：￥{{item.F_Price}}
                                    <b-badge variant="success" style="margin-left: 5px">{{item.F_Tickets}}</b-badge>
                                </label>

                            </b-col>
                        </b-row>
                    </b-button>
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
                ],
                flight_time: ''
            }
        },
        methods: {
            loadTicketsFromBackend(m) {
                const obj = this;
                const url = 'http://127.0.0.1:8080/searchforflight?'
                    + 'city_from=' + m.city_from
                    + '&city_to=' + m.city_to
                    + '&date_start=' + m.date_start;

                this.$axios.get(url).then(
                    function (res) {
                        obj.tickets = res.data['flights'];
                        obj.show = true;
                        let id = 0;
                        obj.tickets.forEach(element => {
                            element['Flight_time'] = obj.cal_flight_time(element.Arr_Scheduled, element.Dep_Scheduled);
                            element['id'] = id;
                            id++;
                        })
                    }
                ).catch(
                    function (error) {
                        console.log(error.response.status);
                        status = error.response.status;
                    }
                )
            },

            cal_flight_time(d1, d2) {
                const date1 = new Date(d1.replace(/-/g, "/"));
                const date2 = new Date(d2.replace(/-/g, "/"));
                const date3 = date1.getTime() - date2.getTime(); //时间差秒
                //计算出相差天数
                let days = Math.floor(date3 / (24 * 3600 * 1000));

                //计算出小时数
                let leave1 = date3 % (24 * 3600 * 1000);    //计算天数后剩余的毫秒数
                let hours = Math.floor(leave1 / (3600 * 1000));

                //计算相差分钟数
                let leave2 = leave1 % (3600 * 1000);       //计算小时数后剩余的毫秒数
                let minutes = Math.floor(leave2 / (60 * 1000));

                if (days > 0) {
                    return days + "天" + hours + "时" + minutes + "分"
                } else {
                    return hours + "时" + minutes + "分"
                }
            },

            choose_ticket(ticket_type, ticket_info) {
                Msg.$emit("choose_ticket",
                    {ticket_type: ticket_type, ticket_info: ticket_info})
                this.$message({
                        message: "机票选择成功",
                        type: 'success'
                    })
            }

        },
        mounted: function () {
            let _this = this;
            Msg.$on('search', function (m) {
                _this.loadTicketsFromBackend(m)
            })
        }
    }
</script>

<style lang="less" scoped>

    .price {
        margin: 5px;
    }

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
