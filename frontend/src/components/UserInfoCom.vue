<template>
    <div>
        <b-container class="small-margin" style="margin-bottom: 2em">
            <b-card no-body class="shadow">
                <b-tabs card>
                    <b-tab title="个人中心" active>
                        <b-container>
                            <b-form @submit="onSubmit" v-if="show">
                                <b-row align-h="start">
                                    <b-col cols="6" style="margin: auto;text-align: center">
                                        <p style="font-size: 1.2em">
                                            <b-row>
                                                <b-col style="text-align: right">
                                                    电话：
                                                </b-col>
                                                <b-col style="text-align: left">
                                                    {{Telephone}}
                                                </b-col>
                                            </b-row>
                                        </p>
                                    </b-col>
                                    <b-col cols="6"/>
                                </b-row>
                                <b-row align-h="start">
                                    <b-col cols="6" style="margin: auto;text-align: center">
                                        <p style="font-size: 1.2em">
                                            <b-row>
                                                <b-col style="text-align: right">
                                                    性别：
                                                </b-col>
                                                <b-col style="text-align: left">
                                                    {{form.Gender}}
                                                </b-col>
                                            </b-row>
                                        </p>
                                    </b-col>
                                    <b-col cols="6">
                                        <b-form-radio-group
                                                style="width: 30%; text-align: center"
                                                id="radio-group-1"
                                                v-model="form.Gender"
                                                :options="options"
                                                name="radio-options"
                                        ></b-form-radio-group>
                                    </b-col>
                                </b-row>
                                <b-row align-h="start">
                                    <b-col cols="6" style="margin: auto;text-align: center">
                                        <p style="font-size: 1.2em">
                                            <b-row>
                                                <b-col style="text-align: right">
                                                    年龄：
                                                </b-col>
                                                <b-col style="text-align: left">
                                                    {{form.Age}}
                                                </b-col>
                                            </b-row>
                                        </p>
                                    </b-col>
                                    <b-col cols="6">
                                        <div>
                                            <b-form-spinbutton
                                                    style="width: 30%"
                                                    id="sb-inline"
                                                    min="10"
                                                    max="100"
                                                    v-model="form.Age"
                                                    inline></b-form-spinbutton>
                                        </div>
                                    </b-col>
                                </b-row>
                                <b-row align-h="start">
                                    <b-col cols="6" style="margin: auto;text-align: center">
                                        <p style="font-size: 1.2em">
                                            <b-row>
                                                <b-col style="text-align: right">
                                                    工作城市：
                                                </b-col>
                                                <b-col style="text-align: left">
                                                    {{form.Work_City}}
                                                </b-col>
                                            </b-row>
                                        </p>
                                    </b-col>
                                    <b-col cols="6">
                                        <b-form-input
                                                id="input-1"
                                                v-model="form.Work_City"
                                                required
                                                placeholder="输入工作城市"
                                                style="width: 30%"

                                        ></b-form-input>
                                    </b-col>
                                </b-row>
                                <b-row align-h="start">
                                    <b-col cols="6" style="margin: auto;text-align: center">
                                        <p style="font-size: 1.2em">
                                            <b-row>
                                                <b-col style="text-align: right">
                                                    工作省份：
                                                </b-col>
                                                <b-col style="text-align: left">
                                                    {{form.Work_Province}}
                                                </b-col>
                                            </b-row>
                                        </p>
                                    </b-col>
                                    <b-col cols="6">
                                        <b-form-input
                                                id="input-2"
                                                v-model="form.Work_Province"
                                                required
                                                placeholder="输入工作省份"
                                                style="width: 30%"
                                        ></b-form-input>
                                    </b-col>
                                </b-row>
                                <b-row align-h="start">
                                    <b-col cols="6" style="margin: auto;text-align: center">
                                        <p style="font-size: 1.2em">
                                            <b-row>
                                                <b-col style="text-align: right">
                                                    工作国家：
                                                </b-col>
                                                <b-col style="text-align: left">
                                                    {{form.Work_Country}}
                                                </b-col>
                                            </b-row>
                                        </p>
                                    </b-col>
                                    <b-col cols="6">
                                        <b-form-input
                                                id="input-3"
                                                v-model="form.Work_Country"
                                                required
                                                placeholder="输入工作国家"
                                                style="width: 30%"
                                        ></b-form-input>
                                    </b-col>
                                </b-row>
                                <b-row align-h="start">
                                    <b-col cols="6" style="margin: auto;text-align: center">
                                        <p style="font-size: 1.2em">
                                            <b-row>
                                                <b-col style="text-align: right">
                                                    航行次数：
                                                </b-col>
                                                <b-col style="text-align: left">
                                                    {{form.Flight_Count}}
                                                </b-col>
                                            </b-row>
                                        </p>
                                    </b-col>
                                    <b-col cols="6"/>
                                </b-row>
                                <b-row align-h="start">
                                    <b-col cols="6" style="margin: auto;text-align: center">
                                        <p style="font-size: 1.2em">
                                            <b-row>
                                                <b-col style="text-align: right">
                                                    航行总里程：
                                                </b-col>
                                                <b-col style="text-align: left">
                                                    {{points.Total_Km}} KM
                                                </b-col>
                                            </b-row>
                                        </p>
                                    </b-col>
                                    <b-col cols="6"/>
                                </b-row>
                                <b-row align-h="start">
                                    <b-col cols="6" style="margin: auto;text-align: center">
                                        <p style="font-size: 1.2em">
                                            <b-row>
                                                <b-col style="text-align: right">
                                                    剩余积分：
                                                </b-col>
                                                <b-col style="text-align: left">
                                                    {{points.Remain_Points}}
                                                </b-col>
                                            </b-row>
                                        </p>
                                    </b-col>
                                    <b-col cols="6"/>
                                </b-row>
                                <b-row align-h="start">
                                    <b-col cols="6" style="margin: auto;text-align: center">
                                        <p style="font-size: 1.2em">
                                            <b-row>
                                                <b-col style="text-align: right">
                                                    积分使用次数：
                                                </b-col>
                                                <b-col style="text-align: left">
                                                    {{points.Exchange_Count}}
                                                </b-col>
                                            </b-row>
                                        </p>
                                    </b-col>
                                    <b-col cols="6"/>
                                </b-row>

                                <b-row>
                                    <b-col sm="7" offset-sm="4" class="text-sm-center">
                                        <b-button-group>
                                            <b-button type="submit" variant="primary">
                                                更新
                                            </b-button>
                                        </b-button-group>
                                    </b-col>
                                </b-row>
                            </b-form>
                        </b-container>
                    </b-tab>
                </b-tabs>
            </b-card>
        </b-container>
    </div>
</template>

<script>
    import Msg from "../assets/axios/infoMsg.js"

    export default {
        name: "UserInfoCom",

        data() {
            return {
                form: {
                    Gender: '男',
                    Age: 21,
                    Work_City: '苏州',
                    Work_Province: '江苏',
                    Work_Country: '中国',
                    Flight_Count: 0,    //累计飞行次数
                },

                Telephone: '',
                points: {
                    Total_Km: 10,    //总里程
                    Exchange_Count: 4,
                    Remain_Points: 1000, //剩余积分
                    Points_Sum: 10000, //总积分
                },
                show: true,
                options: [
                    {text: '男', value: '男'},
                    {text: '女', value: '女'},
                ],
            }
        },

        methods: {
            onSubmit(evt) {
                evt.preventDefault()
                console.log(JSON.stringify(this.form))
                let obj = this
                const url = '/api/authenticated/updatepersonal' + '?token=' + window.sessionStorage.getItem('token')

                const params = new URLSearchParams();
                params.append("age", obj.form.Age)
                params.append("gender", obj.form.Gender)
                params.append("work_city", obj.form.Work_City)
                params.append("work_province", obj.form.Work_Province)
                params.append("work_country", obj.form.Work_Country)
                this.$axios.post(url, params).then(function (response) {
                    console.log(response)
                    obj.$message({
                        message: "成功修改个人信息",
                        type: 'success'
                    })
                }).catch(error => console.log(error))
            },
        },

        mounted() {
            let obj = this;
            Msg.$on("information", function (m) {
                obj.form = m;
            })
            Msg.$on("points", function (m) {
                obj.points = m;
            })
            Msg.$on("register", function (m) {
                obj.Telephone = m.Telephone
            })
        }
    }
</script>

<style scoped>

</style>
