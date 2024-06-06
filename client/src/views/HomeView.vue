<template>
  <div class="grid px-8 gap-y-4">
    <!--Add User-->
    <div class="flex flex-row justify-end">
      <button
        type="button"
        class="bg-pasadita-blue-4 font-normal rounded-lg text-sm p-2.5 text-center inline-flex items-center text-pasadita-blue-2 hover:bg-pasadita-blue-3 hover:text-white active:scale-95 hover:rounded-xl transition-all"
      >
        <VueFeather type="plus" stroke-width="2.5" size="16" class="mr-2"></VueFeather>
        <span>Agregar Residencias</span>
      </button>
    </div>
    <!--Search Bar-->
    <div class="w-full">
      <form>
        <label for="simple-search" class="sr-only">Search</label>
        <div class="relative w-full">
          <div class="absolute inset-y-0 start-0 flex items-center ps-3 pointer-events-none">
            <VueFeather type="search" stroke="#d7d7d7" stroke-width="2.5" size="16"></VueFeather>
          </div>
          <input
            type="text"
            id="simple-search"
            class="bg-white border border-pasadita-shade-2 text-pasadita-shade-0 focus:ring-1 focus:outline-none focus:ring-pasadita-shade-2 text-sm rounded-lg block w-full ps-10 p-2.5"
            placeholder="Buscar por descripcion..."
            required
          />
        </div>
      </form>
    </div>
  </div>
  <!--Cards Grid-->
  <div class="grid grid-cols-5 gap-5 p-8">
    <div v-for="residence in residenceList" :key="residence.description">
      <ResidenceCard :residence="residence" />
    </div>
    <AccessRequestCard :accessRequest="accessExample" />
    <InvitedResidentCard :invitedResident="invitedResidentExample" />
  </div>

  <div class="grid grid-cols-5 gap-5 p-8">
    <div v-for="user in userList" :key="user.rol">
      <UserCard :user="user" />
    </div>
  </div>

  <div class="grid grid-cols-5 gap-5 p-8">
    <EntryCard />
  </div>
  <!--
  <div class="py-20">divider</div>

  <section class="flex flex-col p-5 gap-y-10">
    <InputForm value="149123" @update:value="handleValueUpdate" title="hola" placeholder="odio los negros" />
    <p class="bg-blue-200 p-4">Val: {{ inputValue }}</p>

    <SelectForm :options="options" :disabled="true" :current-index="1" default-option="Select the Animal"
      @update:value="handleSelectUpdate" />
    <p class="bg-red-200 p-4">Val: {{ SelectValue }}</p>

    <SwitchForm @update:value="handleSwitchUpdate" />
    <p class="bg-green-200 p-4">Val: {{ switchValue }}</p>

    <InputForm value="149123" type="date" title="hola" placeholder="odio los negros" />
    <InputForm value="149123" type="number" title="hola" placeholder="odio los negros" />

    <HeaderModal title="Residencia" icon="file-text" action="add" />
    <CreateResidenceForm />

    <ManageResidence />
  </section>
--></template>

<script setup lang="ts">
import ResidenceCard from "../components/ResidenceCard.vue";
import AccessRequestCard from "../components/AccessRequestCard.vue";
import type Residence from "../types/Residence";

import UserCard from "../components/UserCard.vue";
import type User from "../types/User";

import type UserEntry from "../types/UserEntry";

import EntryCard from "../components/EntryCard.vue";

import type AccessRequest from "../types/AccessRequest.ts";
import type InvitedResident from "../types/InvitedResident.ts";
import VueFeather from "vue-feather";
import InvitedResidentCard from "../components/InvitedResidentCard.vue";

const residenceList: Residence[] = [
  {
    maxHabitants: 1,
    description: "Lorem Ipsum",
  },
  {
    maxHabitants: 2,
    description: "Lorem Ipsum",
  },
  {
    maxHabitants: 3,
    description: "Lorem Ipsum",
  },
  {
    maxHabitants: 4,
    description: "Lorem Ipsum",
  },
  {
    maxHabitants: 5,
    description: "Lorem Ipsum",
  },
  {
    maxHabitants: 6,
    description: "Lorem Ipsum",
  },
];

const accessExample: AccessRequest = {
  residentId: "12121221",
  residentName: "Marshall",
  startDate: new Date("2024-06-04"),
  endDate: new Date("2024-06-06"),
  status: "REJECTED",
};

const invitedResidentExample: InvitedResident = {
  name: "Marcelo",
  secondName: "Rivera",
  identification: "010102021",
  entries: 10,
};

const userList: User[] = [
  {
    rol: "Admin",
    name: "Juan",
    lastName: "Alberto",
  },
];

const userEntryList: UserEntry[] = [
  {
    id: 1532,
    rol: "Admin",
    name: "Mario",
    lastName: "Lopez",
  },
];

/*
const residenceList = ref<Residence[]>([])
const userToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwMDU0MjcyNEB1Y2EuZWR1LnN2IiwiaWF0IjoxNzE3MzE0NjQzLCJleHAiOjE3MTg2MTA2NDN9.5RUWkAv1p0D8D6zHNyPUw8k0BiyIeLbOv5kk2tQJt5fRPvaFGRQ0F1LKi73awXaqjyE5ZK1zVAO1x9ejnaQLig"
=======
import InputForm from "@/components/Forms/InputForm.vue";
import ResidenceCard from "../components/ResidenceCard.vue";
import type Residence from "../types/Residence";
import VueFeather from "vue-feather";
import { ref } from "vue";
import type Option from "@/types/Option";
import SelectForm from "@/components/Forms/SelectForm.vue";
import SwitchForm from "@/components/Forms/SwitchForm.vue";
import HeaderModal from "@/components/Modal/HeaderModal.vue";
import CreateResidenceForm from "@/components/CreateResidenceForm.vue";
import ManageResidence from "@/components/Forms/Residence/ManageResidence.vue";

const inputValue = ref("");
const SelectValue = ref<Option>();
const switchValue = ref<boolean>();

const handleValueUpdate = (value: string) => {
  inputValue.value = value;
};
>>>>>>> master

const handleSelectUpdate = (option: Option) => {
  SelectValue.value = option;
};

<<<<<<< HEAD
  if (response.ok) {
    const json = await response.json();
    residenceList.value = json.data
    console.log(json)
  }
})
*/
</script>
