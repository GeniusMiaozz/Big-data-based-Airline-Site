<template>
    <div class="small-margin no-padding">
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

        <b-card bg-variant="light" text-variant="dark" class="card_x">
            <b-row style="margin-bottom: 1em">
                <b-col><P style="font-size: 15px;margin: 2px">东方航空|MU5104|</P></b-col>
            </b-row>
            <b-row>
                <b-col cols="4" style="text-align: right">
                    <time>09:00</time>
                </b-col>
                <b-col cols="4" style="text-align: center">
                    02小时15分钟
                </b-col>
                <b-col cols="4" style="text-align: left">
                    <time>11:15</time>
                </b-col>
            </b-row>
            <b-row>
                <b-col cols="4" style="text-align: right">
                    首都机场T2
                </b-col>
                <b-col cols="4" style="text-align: center">
                    <img src="../../../public/imgs/plane-logo.png" alt="logo" width="31px">
                </b-col>
                <b-col cols="4" style="text-align: left">
                    虹桥国际机场T2
                </b-col>
            </b-row>
            <b-row style="margin-top: 1em">
                <b-col class="button-center">
                    <b-button variant="outline-success">经济舱：￥1,010</b-button>
                </b-col>
                <b-col class="button-center">
                    <b-button variant="outline-success">超级经济舱：￥--</b-button>

                </b-col>
                <b-col class="button-center">
                    <b-button variant="outline-success"> 公务/头等舱：￥3,090</b-button>
                </b-col>
            </b-row>
        </b-card>
        <b-card bg-variant="light" text-variant="dark" class="card_x">
            <b-card-text>
                机票样式待设计
            </b-card-text>
        </b-card>
    </div>
</template>

<script>
    export default {
        name: "FlightList"
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
