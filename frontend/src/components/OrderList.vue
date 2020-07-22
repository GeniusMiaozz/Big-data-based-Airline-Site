<template>
    <b-container class="small-margin" style="margin-bottom: 5em">
        <b-card header="个人订单管理" header-bg-variant="dark" header-text-variant="white" class="shadow">
            <blockquote class="blockquote mb-0">
                <div>
                    <b-table :items="items" :fields="fields" striped responsive="sm" outlined>
                        <template v-slot:cell(show_details)="row">
                            <b-button size="sm" @click="row.toggleDetails" class="mr-2">
                                {{ row.detailsShowing ? '隐藏详情' : '显示详情'}}
                            </b-button>
                        </template>

                        <template v-slot:row-details="row">
                            <b-card>

                                <b-row class="mb-2">
                                    <b-col sm="3" class="text-sm-right"><b>订单编号:</b></b-col>
                                    <b-col>{{ row.item.Order_No }}</b-col>
                                </b-row>

                                <b-row class="mb-2">
                                    <b-col sm="3" class="text-sm-right"><b>乘客编号:</b></b-col>
                                    <b-col>{{ row.item.Member_No }}</b-col>
                                </b-row>

                                <b-row class="mb-2">
                                    <b-col sm="3" class="text-sm-right"><b>机舱类型:</b></b-col>
                                    <b-col>{{ row.item.seat }}</b-col>
                                </b-row>

                                <b-row class="mb-2">
                                    <b-col sm="3" class="text-sm-right"><b>票价:</b></b-col>
                                    <b-col>{{ ~~(row.item.Price*row.item.Discount) }} ￥</b-col>
                                </b-row>

                                <b-row>
                                    <b-col sm="4" offset-sm="8" class="text-sm-center">
                                        <b-button-group>
                                            <b-button variant="dark">改签</b-button>
                                            <b-button @click="refund(row.item.Order_No)" variant="primary">退款
                                            </b-button>
                                        </b-button-group>
                                    </b-col>
                                </b-row>

                            </b-card>
                        </template>
                    </b-table>
                </div>
            </blockquote>
        </b-card>
    </b-container>
</template>

<script>
    import JSONBig from "json-bigint";

    export default {
        name: "OrderList",
        data: function () {
            return {
                fields: [
                    {
                        key: 'flight_no',
                        label: '航班号',
                        sortable: true
                    },
                    {
                        key: 'date',
                        label: '乘机日期',
                        sortable: true
                    },
                    {
                        key: 'available',
                        label: '航班状况',
                        sortable: true
                    },
                    {
                        key: 'show_details',
                        label: '显示详情',
                        sortable: false
                    }
                ],
                items: []
            }
        },

        created() {
            let _this = this;
            const url = '/api/authenticated/personal';

            this.$axios(
                {
                    method: 'get',
                    url: url,
                    params: {
                        token: window.sessionStorage.getItem('token')
                    },
                    transformResponse: [
                        function (data) {
                            return JSONBig.parse(data)
                        }
                    ]
                }
            ).then(function (response) {
                let order = response.data.order;
                console.log(order);
                let seat_types = {
                    E: '经济舱',
                    S: '超级经济舱',
                    F: '豪华舱'
                }
                let flight_state = ['待起飞', '已使用', '已退票']

                _this.items = [];
                for (let i = 0; i < order.length; i++) {
                    order[i]['available'] = flight_state[order[i]['Refund_Or_Change']]
                    order[i]['seat'] = seat_types[order[i]['seat_level']]
                    _this.items.push(order[i])
                }
            }).catch(
                function (error) {
                    console.log(error)
                }
            )
        },
        methods: {
            refund(order_number) {
                const url = "/api/authenticated/refund"
                const _this = this
                console.log(order_number)
                this.$axios.get(
                    url, {
                        params: {
                            token: window.sessionStorage.getItem('token'),
                            order_number: order_number.c[0].toString() + order_number.c[1].toString(),
                        }
                    }
                ).then(
                    function (response) {
                        console.log(response)
                        _this.$message({
                            message: "退票成功",
                            type: 'success'
                        })
                    }
                ).catch(error => console.log(error))
            }
        }
    }
</script>

<style lang="less" scoped>

</style>
