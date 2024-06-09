<template>
  <CardsContainer icon="grid" title="Access Request Card">
    <ul class="grid w-full gap-4 p-4 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">
      <AccessRequestCard
        :access-request="item"
        :key="index"
        v-for="(item, index) in accessRequestItems"
      />
    </ul>
  </CardsContainer>

  <button></button>
  <ManageResidence />
</template>

<script setup lang="ts">
import AccessRequestCard from "@/components/Cards/AccessRequestCard.vue";
import ManageResidence from "@/components/Modal/Residence/ManageResidence.vue";
import CardsContainer from "@/components/CardsContainer.vue";
import type AccessRequest from "@/types/AccessRequest";

const accessRequestItems: AccessRequest[] = [
  {
    status: "ACCEPTED",
    startDate: new Date("10-04-2004"),
    endDate: new Date("10-04-2024"),
    residentId: "0011904041016S",
    residentName: "Juan Daniel Gaturron",
  },
  {
    status: "PENDING",
    startDate: new Date("10-04-2004"),
    endDate: new Date("10-04-2024"),
    residentId: "0011904041016S",
    residentName: "Juan Daniel Gaturron",
  },
  {
    status: "REJECTED",
    startDate: new Date("10-04-2004"),
    endDate: new Date("10-04-2024"),
    residentId: "0011904041016S",
    residentName: "Juan Daniel Gaturron",
  },
];

/*

const inputValue = ref("");
const selectValue = ref<Option>();
const switchValue = ref<boolean>();

const boxItems: BoxContainerItem[] = [
  {
    text: "Gato",
    icon: true,
    value: "hello",
  },
  {
    text: "Perro",
    icon: true,
    value: "hello",
  },
];

const options: Option[] = [
  {
    text: "hola",
    value: "cat",
  },
  {
    text: "adios",
    value: "cattt",
  },
];
const handleValueUpdate = (value: string) => {
  inputValue.value = value;
};

const handleSelectUpdate = (option: Option) => {
  selectValue.value = option;
};

const handleSwitchUpdate = (value: boolean) => {
  switchValue.value = value;
};

<section class="flex flex-col p-5 gap-y-10">
    <InputForm name="text" value="149123" @update:value="handleValueUpdate" title="hola"
      placeholder="odio los negros" />
    <p class="bg-blue-200 p-4">Val: {{ inputValue }}</p>

    <SelectForm name="select" :options="options" :disabled="true" :current-index="1" default-option="Select the Animal"
      @update:value="handleSelectUpdate" />
    <p class="bg-red-200 p-4">Val: {{ selectValue }}</p>

    <SwitchForm name="switch" @update:value="handleSwitchUpdate" />
    <p class="bg-green-200 p-4">Val: {{ switchValue }}</p>

    <InputForm name="date" value="149123" type="date" title="hola" placeholder="odio los negros" />
    <InputForm name="number" value="149123" type="number" title="hola" placeholder="odio los negros" />
    <HeaderModal title="Residencia" icon="file-text" action="add" />
    <BoxContainerForm :items="boxItems" title="hola" />

    <CreateResidenceForm />
    <ManageResidence />
    <CardsContainer icon="tablet" title="Home">
      <p>holahola</p>
    </CardsContainer>
  </section>

*/

/*
const residenceList = ref<Residence[]>([])
const userToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwMDU0MjcyNEB1Y2EuZWR1LnN2IiwiaWF0IjoxNzE3MzE0NjQzLCJleHAiOjE3MTg2MTA2NDN9.5RUWkAv1p0D8D6zHNyPUw8k0BiyIeLbOv5kk2tQJt5fRPvaFGRQ0F1LKi73awXaqjyE5ZK1zVAO1x9ejnaQLig"

  if (response.ok) {
    const json = await response.json();
    residenceList.value = json.data
    console.log(json)
  }
})
*/
</script>
