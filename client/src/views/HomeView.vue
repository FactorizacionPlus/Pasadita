<template>
  <div v-for="residence in residenceList" :key="residence.description">
    <ResidenceCard :residence="residence"/>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from "vue";
import ResidenceCard from "../components/ResidenceCard.vue"
import type Residence from "../types/Residence"

const residenceList = ref<Residence[]>([])
const userToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwMDU0MjcyNEB1Y2EuZWR1LnN2IiwiaWF0IjoxNzE3MzE0NjQzLCJleHAiOjE3MTg2MTA2NDN9.5RUWkAv1p0D8D6zHNyPUw8k0BiyIeLbOv5kk2tQJt5fRPvaFGRQ0F1LKi73awXaqjyE5ZK1zVAO1x9ejnaQLig"

onMounted(async () => {
  const response = await fetch("http://localhost:8080/api/residence/", {
    headers:{
      Authorization: "Bearer " + userToken, 
    }
  });

  if(response.ok){
    const json = await response.json();
    residenceList.value = json.data
    console.log(json)
  }
})

</script>