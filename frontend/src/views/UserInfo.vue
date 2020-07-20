<template>
    <div>
        <Header :index="index" :username="username"/>
        <UserInfoCom/>
    </div>
</template>

<script>
    import Header from "../components/Header";
    import UserInfoCom from "../components/UserInfoCom";

    export default {
        name: "UserInfo",
        components: {UserInfoCom, Header},
        data() {
            return {
                index: 5,
                username: 'user_center'
            }
        }
    }
</script>

<style scoped>

</style>
