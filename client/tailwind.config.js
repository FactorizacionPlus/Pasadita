/** @type {import('tailwindcss').Config} */
export default {
  content: ["./index.html", "./src/**/*.{vue,js,ts,jsx,tsx}"],
  theme: {
    extend: {
      colors: {
        pasadita: {
          blue: {
            0: "#000D1F",
            1: "#01193F",
            2: "#004f82",
            3: "#17a4fe",
            4: "#f3faff",
            5: "#f6f8fa",
          },
          green: {
            0: "#008209",
            1: "#608951",
            2: "#deffd0",
            3: "#eff5ea",
          },
          yellow: {
            0: "#B38A58",
            1: "#F5F1EA",
          },
          red: {
            0: "#895151",
            1: "#ffd0d0",
            2: "#f5eaea",
          },
          shade: {
            0: "#3f3f3f",
            1: "#8b8b8b",
            2: "#d7d7d7",
            3: "#fcfcfc",
          },
        },
      },
    },
  },
  plugins: [],
};
