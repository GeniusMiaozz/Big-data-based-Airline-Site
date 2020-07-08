<template>
    <div>
        <Header/>
        <Booking/>
    </div>
</template>

<script>
    import Header from "../components/Header";
    import Booking from "../components/Booking";

    export default {
        name: "Home",
        components: {Booking, Header},
    }
</script>

