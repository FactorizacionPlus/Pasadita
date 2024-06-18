<script setup lang="ts">
import CurrentPageInfo from "@/components/CurrentPageInfo.vue";
import Guest from "@/components/Cards/InvitedResidentCard.vue";
import type InvitedResident from "@/types/InvitedResident";
import PaginationItem from "@/components/PaginationItem.vue";
import SearchBar from "@/components/SearchBar.vue";

const invitedResidentList: InvitedResident[] = [
  {
    name: "Dalas",
    secondName: "Review",
    identification: "D1234567",
    entries: 1,
    image:
      "https://imagenes.20minutos.es/files/image_1920_1080/uploads/imagenes/2021/04/05/el-youtuber-dalas-review.jpeg",
  },
  {
    name: "Elon",
    secondName: "Musk",
    identification: "E2345678",
    entries: 2,
    image:
      "https://fotografias.lasexta.com/clipping/cmsimages02/2022/05/03/6F9C82A4-0FA4-40F9-BE2F-87F8DBBC1224/elon-musk-met-gala_69.jpg?crop=1407,791,x0,y150&width=1280&height=720&optimize=low&format=jpg",
  },
  {
    name: "Mark",
    secondName: "Zuckerberg",
    identification: "M3456789",
    entries: 0,
    image:
      "https://upload.wikimedia.org/wikipedia/commons/1/18/Mark_Zuckerberg_F8_2019_Keynote_%2832830578717%29_%28cropped%29.jpg",
  },
  {
    name: "Nicolas",
    secondName: "Maduro",
    identification: "N4567890",
    entries: 3,
    image: "https://pasadita.vercel.app/madurito.png",
  },
  {
    name: "Nayib",
    secondName: "Bukele",
    identification: "N5678901",
    entries: 1,
    image:
      "https://upload.wikimedia.org/wikipedia/commons/e/ef/Presidente_Bukele_%28cropped%29.jpg",
  },
];
</script>

<template>
  <CurrentPageInfo title="Invitados" icon="user" />
  <SearchBar class="py-3" />
  <ul class="grid min-h-[43rem] gap-4 pt-4 lg:grid-cols-2 xl:grid-cols-3 2xl:grid-cols-4">
    <Guest
      :invitedResident="invitedResident"
      v-for="invitedResident in invitedResidentList"
      :key="invitedResident.identification"
    />
  </ul>
  <PaginationItem :totalPages="5" />
</template>
