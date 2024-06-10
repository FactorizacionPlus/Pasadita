<script setup lang="ts">
import type Options from "@/types/Option";
import Pasadita from "@/assets/PasaditaLogoV2.svg?url";
import InputForm from "@/components/Forms/InputForm.vue";
import SelectForm from "@/components/Forms/SelectForm.vue";

const options: Options[] = [
    {
        text: "Vigilante",
        value: "entrie",
    },
    {
        text: "Entrada Vehicular",
        value: "entrie",
    },
    {
        text: "Entrada Peatonal",
        value: "entrie",
    },
];
</script>

<template>
    <div class="flex h-screen flex-col items-center justify-center gap-5">
        <img :src="Pasadita" class="size-72" />
        <div class="flex flex-col gap-5">
            <SelectForm name="terminal" defaultOption="Seleccione la Terminal" title="Terminales" :options="options" />
            <InputForm name="contraseña" title="Contreseña" type="password" placeholder="Contreseña" />
        </div>
    </div>

</template>