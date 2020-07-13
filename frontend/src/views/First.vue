<template>
    <div class="main">
        <div class="left">
            <Left/>
        </div>
        <div class="right">
            <div class="top">
                <img src="https://figure-bed-y.oss-cn-beijing.aliyuncs.com/img/20200521.jpg" alt="img">
            </div>
            <div class="button">
                <Right/>
            </div>
        </div>
    </div>
</template>

<script>
    import Left from "../components/Firsts/Left";
    import Right from "../components/Firsts/Right";
    export default {
        name: "First",
        components: {Right, Left}
    }
</script>

<style scoped>
    * {
        padding: 0;
        margin: 0;
    }
    .left{
        width: 100px;
        float: left;
        margin-right: 10px;
    }
    .right{
        width: 1000px;
        float: left;
        margin-left: 10px;
    }
    .main{
        width: 1200px;
        margin: 20px auto;
    }
    .top img{
        height: 300px;
        width: 1000px;
    }
    .left,.right{
        background-color: #f5f5f5;
        height: 500px;
        color: black;
    }

</style>
