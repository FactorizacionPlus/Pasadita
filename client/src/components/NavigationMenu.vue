<template>

    <div class="flex flex-col justify-between h-dvh w-52 ">
        <div>
            <div class="">
                <img class="h-12 w-[100%] object-fit" src="../assets/logo.svg">
            </div>
            <div class="h-70 flex flex-col justify-between h-[100%] mt-2">
                <RouterLink class="h-10 rounded-sm text-blue02 hover:bg-blue02 hover:text-white " to="/">Entradas</RouterLink>
                <RouterLink class="h-10 rounded-sm text-blue02 hover:bg-blue02 hover:text-white" to="/">Historial de permisos</RouterLink>
                <RouterLink class="h-10 rounded-sm text-blue02 hover:bg-blue02 hover:text-white" to="/">Terminales</RouterLink>
                <RouterLink class="h-10 rounded-sm text-blue02 hover:bg-blue02 hover:text-white" to="/">Residenciales</RouterLink>
                <RouterLink class="h-10 rounded-sm text-blue02 hover:bg-blue02 hover:text-white" to="/">Invitados</RouterLink>
                <RouterLink class="h-10 rounded-sm text-blue02 hover:bg-blue02 hover:text-white" to="/about">About</RouterLink>
            </div>
        </div>

        <div class="">
            <button class="h-10 rounded-sm text-blue02 hover:bg-blue02 hover:text-white w-[100%] text-left">Configuraciones</button>
        </div>
        
    </div>

</template>

<script setup lang="ts">
    import { RouterLink} from "vue-router";
</script>

<style scoped>

</style>
