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
            </b-card-group>
        </b-row>


        <!--游记-->
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
            </b-card-group>
        </b-row>
        <b-row class="recommend">
            <div>
                <b-card-group columns>
                    <b-card
                            class="shadow-sm"
                            title="Card title that wraps to a new line"
                            img-src="https://placekitten.com/g/400/450"
                            img-alt="Image"
                            img-top
                    >
                        <b-card-text>
                            This is a wider card with supporting text below as a natural lead-in to additional content.
                            This content is a little bit longer.
                        </b-card-text>
                    </b-card>

                    <b-card header="Quote" class="shadow-sm">
                        <blockquote class="blockquote mb-0">
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.</p>
                            <footer class="blockquote-footer">
                                Someone famous in <cite title="Source Title">Source Title</cite>
                            </footer>
                        </blockquote>
                    </b-card>

                    <b-card title="Title" class="shadow-sm" img-src="https://placekitten.com/500/350" img-alt="Image"
                            img-top>
                        <b-card-text>
                            This card has supporting text below as a natural lead-in to additional content.
                        </b-card-text>
                        <b-card-text class="small text-muted">Last updated 3 mins ago</b-card-text>
                    </b-card>

                    <b-card bg-variant="primary" text-variant="white" class="shadow-sm">
                        <blockquote class="card-blockquote">
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.</p>
                            <footer>
                                <small>Someone famous in <cite title="Source Title">Source Title</cite></small>
                            </footer>
                        </blockquote>
                    </b-card>

                    <b-card class="shadow-sm">
                        <b-card-title>Title</b-card-title>
                        <b-card-text>
                            This card has supporting text below as a natural lead-in to additional content.
                        </b-card-text>
                        <b-card-text class="small text-muted">Last updated 3 mins ago</b-card-text>
                    </b-card>

                    <b-card class="shadow-sm" img-src="https://picsum.photos/400/400/?image=41" img-alt="Image"
                            overlay></b-card>

                    <b-card class="shadow-sm" img-src="https://picsum.photos/400/200/?image=41" img-alt="Image" img-top>
                        <b-card-text>
                            This is a wider card with supporting text below as a natural lead-in to additional content.
                            This card has even longer content than the first.
                        </b-card-text>
                        <template v-slot:footer>
                            <small class="text-muted">Footer Text</small>
                        </template>
                    </b-card>
                </b-card-group>
            </div>
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
