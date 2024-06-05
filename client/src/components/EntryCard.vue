<script setup lang="ts">
import { defineProps } from 'vue';
import VueFeather from 'vue-feather';
import type EntryCard from '../types/EntryCard';

import UserCardEntry from "../components/UserCardEntry.vue"



</script>

<template>
    <div class="w-[550px] ">
    
        <div class="flex flex-row justify-between">
            <UserCardEntry />

            <div class="flex row px-2 gap-3 items-center">
                <div class="flex flex-col text-pasadita-blue-1">
                    <p class="font-semibold">Fecha y hora de entrada</p>
                    <p>29 de agosto 2023 - 12:36hrs</p>
                </div>
            </div>
        </div>
       
        <div class="flex items-center px-2 py-2 ">
            
            <div class="flex-shrink-0">
                <VueFeather type="home" size="40" stroke="#01193F" stroke-width="1.5"></VueFeather>
            </div>

            <p>Rumbo al palacio de miraflores para realizar tareas de mantenimiento</p>
        </div>
    </div>
</template>