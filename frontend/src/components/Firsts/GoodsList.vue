<template>
    <div name="show">
        <ul>
            <li v-for="good in list">
                <img v-bind:src="good.img">
                <p>{{good.goodName}}</p>
            </li>
        </ul>
    </div>
</template>

<script>
    export default {
        name: "GoodsList",
        data() {
            return {
                list: []
            }
        },
        props: {
            goodId: Number
        },
        watch: {
            goodId() {
                var obj = this;
                var url = "";
                if (obj.goodId == 1) {
                    url = "json\\goods.json"
                } else if (obj.goodId == 2) {
                    url = "json/goods1.json"
                }
                this.$axios.get(url).then(
                    function (res) {
                        obj.list = res.data;
                    }
                )
            }
        }
    }
</script>

<style scoped>
    li {
        width: 200px;
        height: 200px;
        list-style: none;
        float: left;
        font-size: 9px;
        color: red;
    }
</style>
