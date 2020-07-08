<template>
    <div>
        <Header :index="index" :username="username"/>
        <AirSearchMain/>
    </div>
</template>

<script>
    import Header from "../components/Header";
    import AirSearchMain from "../components/AirSearchMain";

    export default {
        name: "AirSearch",
        components: {Header, AirSearchMain},
        data() {
            return {
                index: 2,
                username: 'search'
            }
        }
    }
</script>

<style scoped>

</style>
