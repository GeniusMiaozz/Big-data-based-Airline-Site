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
                                    <b-col sm="3" class="text-sm-right"><b>乘机人:</b></b-col>
                                    <b-col>{{ row.item.username }}</b-col>
                                </b-row>

                                <b-row class="mb-2">
                                    <b-col sm="3" class="text-sm-right"><b>Is Active:</b></b-col>
                                    <b-col>{{ row.item.isActive }}</b-col>
                                </b-row>
                                <b-row>
                                    <b-col sm="4" offset-sm="8" class="text-sm-center">
                                        <b-button-group>
                                            <b-button variant="dark">改签</b-button>
                                            <b-button variant="primary">删除</b-button>
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
    export default {
        name: "OrderList",
        data() {
            return {
                fields: [
                    {
                        key: 'id',
                        label: '编号',
                        sortable: true
                    },
                    {
                        key: 'flight_no',
                        label: '航班号',
                        sortable: true
                    },
                    {
                        key: 'company',
                        label: '航空公司',
                        sortable: true
                    },
                    {
                        key: 'date',
                        label: '购票日期',
                        sortable: true
                    },
                    {
                        key: 'show_details',
                        label: '显示详情',
                        sortable: false
                    }

                ],
                items: [
                    {
                        id: 0,
                        flight_no: 'MU201',
                        company: '东方航空',
                        isActive: true,
                        username: '小明',
                        date: '2020-2-10 10:00:00'
                    },
                    {
                        id: 1,
                        flight_no: 'MU201',
                        company: '东方航空',
                        isActive: false,
                        username: '小红',
                        date: '2020-2-11 10:11:11'
                    },
                    {
                        id: 2,
                        flight_no: 'MU201',
                        company: '东方航空',
                        isActive: false,
                        username: '小刚',
                        date: '2020-2-12 10:00:00'
                    },
                    {
                        id: 3,
                        flight_no: 'MU201',
                        company: '东方航空',
                        isActive: true,
                        username: '小花',
                        date: '2020-2-13 10:00:00'
                    }
                ]
            }
        }
    }
</script>

<style lang="less" scoped>

</style>
