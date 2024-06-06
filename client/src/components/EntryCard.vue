<script setup lang="ts"> 
import { defineProps } from 'vue';
import VueFeather from 'vue-feather';
import type EntryCard from '@/types/EntryCard';

interface Props {
    entrycard: EntryCard   
}

const props = defineProps<Props>();

</script>

<template>
    <div>
        <div class="user">
            <p>{{ props.entrycard.userType}}</p>
            <div>
                <VueFeather type="trash-2" stroke-width="2.5" size="16"></VueFeather>
                <p>{{props.entrycard.id}}</p>
                <p>{{props.entrycard.username }}</p>
            </div>
        </div>

        <div class="dateTime">
            <p>Fecha y hora de entrada</p>
            <p>{{props.entrycard.dateTime}}</p>
        </div>

        <div class="direction">
            <VueFeather type="trash-2" stroke-width="2.5" size="16"></VueFeather>
            <p>{{props.entrycard.direction}}</p>
        </div>
    </div>
</template>