<script setup>
import NavigationMenu from './NavigationMenu.vue'; 
</script>

<template>
    <div class="flex flex-row">
        <NavigationMenu class="flex-wrap w-[15%] z-50"/>
        <RouterView class="flex-wrap w-[85%] z-10"/>
    </div>
</template>

<style scoped>

</style>
