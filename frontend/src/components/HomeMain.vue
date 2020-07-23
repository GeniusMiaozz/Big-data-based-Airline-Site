<template>
    <b-container class="small-margin">
        <!--几个最受欢迎的地点-->
        <b-row>
            <div class="slide-x shadow" style="width: 100%">
                <b-carousel
                        id="carousel-1"
                        v-model="slide"
                        :interval="4000"
                        controls
                        indicators
                        background="#ababab"
                        img-width="1024"
                        img-height="480"
                        style="text-shadow: 1px 1px 2px #333;"
                        @sliding-start="onSlideStart"
                        @sliding-end="onSlideEnd"
                >
                    <b-carousel-slide v-for="item in slideList"
                                      v-bind:caption="item.caption"
                                      v-bind:text="item.text"
                                      v-bind:img-src="item.img" :key="item.id"
                    ></b-carousel-slide>

                </b-carousel>
            </div>
        </b-row>

        <!--特价机票-->
        <b-row class="recommend">
            <h2>
                <b-badge variant="info" style="font-weight: normal">机票推荐</b-badge>
            </h2>
        </b-row>
        <b-row class="recommend">
            <b-card-group columns>
                <b-card bg-variant="light" class="shadow-sm" text-variant="black" v-for="item in ticket_rec"
                        :key="item.Id">
                    <blockquote class="card-blockquote" style="margin: auto">
                        <b-container>
                            <b-row align-h="start">
                                <b-col cols="8">
                                    <p style="font-size: 18px;font-weight: bold;">
                                        {{item.FromCity}}
                                        <i class="fa fa-fighter-jet" aria-hidden="true"></i>
                                        {{item.ToCity}}
                                    </p>
                                </b-col>
                                <b-col cols="4">
                                    <p class="card-font">
                                        {{~~(item.Price*item.Discount)}}￥

                                    </p>
                                </b-col>
                            </b-row>
                            <b-row align-h="center">
                                <b-col>
                                    {{item.Date}}
                                </b-col>
                            </b-row>
                        </b-container>
                    </blockquote>
                </b-card>
                <b-card bg-variant="light" class="shadow-sm" text-variant="black">
                    <blockquote class="card-blockquote" style="margin: auto">
                        <b-container>
                            <b-row align-h="start">
                                <b-col cols="8">
                                    <p style="font-size: 18px;font-weight: bold;">
                                        xx
                                        <i class="fa fa-fighter-jet" aria-hidden="true"></i>
                                        xx
                                    </p>
                                </b-col>
                                <b-col cols="4">
                                    <p class="card-font">
                                        xxx￥
                                    </p>
                                </b-col>
                            </b-row>
                            <b-row align-h="center">
                                <b-col>
                                    xxxx-xx-xx
                                </b-col>
                            </b-row>
                        </b-container>
                    </blockquote>
                </b-card>
            </b-card-group>
        </b-row>


        <b-row class="recommend">
            <h2>
                <b-badge variant="primary" style="font-weight: normal">景点推荐</b-badge>
            </h2>
        </b-row>
        <b-row class="recommend">
            <b-card-group columns>
                <b-card bg-variant="primary" text-variant="white" v-for="item in site_rec" :key="item"
                        style="text-align: center">
                    <p style="margin: auto">{{item}}</p>
                </b-card>
                <b-card bg-variant="primary" text-variant="white"
                        style="text-align: center">
                    <p style="margin: auto">xxx</p>
                </b-card>
            </b-card-group>
        </b-row>
        <b-row class="recommend">
            <b-card-group columns>
                <div v-for="(item,key) in online_rec" :key="key">
                    <b-card class="shadow-sm"
                            :title=key
                            :img-src=item.picture
                            img-top>
                        <b-card-text>
                            {{item.introduction}}
                        </b-card-text>
                    </b-card>
                    <b-card header="气候">
                        <blockquote class="blockquote mb-0">
                            <div v-for="value in item.climate">
                                {{value}}
                            </div>
                        </blockquote>
                    </b-card>
                    <b-card header="旅行信息">
                        <blockquote class="blockquote mb-0">
                            <div v-for="value in item.travel">{{value}}</div>
                        </blockquote>
                    </b-card>
                </div>
            </b-card-group>
        </b-row>
    </b-container>

</template>

<script>
    export default {
        name: "HomeMain",
        data() {
            return {
                slide: 0,
                sliding: null,
                slideList: [],
                cheapFlights: [],
                site_rec: [],
                ticket_rec: [],
                online_rec: {},
            }
        },
        methods: {
            onSlideStart() {
                this.sliding = true
            },

            onSlideEnd() {
                this.sliding = false
            },

            loadCheepFlights() {
                const obj = this;
                const url2 = "json\\slidelist.json";
                this.$axios.get(url2).then(
                    function (res) {
                        obj.slideList = res.data;
                    }
                )

                // 地点推荐
                const url_1 = "/api/authenticated/siterecommend"  //地点推荐
                this.$axios.get(url_1,
                    {
                        params: {
                            token: window.sessionStorage.getItem('token')
                        }
                    }
                ).then(function (response) {
                    // console.log(response.data)
                    let site_rec = response.data.site_rec;
                    let s1 = new Set()
                    site_rec.forEach(item => s1.add(item.ATTRACTION))
                    obj.site_rec = s1
                    obj.site_rec.forEach(item => console.log(item))
                })

                // 机票推荐
                const url_2 = "/api/authenticated/ticketrecommend"
                this.$axios.get(url_2, {
                    params: {
                        token: window.sessionStorage.getItem('token')
                    }
                }).then(function (response) {
                    obj.ticket_rec = response.data.ticket_rec;
                })
            }
        },
        mounted() {
            this.loadCheepFlights();
            let obj = this;
            let url = "json\\rec.json"
            this.$axios.get(url).then(function (response) {
                console.log(response)
                obj.online_rec = response.data.guide
            }).catch(error => console.log(error))
        }
    }

</script>

<style scoped>
    .recommend, .slide-x {
        margin-bottom: 10px;
    }

    .small-margin {
        padding: 0 2em;
    }

    .card-font {
        font-size: 18px;
        font-weight: bold;
    }

</style>
