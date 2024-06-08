<script setup lang="ts">
import VueFeather from 'vue-feather';
import type Terminal from '../../types/AdminTerminal';

const props = defineProps<{ 
    terminal:Terminal
 }>();
</script>

<template>
    <div class="flex flex-col max-w-sm bg-white">
        <!--Information-->
        <div class="flex items-center justify-center border-t border-l border-r border-pasadita-shade-2 px-2 py-2 rounded-t-lg gap-2">
            <div class="flex items-center ">
                <VueFeather type="tablet" size="40" stroke="#01193F" stroke-width="1.5"></VueFeather>
            </div>
            <div class="flex-1">
                <p class="text-base font-medium text-pasadita-blue-1">
                    Terminal
                </p>
                <p class="text-lg font-normal text-pasadita-blue-1">
                    {{ props.terminal.type}}
                </p>
            </div>
        </div>
        <!--Description-->
        <div class="flex flex-row h-[2.5rem] border-x border-pasadita-shade-2 px-2 py-2 gap-1">
            <p class="font-medium line-clamp-2 text-pasadita-blue-1">
                Cantidad de entradas:
            </p>
            <p class="font-normal line-clamp-2 text-pasadita-blue-1">
                {{ props.terminal.numentries }}
            </p>
        </div>
        <!--CRUD-->
        <div class="flex flex-row justify-end border border-pasadita-shade-2 px-2 py-2 rounded-b-lg gap-2">
            <button type="button"
                class="bg-pasadita-blue-4 font-normal rounded-lg text-sm p-2.5 text-center inline-flex items-center text-pasadita-blue-2 hover:bg-pasadita-blue-3 hover:text-white active:scale-95 hover:rounded-xl transition-all">
                <VueFeather type="eye" stroke-width="3.5" size="16"></VueFeather>
            </button>
        </div>
    </div>

</template>
