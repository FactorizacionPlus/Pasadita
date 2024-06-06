<script setup lang="ts">

import VueFeather from 'vue-feather';

</script>

<template>
    
    <div class="flex flex-col max-w-sm">
        <div class="ml-3">
            <p class="text-base font-semibold text-pasadita-blue-1">
                Administrador
            </p>
        </div>
        <div class="flex items-center px-2 py-2 rounded-t-lg gap-2">
            <div class="flex justify-center items-center flex-shrink-0 border-2 border-pasadita-blue-2 rounded-full w-10 h-10 bg-pasadita-blue-4">
                <VueFeather type="user" size="25" stroke="#004f82" stroke-width="1.5"></VueFeather>
            </div>
            
            <div class="flex-1">
                <p class="text-base font-semibold text-pasadita-blue-1">
                    414413432
                </p>
                <p class="text-base font-normal text-pasadita-blue-1">
                    Juan Valdez
                </p>
            </div>
    </div>
    </div>
</template>