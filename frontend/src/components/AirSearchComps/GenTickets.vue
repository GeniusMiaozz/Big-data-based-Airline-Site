<template>
    <div>
        <b-card
                class="shadow-sm"
                border-variant="info"
                header="机票信息"
                header-text-variant="info"
                align="center"
                v-show="ticket_display"
                style="margin-bottom: 2em">
            <b-row style="margin-bottom: 1em">
                <b-col><P style="font-size: 20px;margin: 2px;color: cadetblue">
                    {{ticket_info_normal.Company}}|{{ticket_info_normal.Flight_No}}</P>
                </b-col>
                <b-col>
                    <P style="font-size: 20px;margin: 2px;color: brown">
                        {{ticket_type_name}}：￥{{~~(ticket_price*ticket_discount)}}
                    </P>
                </b-col>
            </b-row>

            <b-row>
                <b-col cols="4" style="text-align: right;font-size: 1.1em">
                    <time>{{ticket_info_normal.Dep_Scheduled}}</time>
                </b-col>
                <b-col cols="4" style="text-align: center;font-size: 1.3em;color: brown">
                    {{ticket_info_normal.Flight_time}}
                </b-col>
                <b-col cols="4" style="text-align: left;font-size: 1.1em">
                    <time>{{ticket_info_normal.Arr_Scheduled}}</time>
                </b-col>
            </b-row>

            <b-row>
                <b-col cols="4" style="text-align: right;font-size: 1.3em">
                    {{ticket_info_normal.Dep_Airport}}{{ticket_info_normal.Dep_Terminal}}
                </b-col>
                <b-col cols="4" style="text-align: center;font-size: 1.3em">
                    <i class="fa fa-fighter-jet" aria-hidden="true"></i>
                    <i class="fa fa-fighter-jet" aria-hidden="true"></i>
                    <i class="fa fa-fighter-jet" aria-hidden="true"></i>
                </b-col>
                <b-col cols="4" style="text-align: left;font-size: 1.3em">
                    {{ticket_info_normal.Arr_Airport}}{{ticket_info_normal.Arr_Terminal}}
                </b-col>
            </b-row>
        </b-card>

        <b-card
                class="shadow-sm"
                border-variant="secondary"
                v-show="user_display"
                header="乘客信息"
                header-border-variant="secondary"
                align="center"
                style="margin-bottom: 2em"
        >
            <b-table :items="items" :fields="fields" striped responsive="sm">
                <template v-slot:cell(delete)="row">
                    <b-button size="sm" class="mr-2" variant="danger" @click="delete_p(row.item.index)">
                        删除
                    </b-button>
                </template>
            </b-table>
        </b-card>
        <b-card
                class="shadow-sm"
                border-variant="danger"
                header="账单"
                header-border-variant="danger"
                align="center"
                v-show="bill_display"
        >
            <b-row align-h="start">
                <b-col cols="6" style="font-size: 1.3em;font-weight: bold;margin: auto">
                    总收费：
                    {{ticket_price}} X {{ticket_discount}} X {{num}} = {{~~(ticket_price*ticket_discount*num)}} ￥
                </b-col>
                <b-col style="margin: auto">
                    <b-button variant="primary" @click="SubmitOrders">提交订单</b-button>
                </b-col>
            </b-row>
        </b-card>
    </div>


</template>

<script>
    import Msg from './msg'

    export default {
        name: "GenTickets",
        data() {
            return {
                ticket_display: false,
                user_display: false,
                ticket_info_normal:
                    {
                        "Company": "",
                        "Flight_No": "",
                        "Date": "",
                        "Dep_Ct": "",
                        "Dep_Airport": "",
                        "Arr_Ct": "",
                        "Arr_Airport": "",
                        "Dep_Terminal": "",
                        "Arr_Terminal": "",
                        "Dep_Scheduled": "",
                        "Arr_Scheduled": "",
                        "Flight_time": '',
                    },
                ticket_type_name: null,
                ticket_price: 0,
                ticket_discount: 1,
                ticket_type_name_x: 'E',
                num: 0,

                ticket_type_normal: 0,
                fields: [
                    {
                        key: 'name',
                        label: '乘客姓名',
                    },
                    {
                        key: 'id_card',
                        label: '乘客身份证号'
                    },
                    {
                        key: 'delete',
                        label: '操作',
                        sortable: false
                    }
                ],
                bill_display: false,

                items: null,
            }
        },
        methods: {
            delete_p(index) {
                if (this.items.length > 1) {
                    this.items.splice(index, 1);
                    this.num--;
                }
            },
            SubmitOrders() {
                const _this = this;
                const params = new URLSearchParams();
                params.append('token', window.sessionStorage.getItem('token'))
                let data = []
                for (let i = 0; i < this.num; i++) {
                    data.push(
                        {
                            flight_no: _this.ticket_info_normal.Flight_No,
                            date: _this.ticket_info_normal.Dep_Scheduled.split(' ')[0],
                            seat_level: _this.ticket_type_name_x,
                        }
                    )
                }

                this.$axios(
                    {
                        method: 'post',
                        url: '/api/authenticated/book',
                        params: params,
                        header: {
                            'Content-Type': 'application/json',
                        },
                        data: data,
                    }
                ).then(function (response) {
                    console.log(response);
                    _this.$message({
                        message:"成功订购"+_this.num+"张票",
                        type: 'success'
                    })
                }).catch(
                    error => console.log(error)
                );
            }
        },
        mounted: function () {
            const _this = this;
            Msg.$on('choose_ticket', function (m) {
                _this.ticket_info_normal = m.ticket_info
                _this.ticket_type_normal = m.ticket_type
                let typeList = ["经济舱", "超级经济舱", "头等舱"]
                let priceList = [_this.ticket_info_normal['E_Price'], _this.ticket_info_normal['S_Price'], _this.ticket_info_normal['F_Price']]
                let discountList = [_this.ticket_info_normal['E_Discount'], _this.ticket_info_normal['S_Discount'], _this.ticket_info_normal['F_Discount']]
                let types_s = ['E', 'S', 'F']
                _this.ticket_type_name = typeList[m.ticket_type]
                _this.ticket_price = priceList[m.ticket_type];
                _this.ticket_discount = discountList[m.ticket_type]
                _this.ticket_display = true
                _this.ticket_type_name_x = types_s[m.ticket_type]
                _this.bill_display = _this.user_display && _this.ticket_display
            });
            Msg.$on('user_info', function (m) {
                _this.items = []
                _this.num = m.nums
                for (let i = 0; i < m.nums; i++) {
                    var tmp = {
                        index: i,
                        name: m.names[i],
                        id_card: m.id_cards[i],
                    }
                    _this.items.push(tmp)
                }
                _this.user_display = true
                _this.bill_display = _this.user_display && _this.ticket_display
            })
        }
    }
</script>

<style scoped>

</style>
