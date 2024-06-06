<template>
    <header class="flex flex-col justify-between h-dvh py-2 bg-pasadita-shade-3 border-b border-b-pasadita-shade-2">
        <div class="flex items-center justify-between px-4 max-w-screen-2xl mx-auto w-full">
            <img class="h-8 w-auto object-fit" src="/identity/pasaditaLogo.svg">
            <div class="flex items-center gap-1">
                <div
                    class="flex items-center rounded-lg hover:bg-black/5 active:scale-95 transition-all cursor-pointer text-pasadita-blue-2 rounded-t-lg p-2 gap-1">
                    <div
                        class="flex justify-center items-center flex-shrink-0 border-2 border-pasadita-blue-2 rounded-full size-10 bg-pasadita-blue-4">
                        <VueFeather type="user" size="25" stroke="#004f82" stroke-width="1.5"></VueFeather>
                    </div>

                    <div class="flex-1">
                        <p class="text-xs leading-none">
                            Rol
                        </p>
                        <p class="text-xl font-semibold leading-tight text-pasadita-blue-1">
                            Username
                        </p>
                    </div>

                    <div class="flex items-center">
                        <VueFeather type="chevron-down" size="25" stroke="#004f82" stroke-width="1.5"></VueFeather>
                    </div>
                </div>
                <RouterLink
                    class="grid size-10 gap-2 place-items-center rounded-md  hover:bg-pasadita-blue-2 text-pasadita-blue-2 hover:text-white transition-all active:scale-95"
                    to="configuraciones">
                    <VueFeather type="settings" size="25" stroke-width="1.5"></VueFeather>
                </RouterLink>
            </div>
        </div>
    </header>
</template>

<script setup lang="ts">
import VueFeather from 'vue-feather';

</script>

<style scoped></style>