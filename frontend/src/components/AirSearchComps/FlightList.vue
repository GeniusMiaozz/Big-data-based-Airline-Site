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

        <b-card bg-variant="light" text-variant="dark" class="card_x" v-for="item in tickets" :key="item.id">
            <b-row style="margin-bottom: 1em">
                <b-col><P style="font-size: 15px;margin: 2px">{{item.airline}}|{{item.flight_number}}|</P></b-col>
            </b-row>
            <b-row>
                <b-col cols="4" style="text-align: right">
                    <time>{{item.departure_time}}</time>
                </b-col>
                <b-col cols="4" style="text-align: center">
                    {{item.flight_time}}
                </b-col>
                <b-col cols="4" style="text-align: left">
                    <time>{{item.landing_time}}</time>
                </b-col>
            </b-row>
            <b-row>
                <b-col cols="4" style="text-align: right">
                    {{item.departure_airport}}
                </b-col>
                <b-col cols="4" style="text-align: center">
                    <img src="../../../public/imgs/plane-logo.png" alt="logo" width="31px">
                </b-col>
                <b-col cols="4" style="text-align: left">
                    {{item.landing_airport}}
                </b-col>
            </b-row>
            <b-row style="margin-top: 1em">
                <b-col class="button-center">
                    <b-button variant="outline-success">经济舱：￥{{item.economy_class}}</b-button>
                </b-col>
                <b-col class="button-center">
                    <b-button variant="outline-success">超级经济舱：￥{{item.s_economy_class}}</b-button>
                </b-col>
                <b-col class="button-center">
                    <b-button variant="outline-success"> 公务/头等舱：￥{{item.first_class}}</b-button>
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
                tickets: []
            }
        },
        methods: {
            loadTickets() {
                const obj = this;
                const url = "json\\ticketsList.json";
                this.$axios.get(url).then(
                    function (res) {
                        obj.tickets = res.data;
                        obj.show = true;
                    }
                )
            }
        },
        mounted: function () {
            var _this = this
            Msg.$on('val', function (m) {
                _this.loadTickets()
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
