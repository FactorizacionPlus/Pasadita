<script>
import { defineProps } from 'vue';
import VueFeather from 'vue-feather';
import type EntryCard from '../types/EntryCard';

const props = defineProps<{ entrycard: EntryCard }>();
</script>

<template>
    <div>
        <div class="user">
            <p>{{entrycard.userType}}</p>
            <div>
                <VueFeather type="trash-2" stroke-width="2.5" size="16"></VueFeather>
                <p>{{entrycard.id}}</p>
                <p>{{entrycard.userName}}</p>
            </div>
        </div>

        <div class="dateTime">
            <p>Fecha y hora de entrada</p>
            <p>{{entrycard.dateTime}}</p>
        </div>

        <div class="direction">
            <VueFeather type="trash-2" stroke-width="2.5" size="16"></VueFeather>
            <p>{{entrycard.direction}}</p>
        </div>
    </div>
</template>