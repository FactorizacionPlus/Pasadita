<template>

    <div class="flex flex-col justify-between h-dvh w-52 ">
        <div>
            <div class="">
                <img class="h-12 w-[100%] object-fit" src="../assets/logo.svg">
            </div>
            <div class="h-70 flex flex-col justify-between h-[100%] mt-2">
                <RouterLink class="flex flex-row  gap-2  items-center h-10 rounded-sm text-blue02 hover:bg-pasadita-blue-2 text-pasadita-blue-2 hover:text-white " to="/">
                    <div class="ml-1 flex justify-center items-center flex-shrink-0 ">
                        <VueFeather type="folder" size="25"  stroke-width="1.5"></VueFeather>    
                    </div>
                    Entradas
                </RouterLink>
                <RouterLink class="flex flex-row  gap-2 items-center h-10 rounded-sm text-blue02 hover:bg-pasadita-blue-2 text-pasadita-blue-2 hover:text-white " to="/">
                    <div class="ml-1 flex justify-center items-center flex-shrink-0 ">
                        <VueFeather type="file-minus" size="25"  stroke-width="1.5"></VueFeather>    
                    </div>
                    Historial de permisos
                </RouterLink>
                <RouterLink class="flex flex-row  gap-2 items-center h-10 rounded-sm text-blue02 hover:bg-pasadita-blue-2 text-pasadita-blue-2 hover:text-white " to="/">
                    <div class="ml-1 flex justify-center items-center flex-shrink-0 ">
                        <VueFeather type="tablet" size="25"  stroke-width="1.5"></VueFeather>    
                    </div>
                    Terminales
                </RouterLink>
                <RouterLink class="flex flex-row gap-2 items-center h-10 rounded-sm text-blue02 hover:bg-pasadita-blue-2 text-pasadita-blue-2 hover:text-white " to="/">
                    <div class="ml-1 flex justify-center items-center flex-shrink-0 ">
                        <VueFeather type="home" size="25"  stroke-width="1.5"></VueFeather>    
                    </div>
                    Residencias
                </RouterLink>
                <RouterLink class="flex flex-row gap-2 items-center h-10 rounded-sm text-blue02 hover:bg-pasadita-blue-2 text-pasadita-blue-2 hover:text-white " to="/">
                    <div class="ml-1 flex justify-center items-center flex-shrink-0 ">
                        <VueFeather type="user" size="25"  stroke-width="1.5"></VueFeather>    
                    </div>
                    Residentes
                </RouterLink>
                <RouterLink class="flex flex-row  gap-2 items-center h-10 rounded-sm text-blue02 hover:bg-pasadita-blue-2 text-pasadita-blue-2 hover:text-white " to="/">
                    <div class="ml-1 flex justify-center items-center flex-shrink-0 ">
                        <VueFeather type="user" size="25"  stroke-width="1.5"></VueFeather>    
                    </div>
                    Invitados
                </RouterLink>
                
                <RouterLink class="h-10 rounded-sm text-blue02 hover:bg-pasadita-blue-2 hover:text-white" to="/about">About</RouterLink>
                <RouterLink class="h-10 rounded-sm text-blue02 hover:bg-pasadita-blue-2 hover:text-white" to="/">Home</RouterLink>
                <RouterLink class="h-10 rounded-sm text-blue02 hover:bg-pasadita-blue-2 hover:text-white" to="/guest/about">About</RouterLink>
                <RouterLink class="h-10 rounded-sm text-blue02 hover:bg-pasadita-blue-2 hover:text-white" to="/guest/access">Access</RouterLink>
            </div>
        </div>

        <RouterLink class="flex flex-row gap-2 items-center h-10 rounded-sm text-blue02 hover:bg-pasadita-blue-2 text-pasadita-blue-2 hover:text-white " to="/">
                    <div class="ml-1 flex justify-center items-center flex-shrink-0 ">
                        <VueFeather type="settings" size="25"  stroke-width="1.5"></VueFeather>    
                    </div>
                    Configuraciones
        </RouterLink>
        
    </div>

</template>

<script setup lang="ts">
    import { RouterLink} from "vue-router";
    import VueFeather from 'vue-feather';
</script>

<style scoped>

</style>