<template>
    <div>
        <Header :index="index" :username="username"/>
        <OrderList/>
    </div>

</template>

<script>
    import Header from "../components/Header";
    import OrderList from "../components/OrderList";

    export default {
        name: "OrderManagement",
        components:{OrderList, Header},
        data() {
            return {
                index: 4,
                username: 'orderManagement'
            }
        }
    }
</script>

<style scoped>

</style>
