<script setup lang="ts">
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";

enum dic {
    TITLE = "Dashboard",
}


</script>
<template>
    <CurrentPageInfo :title="dic.TITLE" icon="grid" />
    <section class="grid flex-1 grid-cols-2 grid-rows-3 gap-4">
        <article class="rounded-lg bg-white">
            <h2>SDAaskd</h2>

        </article>
        <article class="rounded-lg bg-white">
            <h2>SDAaskd</h2>

        </article>
        <article class="col-span-2 row-span-2 rounded-lg bg-white">
            <h2>SDAaskd</h2>

        </article>
    </section>
</template>