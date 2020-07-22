<template>
    <div>
        <Header :index="index" :username="username"/>
        <UserInfoCom/>
        <OrderList/>
    </div>
</template>

<script>
    import Header from "../components/Header";
    import OrderList from "../components/OrderList";
    import UserInfoCom from "../components/UserInfoCom";
    import JSONBig from "json-bigint";
    import Msg from "../assets/axios/infoMsg.js"

    export default {
        name: "OrderManagement",
        components: {UserInfoCom, OrderList, Header},
        data() {
            return {
                index: 4,
                username: 'orderManagement',
            }
        },
        created() {
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
                let information = response.data.information;
                let points = response.data.points;
                let register = response.data.register;
                Msg.$emit("order", order)
                Msg.$emit("information", information)
                Msg.$emit("points", points)
                Msg.$emit("register", register)
            })
        }
    }
</script>

<style scoped>

</style>
