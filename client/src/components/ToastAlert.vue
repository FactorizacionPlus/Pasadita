
<script setup lang="ts">
import { defineProps } from 'vue';
import VueFeather from 'vue-feather';
import type Toast from '../types/Toast';


const props = defineProps<{ toast: Toast }>();
</script>

<template>
    <div>
        <div class="flex flex-row items-center justify-between gap-2 rounded-md border-2 " :class="toast.bgColor ">
            
            <div class="flex flex-row items-center">
                <div class="flex justify-center items-center flex-shrink-0 w-10 h-10">
                     <VueFeather :type="toast.icon" size="25" :stroke="toast.strokeColor" stroke-width="1.5"></VueFeather>
                 </div>

                   <p :class="toast.textColor" class="font-semibold">{{ toast.description }}</p>
            </div>
            
           
            <div class="flex flex-row items-center justify-center mr-3"> 
                <VueFeather type="x" size="25" :stroke="toast.strokeColor" stroke-width="1.5"></VueFeather>
            </div>
            
        </div>
    </div>
</template>