/** @type {import('tailwindcss').Config} */
export default {
  content: ["./index.html", "./src/**/*.{vue,js,ts,jsx,tsx}"],
  safelist: [
    'animate-slide-from-left'
  ],
  theme: {
    fontFamily:{
      'rubik': ['Rubik Variable', 'sans-serif']
    },
    extend: {
      boxShadow: {
        header: "0px 2px 12px 0px rgba(0,52,116,0.15);",
        glow: "0px 4px 30px 0px rgba(154, 201,255,0.7);",
      },
      animation: {
        'scale-up': 'scale_up 0.3s',
        'slide-left': 'slide_from_left 1s'
      },
      keyframes: {
        scale_up: {
          '0%': { transform: 'scale(1.10)'},
          '100%': { transform: 'scale(1)'}
        },
        slide_from_left: {
          '0%': { trasform: 'translateX(110%)' },
          '100%': { trasform: 'translateX(0)' },
        }
      },
      colors: {
        yellow: {
          100: "#FFFFF0",
          200: "#FFFFD7",
          300: "#FFFF9A",
          400: "#825C24",
        },
        green: {
          100: "#F0FFF7",
          200: "#D7FFEB",
          300: "#9AFFCD",
          400: "#00743A",
        },
        red: {
          100: "#FFF0F0",
          200: "#FFD7D7",
          300: "#FF9A9A",
          400: "#740000",
        },
        blue: {
          100: "#f0f6ff",
          200: "#d7e9ff",
          300: "#9ac9ff",
          400: "#003474",
          500: "#001024",
        },
        shades: {
          100: "#F6F8FA",
          200: "#E6E6E6",
          300: "#B5B5B5",
          400: "#747474",
        },
        pasadita: {
          blue: {
            0: "#000D1F",
            1: "#01193F",
            2: "#004f82",
            3: "#17a4fe",
            4: "#f3faff",
            5: "#f6f8fa",
            6: "#dcf0ff",
          },
          green: {
            0: "#008209",
            1: "#608951",
            2: "#deffd0",
            3: "#eff5ea",
          },
          yellow: {
            0: "#fff5d0",
            1: "#FFE67B",
            2: "#826800",
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
