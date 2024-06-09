<template>
  <li class="mx-auto rounded-lg border border-pasadita-shade-2">
    <div
      class="rounded-t-lg border-b border-pasadita-shade-2 bg-pasadita-shade-3 p-2 text-pasadita-blue-1"
    >
      Agregar Residencia
    </div>
    <div>
      <form>
        <!--Habitants-->
        <div class="mt-3 px-3">
          <label
            :class="[isHabitantesFocused ? 'text-pasadita-blue-3' : 'text-gray-900']"
            for="text"
            class="mb-2 block text-sm font-medium"
            >Cantidad de habitantes</label
          >
          <input
            v-model="habitantes"
            type="number"
            onkeydown="javascript: return event.keyCode == 69 ? false : true"
            id="habitantes"
            @focus="isHabitantesFocused = true"
            @blur="isHabitantesFocused = false"
            class="peer block w-full rounded-lg border border-pasadita-shade-2 bg-white p-2.5 text-sm text-pasadita-shade-0 focus:outline-none focus:ring-2 focus:ring-pasadita-blue-3"
            placeholder="Ingrese la cantidad de Habitantes"
            required
          />
        </div>

        <!--Description-->
        <div class="mb-5 mt-3 px-3">
          <label
            :class="[isInputFocused ? 'text-pasadita-blue-3' : 'text-gray-900']"
            for="input"
            class="mb-2 block text-sm font-medium"
            >Input Label</label
          >
          <textarea
            v-model="descripcion"
            rows="4"
            @focus="isInputFocused = true"
            @blur="isInputFocused = false"
            class="peer block w-full rounded-lg border border-pasadita-shade-2 p-2.5 text-sm text-pasadita-shade-0 focus:outline-none focus:ring-2 focus:ring-pasadita-blue-3"
            placeholder="Descripcion"
            required
          ></textarea>
        </div>

        <!--Buttons-->
        <div class="flex flex-row justify-end rounded-b-lg border-t border-pasadita-shade-2 p-2">
          <button
            type="submit"
            class="me-2 inline-flex items-center rounded-lg bg-pasadita-red-2 p-2.5 text-center text-sm font-normal text-pasadita-red-0 transition-all hover:rounded-xl hover:bg-pasadita-red-1 active:scale-95"
          >
            <VueFeather type="check" stroke-width="2.5" size="16"></VueFeather>
            <span>Agregar Residencias</span>
          </button>
          <button
            type="submit"
            @click="cancelForm"
            class="me-2 inline-flex items-center rounded-lg bg-pasadita-blue-4 p-2.5 text-center text-sm font-normal text-pasadita-blue-2 transition-all hover:rounded-xl hover:bg-pasadita-blue-3 hover:text-white active:scale-95"
          >
            <VueFeather type="x" stroke-width="2.5" size="16"></VueFeather>
            <span>Cancelar</span>
          </button>
        </div>

        <!--
      <button type="submit"
        class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Aceptar</button>
      <button type="button" @click="cancelForm"
        class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Cancelar</button>
      --></form>
    </div>
  </li>
</template>

<script setup lang="ts">
import { ref } from "vue";
import VueFeather from "vue-feather";

const habitantes = ref<number>(0);
const descripcion = ref<string>("");
const isHabitantesFocused = ref<boolean>(false);
const isInputFocused = ref<boolean>(false);
/*
const userToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwMDU0MjcyNEB1Y2EuZWR1LnN2IiwiaWF0IjoxNzE3MzE0NjQzLCJleHAiOjE3MTg2MTA2NDN9.5RUWkAv1p0D8D6zHNyPUw8k0BiyIeLbOv5kk2tQJt5fRPvaFGRQ0F1LKi73awXaqjyE5ZK1zVAO1x9ejnaQLig"
 @submit.prevent="submitForm"

const submitForm = async () => {
  const formData = {
    maxHabitants: habitantes.value,
    description: descripcion.value
  };

  const response = await fetch("http://localhost:8080/api/residence/", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + userToken,
    },
    body: JSON.stringify(formData),
  });

  const responseData = await response.json(); // o response.text() dependiendo del formato de respuesta
  console.log(responseData);

  if (response.ok) {
    // Manejar respuesta exitosa
    console.log("La residencia ha sido creada exitosamente.");
  } else {
    // Manejar error en la respuesta
    console.error("Ha ocurrido un error al crear la residencia.");
  }
};
*/
const cancelForm = () => {
  // Limpiar los campos o realizar cualquier otra acción necesaria al cancelar el formulario
  habitantes.value = 0;
  descripcion.value = "";
};
</script>
