<template>

    <div class="flex flex-col justify-between h-dvh min-w-52 p-4 bg-pasadita-shade-3 border-r border-r-pasadita-shade-2">
        <div>
            <div class="flex justify-center">
                <img class="h-12 w-[80%] object-fit" src="../assets/pasaditaLogo.svg">
            </div>
            <div class="ml-1 h-70 flex flex-col justify-between h-[100%] mt-2">
                <RouterLink class="flex flex-row  gap-2  items-center h-10 rounded-md text-blue02 hover:bg-pasadita-blue-2 text-pasadita-blue-2 hover:text-white " to="/">
                    <div class="ml-1 flex justify-center items-center flex-shrink-0 ">
                        <VueFeather type="folder" size="25"  stroke-width="1.5"></VueFeather>    
                    </div>
                    Entradas
                </RouterLink>
                <RouterLink class="flex flex-row  gap-2 items-center h-10 rounded-md text-blue02 hover:bg-pasadita-blue-2 text-pasadita-blue-2 hover:text-white " to="/">
                    <div class="ml-1 flex justify-center items-center flex-shrink-0 ">
                        <VueFeather type="file-minus" size="25"  stroke-width="1.5"></VueFeather>    
                    </div>
                    Historial de permisos
                </RouterLink>
                <RouterLink class="flex flex-row  gap-2 items-center h-10 rounded-md text-blue02 hover:bg-pasadita-blue-2 text-pasadita-blue-2 hover:text-white " to="/">
                    <div class="ml-1 flex justify-center items-center flex-shrink-0 ">
                        <VueFeather type="tablet" size="25"  stroke-width="1.5"></VueFeather>    
                    </div>
                    Terminales
                </RouterLink>
                <RouterLink class="flex flex-row gap-2 items-center h-10 rounded-md text-blue02 hover:bg-pasadita-blue-2 text-pasadita-blue-2 hover:text-white " to="/">
                    <div class="ml-1 flex justify-center items-center flex-shrink-0 ">
                        <VueFeather type="home" size="25"  stroke-width="1.5"></VueFeather>    
                    </div>
                    Residencias
                </RouterLink>
                <RouterLink class="flex flex-row gap-2 items-center h-10 rounded-md text-blue02 hover:bg-pasadita-blue-2 text-pasadita-blue-2 hover:text-white " to="/">
                    <div class="ml-1 flex justify-center items-center flex-shrink-0 ">
                        <VueFeather type="user" size="25"  stroke-width="1.5"></VueFeather>    
                    </div>
                    Residentes
                </RouterLink>
                <RouterLink class="flex flex-row  gap-2 items-center h-10 rounded-md text-blue02 hover:bg-pasadita-blue-2 text-pasadita-blue-2 hover:text-white " to="/">
                    <div class="ml-1 flex justify-center items-center flex-shrink-0 ">
                        <VueFeather type="user" size="25"  stroke-width="1.5"></VueFeather>    
                    </div>
                    Invitados
                </RouterLink>
                
                <!-- <RouterLink class="h-10 rounded-md text-blue02 hover:bg-pasadita-blue-2 hover:text-white" to="/about">About</RouterLink>
                <RouterLink class="h-10 rounded-md text-blue02 hover:bg-pasadita-blue-2 hover:text-white" to="/">Home</RouterLink>
                <RouterLink class="h-10 rounded-md text-blue02 hover:bg-pasadita-blue-2 hover:text-white" to="/guest/about">About</RouterLink>
                <RouterLink class="h-10 rounded-md text-blue02 hover:bg-pasadita-blue-2 hover:text-white" to="/guest/access">Access</RouterLink> -->
            </div>
        </div>

        <RouterLink class="ml-1 flex flex-row gap-2 items-center h-10 rounded-md text-blue02 hover:bg-pasadita-blue-2 text-pasadita-blue-2 hover:text-white " to="/">
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