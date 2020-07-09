<template>
    <b-container class="small-margin">
        <div role="tablist">
            <b-card no-body class="mb-1">
                <b-card-header header-tag="header" class="p-1" role="tab">
                    <b-button block v-b-toggle.accordion-1 variant="outline">查询机票</b-button>
                </b-card-header>
                <b-collapse id="accordion-1" visible accordion="my-accordion" role="tabpanel">
                    <b-card-body>
                        <SearchTicket/>
                    </b-card-body>
                </b-collapse>
            </b-card>

            <b-card no-body class="mb-1">
                <b-card-header header-tag="header" class="p-1" role="tab">
                    <b-button block v-b-toggle.accordion-2 variant="outline">添加乘机人</b-button>
                </b-card-header>
                <b-collapse id="accordion-2" accordion="my-accordion" role="tabpanel">
                    <b-card-body>
                        <AddPassengers/>
                    </b-card-body>
                </b-collapse>
            </b-card>

            <b-card no-body class="mb-1">
                <b-card-header header-tag="header" class="p-1" role="tab">
                    <b-button block v-b-toggle.accordion-3 variant="outline">机票生成</b-button>
                </b-card-header>
                <b-collapse id="accordion-3" accordion="my-accordion" role="tabpanel">
                    <b-card-body>
                        <!--这里放入相应的div-->
                    </b-card-body>
                </b-collapse>
            </b-card>
        </div>
        <FlightList/>

    </b-container>
</template>

<script>
    import SearchTicket from "./AirSearchComps/SearchTicket";
    import AddPassengers from "./AirSearchComps/AddPassengers";
    import FlightList from "./AirSearchComps/FlightList";

    export default {
        name: "AirSearchMain",
        components: {FlightList, AddPassengers, SearchTicket},
    }
</script>

<style scoped>
    .small-margin {
        padding: 0 2em;
        margin-top: 1em;
    }

</style>
