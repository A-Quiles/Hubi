/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{html,js}"],
  theme: {
    extend: {
      width: {
        'hubi': '27rem',
      },
      colors: {
        'grey-hubic': '#9c9c9c',
      },
      boxShadow: {
        'botones-inicio': '-1px 0px 28px 8px rgba(255,255,255,0.15)'
      }
    },
    fontFamily: {
      'raleway': 'Raleway',
    }
  },
  plugins: [],
}
