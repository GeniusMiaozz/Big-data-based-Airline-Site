<template>
    <b-container class="small-margin">
        <div style="margin-bottom: 3em">
            <b-card no-body class="shadow">
                <b-tabs small card>
                    <b-tab title="查询机票">
                        <div>
                            <SearchTicket/>
                        </div>
                    </b-tab>
                    <b-tab title="添加乘机人">
                        <AddPassengers/>
                    </b-tab>
                    <b-tab title="机票生成">
                        <GenTickets/>
                    </b-tab>
                </b-tabs>
            </b-card>
        </div>
        <!--机票列表-->
        <FlightList/>
    </b-container>
</template>

<script>
    import SearchTicket from "./AirSearchComps/SearchTicket";
    import AddPassengers from "./AirSearchComps/AddPassengers";
    import FlightList from "./AirSearchComps/FlightList";
    import GenTickets from "./AirSearchComps/GenTickets";

    export default {
        name: "AirSearchMain",
        components: {FlightList, AddPassengers, SearchTicket, GenTickets},
    }
</script>
