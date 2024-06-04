/** @type {import('tailwindcss').Config} */
export default {
  content: ["./index.html", "./src/**/*.{vue,js,ts,jsx,tsx}"],
  theme: {
    extend: {},
    colors: {
      'black': '#000000',
      'white': '#FFFFFF',
      'blue00': '#000D1F',
      'blue01': '#01193F',
      'blue02': '#004F82',
      'blue03': '#009CFF',
      'blue04': '#F3FAFF',
      'blue05': 'F6F8FA',
      'green01': '#608951',
      'green02': '#DEFFD0',
      'green03': '#EFF5EA',
      'yellow': '#D6AF7F',
      'red01': '#895151',
      'red02': '#ffd0d0',
      'red03': '#f5eaea',
    },
  },
  plugins: [],
};
