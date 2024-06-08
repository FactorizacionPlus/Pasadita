<script  setup lang="ts">
    import CurrentPageInfo from "@/components/CurrentPageInfo.vue"
    import Entry from "@/components/Cards/EntryCard.vue"
    import type EntryCard from "@/types/EntryCard"

    const entryCardList: EntryCard[] = [
  {
    id: 1,
    userType: "",
    username: "",
    dateTime: "5 de Mayo de 2024",
    direction: "Rumbo a Palacio de Miraflores para realizar tareas de mantenimiento",
    isRegister: true
  },
]

</script>

<template>
    <CurrentPageInfo title="Entradas" icon="folder"/>
    <Entry :entryCard="entryCard" v-for="entryCard in entryCardList" :key="entryCard.id" />
</template>