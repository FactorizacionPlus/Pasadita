<script setup lang="ts">
import AdministratorCurrentUserCard from "@/components/Cards/AdministratorCurrentUserCard.vue";
import AdministratorTerminalCard from "@/components/Cards/AdministratorTerminalCard.vue"
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import GenericTable from "@/components/GenericTable.vue";
import type Administrator from "@/types/Administrator";
import type Entry from "@/types/Entry";
import type Terminal from "@/types/Terminal";
import type User from "@/types/User";
import VueFeather from "vue-feather";

enum dic {
    TITLE = "Dashboard",
    ACTIVE_TERMINALS  = "Terminales Activas",
    RECENT_ENTRIES = "Entradas Recientes"
}

const user: Administrator = {
    firstName: "Juan Daniel",
    identifier: "C137",
    identifierType: "PASSPORT",
    imageUrl: "https://www.github.com/davidquintr.png",
    lastName: "Treminio",
    status: "ACTIVE",
    uuid: "xd"
}

const terminals: Terminal[] = [
    {
        type: "Peatonal",
        uuid: "xd"
    },
    {
        type: "Vehicular",
        uuid: "xd"
    }
]

const users: User[] = [
    {
        firstName: "Juan Daniel",
        identifier: "C137",
        identifierType: "PASSPORT",
        lastName: "Treminio",
        uuid: "xddddd"
    },
    {
        firstName: "David",
        identifier: "C137",
        identifierType: "PASSPORT",
        lastName: "Quintanilla Ruiz",
        uuid: "xddddd"
    },
    {
        firstName: "Jezer",
        identifier: "C137",
        identifierType: "PASSPORT",
        lastName: "Mejía Otero",
        uuid: "xddddd"
    },
    {
        firstName: "Marcelo",
        identifier: "C137",
        identifierType: "PASSPORT",
        lastName: "Rivera Soto",
        uuid: "xddddd"
    },
]


const entries: Entry[] = [
    {
        uuid: "xdddddd",
        accessDate: new Date(),
        description: "Alguna descripción",
        terminal: terminals[0],
        user: users[0],
    },
    {
        uuid: "xdddddd",
        accessDate: new Date(),
        description: "Alguna descripción",
        terminal: terminals[1],
        user: users[1],
    }
]

const rows = [
    {
        name: "Nombre y Apellidos",
        icon: "user",
        items: [
        ...entries.map((item) => (item.user.firstName + ' ' + item.user.lastName))
        ]
    },
    {
        name: "Tipo",
        icon: "menu",
        items: [
        ...entries.map((item) => item.user.identifierType)
        ]
    },
    {
        name: "Identificacion",
        icon: "user-check",
        items: [
        ...entries.map((item) => item.user.identifier)
        ]
    },
    {
        name: "Terminal",
        icon: "tablet",
        items: [
            ...entries.map((item) => item.terminal.type)
        ]
    },
    {
        name: "Descripción",
        icon: 'align-left',
        items: [
            ...entries.map((item) => item.description)
        ]
    },
    {
        name: "Fecha de Entrada",
        icon: "calendar",
        items: [
            ...entries.map((item) => item.accessDate)
        ]
    }

]


</script>
<template>
    <CurrentPageInfo :title="dic.TITLE" icon="grid" />
    <section class="grid flex-1 grid-rows-3 gap-4 lg:grid-cols-2">
        <article class="grid place-items-center rounded-lg bg-white p-4">
            <AdministratorCurrentUserCard :admin="user" />
        </article>
        <article class="flex flex-col gap-2 rounded-lg bg-white p-4">
            <div class="flex items-center gap-1 font-medium text-blue-500">
                <VueFeather type="tablet" class="size-[18px]" />
                <h2 class="text-xl">{{ dic.ACTIVE_TERMINALS }}</h2>
            </div>
            <ul class="grid grid-cols-2 gap-2">
                <AdministratorTerminalCard :terminal="item" v-for="(item, index) in terminals" :key="index" />
            </ul>
        </article>
        <article class="row-span-2 flex flex-col gap-2 overflow-x-auto rounded-lg bg-white p-4 lg:col-span-2">
            <div class="flex items-center gap-1 font-medium text-blue-500">
                <VueFeather type="tablet" class="size-[18px]" />
                <h2 class="text-xl">{{ dic.RECENT_ENTRIES }}</h2>
            </div>
            <GenericTable :table="rows" />
        </article>
    </section>
</template>
