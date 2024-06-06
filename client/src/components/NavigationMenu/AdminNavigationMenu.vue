<template>
    <nav
        class="flex flex-col justify-between h-dvh min-w-52 p-4 bg-pasadita-shade-3 border-r border-r-pasadita-shade-2">
        <div>
            <div class="flex justify-center">
                <img class="h-12 w-[80%] object-fit" src="/identity/pasaditaLogo.svg">
            </div>
            <div class="ml-1 h-70 flex flex-col justify-between h-[100%] mt-2">
                <RouterLink :key="index" v-for="route, index in routes"
                    class="flex flex-row min-w-32 gap-2  items-center h-10 rounded-md  hover:bg-pasadita-blue-2 text-pasadita-blue-2 hover:text-white transition-all active:scale-95"
                    :to="`${ADMIN_ROUTE}${route.link}`">
                    <div class="ml-1 flex justify-center items-center flex-shrink-0 ">
                        <VueFeather :type="route.icon" size="25" stroke-width="1.5"></VueFeather>
                    </div>
                    {{ route.text }}
                </RouterLink>
            </div>
        </div>

        <RouterLink
            class="ml-1 flex flex-row gap-2 items-center h-10 rounded-md hover:bg-pasadita-blue-2 text-pasadita-blue-2 hover:text-white "
            to="/">
            <div class="ml-1 flex justify-center items-center flex-shrink-0 ">
                <VueFeather type="settings" size="25" stroke-width="1.5"></VueFeather>
            </div>
            Configuraciones
        </RouterLink>
    </nav>
</template>

<script setup lang="ts">
import { RouterLink } from "vue-router";
import VueFeather from 'vue-feather';

const ADMIN_ROUTE = "/admin/";
const routes = [
    {
        text: "Entradas",
        link: "entradas",
        icon: "folder"
    },
    {
        text: "Historial de Permisos",
        link: "permisos",
        icon: "file-minus"
    },
    {
        text: "Terminales",
        link: "terminales",
        icon: "tablet"
    },
    {
        text: "Residencias",
        link: "residencias",
        icon: "home"
    },
    {
        text: "Residentes",
        link: "residentes",
        icon: "user"
    },
    {
        text: "Invitados",
        link: "invitados",
        icon: "user"
    }

]


</script>

<style scoped></style>