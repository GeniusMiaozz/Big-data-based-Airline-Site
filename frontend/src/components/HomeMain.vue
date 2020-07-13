<template>
    <b-container class="small-margin">
        <!--几个最受欢迎的地点-->
        <b-row>
            <div class="slide-x" style="width: 100%">
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
                <b-badge variant="info" style="font-weight: normal">特价机票</b-badge>
            </h2>
        </b-row>
        <b-row class="recommend">
            <b-card-group columns>
                <b-card bg-variant="light" text-variant="black" v-for="item in cheapFlights" :key="item.id">
                    <blockquote class="card-blockquote" style="margin: auto">
                        <b-container>
                            <b-row align-h="start">
                                <b-col cols="8">
                                    <p style="font-size: 18px;font-weight: bold;">
                                        {{item.from}}
                                        <b-icon-arrow-right/>
                                        {{item.to}}
                                    </p>
                                </b-col>
                                <b-col cols="4">
                                    <p class="card-font">
                                        {{item.price}}￥
                                    </p>
                                </b-col>
                            </b-row>
                            <b-row align-h="center">
                                <b-col>
                                    {{item.date_x}}
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
                <b-badge variant="primary" style="font-weight: normal">商城优惠</b-badge>
            </h2>
        </b-row>
        <b-row class="recommend">
            <div>
                <b-card-group columns>
                    <b-card
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

                    <b-card header="Quote">
                        <blockquote class="blockquote mb-0">
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.</p>
                            <footer class="blockquote-footer">
                                Someone famous in <cite title="Source Title">Source Title</cite>
                            </footer>
                        </blockquote>
                    </b-card>

                    <b-card title="Title" img-src="https://placekitten.com/500/350" img-alt="Image" img-top>
                        <b-card-text>
                            This card has supporting text below as a natural lead-in to additional content.
                        </b-card-text>
                        <b-card-text class="small text-muted">Last updated 3 mins ago</b-card-text>
                    </b-card>

                    <b-card bg-variant="primary" text-variant="white">
                        <blockquote class="card-blockquote">
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.</p>
                            <footer>
                                <small>Someone famous in <cite title="Source Title">Source Title</cite></small>
                            </footer>
                        </blockquote>
                    </b-card>

                    <b-card>
                        <b-card-title>Title</b-card-title>
                        <b-card-text>
                            This card has supporting text below as a natural lead-in to additional content.
                        </b-card-text>
                        <b-card-text class="small text-muted">Last updated 3 mins ago</b-card-text>
                    </b-card>

                    <b-card img-src="https://picsum.photos/400/400/?image=41" img-alt="Image" overlay></b-card>

                    <b-card img-src="https://picsum.photos/400/200/?image=41" img-alt="Image" img-top>
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
                cheapFlights: []
            }
        },
        methods: {
            onSlideStart(slide) {
                this.sliding = true
            },
            onSlideEnd(slide) {
                this.sliding = false
            },

            loadCheepFlights() {
                const obj = this;
                const url1 = "json\\cheapFlights.json";
                const url2 = "json\\slidelist.json";
                this.$axios.get(url1).then(
                    function (res) {
                        obj.cheapFlights = res.data;
                    }
                )
                this.$axios.get(url2).then(
                    function (res) {
                        obj.slideList = res.data;
                    }
                )

            }

        },
        mounted() {
            this.loadCheepFlights();

        }
    }

</script>

<style scoped>
    .recommend, .slide-x {
        margin-top: 10px;
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
